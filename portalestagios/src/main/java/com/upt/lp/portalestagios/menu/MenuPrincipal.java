package com.upt.lp.portalestagios.menu;

import com.upt.lp.portalestagios.entity.Coordenador;
import com.upt.lp.portalestagios.entity.Estudante;
import com.upt.lp.portalestagios.entity.RepresentanteEmpresa;
import com.upt.lp.portalestagios.entity.Utilizador;
import com.upt.lp.portalestagios.util.SessaoUtil;

import java.util.Scanner;

public class MenuPrincipal {

    private final MenuAutenticacao menuAut;
    private final MenuEstudante menuEst;
    private final MenuCoordenador menuCoord;
    private final MenuEmpresa menuEmp;

    public MenuPrincipal(MenuAutenticacao menuAut,
                         MenuEstudante menuEst,
                         MenuCoordenador menuCoord,
                         MenuEmpresa menuEmp) {

        this.menuAut = menuAut;
        this.menuEst = menuEst;
        this.menuCoord = menuCoord;
        this.menuEmp = menuEmp;
    }

    public void mostrar() {

        Scanner sc = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n===== MENU PRINCIPAL =====");

            if (!SessaoUtil.isLogado()) {
                System.out.println("1 - Autenticar");
            } else {

                Utilizador u = SessaoUtil.getUtilizadorLogado();

                if (u instanceof Estudante)
                    System.out.println("2 - Menu Estudante");

                if (u instanceof Coordenador)
                    System.out.println("3 - Menu Coordenador");

                if (u instanceof RepresentanteEmpresa)
                    System.out.println("4 - Menu Empresa");

                System.out.println("9 - Logout");
            }

            System.out.println("0 - Sair");

            System.out.print("Escolha: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> menuAut.mostrar();
                case 2 -> menuEst.mostrar();
                case 3 -> menuCoord.mostrar();
                case 4 -> menuEmp.mostrar();
                case 9 -> SessaoUtil.logout();
                case 0 -> System.out.println("A terminar programa...");
                default -> System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }
}

