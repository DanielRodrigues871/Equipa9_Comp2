package com.upt.lp.portalestagios.menu;

import com.upt.lp.portalestagios.entity.RepresentanteEmpresa;
import com.upt.lp.portalestagios.entity.Utilizador;
import com.upt.lp.portalestagios.service.OfertaEstagioService;
import com.upt.lp.portalestagios.util.SessaoUtil;

import java.util.Scanner;
import java.util.UUID;

public class MenuEmpresa {

    private final OfertaEstagioService ofertaService;

    public MenuEmpresa(OfertaEstagioService ofertaService) {
        this.ofertaService = ofertaService;
    }

    public void mostrar() {

        Utilizador logado = SessaoUtil.getUtilizadorLogado();
        if (!(logado instanceof RepresentanteEmpresa)) {
            System.out.println("Acesso negado! Apenas representantes de empresa.");
            return;
        }

        RepresentanteEmpresa rep = (RepresentanteEmpresa) logado;

        Scanner sc = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n===== MENU EMPRESA =====");
            System.out.println("Representante: " + rep.getNome() + " | Empresa: " + rep.getEmpresa().getNome());
            System.out.println("1 - Criar proposta");
            System.out.println("2 - Ver propostas da empresa");
            System.out.println("0 - Voltar");

            System.out.print("Opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> criarProposta(rep, sc);
                case 2 -> ofertaService.listarPorEmpresa(rep.getEmpresa().getId());
                case 0 -> System.out.println("A voltar...");
                default -> System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }

    private void criarProposta(RepresentanteEmpresa rep, Scanner sc) {
        System.out.println("\n--- CRIAR PROPOSTA ---");

        System.out.print("Título: ");
        String titulo = sc.nextLine();

        System.out.print("Descrição: ");
        String descricao = sc.nextLine();

        System.out.print("ID área: ");
        UUID area = UUID.fromString(sc.nextLine());

        ofertaService.criar(titulo, descricao, rep.getEmpresa().getId(), area, null);
    }
}

