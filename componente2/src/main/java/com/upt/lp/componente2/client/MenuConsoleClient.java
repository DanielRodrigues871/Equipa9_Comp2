package com.upt.lp.componente2.client;

import com.upt.lp.componente2.entity.*;
import com.upt.lp.componente2.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.Scanner;

@Component
public class MenuConsoleClient implements CommandLineRunner {
    
    @Autowired
    private AuthService authService;
    
    @Autowired
    private EstudanteService estudanteService;
    
    @Autowired
    private CoordenadorService coordenadorService;
    
    @Autowired
    private RepresentanteEmpresaService representanteService;
    
    @Autowired
    private EmpresaService empresaService;
    
    @Autowired
    private OfertaEstagioService ofertaService;
    
    @Autowired
    private CandidaturaService candidaturaService;
    
    @Autowired
    private CursoService cursoService;
    
    @Autowired
    private PropostaEstagioService propostaService;
    
    private final Scanner scanner = new Scanner(System.in);
    
    @Override
    public void run(String... args) {
        System.out.println("=== SISTEMA INICIADO ===");
        System.out.println("Aguardando conexões HTTP...");
        System.out.println("Para usar o sistema via web, acesse: http://localhost:8080");
        System.out.println("\nPressione ENTER para abrir o menu de console...");
        scanner.nextLine();
        
        menuPrincipal();
    }
    
    private void menuPrincipal() {
        while (true) {
            System.out.println("\n=== BEM VINDO AO PORTAL DE ESTÁGIOS ===");
            System.out.println("1. Já tenho conta (Login)");
            System.out.println("2. Não tenho conta (Registrar)");
            System.out.println("3. Sair do Portal");
            System.out.print("Escolha uma opção: ");
            
            int opcao = lerInteiro();
            
            switch (opcao) {
                case 1 -> menuLogin();
                case 2 -> menuRegistro();
                case 3 -> {
                    System.out.println("Adeus!");
                    return;
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }
    
    private void menuLogin() {
        System.out.println("\n=== LOGIN ===");
        System.out.println("Selecione o tipo de utilizador:");
        System.out.println("1. Estudante");
        System.out.println("2. Coordenador");
        System.out.println("3. Representante de Empresa");
        System.out.println("0. Voltar");
        System.out.print("Escolha: ");
        
        int tipo = lerInteiro();
        
        if (tipo == 0) return;
        
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        
        try {
            switch (tipo) {
                case 1 -> {
                    Estudante estudante = authService.authenticateEstudante(email, password);
                    if (estudante != null) {
                        menuEstudante(estudante);
                    } else {
                        System.out.println("Credenciais inválidas!");
                    }
                }
                case 2 -> {
                    Coordenador coordenador = authService.authenticateCoordenador(email, password);
                    if (coordenador != null) {
                        menuCoordenador(coordenador);
                    } else {
                        System.out.println("Credenciais inválidas!");
                    }
                }
                case 3 -> {
                    RepresentanteEmpresa representante = authService.authenticateRepresentante(email, password);
                    if (representante != null) {
                        menuRepresentante(representante);
                    } else {
                        System.out.println("Credenciais inválidas!");
                    }
                }
                default -> System.out.println("Opção inválida!");
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    
    private void menuRegistro() {
        System.out.println("\n=== REGISTRO ===");
        System.out.println("Selecione o tipo de utilizador:");
        System.out.println("1. Estudante");
        System.out.println("2. Coordenador");
        System.out.println("3. Representante de Empresa");
        System.out.println("0. Voltar");
        System.out.print("Escolha: ");
        
        int tipo = lerInteiro();
        
        if (tipo == 0) return;
        
        try {
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            System.out.print("Email: ");
            String email = scanner.nextLine();
            System.out.print("Password: ");
            String password = scanner.nextLine();
            
            switch (tipo) {
                case 1 -> {
                    System.out.print("Número de estudante: ");
                    String numeroEstudante = scanner.nextLine();
                    System.out.print("Ano de matrícula: ");
                    int anoMatricula = lerInteiro();
                    
                    Estudante estudante = new Estudante(nome, email, password, numeroEstudante, anoMatricula);
                    estudanteService.createEstudante(estudante, null);
                    System.out.println("Estudante registrado com sucesso!");
                }
                case 2 -> {
                    System.out.print("ID do departamento: ");
                    String departamentoId = scanner.nextLine();
                    
                    Coordenador coordenador = new Coordenador();
                    coordenador.setNome(nome);
                    coordenador.setEmail(email);
                    coordenador.setPassword(password);
                    coordenadorService.createCoordenador(coordenador, departamentoId);
                    System.out.println("Coordenador registrado com sucesso!");
                }
                case 3 -> {
                    System.out.print("Cargo: ");
                    String cargo = scanner.nextLine();
                    System.out.print("ID da empresa: ");
                    String empresaId = scanner.nextLine();
                    
                    RepresentanteEmpresa representante = new RepresentanteEmpresa(nome, email, password, cargo);
                    representanteService.createRepresentante(representante, empresaId);
                    System.out.println("Representante registrado com sucesso!");
                }
                default -> System.out.println("Opção inválida!");
            }
        } catch (Exception e) {
            System.out.println("Erro no registro: " + e.getMessage());
        }
    }
    
    private void menuEstudante(Estudante estudante) {
        while (true) {
            System.out.println("\n===== MENU ESTUDANTE =====");
            System.out.println("Estudante: " + estudante.getNome());
            System.out.println("1. Consultar ofertas disponíveis");
            System.out.println("2. Ver minhas candidaturas");
            System.out.println("3. Menu de Candidaturas");
            System.out.println("0. Logout");
            System.out.print("Escolha uma opção: ");
            
            int opcao = lerInteiro();
            
            switch (opcao) {
                case 1 -> consultarOfertasDisponiveis(estudante);
                case 2 -> verMinhasCandidaturas(estudante);
                case 3 -> menuCandidaturas(estudante);
                case 0 -> {
                    System.out.println("Logout realizado!");
                    return;
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }
    
    private void consultarOfertasDisponiveis(Estudante estudante) {
        System.out.println("\n--- CONSULTAR OFERTAS ---");
        System.out.println("1. Ver todas as ofertas");
        System.out.println("2. Filtrar por curso");
        System.out.println("3. Filtrar por tipo");
        System.out.println("4. Filtrar por empresa");
        System.out.println("0. Voltar");
        System.out.print("Escolha: ");
        
        int opcao = lerInteiro();
        
        try {
            switch (opcao) {
                case 1 -> {
                    var ofertas = ofertaService.getAllOfertas();
                    if (ofertas.isEmpty()) {
                        System.out.println("Nenhuma oferta disponível.");
                    } else {
                        ofertas.forEach(oferta -> 
                            System.out.println(oferta.getId() + " - " + oferta.getTitulo() + 
                                             " (" + oferta.getEmpresa().getNome() + ")"));
                    }
                }
                case 2 -> {
                    System.out.print("ID do curso: ");
                    String cursoId = scanner.nextLine();
                    var ofertas = ofertaService.getOfertasByCurso(cursoId);
                    ofertas.forEach(oferta -> System.out.println(oferta.getId() + " - " + oferta.getTitulo()));
                }
                // Outros filtros podem ser implementados aqui
                case 0 -> { return; }
                default -> System.out.println("Opção inválida!");
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    
    private void verMinhasCandidaturas(Estudante estudante) {
        try {
            var candidaturas = candidaturaService.getCandidaturasByEstudante(estudante.getId());
            if (candidaturas.isEmpty()) {
                System.out.println("Nenhuma candidatura encontrada.");
            } else {
                candidaturas.forEach(candidatura -> 
                    System.out.println(candidatura.getId() + " - " + 
                                     candidatura.getOferta().getTitulo() + 
                                     " - Status: " + candidatura.getStatus()));
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    
    private void menuCandidaturas(Estudante estudante) {
        while (true) {
            System.out.println("\n===== CANDIDATURAS =====");
            System.out.println("1. Candidatar-se a oferta");
            System.out.println("2. Listar ofertas disponíveis");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            
            int opcao = lerInteiro();
            
            switch (opcao) {
                case 1 -> candidatarOferta(estudante);
                case 2 -> consultarOfertasDisponiveis(estudante);
                case 0 -> { return; }
                default -> System.out.println("Opção inválida!");
            }
        }
    }
    
    private void candidatarOferta(Estudante estudante) {
        try {
            System.out.print("ID da oferta: ");
            String ofertaId = scanner.nextLine();
            System.out.println("Carta de motivação (mínimo 50 caracteres):");
            String cartaMotivacao = scanner.nextLine();
            
            candidaturaService.createCandidatura(estudante.getId(), ofertaId, cartaMotivacao);
            System.out.println("Candidatura submetida com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    
    private void menuCoordenador(Coordenador coordenador) {
        while (true) {
            System.out.println("\n===== MENU COORDENADOR =====");
            System.out.println("Coordenador: " + coordenador.getNome());
            System.out.println("1. Criar nova oferta");
            System.out.println("2. Listar ofertas pendentes");
            System.out.println("3. Aprovar proposta de empresa");
            System.out.println("4. Listar todas as ofertas");
            System.out.println("5. Visualizar candidaturas");
            System.out.println("6. Aceitar/rejeitar candidatura");
            System.out.println("7. Registar curso");
            System.out.println("8. Listar estudantes do curso");
            System.out.println("9. Menu de Candidaturas");
            System.out.println("10. Menu de Empresas");
            System.out.println("0. Logout");
            System.out.print("Escolha uma opção: ");
            
            int opcao = lerInteiro();
            
            switch (opcao) {
                case 1 -> criarOferta(coordenador);
                case 2 -> listarOfertasPendentes();
                case 3 -> aprovarPropostaEmpresa(coordenador);
                case 4 -> listarTodasOfertas();
                case 5 -> visualizarCandidaturas(coordenador);
                case 6 -> gerirCandidaturas(coordenador);
                case 7 -> registrarCurso();
                case 8 -> listarEstudantesCurso();
                case 9 -> menuCandidaturasCoordenador(coordenador);
                case 10 -> menuEmpresas();
                case 0 -> {
                    System.out.println("Logout realizado!");
                    return;
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }
    
    private void criarOferta(Coordenador coordenador) {
        try {
            System.out.print("Título: ");
            String titulo = scanner.nextLine();
            System.out.print("Descrição: ");
            String descricao = scanner.nextLine();
            System.out.print("ID da empresa: ");
            String empresaId = scanner.nextLine();
            System.out.print("Tipo (CURRICULAR/EXTRA_CURRICULAR): ");
            String tipoStr = scanner.nextLine();
            System.out.print("Duração em meses: ");
            int duracaoMeses = lerInteiro();
            
            var tipo = com.upt.lp.componente2.enums.TipoEstagio.valueOf(tipoStr);
            var empresa = empresaService.getEmpresaById(empresaId);
            
            OfertaEstagio oferta = new OfertaEstagio(titulo, descricao, empresa, tipo, duracaoMeses);
            oferta.setCoordenadorResponsavel(coordenador);
            
            ofertaService.createOferta(oferta, empresaId, null, null, coordenador.getId());
            System.out.println("Oferta criada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    
    private void listarOfertasPendentes() {
        try {
            var ofertas = ofertaService.getOfertasByStatus(com.upt.lp.componente2.enums.StatusOferta.PENDENTE);
            if (ofertas.isEmpty()) {
                System.out.println("Nenhuma oferta pendente.");
            } else {
                ofertas.forEach(oferta -> 
                    System.out.println(oferta.getId() + " - " + oferta.getTitulo()));
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    
    private void aprovarPropostaEmpresa(Coordenador coordenador) {
        try {
            var propostas = propostaService.getPropostasPendentes();
            if (propostas.isEmpty()) {
                System.out.println("Nenhuma proposta pendente.");
                return;
            }
            
            propostas.forEach(p -> 
                System.out.println(p.getId() + " - " + p.getTitulo()));
            
            System.out.print("ID da proposta a aprovar: ");
            String propostaId = scanner.nextLine();
            
            propostaService.aprovarProposta(propostaId);
            System.out.println("Proposta aprovada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    
    private void visualizarCandidaturas(Coordenador coordenador) {
        try {
            var candidaturas = candidaturaService.getCandidaturasByCoordenador(coordenador.getId());
            if (candidaturas.isEmpty()) {
                System.out.println("Nenhuma candidatura encontrada.");
            } else {
                candidaturas.forEach(c -> 
                    System.out.println(c.getId() + " - Estudante: " + 
                                     c.getEstudante().getNome() + 
                                     " - Oferta: " + c.getOferta().getTitulo() +
                                     " - Status: " + c.getStatus()));
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    
    private void gerirCandidaturas(Coordenador coordenador) {
        try {
            var candidaturas = candidaturaService.getCandidaturasByStatus(
                com.upt.lp.componente2.enums.StatusCandidatura.EM_ANALISE);
            
            if (candidaturas.isEmpty()) {
                System.out.println("Nenhuma candidatura para gerir.");
                return;
            }
            
            candidaturas.forEach(c -> 
                System.out.println(c.getId() + " - " + c.getEstudante().getNome()));
            
            System.out.print("ID da candidatura: ");
            String candidaturaId = scanner.nextLine();
            
            System.out.println("1. Aceitar candidatura");
            System.out.println("2. Rejeitar candidatura");
            System.out.print("Escolha: ");
            int opcao = lerInteiro();
            
            switch (opcao) {
                case 1 -> candidaturaService.aprovarCandidatura(candidaturaId);
                case 2 -> {
                    System.out.print("Observações: ");
                    String observacoes = scanner.nextLine();
                    candidaturaService.rejeitarCandidatura(candidaturaId, observacoes);
                }
                default -> System.out.println("Opção inválida!");
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    
    private void menuCandidaturasCoordenador(Coordenador coordenador) {
        while (true) {
            System.out.println("\n===== GERIR CANDIDATURAS =====");
            System.out.println("1. Listar todas as candidaturas");
            System.out.println("2. Listar candidaturas em análise");
            System.out.println("3. Listar candidaturas aprovadas");
            System.out.println("4. Listar candidaturas rejeitadas");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            
            int opcao = lerInteiro();
            
            switch (opcao) {
                case 1 -> visualizarCandidaturas(coordenador);
                case 2 -> {
                    var candidaturas = candidaturaService.getCandidaturasByStatus(
                        com.upt.lp.componente2.enums.StatusCandidatura.EM_ANALISE);
                    candidaturas.forEach(System.out::println);
                }
                case 3 -> {
                    var candidaturas = candidaturaService.getCandidaturasByStatus(
                        com.upt.lp.componente2.enums.StatusCandidatura.APROVADA);
                    candidaturas.forEach(System.out::println);
                }
                case 4 -> {
                    var candidaturas = candidaturaService.getCandidaturasByStatus(
                        com.upt.lp.componente2.enums.StatusCandidatura.REJEITADA);
                    candidaturas.forEach(System.out::println);
                }
                case 0 -> { return; }
                default -> System.out.println("Opção inválida!");
            }
        }
    }
    
    private void menuEmpresas() {
        while (true) {
            System.out.println("\n===== GESTÃO DE EMPRESAS =====");
            System.out.println("1. Registar nova empresa");
            System.out.println("2. Listar empresas");
            System.out.println("3. Editar empresa");
            System.out.println("4. Remover empresa");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            
            int opcao = lerInteiro();
            
            switch (opcao) {
                case 1 -> registrarEmpresa();
                case 2 -> listarEmpresas();
                case 3 -> editarEmpresa();
                case 4 -> removerEmpresa();
                case 0 -> { return; }
                default -> System.out.println("Opção inválida!");
            }
        }
    }
    
    private void registrarEmpresa() {
        try {
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            System.out.print("NIF: ");
            String nif = scanner.nextLine();
            System.out.print("Email: ");
            String email = scanner.nextLine();
            System.out.print("Morada: ");
            String morada = scanner.nextLine();
            
            Empresa empresa = new Empresa(nome, nif, email, morada);
            empresaService.createEmpresa(empresa);
            System.out.println("Empresa registrada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    
    private void listarEmpresas() {
        try {
            var empresas = empresaService.getAllEmpresas();
            if (empresas.isEmpty()) {
                System.out.println("Nenhuma empresa encontrada.");
            } else {
                empresas.forEach(e -> 
                    System.out.println(e.getId() + " - " + e.getNome() + 
                                     " - NIF: " + e.getNif()));
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    
    private void menuRepresentante(RepresentanteEmpresa representante) {
        while (true) {
            System.out.println("\n===== MENU REPRESENTANTE =====");
            System.out.println("Representante: " + representante.getNome() + 
                             " (" + representante.getEmpresa().getNome() + ")");
            System.out.println("1. Criar proposta de estágio");
            System.out.println("2. Consultar minhas propostas");
            System.out.println("3. Menu de Empresas");
            System.out.println("0. Logout");
            System.out.print("Escolha uma opção: ");
            
            int opcao = lerInteiro();
            
            switch (opcao) {
                case 1 -> criarPropostaEstagio(representante);
                case 2 -> consultarMinhasPropostas(representante);
                case 3 -> menuEmpresasRepresentante(representante);
                case 0 -> {
                    System.out.println("Logout realizado!");
                    return;
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }
    
    private void criarPropostaEstagio(RepresentanteEmpresa representante) {
        try {
            System.out.print("Título: ");
            String titulo = scanner.nextLine();
            System.out.print("Descrição: ");
            String descricao = scanner.nextLine();
            System.out.print("Requisitos: ");
            String requisitos = scanner.nextLine();
            System.out.print("Localização: ");
            String localizacao = scanner.nextLine();
            System.out.print("Duração em meses: ");
            int duracaoMeses = lerInteiro();
            System.out.print("É remunerado? (true/false): ");
            boolean remunerado = Boolean.parseBoolean(scanner.nextLine());
            System.out.print("Vagas disponíveis: ");
            int vagas = lerInteiro();
            System.out.print("Tipo (CURRICULAR/EXTRA_CURRICULAR): ");
            String tipo = scanner.nextLine();
            
            PropostaEstagio proposta = new PropostaEstagio(
                titulo, descricao, requisitos, localizacao, 
                duracaoMeses, remunerado, vagas, tipo,
                representante.getEmpresa(), representante
            );
            
            propostaService.createProposta(proposta, 
                representante.getEmpresa().getId(), 
                representante.getId(), 
                null);
            
            System.out.println("Proposta criada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    
    private void consultarMinhasPropostas(RepresentanteEmpresa representante) {
        try {
            var propostas = propostaService.getPropostasByRepresentante(representante.getId());
            if (propostas.isEmpty()) {
                System.out.println("Nenhuma proposta encontrada.");
            } else {
                propostas.forEach(p -> 
                    System.out.println(p.getId() + " - " + p.getTitulo() + 
                                     " - Status: " + p.getStatus()));
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    
    private void menuEmpresasRepresentante(RepresentanteEmpresa representante) {
        // Menu específico para representante ver sua empresa
        System.out.println("\n--- MINHA EMPRESA ---");
        Empresa empresa = representante.getEmpresa();
        System.out.println("Nome: " + empresa.getNome());
        System.out.println("NIF: " + empresa.getNif());
        System.out.println("Email: " + empresa.getEmail());
        System.out.println("Ativa: " + empresa.getAtiva());
    }
    
    private int lerInteiro() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Por favor, insira um número válido: ");
            }
        }
    }
    
    private void listarTodasOfertas() {
        try {
            var ofertas = ofertaService.getAllOfertas();
            ofertas.forEach(o -> 
                System.out.println(o.getId() + " - " + o.getTitulo() + 
                                 " - Status: " + o.getStatus()));
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    
    private void registrarCurso() {
        try {
            System.out.print("Nome do curso: ");
            String nome = scanner.nextLine();
            System.out.print("Código: ");
            String codigo = scanner.nextLine();
            System.out.print("Duração em anos: ");
            int duracao = lerInteiro();
            System.out.print("Grau: ");
            String grau = scanner.nextLine();
            
            Curso curso = new Curso(nome, codigo, duracao, grau);
            cursoService.createCurso(curso, null, null);
            System.out.println("Curso registrado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    
    private void listarEstudantesCurso() {
        try {
            System.out.print("ID do curso: ");
            String cursoId = scanner.nextLine();
            
            var estudantes = estudanteService.getEstudantesByCurso(cursoId);
            if (estudantes.isEmpty()) {
                System.out.println("Nenhum estudante neste curso.");
            } else {
                estudantes.forEach(e -> 
                    System.out.println(e.getId() + " - " + e.getNome()));
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    
    private void editarEmpresa() {
        try {
            System.out.print("ID da empresa: ");
            String id = scanner.nextLine();
            
            Empresa empresa = empresaService.getEmpresaById(id);
            System.out.println("Editando: " + empresa.getNome());
            
            System.out.print("Novo nome (enter para manter): ");
            String nome = scanner.nextLine();
            if (!nome.isBlank()) empresa.setNome(nome);
            
            System.out.print("Novo email (enter para manter): ");
            String email = scanner.nextLine();
            if (!email.isBlank()) empresa.setEmail(email);
            
            empresaService.updateEmpresa(id, empresa);
            System.out.println("Empresa atualizada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    
    private void removerEmpresa() {
        try {
            System.out.print("ID da empresa: ");
            String id = scanner.nextLine();
            
            System.out.print("Tem certeza? (s/n): ");
            String confirmacao = scanner.nextLine();
            
            if (confirmacao.equalsIgnoreCase("s")) {
                empresaService.deleteEmpresa(id);
                System.out.println("Empresa removida com sucesso!");
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
