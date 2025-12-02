package com.upt.pt.api.client;

import java.io.IOException;
import java.util.Scanner;

public class CandidaturasMenuClient {

    private final Scanner sc;

    public CandidaturasMenuClient(Scanner sc) {
        this.sc = sc;
    }

    public void run() {
        while (true) {
            System.out.println("\n===== MENU CANDIDATURAS =====");
            System.out.println("1. Listar todas as candidaturas");
            System.out.println("2. Listar candidaturas por oferta");
            System.out.println("3. Listar candidaturas por estudante");
            System.out.println("4. Candidatar a uma oferta");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");

            int op = MainConsole.lerInteiro(sc);
            sc.nextLine();

            try {
                switch (op) {
                    case 1 -> RestClientHelper.get("/api/candidaturas");
                    case 2 -> listarPorOferta();
                    case 3 -> listarPorEstudante();
                    case 4 -> candidatar();
                    case 0 -> { return; }
                    default -> System.out.println("Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    private void listarPorOferta() throws IOException, InterruptedException {
        System.out.print("ID da oferta: ");
        String ofertaId = sc.nextLine();
        RestClientHelper.get("/api/candidaturas/oferta/" + ofertaId);
    }

    private void listarPorEstudante() throws IOException, InterruptedException {
        System.out.print("ID do estudante: ");
        String estId = sc.nextLine();
        RestClientHelper.get("/api/candidaturas/estudante/" + estId);
    }

    private void candidatar() throws IOException, InterruptedException {
        System.out.println("\n--- CANDIDATAR A UMA OFERTA ---");
        System.out.print("ID do estudante: ");
        String estudanteId = sc.nextLine();
        System.out.print("ID da oferta: ");
        String ofertaId = sc.nextLine();
        System.out.print("Carta de motivação: ");
        String carta = sc.nextLine();

        String json = """
                {
                  "cartaMotivacao": "%s"
                }
                """.formatted(carta);

        RestClientHelper.post("/api/candidaturas?estudanteId=" + estudanteId + "&ofertaId=" + ofertaId, json);
    }
}
