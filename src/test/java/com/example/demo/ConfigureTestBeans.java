package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

/**
 * This class needs to be @Lazy in order for it to
 * <a href="https://stackoverflow.com/a/70897781/2648">access the server port</a>
 */
@Lazy
@TestConfiguration
public class ConfigureTestBeans {

    @Bean
    RestClient restClient(@LocalServerPort int port, @Autowired RestClient.Builder restClientBuilder) {
        return restClientBuilder.baseUrl("http://localhost:" + port)
            .requestFactory(new JdkClientHttpRequestFactory())
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();
    }

    @Bean
    ResponseBodyUtils responseBodyUtils(ObjectMapper objectMapper) {
        return new ResponseBodyUtils(objectMapper);
    }
}
