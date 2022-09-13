package com.example.salesdepartment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SalesDepartmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalesDepartmentApplication.class, args);
	}

}
