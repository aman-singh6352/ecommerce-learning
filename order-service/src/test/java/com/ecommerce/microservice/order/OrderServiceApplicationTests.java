package com.ecommerce.microservice.order;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.mysql.MySQLContainer;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderServiceApplicationTests {

	// Using official MySQL Testcontainer matching your docker-compose version
	@ServiceConnection
	static MySQLContainer mySQLContainer = new MySQLContainer("mysql:8.3.0");

	@LocalServerPort
	private Integer port;

	@BeforeEach
	void setup() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	@Test
	void shouldCreateOrder() {
		// Your Order payload
		String requestBody = """
              {
                  "skuCode": "iphone_15",
                  "price": 1000,
                  "quantity": 1
              }
           """;

		RestAssured.given()
				.contentType("application/json")
				.body(requestBody)
				.when()
				.post("/api/order") // Adjusted to standard order endpoint path
				.then()
				.statusCode(201) // or 201 Created depending on your Controller setup
				.body(Matchers.equalTo("Order Created Successfully!"));
	}

	static {
		mySQLContainer.start();
	}
}