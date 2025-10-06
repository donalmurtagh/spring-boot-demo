package com.example.demo;

import org.springframework.boot.jackson.JsonComponent;
import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ValueDeserializer;

@JsonComponent
public class ApiErrorResponseDeserializer extends ValueDeserializer<ApiErrorResponse> {

    @Override
    public ApiErrorResponse deserialize(JsonParser parser, DeserializationContext ctx) throws JacksonException {
        var codeFieldName = "code";
        var messageFieldName = "message";

        JsonNode jsonRoot = parser.readValueAsTree();
        var code = jsonRoot.get(codeFieldName).asString();
        var message = jsonRoot.get(messageFieldName).asString();
        var apiErrorResponse = new ApiErrorResponse(code, message);

        if (jsonRoot.has("fieldErrors")) {
            jsonRoot.get("fieldErrors").forEach(error -> {
                var errorCode = error.get(codeFieldName).asString();
                var errorMessage = error.get(messageFieldName).asString();
                var property = error.get("property").asString();
                var path = error.get("path").asString();
                var fieldError = new ApiFieldError(errorCode, property, errorMessage, path);
                apiErrorResponse.addFieldError(fieldError);
            });
        }
        return apiErrorResponse;
    }
}
