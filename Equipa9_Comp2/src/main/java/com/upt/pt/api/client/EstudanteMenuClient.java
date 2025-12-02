package com.upt.pt.api.client;

import java.io.IOException;
import java.util.Scanner;

public class EstudanteMenuClient {

    private final Scanner sc;

    public EstudanteMenuClient(Scanner sc) {
        this.sc = sc;
    }

    public void run() {
        while (true) {
            System.out.println("\n===== MENU ESTUDANTE =====");
            System.out.println("1. Consultar ofertas aprovadas");
            System.out.println("2. Ver minhas candidaturas");
            System.out.println("3. Menu Candidaturas");
            System.out.println("0. Logout");
            System.out.print("Escolha: ");

            int op = MainConsole.lerInteiro(sc);
            sc.nextLine();

            try {
                switch (op) {
                    case 1 -> RestClientHelper.get("/api/ofertas/status/APROVADO");
                    case 2 -> listarMinhasCandidaturas();
                    case 3 -> new CandidaturasMenuClient(sc).run();
                    case 0 -> { return; }
                    default -> System.out.println("Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    private void listarMinhasCandidaturas() throws IOException, InterruptedException {
        System.out.print("ID do estudante: ");
        String estId = sc.nextLine();
        RestClientHelper.get("/api/candidaturas/estudante/" + estId);
    }
}
