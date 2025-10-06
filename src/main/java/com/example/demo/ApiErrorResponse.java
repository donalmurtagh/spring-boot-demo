package com.example.demo;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ApiErrorResponse {
    private final String code;
    private final String message;
    private final Map<String, Object> properties;
    private final List<ApiFieldError> fieldErrors;

    public ApiErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
        this.properties = new HashMap<>();
        this.fieldErrors = new ArrayList<>();
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @JsonAnyGetter
    public Map<String, Object> getProperties() {
        return properties;
    }

    public List<ApiFieldError> getFieldErrors() {
        return fieldErrors;
    }

    public void addErrorProperties(Map<String, Object> errorProperties) {
        properties.putAll(errorProperties);
    }

    public void addErrorProperty(String propertyName, Object propertyValue) {
        properties.put(propertyName, propertyValue);
    }

    public void addFieldError(ApiFieldError fieldError) {
        fieldErrors.add(fieldError);
    }

}
