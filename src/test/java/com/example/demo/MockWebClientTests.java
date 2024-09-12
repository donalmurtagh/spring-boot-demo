package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
@AutoConfigureMockMvc
class MockWebClientTests {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void getUsers() {
        webTestClient.get().uri("/users")
            .exchange()
            .expectStatus().isOk();
    }
}

