package com.example.demo;

import org.springframework.boot.jackson.JsonComponent;
import org.springframework.boot.jackson.ObjectValueSerializer;
import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.SerializationContext;

import java.util.List;

@JsonComponent
public class ApiErrorResponseSerializer extends ObjectValueSerializer<ApiErrorResponse> {


    @Override
    public void serializeObject(ApiErrorResponse errorResponse,
                                JsonGenerator jsonGenerator,
                                SerializationContext serializationContext) {

        jsonGenerator.writeStringProperty("code", errorResponse.getCode());
        jsonGenerator.writeStringProperty("message", errorResponse.getMessage());

        List<ApiFieldError> fieldErrors = errorResponse.getFieldErrors();
        if (!fieldErrors.isEmpty()) {
            jsonGenerator.writeArrayPropertyStart("fieldErrors");
            for (ApiFieldError fieldError : fieldErrors) {
                jsonGenerator.writeStartObject()
                    .writeStringProperty("code", fieldError.getCode())
                    .writeStringProperty("message", fieldError.getMessage())
                    .writeStringProperty("property", fieldError.getProperty())
                    .writeStringProperty("path", fieldError.getPath())
                    .writeEndObject();
            }
            jsonGenerator.writeEndArray();
        }
    }
}
