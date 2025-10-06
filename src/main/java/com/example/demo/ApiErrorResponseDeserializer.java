package com.example.demo;

import org.springframework.boot.jackson.JsonComponent;
import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ValueDeserializer;

/**
 * This deserializer should do the inverse of ApiErrorResponseSerializer. For example, we cannot assume the
 * status field is present because the serializer doesn't always include it.
 * <p>
 * With v4.6.0 of the starter in Spring Boot v3.x, it's possible to fully deserialize an `ApiErrorResponse` like so:
 * <pre>
 * {@code
 * ApiErrorResponse errorResponse = objectMapper.readValue(jsonString, ApiErrorResponse.class);
 * }
 * </pre>
 * <p>
 * In the absence of this deserializer, in Spring Boot v4 the snippet above only deserializes the root properties of
 * ApiErrorResponse. Nested properties such as globalErrors and fieldErrors are not populated.
 * <p>
 * This starter restores the ability to fully deserialize an ApiErrorResponse in Spring Boot v4 via the snippet above.
 */
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
