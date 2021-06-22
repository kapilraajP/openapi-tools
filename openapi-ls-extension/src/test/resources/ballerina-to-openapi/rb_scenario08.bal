import ballerina/http;

listener http:Listener helloEp = new (9090);
type Pet record {
    int id;
    string name;
};
service /payloadV on helloEp {
    resource function post pets(@http:Payload{mediaType: ["application/json", "application/xml"]} Pet payload) returns  http:Ok {
       }

}

