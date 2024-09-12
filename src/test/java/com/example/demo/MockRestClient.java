package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

@Lazy
@TestConfiguration
class MockRestClient {

    @Bean
    RestClient restClient(@Value("${server.port}") int port, @Autowired RestClient.Builder restClientBuilder) {
        return restClientBuilder.baseUrl("http://localhost:" + port)
            // We need to change the default request factory in order to use PATCH requests
            // https://github.com/spring-projects/spring-framework/issues/31590
            .requestFactory(new JdkClientHttpRequestFactory())
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();
    }
}
