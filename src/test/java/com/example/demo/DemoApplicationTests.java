package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestClient;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = RANDOM_PORT, classes = ConfigureTestBeans.class)
class DemoApplicationTests {

	@Autowired
	private RestClient restClient;

	@Autowired
	private ResponseBodyUtils responseBodyUtils;

	// Call an endpoint that returns OK, just to demonstrate that everything is setup properly
	@Test
	void success() {
		var response = restClient.get()
			.uri("/users/success")
			.retrieve()
			.toBodilessEntity();

		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	// Call an endpoint that throws an error in the controller. Convert the response to an ApiErrorResponse to
	// confirm that it was handled by error-handling-spring-boot-starter
	@Test
	void errorInController() {
		var response = restClient.get()
			.uri("/users/errorInController")
			.exchange(responseBodyUtils.errorBody(HttpStatus.INTERNAL_SERVER_ERROR));

		assertEquals("error in controller", response.getBody().getMessage());
	}

	// Call an endpoint that throws an error in the filter chain. Try to convert the response to an ApiErrorResponse,
	// but this attempt will fail because of https://github.com/wimdeblauwe/error-handling-spring-boot-starter/issues/87
	@Test
	void errorInFilter() {
		var response = restClient.get()
			.uri("/users/errorInFilter")
			.exchange(responseBodyUtils.errorBody(HttpStatus.INTERNAL_SERVER_ERROR));

		assertEquals("error in filter", response.getBody().getMessage());
	}
}
