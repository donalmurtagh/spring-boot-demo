package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = MockRestClient.class)
@AutoConfigureMockMvc
class MockRestClientTests {

	@Autowired
	private RestClient restClient;

	@Test
	void getUsers() {
		var status = restClient.get().uri("/users")
			.retrieve()
			.toBodilessEntity()
			.getStatusCode();

		assertEquals(HttpStatus.OK, status);
	}
}

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
