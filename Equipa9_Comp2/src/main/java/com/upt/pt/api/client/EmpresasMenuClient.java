package com.upt.pt.api.client;

import java.io.IOException;
import java.util.Scanner;

public class EmpresasMenuClient {

    private final Scanner sc;

    public EmpresasMenuClient(Scanner sc) {
        this.sc = sc;
    }

    public void run() {
        while (true) {
            System.out.println("\n===== MENU EMPRESAS =====");
            System.out.println("1. Listar empresas");
            System.out.println("2. Criar empresa");
            System.out.println("3. Ativar empresa");
            System.out.println("4. Desativar empresa");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");

            int op = MainConsole.lerInteiro(sc);
            sc.nextLine();

            try {
                switch (op) {
                    case 1 -> RestClientHelper.get("/api/empresas");
                    case 2 -> criarEmpresa();
                    case 3 -> alterarEstadoEmpresa(true);
                    case 4 -> alterarEstadoEmpresa(false);
                    case 0 -> { return; }
                    default -> System.out.println("Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    private void criarEmpresa() throws IOException, InterruptedException {
        System.out.println("\n--- CRIAR EMPRESA ---");
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("NIF: ");
        String nif = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Morada: ");
        String morada = sc.nextLine();

        String json = """
                {
                  "nome": "%s",
                  "nif": "%s",
                  "email": "%s",
                  "morada": "%s",
                  "ativa": true
                }
                """.formatted(nome, nif, email, morada);

        RestClientHelper.post("/api/empresas", json);
    }

    private void alterarEstadoEmpresa(boolean ativar) throws IOException, InterruptedException {
        System.out.print("ID da empresa: ");
        String id = sc.nextLine();
        String acao = ativar ? "ativar" : "desativar";
        RestClientHelper.post("/api/empresas/" + id + "/" + acao, "");
    }
}
