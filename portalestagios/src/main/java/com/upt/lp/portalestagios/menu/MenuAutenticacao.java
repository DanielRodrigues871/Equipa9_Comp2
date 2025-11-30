package com.upt.lp.portalestagios.menu;

import com.upt.lp.portalestagios.dto.auth.LoginRequestDTO;
import com.upt.lp.portalestagios.entity.Utilizador;
import com.upt.lp.portalestagios.service.AuthService;
import com.upt.lp.portalestagios.util.SessaoUtil;

import java.util.Scanner;

public class MenuAutenticacao {

    private final AuthService authService;

    public MenuAutenticacao(AuthService authService) {
        this.authService = authService;
    }

    public void mostrar() {

        Scanner sc = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n--- AUTENTICAÇÃO ---");
            System.out.println("1 - Login");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> login(sc);
                case 0 -> System.out.println("A voltar...");
                default -> System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }

    private void login(Scanner sc) {
        System.out.println("\n--- LOGIN ---");

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        LoginRequestDTO dto = new LoginRequestDTO(email, password);

        Utilizador u = authService.login(dto);

        if (u != null) {
            SessaoUtil.setUtilizadorLogado(u);
            System.out.println("\n✔ Login efetuado com sucesso! Bem-vindo, " + u.getNome());
        } else {
            System.out.println("\n✘ Credenciais inválidas.");
        }
    }
}
