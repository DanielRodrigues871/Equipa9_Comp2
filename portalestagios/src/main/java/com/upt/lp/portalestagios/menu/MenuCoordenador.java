package com.upt.lp.portalestagios.menu;

import com.upt.lp.portalestagios.entity.Coordenador;
import com.upt.lp.portalestagios.entity.Utilizador;
import com.upt.lp.portalestagios.service.CandidaturaService;
import com.upt.lp.portalestagios.service.OfertaEstagioService;
import com.upt.lp.portalestagios.service.CursoService;
import com.upt.lp.portalestagios.util.SessaoUtil;

import java.util.Scanner;
import java.util.UUID;

public class MenuCoordenador {

    private final OfertaEstagioService ofertaService;
    private final CandidaturaService candidaturaService;
    private final CursoService cursoService;

    public MenuCoordenador(OfertaEstagioService ofertaService,
                           CandidaturaService candidaturaService,
                           CursoService cursoService) {
        this.ofertaService = ofertaService;
        this.candidaturaService = candidaturaService;
        this.cursoService = cursoService;
    }

    public void mostrar() {

        Utilizador logado = SessaoUtil.getUtilizadorLogado();
        if (!(logado instanceof Coordenador)) {
            System.out.println("Acesso negado! Apenas coordenadores.");
            return;
        }

        Coordenador coord = (Coordenador) logado;

        Scanner sc = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n===== MENU COORDENADOR =====");
            System.out.println("Coordenador: " + coord.getNome());
            System.out.println("1 - Criar oferta");
            System.out.println("2 - Editar oferta");
            System.out.println("3 - Eliminar oferta");
            System.out.println("4 - Listar ofertas");
            System.out.println("5 - Ver candidaturas");
            System.out.println("6 - Gerir candidatura");
            System.out.println("7 - Criar curso");
            System.out.println("0 - Voltar");

            System.out.print("Opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> criarOferta(coord, sc);
                case 2 -> editarOferta(sc);
                case 3 -> eliminarOferta(sc);
                case 4 -> ofertaService.listarTodas();
                case 5 -> candidaturaService.listarPorCoordenador(coord.getId());
                case 6 -> gerirCandidatura(coord, sc);
                case 7 -> criarCurso(coord, sc);
                case 0 -> System.out.println("A voltar...");
                default -> System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }

    private void criarOferta(Coordenador coord, Scanner sc) {
        System.out.println("\n--- CRIAR OFERTA ---");

        System.out.print("Título: ");
        String titulo = sc.nextLine();

        System.out.print("Descrição: ");
        String descricao = sc.nextLine();

        System.out.print("ID área: ");
        UUID area = UUID.fromString(sc.nextLine());

        System.out.print("ID empresa: ");
        UUID empresa = UUID.fromString(sc.nextLine());

        ofertaService.criar(titulo, descricao, empresa, area, coord.getId());
    }

    private void editarOferta(Scanner sc) {
        System.out.print("ID da oferta: ");
        UUID id = UUID.fromString(sc.nextLine());
        ofertaService.editar(id);
    }

    private void eliminarOferta(Scanner sc) {
        System.out.print("ID da oferta: ");
        UUID id = UUID.fromString(sc.nextLine());
        ofertaService.eliminar(id);
    }

    private void gerirCandidatura(Coordenador coord, Scanner sc) {
        System.out.print("ID da candidatura: ");
        UUID id = UUID.fromString(sc.nextLine());

        System.out.println("1 - Aceitar");
        System.out.println("2 - Rejeitar");
        System.out.print("Opção: ");
        int opt = sc.nextInt();

        if (opt == 1)
            candidaturaService.aceitar(id);
        else if (opt == 2)
            candidaturaService.rejeitar(id, coord.getId());
    }

    private void criarCurso(Coordenador coord, Scanner sc) {
        System.out.println("\n--- CRIAR CURSO ---");

        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("Código: ");
        String codigo = sc.nextLine();

        System.out.print("Duração (anos): ");
        int duracao = sc.nextInt();
        sc.nextLine();

        System.out.print("Grau: ");
        String grau = sc.nextLine();

        cursoService.criar(nome, codigo, duracao, grau, coord.getDepartamento().getId());
    }
}

