
/*
 *  Copyright (c) 2021, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

package io.ballerina.openapi.extension;

import io.ballerina.compiler.api.SemanticModel;
import io.ballerina.compiler.syntax.tree.SyntaxTree;
import io.ballerina.openapi.converter.utils.ServiceToOpenAPIConverterUtils;
import org.ballerinalang.annotation.JavaSPIService;
import org.ballerinalang.langserver.commons.service.spi.ExtendedLanguageServerService;
import org.ballerinalang.langserver.commons.workspace.WorkspaceManager;
import org.eclipse.lsp4j.jsonrpc.services.JsonRequest;
import org.eclipse.lsp4j.jsonrpc.services.JsonSegment;
import org.eclipse.lsp4j.services.LanguageServer;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * The extended service for the Ballerina to OpenAPI LS extension endpoint.
 *
 * @since 2.0.0
 */
@JavaSPIService("org.ballerinalang.langserver.commons.service.spi.ExtendedLanguageServerService")
@JsonSegment("openAPILSExtension")
public class OpenAPIConverterService implements ExtendedLanguageServerService {
    private WorkspaceManager workspaceManager;

    @Override
    public void init(LanguageServer languageServer, WorkspaceManager workspaceManager) {
        this.workspaceManager = workspaceManager;
    }

    @Override
    public Class<?> getRemoteInterface() {
        return getClass();
    }

    @JsonRequest
    public CompletableFuture<OpenAPIConverterResponse> generateOpenAPIFile(OpenAPIConverterRequest request) {
        return CompletableFuture.supplyAsync(() -> {
            OpenAPIConverterResponse response = new OpenAPIConverterResponse();
            try {
                String fileUri = request.getDocumentFilePath();
                Optional<Path> documentPath = getPathFromURI(fileUri);
                Optional<SyntaxTree> syntaxTree = workspaceManager.syntaxTree(documentPath.orElseThrow());
                Optional<SemanticModel> semanticModel = workspaceManager.semanticModel(documentPath.orElseThrow());

                //Response should handle
                if (semanticModel.isEmpty() || syntaxTree.isEmpty()) {
                    StringBuilder errorString = getErrorMessage(syntaxTree, semanticModel);
                    response.setYamlContent(errorString.toString());
                }
                Map<String, String> yamlContent =
                        ServiceToOpenAPIConverterUtils.generateOAS3Definition(syntaxTree.get(),
                                semanticModel.get(), null, false, null);
                Map.Entry<String, String> content = yamlContent.entrySet().iterator().next();

                if (!yamlContent.isEmpty()) {
                    response.setYamlContent(content.getValue());
                } else {
                    response.setYamlContent("Error occurred while generating yaml");
                }
            } catch (Throwable e) {
                // Throw an exceptions.
                response.setYamlContent("Error occurred while generating yaml : " + e.getMessage());
            }
            return response;
        });
    }

    // Refactor documentation path
    public static Optional<Path> getPathFromURI(String uri) {
        try {
            return Optional.of(Paths.get(new URL(uri).toURI()));
        } catch (URISyntaxException | MalformedURLException e) {
            // ignore
        }
        return Optional.empty();
    }

    // Generate error message.
    private StringBuilder getErrorMessage(Optional<SyntaxTree> syntaxTree, Optional<SemanticModel> semanticModel) {
        StringBuilder errorString = new StringBuilder();
        if (syntaxTree.isEmpty()) {
            errorString.append("Error while generating syntax tree.").append(System.lineSeparator());
        }
        if (semanticModel.isEmpty()) {
            errorString.append("Error while generating semantic model.");
        }
        return errorString;
    }
}
