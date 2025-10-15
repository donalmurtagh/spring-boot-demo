package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.client.RestTestClient;

@WebMvcTest(ItemController.class)
@Import(RestSpringBeans.class)
class ItemControllerTests {

    @Autowired
    private MockMvcTester mockMvcTester;

    @Autowired
    private RestTestClient restTestClient;

    /**
     * This test passes because the unauthenticated client is (correctly) not allowed to access the endpoint.
     */
    @Test
    void getWithMockMvc() {
        mockMvcTester.get().uri("/api/item")
            .assertThat().hasStatus(HttpStatus.UNAUTHORIZED);
    }

    /**
     * This test fails because the unauthenticated client is (incorrectly) allowed to access the endpoint.
     */
    @Test
    void getWithRestTestClient() {
        restTestClient.get().uri("/api/item")
            .exchange()
            .expectStatus().isUnauthorized();
    }
}
