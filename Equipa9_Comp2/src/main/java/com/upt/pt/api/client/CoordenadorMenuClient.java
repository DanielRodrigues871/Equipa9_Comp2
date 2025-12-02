package com.upt.pt.api.client;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class CoordenadorMenuClient {

    private final Scanner sc;

    public CoordenadorMenuClient(Scanner sc) {
        this.sc = sc;
    }

    public void run() {
        while (true) {
            System.out.println("\n===== MENU COORDENADOR =====");
            System.out.println("1. Criar nova oferta");
            System.out.println("2. Editar oferta");
            System.out.println("3. Eliminar oferta");
            System.out.println("4. Listar ofertas pendentes");
            System.out.println("5. Aprovar oferta");
            System.out.println("6. Rejeitar oferta");
            System.out.println("7. Listar todas as ofertas");
            System.out.println("8. Menu Candidaturas");
            System.out.println("9. Menu Empresas");
            System.out.println("0. Logout");
            System.out.print("Escolha: ");

            int op = MainConsole.lerInteiro(sc);
            sc.nextLine();

            try {
                switch (op) {
                    case 1 -> criarOferta();
                    case 2 -> editarOferta();
                    case 3 -> eliminarOferta();
                    case 4 -> RestClientHelper.get("/api/ofertas/status/PENDENTE");
                    case 5 -> workflowOferta("aprovar");
                    case 6 -> workflowOferta("rejeitar");
                    case 7 -> RestClientHelper.get("/api/ofertas");
                    case 8 -> new CandidaturasMenuClient(sc).run();
                    case 9 -> new EmpresasMenuClient(sc).run();
                    case 0 -> { return; }
                    default -> System.out.println("Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    private void criarOferta() throws IOException, InterruptedException {
        System.out.println("\n--- CRIAR OFERTA ---");
        System.out.print("Título: ");
        String titulo = sc.nextLine();
        System.out.print("Descrição: ");
        String descricao = sc.nextLine();
        System.out.print("Tipo (CURRICULAR/EXTRA_CURRICULAR): ");
        String tipo = sc.nextLine();
        System.out.print("Duração (meses): ");
        int duracao = MainConsole.lerInteiro(sc);
        sc.nextLine();
        System.out.print("Número de vagas: ");
        int vagas = MainConsole.lerInteiro(sc);
        sc.nextLine();
        System.out.print("EmpresaId: ");
        String empresaId = sc.nextLine();

        String json = """
                {
                  "titulo": "%s",
                  "descricao": "%s",
                  "tipo": "%s",
                  "duracaoMeses": %d,
                  "numeroVagas": %d
                }
                """.formatted(titulo, descricao, tipo, duracao, vagas);

        HttpResponse<String> resp =
                RestClientHelper.post("/api/ofertas?empresaId=" + empresaId, json);
        System.out.println("HTTP " + resp.statusCode());
        System.out.println(resp.body());
    }

    private void editarOferta() throws IOException, InterruptedException {
        System.out.println("\n--- EDITAR OFERTA ---");
        System.out.print("ID da oferta: ");
        String id = sc.nextLine();

        System.out.print("Novo título: ");
        String titulo = sc.nextLine();
        System.out.print("Nova descrição: ");
        String descricao = sc.nextLine();
        System.out.print("Tipo (CURRICULAR/EXTRA_CURRICULAR): ");
        String tipo = sc.nextLine();
        System.out.print("Duração (meses): ");
        int duracao = MainConsole.lerInteiro(sc);
        sc.nextLine();
        System.out.print("Número de vagas: ");
        int vagas = MainConsole.lerInteiro(sc);
        sc.nextLine();
        System.out.print("EmpresaId: ");
        String empresaId = sc.nextLine();

        String json = """
                {
                  "titulo": "%s",
                  "descricao": "%s",
                  "tipo": "%s",
                  "duracaoMeses": %d,
                  "numeroVagas": %d
                }
                """.formatted(titulo, descricao, tipo, duracao, vagas);

        HttpResponse<String> resp =
                RestClientHelper.put("/api/ofertas/" + id + "?empresaId=" + empresaId, json);
        System.out.println("HTTP " + resp.statusCode());
        System.out.println(resp.body());
    }

    private void eliminarOferta() throws IOException, InterruptedException {
        System.out.println("\n--- ELIMINAR OFERTA ---");
        System.out.print("ID da oferta: ");
        String id = sc.nextLine();
        RestClientHelper.delete("/api/ofertas/" + id);
    }

    private void workflowOferta(String acao) throws IOException, InterruptedException {
        System.out.print("ID da oferta: ");
        String id = sc.nextLine();
        RestClientHelper.post("/api/ofertas/" + id + "/" + acao, "");
    }
}
