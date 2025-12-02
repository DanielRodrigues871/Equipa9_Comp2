package com.upt.pt.api.client;

import java.io.IOException;
import java.util.Scanner;

public class RepresentanteEmpresaMenuClient {

    private final Scanner sc;

    public RepresentanteEmpresaMenuClient(Scanner sc) {
        this.sc = sc;
    }

    public void run() {
        while (true) {
            System.out.println("\n===== MENU REPRESENTANTE =====");
            System.out.println("1. Criar proposta de estágio");
            System.out.println("2. Consultar minhas propostas");
            System.out.println("3. Menu Empresas");
            System.out.println("0. Logout");
            System.out.print("Escolha: ");

            int op = MainConsole.lerInteiro(sc);
            sc.nextLine();

            try {
                switch (op) {
                    case 1 -> criarProposta();
                    case 2 -> listarPropostas();
                    case 3 -> new EmpresasMenuClient(sc).run();
                    case 0 -> { return; }
                    default -> System.out.println("Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    private void criarProposta() throws IOException, InterruptedException {
        System.out.println("\n--- CRIAR PROPOSTA DE ESTÁGIO ---");
        System.out.print("Título: ");
        String titulo = sc.nextLine();
        System.out.print("Descrição: ");
        String descricao = sc.nextLine();
        System.out.print("Requisitos: ");
        String requisitos = sc.nextLine();
        System.out.print("Localização: ");
        String localizacao = sc.nextLine();
        System.out.print("Duração (meses): ");
        int duracao = MainConsole.lerInteiro(sc);
        sc.nextLine();
        System.out.print("Vagas disponíveis: ");
        int vagas = MainConsole.lerInteiro(sc);
        sc.nextLine();
        System.out.print("Tipo (CURRICULAR/EXTRA_CURRICULAR): ");
        String tipo = sc.nextLine();
        System.out.print("EmpresaId: ");
        String empresaId = sc.nextLine();
        System.out.print("RepresentanteId: ");
        String representanteId = sc.nextLine();

        String json = """
                {
                  "titulo": "%s",
                  "descricao": "%s",
                  "requisitos": "%s",
                  "localizacao": "%s",
                  "duracaoMeses": %d,
                  "vagasDisponiveis": %d,
                  "tipo": "%s"
                }
                """.formatted(titulo, descricao, requisitos, localizacao, duracao, vagas, tipo);

        RestClientHelper.post("/api/propostas?empresaId=" + empresaId + "&representanteId=" + representanteId, json);
    }

    private void listarPropostas() throws IOException, InterruptedException {
        System.out.print("ID do representante: ");
        String repId = sc.nextLine();
        RestClientHelper.get("/api/propostas/representante/" + repId);
    }
}
