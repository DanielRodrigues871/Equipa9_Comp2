package com.upt.lp.portalestagios.menu;

import com.upt.lp.portalestagios.entity.Utilizador;
import com.upt.lp.portalestagios.entity.Estudante;
import com.upt.lp.portalestagios.service.CandidaturaService;
import com.upt.lp.portalestagios.service.OfertaEstagioService;
import com.upt.lp.portalestagios.util.SessaoUtil;

import java.util.Scanner;
import java.util.UUID;

public class MenuEstudante {

    private final OfertaEstagioService ofertaService;
    private final CandidaturaService candidaturaService;

    public MenuEstudante(OfertaEstagioService ofertaService,
                         CandidaturaService candidaturaService) {
        this.ofertaService = ofertaService;
        this.candidaturaService = candidaturaService;
    }

    public void mostrar() {

        Utilizador logado = SessaoUtil.getUtilizadorLogado();
        if (!(logado instanceof Estudante)) {
            System.out.println("Acesso negado! Apenas estudantes.");
            return;
        }

        Estudante estudante = (Estudante) logado;

        Scanner sc = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n===== MENU ESTUDANTE =====");
            System.out.println("Estudante: " + estudante.getNome());
            System.out.println("1 - Listar ofertas disponíveis");
            System.out.println("2 - Ver minhas candidaturas");
            System.out.println("3 - Candidatar-me a uma oferta");
            System.out.println("0 - Voltar");

            System.out.print("Opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> ofertaService.listarDisponiveis();
                case 2 -> candidaturaService.listarPorEstudante(estudante.getId());
                case 3 -> candidatar(estudante, sc);
                case 0 -> System.out.println("A voltar...");
                default -> System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }

    private void candidatar(Estudante estudante, Scanner sc) {
        System.out.println("\n--- CANDIDATAR A OFERTA ---");

        ofertaService.listarDisponiveis();

        System.out.print("ID da oferta: ");
        String id = sc.nextLine();

        System.out.print("Carta de motivação: ");
        String motivacao = sc.nextLine();

        candidaturaService.criar(estudante.getId(), UUID.fromString(id), motivacao);
    }
}

