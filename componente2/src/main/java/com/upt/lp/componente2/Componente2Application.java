package com.upt.lp.componente2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.upt.pt.api")
@EnableJpaRepositories(basePackages = "com.upt.pt.api.repository")
@EntityScan(basePackages = "com.upt.pt.api.entity")
public class Componente2Application {

	public static void main(String[] args) {
		SpringApplication.run(Componente2Application.class, args);
	}
}