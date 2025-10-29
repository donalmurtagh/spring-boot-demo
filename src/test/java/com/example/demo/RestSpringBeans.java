package com.example.demo;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.JacksonJsonHttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.client.RestTestClient;

/**
 * This class defines Spring beans that are useful for testing the application via it's REST API i.e. by making
 * HTTP calls to the endpoints.
 * <p>
 * This class needs to be @Lazy in order for it to
 * <a href="https://stackoverflow.com/a/70897781/2648">access the server port</a>
 */
@Lazy
@TestConfiguration
public class RestSpringBeans {

    @Bean
    RestTestClient restTestClient(MockMvc mockMvc) {
        return RestTestClient.bindTo(mockMvc)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();
    }
}
