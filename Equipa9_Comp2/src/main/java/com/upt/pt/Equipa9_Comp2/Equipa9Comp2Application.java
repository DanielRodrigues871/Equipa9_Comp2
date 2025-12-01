package com.upt.pt.Equipa9_Comp2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.upt.pt.api")
@EnableJpaRepositories(basePackages = "com.upt.pt.api.repository")
@EntityScan(basePackages = "com.upt.pt.api.entity")
public class Equipa9Comp2Application {

	public static void main(String[] args) {
		SpringApplication.run(Equipa9Comp2Application.class, args);
	}

}
