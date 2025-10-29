package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.client.RestTestClient;

@WebMvcTest(ItemController.class)
@Import(RestSpringBeans.class)
@WithMockUser
class ItemControllerTests {

    @Autowired
    private MockMvcTester mockMvcTester;

    @Autowired
    private RestTestClient restTestClient;

    private final String expectedJson = """
        {
            "nameOfItem": "something",
            "deleted": false
        }
        """;

    @Test
    void getWithMockMvc() {
        mockMvcTester.get().uri("/api/item")
            .assertThat().hasStatus2xxSuccessful()
            .bodyJson()
            .isEqualTo(expectedJson);
    }

    @Test
    void getWithRestTestClient() {
        restTestClient.get().uri("/api/item")
            .exchange()
            .expectStatus().is2xxSuccessful()
            .expectBody()
            .json(expectedJson);
    }
}
