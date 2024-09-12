package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestClient;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = RestClientBean.class)
class MockEnvTests {

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
