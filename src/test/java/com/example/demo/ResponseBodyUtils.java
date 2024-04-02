package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.wimdeblauwe.errorhandlingspringbootstarter.ApiErrorResponse;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient.RequestHeadersSpec.ExchangeFunction;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ResponseBodyUtils {

    private final ObjectMapper objectMapper;

    public ResponseBodyUtils(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * Provides an exchange function that expects a request to fail
     *
     * @param expectedStatus an unsuccessful status
     * @return the body will be returned as an ApiErrorResponse
     */
    public ExchangeFunction<ResponseEntity<ApiErrorResponse>> errorBody(HttpStatusCode expectedStatus) {
        return (clientRequest, clientResponse) -> {
            var responseStatus = clientResponse.getStatusCode();
            assertFalse(responseStatus.is2xxSuccessful());
            assertTrue(clientResponse.getStatusCode().isSameCodeAs(expectedStatus));
            var errorResponse = objectMapper.readValue(clientResponse.getBody(), ApiErrorResponse.class);
            return ResponseEntity.status(clientResponse.getStatusCode())
                .headers(clientResponse.getHeaders())
                .body(errorResponse);
        };
    }

}
