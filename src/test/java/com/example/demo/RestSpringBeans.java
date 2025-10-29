package com.example.demo;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.client.RestTestClient;

@TestConfiguration
public class RestSpringBeans {

    @Bean
    RestTestClient restTestClient(MockMvc mockMvc) {
        return RestTestClient.bindTo(mockMvc).build();
    }
}
