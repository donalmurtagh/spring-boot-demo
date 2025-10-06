package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.client.RestTestClient;
import tools.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT, classes = RestSpringBeans.class)
class ItemControllerTests {

    @Autowired
    private RestTestClient restTestClient;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void parseResponseAsApiErrorResponse() {
        ApiErrorResponse apiErrorResponse = restTestClient.put()
            .uri("/api/item")
            .exchange()
            .expectStatus().is2xxSuccessful()
            .expectBody(ApiErrorResponse.class)
            .returnResult()
            .getResponseBody();

        assertEquals("code", apiErrorResponse.getCode());
        assertEquals("message", apiErrorResponse.getMessage());

        // The following line fails because ApiErrorResponseDeserializer is not invoked to deserialize the response body
        assertEquals(1, apiErrorResponse.getFieldErrors().size());
    }

    @Test
    void parseResponseAsString() {
        String responseBody = restTestClient.put()
            .uri("/api/item")
            .exchange()
            .expectStatus().is2xxSuccessful()
            .expectBody(String.class)
            .returnResult()
            .getResponseBody();

        var apiErrorResponse = objectMapper.readValue(responseBody, ApiErrorResponse.class);

        assertEquals("code", apiErrorResponse.getCode());
        assertEquals("message", apiErrorResponse.getMessage());
        assertEquals(1, apiErrorResponse.getFieldErrors().size());
    }
}
