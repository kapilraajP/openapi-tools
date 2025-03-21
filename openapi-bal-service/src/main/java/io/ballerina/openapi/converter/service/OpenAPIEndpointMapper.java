/*
 *  Copyright (c) 2020, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
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


package io.ballerina.openapi.converter.service;

import io.ballerina.compiler.syntax.tree.CheckExpressionNode;
import io.ballerina.compiler.syntax.tree.ExplicitNewExpressionNode;
import io.ballerina.compiler.syntax.tree.ExpressionNode;
import io.ballerina.compiler.syntax.tree.FunctionArgumentNode;
import io.ballerina.compiler.syntax.tree.ImplicitNewExpressionNode;
import io.ballerina.compiler.syntax.tree.ListenerDeclarationNode;
import io.ballerina.compiler.syntax.tree.MappingConstructorExpressionNode;
import io.ballerina.compiler.syntax.tree.MappingFieldNode;
import io.ballerina.compiler.syntax.tree.NamedArgumentNode;
import io.ballerina.compiler.syntax.tree.Node;
import io.ballerina.compiler.syntax.tree.NodeList;
import io.ballerina.compiler.syntax.tree.ParenthesizedArgList;
import io.ballerina.compiler.syntax.tree.SeparatedNodeList;
import io.ballerina.compiler.syntax.tree.ServiceDeclarationNode;
import io.ballerina.compiler.syntax.tree.SpecificFieldNode;
import io.ballerina.compiler.syntax.tree.SyntaxKind;
import io.ballerina.openapi.converter.Constants;
import io.ballerina.openapi.converter.utils.ConverterCommonUtils;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.servers.ServerVariable;
import io.swagger.v3.oas.models.servers.ServerVariables;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static io.ballerina.openapi.converter.Constants.PORT;
import static io.ballerina.openapi.converter.Constants.SERVER;

/**
 * Extract OpenApi server information from and Ballerina endpoint.
 */
public class OpenAPIEndpointMapper {
    public static final OpenAPIEndpointMapper ENDPOINT_MAPPER = new OpenAPIEndpointMapper();

    /**
     * Convert endpoints bound to {@code service} openapi server information.
     *
     * @param openAPI   openapi definition to attach extracted information
     * @param endpoints all endpoints defined in ballerina source
     * @param service   service node with bound endpoints
     * @return openapi definition with Server information
     */
    public OpenAPI getServers(OpenAPI openAPI, List<ListenerDeclarationNode> endpoints,
                              ServiceDeclarationNode service) {
        openAPI = extractServerForExpressionNode(openAPI, service.expressions(), service);
        List<Server> servers = openAPI.getServers();
        if (!endpoints.isEmpty()) {
            for (ListenerDeclarationNode ep : endpoints) {
                SeparatedNodeList<ExpressionNode> exprNodes = service.expressions();
                for (ExpressionNode node : exprNodes) {
                    if (node.toString().trim().equals(ep.variableName().text().trim())) {
                        String serviceBasePath = getServiceBasePath(service);
                        Server server = extractServer(ep, serviceBasePath);
                        servers.add(server);
                    }
                }
            }

        }
        if (servers.size() > 1) {
            Server mainServer = addEnumValues(servers);
            openAPI.setServers(Collections.singletonList(mainServer));
        }
        return openAPI;
    }

    private Server addEnumValues(List<Server> servers) {

        Server mainServer = servers.get(0);
        List<Server> rotated = new ArrayList<>(servers);
        ServerVariables mainVariable = mainServer.getVariables();
        ServerVariable hostVariable = mainVariable.get(SERVER);
        ServerVariable portVariable = mainVariable.get(PORT);
        if (servers.size() > 1) {
            Collections.rotate(rotated, servers.size() - 1);
            for (Server server: rotated) {
                ServerVariables variables = server.getVariables();
                if (variables.get(SERVER) != null) {
                    hostVariable.addEnumItem(variables.get(SERVER).getDefault());
                }
                if (variables.get(PORT) != null) {
                    portVariable.addEnumItem(variables.get(PORT).getDefault());
                }
            }
        }
        return mainServer;
    }

    /**
     * Extract server URL from given listener node.
     */
    private Server extractServer(ListenerDeclarationNode ep, String serviceBasePath) {
        Optional<ParenthesizedArgList> list;
        if (ep.initializer().kind() == SyntaxKind.CHECK_EXPRESSION) {
            ExpressionNode expression = ((CheckExpressionNode) ep.initializer()).expression();
            list = extractListenerNodeType(expression);
        } else {
            list = extractListenerNodeType(ep.initializer());
        }
        return generateServer(serviceBasePath, list);
    }

    private Optional<ParenthesizedArgList> extractListenerNodeType(Node expression2) {
        Optional<ParenthesizedArgList> list = Optional.empty();
        if (expression2.kind() == SyntaxKind.EXPLICIT_NEW_EXPRESSION) {
            ExplicitNewExpressionNode bTypeExplicit = (ExplicitNewExpressionNode) expression2;
            list = Optional.ofNullable(bTypeExplicit.parenthesizedArgList());
        } else if (expression2.kind() == SyntaxKind.IMPLICIT_NEW_EXPRESSION) {
            ImplicitNewExpressionNode bTypeInit = (ImplicitNewExpressionNode) expression2;
            list = bTypeInit.parenthesizedArgList();
        }
        return list;
    }

    // Function for handle both ExplicitNewExpressionNode and ImplicitNewExpressionNode in listener.
    private OpenAPI extractServerForExpressionNode(OpenAPI openAPI, SeparatedNodeList<ExpressionNode> bTypeExplicit,
                                                                    ServiceDeclarationNode service) {
        String serviceBasePath = getServiceBasePath(service);
        Optional<ParenthesizedArgList> list;
        List<Server> servers = new ArrayList<>();
        for (ExpressionNode expressionNode: bTypeExplicit) {
            if (expressionNode.kind().equals(SyntaxKind.EXPLICIT_NEW_EXPRESSION)) {
                ExplicitNewExpressionNode explicit = (ExplicitNewExpressionNode) expressionNode;
                list = Optional.ofNullable(explicit.parenthesizedArgList());
                Server server = generateServer(serviceBasePath, list);
                servers.add(server);
            } else if (expressionNode.kind().equals(SyntaxKind.IMPLICIT_NEW_EXPRESSION)) {
                ImplicitNewExpressionNode implicit = (ImplicitNewExpressionNode) expressionNode;
                list = implicit.parenthesizedArgList();
                Server server = generateServer(serviceBasePath, list);
                servers.add(server);
            }
        }
        openAPI.setServers(servers);
        return openAPI;
    }

    //Assign host and port values
    private Server generateServer(String serviceBasePath, Optional<ParenthesizedArgList> list) {

        String port = null;
        String host = null;
        ServerVariables serverVariables = new ServerVariables();
        Server server = new Server();

        if (list.isPresent()) {
            SeparatedNodeList<FunctionArgumentNode> arg = (list.get()).arguments();
            port = arg.get(0).toString();
            if (arg.size() > 1 && (arg.get(1) instanceof NamedArgumentNode)) {
                ExpressionNode bLangRecordLiteral = ((NamedArgumentNode) arg.get(1)).expression();
                if (bLangRecordLiteral instanceof MappingConstructorExpressionNode) {
                    host = extractHost((MappingConstructorExpressionNode) bLangRecordLiteral);
                }
            }
        }
        // Set default values to host and port if values are not defined
        setServerVariableValues(serviceBasePath, port, host, serverVariables, server);
        return server;
    }

    /**
     * Set server variables port and server.
     */
    private void setServerVariableValues(String serviceBasePath, String port, String host,
                                         ServerVariables serverVariables, Server server) {

        String serverUrl;
        if (host != null && port != null) {
            ServerVariable serverUrlVariable = new ServerVariable();
            serverUrlVariable._default(host);
            ServerVariable portVariable =  new ServerVariable();
            portVariable._default(port);

            serverVariables.addServerVariable(SERVER, serverUrlVariable);
            serverVariables.addServerVariable(PORT, portVariable);
            serverUrl = String.format("{server}:{port}%s", serviceBasePath);
            server.setUrl(serverUrl);
            server.setVariables(serverVariables);
        } else if (host != null) {
            ServerVariable serverUrlVariable = new ServerVariable();
            serverUrlVariable._default(host);

            serverVariables.addServerVariable(SERVER, serverUrlVariable);
            serverUrl = "{server}" + serviceBasePath;
            server.setUrl(serverUrl);
            server.setVariables(serverVariables);

        } else if (port != null) {
            if (port.equals("443")) {
                ServerVariable serverUrlVariable = new ServerVariable();
                serverUrlVariable._default("https://localhost");
                ServerVariable portVariable =  new ServerVariable();
                portVariable._default("443");

                serverVariables.addServerVariable(SERVER, serverUrlVariable);
                serverVariables.addServerVariable(PORT, portVariable);
                serverUrl = "{server}:{port}" + serviceBasePath;
                server.setUrl(serverUrl);
                server.setVariables(serverVariables);
            } else {
                ServerVariable serverUrlVariable = new ServerVariable();
                serverUrlVariable._default("http://localhost");
                ServerVariable portVariable =  new ServerVariable();
                portVariable._default(port);

                serverVariables.addServerVariable(SERVER, serverUrlVariable);
                serverVariables.addServerVariable(PORT, portVariable);
                serverUrl = "{server}:{port}" + serviceBasePath;
                server.setUrl(serverUrl);
                server.setVariables(serverVariables);
            }
        }
    }

    // Extract host value for creating URL.
    private String extractHost(MappingConstructorExpressionNode bLangRecordLiteral) {
        String host = "";
        if (bLangRecordLiteral.fields() != null && !bLangRecordLiteral.fields().isEmpty()) {
            SeparatedNodeList<MappingFieldNode> recordFields = bLangRecordLiteral.fields();
            host = concatenateServerURL(host, recordFields);
        }
        if (!host.equals("")) {
           host = host.replaceAll("\"", "");
        }
        return host;
    }

    private String concatenateServerURL(String host, SeparatedNodeList<MappingFieldNode> recordFields) {

        for (MappingFieldNode filed: recordFields) {
            if (filed instanceof SpecificFieldNode) {
                Node fieldName = ((SpecificFieldNode) filed).fieldName();
                if (fieldName.toString().equals(Constants.ATTR_HOST)) {
                    if (((SpecificFieldNode) filed).valueExpr().isPresent()) {
                          host = ((SpecificFieldNode) filed).valueExpr().get().toString();
                    }
                }
            }
        }
        return host;
    }

    /**
     * Gets the base path of a service.
     *
     * @param serviceDefinition The service definition node.
     * @return The base path.
     */
    public String getServiceBasePath(ServiceDeclarationNode serviceDefinition) {
        StringBuilder currentServiceName = new StringBuilder();
        NodeList<Node> serviceNameNodes = serviceDefinition.absoluteResourcePath();
        for (Node serviceBasedPathNode : serviceNameNodes) {
            currentServiceName.append(ConverterCommonUtils.unescapeIdentifier(serviceBasedPathNode.toString()));
        }
        return currentServiceName.toString().trim();
    }
}
