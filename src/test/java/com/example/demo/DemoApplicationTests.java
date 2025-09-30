package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestClient;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT, classes = RestSpringBeans.class)
class DemoApplicationTests {

    @Autowired
    private RestClient restClient;

    @Test
    void updateItemShouldFail() {
        var responseBody = restClient.put()
            .uri("/api/item")
            .body(new UpdateRequest(null))
            .exchange((clientRequest, clientResponse) -> {
                assertTrue(clientResponse.getStatusCode().is4xxClientError());
                return clientResponse.bodyTo(String.class);
            });
        assertEquals("must not be blank", responseBody);
    }
}
