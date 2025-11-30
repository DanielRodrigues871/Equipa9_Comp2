package com.upt.lp.portalestagios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PortalEstagiosApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PortalEstagiosApiApplication.class, args);
		
		MenuAutenticacao menu = new MenuAutenticacao();
        menu.iniciar();
	}

}
