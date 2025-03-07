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

package io.ballerina.openapi.generators;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Constants for openapi code generator.
 */
public class GeneratorConstants {

    /**
     * Enum to select the code generation mode.
     * Ballerina service, mock and client generation is available
     */
    public enum GenType {
        GEN_SERVICE("gen_service"),
        GEN_CLIENT("gen_client"),
        GEN_BOTH("gen_both");

        private String name;

        GenType(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return this.name;
        }
    }
    /**
     * Enum to select the relevant ballerina http auth record.
     */
    public enum AuthConfigTypes {
        BASIC("http:CredentialsConfig"),
        BEARER("http:BearerTokenConfig"),
        CLIENT_CREDENTIAL("http:OAuth2ClientCredentialsGrantConfig"),
        CUSTOM_CLIENT_CREDENTIAL("OAuth2ClientCredentialsGrantConfig"),
        REFRESH_TOKEN("http:OAuth2RefreshTokenGrantConfig"),
        CUSTOM_REFRESH_TOKEN("OAuth2RefreshTokenGrantConfig"),
        PASSWORD("http:OAuth2PasswordGrantConfig"),
        CUSTOM_PASSWORD("OAuth2PasswordGrantConfig");

        private final String authType;

        AuthConfigTypes(String authType) {
            this.authType = authType;
        }

        public String getValue() {
            return authType;
        }
    }

    public static final String CLIENT_TEMPLATE_NAME = "client-ep";
    public static final String TYPE_FILE_NAME = "types.bal";
    public static final String CLIENT_FILE_NAME = "client.bal";
    public static final String UTIL_FILE_NAME = "utils.bal";
    public static final String TEST_FILE_NAME = "test.bal";
    public static final String CONFIG_FILE_NAME = "Config.toml";
    public static final String BALLERINA_TOML = "Ballerina.toml";
    public static final String BALLERINA_TOML_CONTENT = "[package]\n" +
            "org= \"ballerina\"\n" +
            "name= \"testopenapi\"\n" +
            "version= \"0.0.0\"\n";

    public static final String TEMPLATES_SUFFIX = ".mustache";
    public static final String TEMPLATES_DIR_PATH_KEY = "templates.dir.path";
    public static final String DEFAULT_TEMPLATE_DIR = "/templates";
    public static final String DEFAULT_CLIENT_DIR = DEFAULT_TEMPLATE_DIR + "/client";
    public static final String DEFAULT_CLIENT_PKG = "client";
    public static final String DEFAULT_MOCK_PKG = "mock";
    public static final String OAS_PATH_SEPARATOR = "/";
    public static final String USER_DIR = "user.dir";
    public static final String UNTITLED_SERVICE = "UntitledAPI";
    public static final String ESCAPE_PATTERN = "([\\[\\]\\\\?!<>@#&~`*\\-=^+();:\\/\\_{}\\s|.$])";
    //ClientCode generator
    public static final String HTTP = "http";
    public static final String URL = "url";
    public static final String MODULE_TEST  = "test";
    public static final String BALLERINA = "ballerina";
    public static final String PUBLIC = "public";
    public static final String PUBLIC_ISOLATED = "public isolated";
    public static final String CLIENT = "client";
    public static final String CLIENT_CLASS = "Client";
    public static final String CLIENT_EP = "clientEp";
    public static final String CLASS = "class";
    public static final String EQUAL = "=";
    public static final String CONFIG = "config";
    public static final String FUNCTION = "function";
    public static final String RETURN = "returns";
    public static final String PREFIX_TEST = " test";
    public static final String ANNOT_TEST = "test:Config";
    public static final String TEST_DIR = "tests";
    public static final String STRING = "string";
    public static final String XML = "xml";
    public static final String BYTE = "byte";
    public static final String JSON = "json";
    public static final String SERVICE_URL = "serviceUrl";
    public static final String RECORD = "record";
    public static final String IDENTIFIER = "identifier";
    public static final String TYPE_NAME = "typeName";
    public static final String BINARY = "binary";
    public static final String DOUBLE = "double";
    public static final String FLOAT = "float";
    public static final String HEADER = "header";
    public static final String HEADER_VALUES = "headerValues";
    public static final String PAYLOAD = "payload";
    public static final String PAYLOAD_KEYWORD = "Payload";
    public static final String PDF = "pdf";
    public static final String QUERY_PARAM = "queryParam";
    public static final String SELF = "self";
    public static final String TEXT_PREFIX = "text/";
    public static final String XML_DATA = "xmldata";
    public static final String IMAGE = "image";
    public static final String VENDOR_SPECIFIC_TYPE = "vnd.";
    public static final String MIME = "mime";
    public static final String HTTP_HEADERS = "httpHeaders";
    public static final String RESOURCE_PATH = "resourcePath";
    public static final String ARRAY = "array";
    public static final String ERROR = "error";
    public static final String MAP_JSON = "map<json>";
    public static final String MEDIA_TYPE_KEYWORD = "mediaType";

    public static final String ANY_TYPE = "*/*";
    public static final String APPLICATION_JSON = "application/json";
    public static final String APPLICATION_PDF = "application/pdf";
    public static final String APPLICATION_XML = "application/xml";
    public static final String APPLICATION_OCTET_STREAM = "application/octet-stream";
    public static final String APPLICATION_URL_ENCODE = "application/x-www-form-urlencoded";
    public static final String TEXT = "text";
    public static final String IMAGE_PNG = "image/png";
    public static final String TEXT_PLAIN = "text/plain";

    // auth related constants
    public static final String API_KEY = "apikey";
    public static final String API_KEYS_CONFIG = "ApiKeysConfig";
    public static final String API_KEY_CONFIG_PARAM = "apiKeyConfig";
    public static final String API_KEY_CONFIG_RECORD_FIELD = "apiKeys";
    public static final String AUTH = "auth";
    public static final String AUTH_CONFIG = "authConfig";
    public static final String AUTH_CONFIG_RECORD = "AuthConfig";
    public static final String BASIC = "basic";
    public static final String BEARER = "bearer";
    public static final String REFRESH_TOKEN = "refresh_token";
    public static final String CLIENT_CRED = "client_cred";
    public static final String PASSWORD = "password";
    public static final String CONFIG_RECORD_ARG = "clientConfig";
    public static final String CLIENT_CONFIG = "ClientConfig";
    public static final String OAUTH2 = "oauth2";
    public static final String SSL_FIELD_NAME = "secureSocket";
    public static final String DEFAULT_API_KEY_DESC = "API keys for authorization";

    public static final String RESPONSE = "response";
    public static final String TYPE = "type";
    public static final String ANY_DATA = "anydata";

    //Http Methods
    public static final String POST = "post";
    public static final String GET = "get";
    public static final String PUT = "put";
    public static final String DELETE = "delete";
    public static final String PATCH = "patch";
    public static final String EXECUTE = "execute";
    public static final String HEAD = "head";
    public static final String CONNECT = "connect";
    public static final String OPTIONS = "options";
    public static final String TRACE = "trace";

    //Encoding related constants
    public static final String DEEP_OBJECT = "DEEPOBJECT";
    public static final String FORM = "FORM";
    public static final String SPACE_DELIMITED = "SPACEDELIMITED";
    public static final String PIPE_DELIMITED = "PIPEDELIMITED";
    public static final String ENCODING = "Encoding";
    public static final String ENCODING_STYLE = "EncodingStyle";
    public static final String STYLE = "style";
    public static final String EXPLODE = "explode";

    //OpenAPI Ballerina extensions
    public static final String X_BALLERINA_INIT_DESCRIPTION = "x-ballerina-init-description";
    public static final String X_BALLERINA_DISPLAY = "x-ballerina-display";
    public static final String X_BALLERINA_DEPRECATED_REASON = "x-ballerina-deprecated-reason";

    //Service related
    public static final String HOST = "host";
    public static final String NEW = "new";
    public static final String RESOURCE = "resource";
    public static final String QUERY = "query";
    public static final String HTTP_RESPONSE = "http:Response";
    public static final String DEFAULT = "default";
    /**
     * Util for select http key words with http codes.
     * @param code http code.
     * @return Http identification word.
     */
    public static final Map<String, String> HTTP_CODES_DES;
    static {
        Map<String, String> httpCodeMap = new HashMap<>();
        httpCodeMap.put("100", "Continue");
        httpCodeMap.put("101", "SwitchingProtocols");
        httpCodeMap.put("200", "Ok");
        httpCodeMap.put("201", "Created");
        httpCodeMap.put("202", "Accepted");
        httpCodeMap.put("203", "NonAuthoritativeInformation");
        httpCodeMap.put("204", "NoContent");
        httpCodeMap.put("205", "RestContent");
        httpCodeMap.put("206", "PartialContent");
        httpCodeMap.put("300", "MultipleChoices");
        httpCodeMap.put("301", "MovedPermanently");
        httpCodeMap.put("302", "Found");
        httpCodeMap.put("303", "SeeOther");
        httpCodeMap.put("304", "NotModified");
        httpCodeMap.put("305", "UseProxy");
        httpCodeMap.put("307", "TemporaryRedirect");
        httpCodeMap.put("308", "PermanentRedirect");
        httpCodeMap.put("400", "BadRequest");
        httpCodeMap.put("401", "Unauthorized");
        httpCodeMap.put("402", "PaymentRequired");
        httpCodeMap.put("403", "Forbidden");
        httpCodeMap.put("404", "NotFound");
        httpCodeMap.put("405", "MethodNotAllowed");
        httpCodeMap.put("406", "NotAcceptable");
        httpCodeMap.put("407", "ProxyAuthenticationRequired");
        httpCodeMap.put("408", "RequestTimeOut");
        httpCodeMap.put("409", "Conflict");
        httpCodeMap.put("410", "Gone");
        httpCodeMap.put("411", "LengthRequired");
        httpCodeMap.put("412", "PreconditionFailed");
        httpCodeMap.put("413", "PayloadTooLarge");
        httpCodeMap.put("414", "UriTooLong");
        httpCodeMap.put("415", "UnsupportedMediaType");
        httpCodeMap.put("416", "RangeNotSatisfiable");
        httpCodeMap.put("417", "ExpectationFailed");
        httpCodeMap.put("426", "UpgradeRequired");
        httpCodeMap.put("429", "TooManyRequests");
        httpCodeMap.put("431", "RequestHeaderFieldsTooLarge");
        httpCodeMap.put("500", "InternalServerError");
        httpCodeMap.put("501", "NotImplemented");
        httpCodeMap.put("502", "BadGateway");
        httpCodeMap.put("503", "ServiceUnavailable");
        httpCodeMap.put("504", "GatewayTimeOut");
        httpCodeMap.put("505", "HttpVersionNotSupported");
        HTTP_CODES_DES = Collections.unmodifiableMap(httpCodeMap);
    }
    public static final String HTTP_200 = "200";
    public static final String INTEGER = "integer";
    public static final String BOOLEAN = "boolean";
    public static final String NUMBER = "number";
    public static final String OBJECT = "object";
    public static final Integer MAX_ARRAY_LENGTH = 2147483637;
    public static final String NILLABLE = "?";
    public static final String SQUARE_BRACKETS = "[]";
    public static final String BAL_EXTENSION = ".bal";
    public static final String JSON_EXTENSION = ".json";
    public static final String YAML_EXTENSION = ".yaml";
    public static final String YML_EXTENSION = ".yml";

    public static final Map<String, String> TYPE_MAP;
    static {
        Map<String, String> typeMap = new HashMap<>();
        typeMap.put("integer", "int");
        typeMap.put("string", "string");
        typeMap.put("boolean", "boolean");
        typeMap.put("array", "[]");
        typeMap.put("object", "record {}");
        typeMap.put("decimal", "decimal");
        typeMap.put("number", "decimal");
        typeMap.put("double", "float");
        typeMap.put("float", "float");
        typeMap.put("binary", "byte[]");
        typeMap.put("byte", "byte[]");
        TYPE_MAP = Collections.unmodifiableMap(typeMap);
    }

    //Error related
    public static final String UNSUPPORTED_MEDIA_ERROR = "Unsupported media type '%s' is given in the request body";

    // OS specific line separator
    public static final String LINE_SEPARATOR = System.lineSeparator();
    public static final String DOUBLE_LINE_SEPARATOR = LINE_SEPARATOR + LINE_SEPARATOR;
    public static final String DEFAULT_RETURN = HTTP_RESPONSE + "|" + ERROR;

    // Service Generation
    public static final String TREAT_NILABLE_AS_OPTIONAL = "treatNilableAsOptional";
    public static final String SERVICE_CONFIG = "http:ServiceConfig";
    public static final String FALSE = "false";
    public static final String HEADER_ANNOT = "Header";
    public static final String MAP_STRING = "map<string>";
    public static final String TEXT_WILDCARD_REGEX = "text/.*";

}
