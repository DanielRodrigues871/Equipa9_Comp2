package com.upt.lp.portalestagios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.ApplicationContext;

import com.upt.lp.portalestagios.service.AuthService;
import com.upt.lp.portalestagios.service.OfertaEstagioService;
import com.upt.lp.portalestagios.service.CandidaturaService;
import com.upt.lp.portalestagios.service.CursoService;
import com.upt.lp.portalestagios.service.EstatisticasService;

import com.upt.lp.portalestagios.menu.MenuAutenticacao;
import com.upt.lp.portalestagios.menu.MenuEstudante;
import com.upt.lp.portalestagios.menu.MenuCoordenador;
import com.upt.lp.portalestagios.menu.MenuEmpresa;
import com.upt.lp.portalestagios.menu.MenuPrincipal;

@SpringBootApplication
public class PortalEstagiosApiApplication {

    public static void main(String[] args) {
        // inicializa Spring context
        ApplicationContext context = SpringApplication.run(PortalEstagiosApiApplication.class, args);

        // obter beans do contexto
        AuthService authService = context.getBean(AuthService.class);
        OfertaEstagioService ofertaService = context.getBean(OfertaEstagioService.class);
        CandidaturaService candidaturaService = context.getBean(CandidaturaService.class);
        CursoService cursoService = context.getBean(CursoService.class);
        EstatisticasService estatisticasService;
        // EstatisticasService é opcional — só tenta obter se o bean existir
        try {
            estatisticasService = context.getBean(EstatisticasService.class);
        } catch (Exception ex) {
            estatisticasService = null;
        }

        // criar menus (passar os services necessários)
        MenuAutenticacao menuAut = new MenuAutenticacao(authService);
        MenuEstudante menuEst = new MenuEstudante(ofertaService, candidaturaService);
        MenuCoordenador menuCoord = new MenuCoordenador(
                ofertaService,
                candidaturaService,
                cursoService,
                // se estatisticasService for null, podes passar um stub/nulo; o teu MenuCoordenador deve tratar nulls.
                estatisticasService
        );
        MenuEmpresa menuEmp = new MenuEmpresa(ofertaService);

        // criar e montar menu principal
        MenuPrincipal menuPrincipal = new MenuPrincipal(
                menuAut,
                menuEst,
                menuCoord,
                menuEmp
        );

        // arrancar a interface de consola (menus)
        menuPrincipal.mostrar();
    }
}
