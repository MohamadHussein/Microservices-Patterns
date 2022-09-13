package com.example.procurementdepartment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
//@EnableEurekaClient
public class ProcurementDepartmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProcurementDepartmentApplication.class, args);
	}

}
