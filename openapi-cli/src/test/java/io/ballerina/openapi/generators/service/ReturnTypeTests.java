/*
 * Copyright (c) 2021, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package io.ballerina.openapi.generators.service;

import io.ballerina.compiler.syntax.tree.SyntaxTree;
import io.ballerina.openapi.cmd.Filter;
import io.ballerina.openapi.exception.BallerinaOpenApiException;
import io.ballerina.openapi.generators.GeneratorUtils;
import io.swagger.v3.oas.models.OpenAPI;
import org.ballerinalang.formatter.core.FormatterException;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * All the tests related to the {@code io.ballerina.openapi.generators.service.RequestBodyGenerator} util.
 */
public class ReturnTypeTests {
    private static final Path RES_DIR = Paths.get("src/test/resources/").toAbsolutePath();
    BallerinaServiceGenerator ballerinaServiceGenerator = new BallerinaServiceGenerator();
    List<String> list1 = new ArrayList<>();
    List<String> list2 = new ArrayList<>();
    Filter filter = new Filter(list1, list2);
    SyntaxTree syntaxTree;


    @Test(description = "Scenario 01 - Request Body has single content type(application/json)")
    public void generateJsonPayload() throws IOException, BallerinaOpenApiException, FormatterException {
        Path definitionPath = RES_DIR.resolve("generators/swagger/requestBody/scenario01_rb.yaml");
        OpenAPI openAPI = GeneratorUtils.getOpenAPIFromOpenAPIV3Parser(definitionPath);
        syntaxTree = ballerinaServiceGenerator.generateSyntaxTree(openAPI, filter);
        CommonTestFunctions.compareGeneratedSyntaxTreewithExpectedSyntaxTree("requestBody/scenario_01_rb.bal",
                syntaxTree);
    }

    //    //Response scenarios
    @Test(description = "Scenario 01 - Response has single response without content type")
    public void generateResponseScenario01() throws IOException, BallerinaOpenApiException, FormatterException {
        Path definitionPath = RES_DIR.resolve("generators/swagger/scenario_01_rs.yaml");
        OpenAPI openAPI = GeneratorUtils.getOpenAPIFromOpenAPIV3Parser(definitionPath);
        syntaxTree = ballerinaServiceGenerator.generateSyntaxTree(openAPI, filter);
        CommonTestFunctions.compareGeneratedSyntaxTreewithExpectedSyntaxTree("scenario_01_rs.bal", syntaxTree);
    }

    @Test(description = "Scenario 02 - Single response with content type")
    public void generateResponseScenario02() throws IOException, BallerinaOpenApiException, FormatterException {
        Path definitionPath = RES_DIR.resolve("generators/swagger/scenario_02_rs.yaml");
        OpenAPI openAPI = GeneratorUtils.getOpenAPIFromOpenAPIV3Parser(definitionPath);
        syntaxTree = ballerinaServiceGenerator.generateSyntaxTree(openAPI, filter);
        CommonTestFunctions.compareGeneratedSyntaxTreewithExpectedSyntaxTree("scenario_02_rs.bal", syntaxTree);
    }

    @Test(description = "Scenario 03 - Single response with content type application/json")
    public void generateResponseScenario03() throws IOException, BallerinaOpenApiException, FormatterException {
        Path definitionPath = RES_DIR.resolve("generators/swagger/scenario_03_rs.yaml");
        OpenAPI openAPI = GeneratorUtils.getOpenAPIFromOpenAPIV3Parser(definitionPath);
        syntaxTree = ballerinaServiceGenerator.generateSyntaxTree(openAPI, filter);

        CommonTestFunctions.compareGeneratedSyntaxTreewithExpectedSyntaxTree("scenario_03_rs.bal", syntaxTree);
    }

    @Test(description = "Scenario 04 - Response has multiple responses without content type")
    public void generateResponseScenario04() throws IOException, BallerinaOpenApiException, FormatterException {
        Path definitionPath = RES_DIR.resolve("generators/swagger/scenario_04_rs.yaml");
        OpenAPI openAPI = GeneratorUtils.getOpenAPIFromOpenAPIV3Parser(definitionPath);
        syntaxTree = ballerinaServiceGenerator.generateSyntaxTree(openAPI, filter);
        CommonTestFunctions.compareGeneratedSyntaxTreewithExpectedSyntaxTree("scenario_04_rs.bal", syntaxTree);
    }

    @Test(description = "Scenario 05 - Error response with a schema")
    public void generateResponseScenario05() throws IOException, BallerinaOpenApiException, FormatterException {
        Path definitionPath = RES_DIR.resolve("generators/swagger/scenario_05_rs.yaml");
        OpenAPI openAPI = GeneratorUtils.getOpenAPIFromOpenAPIV3Parser(definitionPath);
        syntaxTree = ballerinaServiceGenerator.generateSyntaxTree(openAPI, filter);
        CommonTestFunctions.compareGeneratedSyntaxTreewithExpectedSyntaxTree("scenario_05_rs.bal", syntaxTree);
    }

    @Test(description = "Scenario 06 - Error response with a schema with application/json")
    public void generateResponseScenario06() throws IOException, BallerinaOpenApiException, FormatterException {
        Path definitionPath = RES_DIR.resolve("generators/swagger/scenario_06_rs.yaml");
        OpenAPI openAPI = GeneratorUtils.getOpenAPIFromOpenAPIV3Parser(definitionPath);
        syntaxTree = ballerinaServiceGenerator.generateSyntaxTree(openAPI, filter);
        CommonTestFunctions.compareGeneratedSyntaxTreewithExpectedSyntaxTree("scenario_06_rs.bal", syntaxTree);
    }

    @Test(description = "Scenario 07 - Single response has multiple content types")
    public void generateResponseScenario07() throws IOException, BallerinaOpenApiException, FormatterException {
        Path definitionPath = RES_DIR.resolve("generators/swagger/scenario_07_rs.yaml");
        OpenAPI openAPI = GeneratorUtils.getOpenAPIFromOpenAPIV3Parser(definitionPath);
        syntaxTree = ballerinaServiceGenerator.generateSyntaxTree(openAPI, filter);
        CommonTestFunctions.compareGeneratedSyntaxTreewithExpectedSyntaxTree("scenario_07_rs.bal", syntaxTree);
    }

    @Test(description = "Scenario 08 - Single response has inline record for dataType")
    public void generateResponseScenario08() throws IOException, BallerinaOpenApiException, FormatterException {
        Path definitionPath = RES_DIR.resolve("generators/swagger/scenario_08_rs.yaml");
        OpenAPI openAPI = GeneratorUtils.getOpenAPIFromOpenAPIV3Parser(definitionPath);
        syntaxTree = ballerinaServiceGenerator.generateSyntaxTree(openAPI, filter);
        CommonTestFunctions.compareGeneratedSyntaxTreewithExpectedSyntaxTree("scenario_08_rs.bal", syntaxTree);
    }

    @Test(description = "Scenario 09 - Single response has inline record for dataType with different status code")
    public void generateResponseScenario09() throws IOException, BallerinaOpenApiException, FormatterException {
        Path definitionPath = RES_DIR.resolve("generators/swagger/scenario_09_rs.yaml");
        OpenAPI openAPI = GeneratorUtils.getOpenAPIFromOpenAPIV3Parser(definitionPath);
        syntaxTree = ballerinaServiceGenerator.generateSyntaxTree(openAPI, filter);
        CommonTestFunctions.compareGeneratedSyntaxTreewithExpectedSyntaxTree("scenario_09_rs.bal", syntaxTree);
    }

    @Test(description = "Scenario 10 - Response with a custom media type")
    public void generateResponseScenario10() throws IOException, BallerinaOpenApiException, FormatterException {
        Path definitionPath = RES_DIR.resolve("generators/swagger/scenario_10_rs.yaml");
        OpenAPI openAPI = GeneratorUtils.getOpenAPIFromOpenAPIV3Parser(definitionPath);
        syntaxTree = ballerinaServiceGenerator.generateSyntaxTree(openAPI, filter);
        CommonTestFunctions.compareGeneratedSyntaxTreewithExpectedSyntaxTree("scenario_10_rs.bal", syntaxTree);
    }

    @Test(description = "Scenario 11 - Response has OneOf and AnyOf type 200 ok")
    public void generateResponseScenario11() throws IOException, BallerinaOpenApiException, FormatterException {
        Path definitionPath = RES_DIR.resolve("generators/swagger/scenario_11_rs.yaml");
        OpenAPI openAPI = GeneratorUtils.getOpenAPIFromOpenAPIV3Parser(definitionPath);
        syntaxTree = ballerinaServiceGenerator.generateSyntaxTree(openAPI, filter);
        CommonTestFunctions.compareGeneratedSyntaxTreewithExpectedSyntaxTree("scenario_11_rs.bal", syntaxTree);
    }

    @Test(description = "Scenario 12 - Response has OneOf and AnyOf type for error status code")
    public void generateResponseScenario12() throws IOException, BallerinaOpenApiException, FormatterException {
        Path definitionPath = RES_DIR.resolve("generators/swagger/scenario_12_rs.yaml");
        OpenAPI openAPI = GeneratorUtils.getOpenAPIFromOpenAPIV3Parser(definitionPath);
        syntaxTree = ballerinaServiceGenerator.generateSyntaxTree(openAPI, filter);
        CommonTestFunctions.compareGeneratedSyntaxTreewithExpectedSyntaxTree("scenario_12_rs.bal", syntaxTree);
    }

    @Test(description = "Scenario 13 - Single response has multiple content types with different error code")
    public void generateResponseScenario13() throws IOException, BallerinaOpenApiException, FormatterException {
        Path definitionPath = RES_DIR.resolve("generators/swagger/scenario_13_rs.yaml");
        OpenAPI openAPI = GeneratorUtils.getOpenAPIFromOpenAPIV3Parser(definitionPath);
        syntaxTree = ballerinaServiceGenerator.generateSyntaxTree(openAPI, filter);
        CommonTestFunctions.compareGeneratedSyntaxTreewithExpectedSyntaxTree("scenario_13_rs.bal", syntaxTree);
    }

    @Test(description = "Scenario 14 - Multiple response with same mediaType")
    public void generateResponseScenario14() throws IOException, BallerinaOpenApiException, FormatterException {
        Path definitionPath = RES_DIR.resolve("generators/swagger/scenario_14_rs.yaml");
        OpenAPI openAPI = GeneratorUtils.getOpenAPIFromOpenAPIV3Parser(definitionPath);
        syntaxTree = ballerinaServiceGenerator.generateSyntaxTree(openAPI, filter);
        CommonTestFunctions.compareGeneratedSyntaxTreewithExpectedSyntaxTree("scenario_14_rs.bal", syntaxTree);
    }

    @Test(description = "Scenario 15 - Response has array type data Binding")
    public void generateResponseScenario15() throws IOException, BallerinaOpenApiException, FormatterException {
        Path definitionPath = RES_DIR.resolve("generators/swagger/scenario_15_rs.yaml");
        OpenAPI openAPI = GeneratorUtils.getOpenAPIFromOpenAPIV3Parser(definitionPath);
        syntaxTree = ballerinaServiceGenerator.generateSyntaxTree(openAPI, filter);
        CommonTestFunctions.compareGeneratedSyntaxTreewithExpectedSyntaxTree("scenario_15_rs.bal", syntaxTree);
    }

    @Test(description = "Scenario 16 - Response has array type data Binding with error code")
    public void generateResponseScenario16() throws IOException, BallerinaOpenApiException, FormatterException {
        Path definitionPath = RES_DIR.resolve("generators/swagger/scenario_16_rs.yaml");
        OpenAPI openAPI = GeneratorUtils.getOpenAPIFromOpenAPIV3Parser(definitionPath);
        syntaxTree = ballerinaServiceGenerator.generateSyntaxTree(openAPI, filter);
        CommonTestFunctions.compareGeneratedSyntaxTreewithExpectedSyntaxTree("scenario_16_rs.bal", syntaxTree);
    }
    // Scenario 17, 18 is invalid
    @Test(description = "Scenario 19 - Multiple response with different mediaType")
    public void generateResponseScenario19() throws IOException, BallerinaOpenApiException, FormatterException {
        Path definitionPath = RES_DIR.resolve("generators/swagger/scenario_19_rs.yaml");
        OpenAPI openAPI = GeneratorUtils.getOpenAPIFromOpenAPIV3Parser(definitionPath);
        syntaxTree = ballerinaServiceGenerator.generateSyntaxTree(openAPI, filter);
        CommonTestFunctions.compareGeneratedSyntaxTreewithExpectedSyntaxTree("scenario_19_rs.bal", syntaxTree);
    }

    @Test(description = "Generate functionDefinitionNode for request body with json")
    public void generateResponsePayloadWithRef() throws IOException, BallerinaOpenApiException, FormatterException {
        Path definitionPath = RES_DIR.resolve("generators/swagger/responseRefPayload.yaml");
        OpenAPI openAPI = GeneratorUtils.getOpenAPIFromOpenAPIV3Parser(definitionPath);
        syntaxTree = ballerinaServiceGenerator.generateSyntaxTree(openAPI, filter);

    }

    @Test(description = "Generate functionDefinitionNode for request body with json")
    public void generateResponsePayloadWithRefMulti() throws IOException, BallerinaOpenApiException,
            FormatterException {
        Path definitionPath = RES_DIR.resolve("generators/swagger/responseMultipleRefPayload.yaml");
        OpenAPI openAPI = GeneratorUtils.getOpenAPIFromOpenAPIV3Parser(definitionPath);
        syntaxTree = ballerinaServiceGenerator.generateSyntaxTree(openAPI, filter);

    }

    @Test(description = "Generate functionDefinitionNode for request body with json")
    public void generateResponsePayloadWithDifferentStatusCode() throws IOException, BallerinaOpenApiException,
            FormatterException {
        Path definitionPath = RES_DIR.resolve("generators/swagger/responseDifferentStatusCode.yaml");
        OpenAPI openAPI = GeneratorUtils.getOpenAPIFromOpenAPIV3Parser(definitionPath);
        syntaxTree = ballerinaServiceGenerator.generateSyntaxTree(openAPI, filter);

    }

    @Test(description = "Generate functionDefinitionNode for request body with json")
    public void generateResponseDifferentStatusCode() throws IOException, BallerinaOpenApiException,
            FormatterException {
        Path definitionPath = RES_DIR.resolve("generators/swagger/responseDifferentCodes.yaml");
        OpenAPI openAPI = GeneratorUtils.getOpenAPIFromOpenAPIV3Parser(definitionPath);
        syntaxTree = ballerinaServiceGenerator.generateSyntaxTree(openAPI, filter);

    }

    @Test(description = "oneOf and anyOf, so you can specify alternate schemas for the response body.")
    public void generateResponserecordOneOf() throws IOException, BallerinaOpenApiException,
            FormatterException {
        Path definitionPath = RES_DIR.resolve("generators/swagger/responseOneOf.yaml");
        OpenAPI openAPI = GeneratorUtils.getOpenAPIFromOpenAPIV3Parser(definitionPath);
        syntaxTree = ballerinaServiceGenerator.generateSyntaxTree(openAPI, filter);

    }

    @Test(description = "Default response handling")
    public void generateResponseDefault() throws IOException, BallerinaOpenApiException,
            FormatterException {
        Path definitionPath = RES_DIR.resolve("generators/swagger/petstore_default.yaml");
        OpenAPI openAPI = GeneratorUtils.getOpenAPIFromOpenAPIV3Parser(definitionPath);
        syntaxTree = ballerinaServiceGenerator.generateSyntaxTree(openAPI, filter);
        CommonTestFunctions.compareGeneratedSyntaxTreewithExpectedSyntaxTree("petstore_default.bal", syntaxTree);
    }

    @Test(description = "Path with special characters ")
    public void testWithSpecialCharacters() throws IOException, BallerinaOpenApiException, FormatterException {
        Path definitionPath = RES_DIR.resolve("generators/swagger/path_with_special_characters.yaml");
        OpenAPI openAPI = GeneratorUtils.getOpenAPIFromOpenAPIV3Parser(definitionPath);
        syntaxTree = ballerinaServiceGenerator.generateSyntaxTree(openAPI, filter);
        CommonTestFunctions.compareGeneratedSyntaxTreewithExpectedSyntaxTree("path_with_special_characters.bal", syntaxTree);
    }
}
