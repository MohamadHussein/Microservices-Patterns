package com.aspire.apigateway;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@EnableEurekaClient
@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@Bean
	public CommandLineRunner test(RouteLocator routeLocator) {

		return args -> {
			routeLocator
					.getRoutes()
					.subscribe(r -> {
						System.out.println("r.getFilters() = " + r.getFilters());
						System.out.println("r.getUri() = " + r.getUri());
						System.out.println("r = " + r);

					});
		};
	}
}
