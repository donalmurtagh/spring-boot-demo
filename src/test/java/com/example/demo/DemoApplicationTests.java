package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestClient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT, classes = RestSpringBeans.class)
class DemoApplicationTests {

    @Autowired
    private RestClient restClient;

    @Test
    void createItemWithNotNullId() {
        Item item = restClient.post()
            .uri("/api/item")
            .retrieve()
            .body(Item.class);

        assertEquals("Item 1", item.name());
    }

    @Test
    void createItemWithNullId() {
        Item item = restClient.post()
            .uri("/api/item/null-id")
            .retrieve()
            .body(Item.class);

        assertEquals("Item 1", item.name());
    }
}
