package com.upt.lp.portalestagios;

import org.springframework.boot.SpringApplication;
import com.upt.lp.portalestagios.menu.MenuAutenticacao;
import com.upt.lp.portalestagios.service.AuthService;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PortalEstagiosApiApplication {

	public static void main(String[] args) {
	    var context = SpringApplication.run(PortalEstagiosApiApplication.class, args);
		
		 AuthService authService = context.getBean(AuthService.class);
		
		MenuAutenticacao menu = new MenuAutenticacao(authService);
        menu.mostrar();
	}

}
