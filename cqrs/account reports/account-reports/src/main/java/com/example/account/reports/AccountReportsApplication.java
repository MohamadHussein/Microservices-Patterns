package com.example.account.reports;

import com.example.account.reports.Configuration.AxonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({ AxonConfig.class })
public class AccountReportsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountReportsApplication.class, args);
	}

}
