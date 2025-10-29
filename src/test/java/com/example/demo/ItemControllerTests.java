package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.client.RestTestClient;

@WebMvcTest(ItemController.class)
@AutoConfigureRestTestClient
@Import(SecurityConfiguration.class)
@WithMockUser(authorities = "FOO")
class ItemControllerTests {

    @Autowired
    private MockMvcTester mockMvcTester;

    @Autowired
    private RestTestClient restTestClient;

    @Test
    void getWithMockMvc() {
        mockMvcTester.get().uri("/api/item")
            .assertThat().hasStatus2xxSuccessful();
    }

    @Test
    void getWithRestTestClient() {
        restTestClient.get().uri("/api/item")
            .exchange()
            .expectStatus().is2xxSuccessful();
    }

    @Test
    @WithMockUser(authorities = "invalid")
    void getWithMockMvcBadRole() {
        mockMvcTester.get().uri("/api/item")
            .assertThat().hasStatus(HttpStatus.FORBIDDEN);
    }

    @Test
    @WithMockUser(authorities = "invalid")
    void getWithRestTestClientBadRole() {
        restTestClient.get().uri("/api/item")
            .exchange()
            .expectStatus().isForbidden();
    }
}
