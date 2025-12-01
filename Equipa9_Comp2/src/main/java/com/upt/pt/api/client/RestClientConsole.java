package com.upt.pt.api.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.*;
import java.util.Scanner;

public class RestClientConsole {

    private static final String BASE_URL = "http://localhost:8080";
    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("  PORTAL DE GESTÃO DE ESTÁGIOS - UPT");
        System.out.println("========================================\n");

        while (true) {
            String perfil = iniciarSessaoOuRegistrar(); // "COORD", "EST", "REP"
            if (perfil == null) {
                System.out.println("Falha no login/registo.\n");
                continue;
            }

            switch (perfil) {
                case "COORD" -> menuCoordenador();
                case "EST"   -> menuEstudante();
                case "REP"   -> menuRepresentante();
                default      -> System.out.println("Perfil desconhecido.\n");
            }
        }
    }

    // =======================================================
    // LOGIN / REGISTO (simples, focado em simular o fluxo)
    // =======================================================

    private static String iniciarSessaoOuRegistrar() {
        while (true) {
            System.out.println("=== BEM VINDO AO PORTAL DE ESTÁGIOS ===");
            System.out.println("1. Já tenho conta (Login)");
            System.out.println("2. Não tenho conta (Registrar)");
            System.out.println("3. Sair do Portal");
            System.out.print("Escolha uma opção: ");

            int opcao = lerInteiro();
            scanner.nextLine();

            try {
                switch (opcao) {
                    case 1 -> {
                        return login();
                    }
                    case 2 -> {
                        registarNovoUtilizador();
                    }
                    case 3 -> {
                        System.out.println("Obrigado por utilizar o Portal de Estágios. Até breve!");
                        System.exit(0);
                    }
                    default -> System.out.println("Opção inválida, tente novamente.\n");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage() + "\n");
            }
        }
    }

    // Aqui o login é simulado: faz um POST para /api/auth/login
    // e depois pergunta-te o perfil (porque não sabemos o formato exato da resposta).
    private static String login() throws IOException, InterruptedException {
        System.out.println("\n=== LOGIN ===");
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        String json = """
                {
                  "email": "%s",
                  "password": "%s"
                }
                """.formatted(email, senha);

        HttpResponse<String> resp = post("/api/auth/login", json);
        System.out.println("HTTP " + resp.statusCode());
        System.out.println(resp.body());

        if (resp.statusCode() != 200) {
            System.out.println("Credenciais inválidas ou erro no login.\n");
            return null;
        }

        // Simulação de escolha de perfil (até integrares com a resposta real do backend)
        System.out.println("\nLogin efetuado. Selecione o perfil (simulação):");
        System.out.println("1. Coordenador");
        System.out.println("2. Estudante");
        System.out.println("3. Representante de Empresa");
        System.out.print("Opção: ");
        int op = lerInteiro();
        scanner.nextLine();
        return switch (op) {
            case 1 -> "COORD";
            case 2 -> "EST";
            case 3 -> "REP";
            default -> null;
        };
    }

    private static void registarNovoUtilizador() throws IOException, InterruptedException {
        System.out.println("\n--- REGISTO DE NOVO UTILIZADOR ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        System.out.println("Selecione o tipo de utilizador:");
        System.out.println("1. Estudante");
        System.out.println("2. Coordenador");
        System.out.println("3. Representante de Empresa");
        System.out.print("Opção: ");
        int tipoOpcao = lerInteiro();
        scanner.nextLine();

        String tipo;
        String extraJson = "";

        switch (tipoOpcao) {
            case 1 -> {
                tipo = "ESTUDANTE";
                System.out.print("cursoId: ");
                String cursoId = scanner.nextLine();
                extraJson = """
                        ,
                        "cursoId": "%s"
                        """.formatted(cursoId);
            }
            case 2 -> {
                tipo = "COORDENADOR";
                System.out.print("departamentoId: ");
                String depId = scanner.nextLine();
                extraJson = """
                        ,
                        "departamentoId": "%s"
                        """.formatted(depId);
            }
            case 3 -> {
                tipo = "REPRESENTANTE";
                System.out.print("empresaId: ");
                String empId = scanner.nextLine();
                extraJson = """
                        ,
                        "empresaId": "%s"
                        """.formatted(empId);
            }
            default -> {
                System.out.println("Tipo inválido!");
                return;
            }
        }

        String json = """
                {
                  "nome": "%s",
                  "email": "%s",
                  "password": "%s",
                  "tipo": "%s"%s
                }
                """.formatted(nome, email, senha, tipo, extraJson);

        HttpResponse<String> resp = post("/api/auth/register", json);
        System.out.println("HTTP " + resp.statusCode());
        System.out.println(resp.body());
    }


    // =======================================================
    // MENU COORDENADOR (versão completa, baseada na 1ª componente)
    // =======================================================

    private static void menuCoordenador() {
        while (true) {
            System.out.println("\n===== MENU COORDENADOR =====");
            System.out.println("1. Criar nova oferta");
            System.out.println("2. Editar oferta");
            System.out.println("3. Eliminar oferta");
            System.out.println("4. Listar ofertas pendentes");
            System.out.println("5. Aprovar oferta");
            System.out.println("6. Rejeitar oferta");
            System.out.println("7. Listar todas as ofertas");
            System.out.println("8. Visualizar candidaturas por oferta");
            System.out.println("9. Aceitar/rejeitar candidatura");
            System.out.println("10. Registar curso");
            System.out.println("11. Listar estudantes de um curso");
            System.out.println("12. Menu de Candidaturas");
            System.out.println("13. Menu de Empresas");
            System.out.println("0. Logout");
            System.out.print("Escolha uma opção: ");

            int opcao = lerInteiro();
            scanner.nextLine();

            try {
                switch (opcao) {
                    case 1 -> criarOfertaREST();
                    case 2 -> editarOfertaREST();
                    case 3 -> eliminarOfertaREST();
                    case 4 -> get("/api/ofertas/status/PENDENTE");
                    case 5 -> workflowOferta("aprovar");
                    case 6 -> workflowOferta("rejeitar");
                    case 7 -> get("/api/ofertas");
                    case 8 -> listarCandidaturasPorOferta();
                    case 9 -> gerirCandidatura();
                    case 10 -> registarCursoREST();
                    case 11 -> listarEstudantesPorCursoREST();
                    case 12 -> menuCandidaturas();
                    case 13 -> menuEmpresas();
                    case 0 -> { return; }
                    default -> System.out.println("Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    // =======================================================
    // MENU ESTUDANTE
    // =======================================================

    private static void menuEstudante() {
        while (true) {
            System.out.println("\n===== MENU ESTUDANTE =====");
            System.out.println("1. Consultar ofertas disponíveis (APROVADAS)");
            System.out.println("2. Ver minhas candidaturas");
            System.out.println("3. Menu de Candidaturas");
            System.out.println("0. Logout");
            System.out.print("Escolha uma opção: ");

            int opcao = lerInteiro();
            scanner.nextLine();

            try {
                switch (opcao) {
                    case 1 -> consultarOfertasComFiltrosREST();
                    case 2 -> listarCandidaturasEstudanteREST();
                    case 3 -> menuCandidaturas();
                    case 0 -> { return; }
                    default -> System.out.println("Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    // =======================================================
    // MENU REPRESENTANTE
    // =======================================================

    private static void menuRepresentante() {
        while (true) {
            System.out.println("\n===== MENU REPRESENTANTE =====");
            System.out.println("1. Criar proposta de estágio");
            System.out.println("2. Consultar minhas propostas");
            System.out.println("3. Menu de Empresas");
            System.out.println("0. Logout");
            System.out.print("Escolha uma opção: ");

            int opcao = lerInteiro();
            scanner.nextLine();

            try {
                switch (opcao) {
                    case 1 -> criarPropostaREST();
                    case 2 -> listarPropostasRepresentanteREST();
                    case 3 -> menuEmpresas();
                    case 0 -> { return; }
                    default -> System.out.println("Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    // =======================================================
    // SUB-MENU CANDIDATURAS
    // =======================================================

    private static void menuCandidaturas() {
        while (true) {
            System.out.println("\n===== MENU CANDIDATURAS =====");
            System.out.println("1. Listar todas as candidaturas");
            System.out.println("2. Listar candidaturas por oferta");
            System.out.println("3. Listar candidaturas por estudante");
            System.out.println("4. Candidatar a uma oferta");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");

            int op = lerInteiro();
            scanner.nextLine();

            try {
                switch (op) {
                    case 1 -> get("/api/candidaturas");
                    case 2 -> listarCandidaturasPorOferta();
                    case 3 -> listarCandidaturasEstudanteREST();
                    case 4 -> criarCandidaturaREST();
                    case 0 -> { return; }
                    default -> System.out.println("Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    // =======================================================
    // SUB-MENU EMPRESAS
    // =======================================================

    private static void menuEmpresas() {
        while (true) {
            System.out.println("\n===== MENU EMPRESAS =====");
            System.out.println("1. Listar empresas");
            System.out.println("2. Criar empresa");
            System.out.println("3. Ativar empresa");
            System.out.println("4. Desativar empresa");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");

            int op = lerInteiro();
            scanner.nextLine();

            try {
                switch (op) {
                    case 1 -> get("/api/empresas");
                    case 2 -> criarEmpresaREST();
                    case 3 -> alterarEstadoEmpresaREST(true);
                    case 4 -> alterarEstadoEmpresaREST(false);
                    case 0 -> { return; }
                    default -> System.out.println("Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    // =======================================================
    // FUNCIONALIDADES COORDENADOR (REST)
    // =======================================================

    private static void criarOfertaREST() throws IOException, InterruptedException {
        System.out.println("\n--- CRIAR NOVA OFERTA ---");
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
        System.out.print("Tipo (CURRICULAR/EXTRA_CURRICULAR): ");
        String tipo = scanner.nextLine();
        System.out.print("Duração (meses): ");
        int duracao = lerInteiro();
        scanner.nextLine();
        System.out.print("Número de vagas: ");
        int vagas = lerInteiro();
        scanner.nextLine();
        System.out.print("EmpresaId: ");
        String empresaId = scanner.nextLine();

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
                post("/api/ofertas?empresaId=" + empresaId, json);
        System.out.println("HTTP " + resp.statusCode());
        System.out.println(resp.body());
    }

    private static void editarOfertaREST() throws IOException, InterruptedException {
        System.out.println("\n--- EDITAR OFERTA ---");
        System.out.print("ID da oferta: ");
        String id = scanner.nextLine();

        System.out.print("Novo título: ");
        String titulo = scanner.nextLine();
        System.out.print("Nova descrição: ");
        String descricao = scanner.nextLine();
        System.out.print("Tipo (CURRICULAR/EXTRA_CURRICULAR): ");
        String tipo = scanner.nextLine();
        System.out.print("Duração (meses): ");
        int duracao = lerInteiro();
        scanner.nextLine();
        System.out.print("Número de vagas: ");
        int vagas = lerInteiro();
        scanner.nextLine();
        System.out.print("EmpresaId: ");
        String empresaId = scanner.nextLine();

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
                put("/api/ofertas/" + id + "?empresaId=" + empresaId, json);
        System.out.println("HTTP " + resp.statusCode());
        System.out.println(resp.body());
    }

    private static void eliminarOfertaREST() throws IOException, InterruptedException {
        System.out.println("\n--- ELIMINAR OFERTA ---");
        System.out.print("ID da oferta: ");
        String id = scanner.nextLine();
        HttpResponse<String> resp = delete("/api/ofertas/" + id);
        System.out.println("HTTP " + resp.statusCode());
        System.out.println(resp.body());
    }

    private static void workflowOferta(String acao) throws IOException, InterruptedException {
        System.out.print("ID da oferta: ");
        String id = scanner.nextLine();
        HttpResponse<String> resp =
                post("/api/ofertas/" + id + "/" + acao, "");
        System.out.println("HTTP " + resp.statusCode());
        System.out.println(resp.body());
    }

    private static void listarCandidaturasPorOferta() throws IOException, InterruptedException {
        System.out.print("ID da oferta: ");
        String ofertaId = scanner.nextLine();
        HttpResponse<String> resp = get("/api/candidaturas/oferta/" + ofertaId);
        System.out.println("HTTP " + resp.statusCode());
        System.out.println(resp.body());
    }

    private static void gerirCandidatura() throws IOException, InterruptedException {
        System.out.print("ID da candidatura: ");
        String candId = scanner.nextLine();
        System.out.println("1. Aceitar");
        System.out.println("2. Rejeitar");
        System.out.print("Opção: ");
        int op = lerInteiro();
        scanner.nextLine();

        String acao = (op == 1) ? "aceitar" : "rejeitar";
        HttpResponse<String> resp =
                post("/api/candidaturas/" + candId + "/" + acao, "");
        System.out.println("HTTP " + resp.statusCode());
        System.out.println(resp.body());
    }

    private static void registarCursoREST() throws IOException, InterruptedException {
        System.out.println("\n--- REGISTAR NOVO CURSO ---");
        System.out.print("Nome do curso: ");
        String nome = scanner.nextLine();
        System.out.print("Código: ");
        String codigo = scanner.nextLine();
        System.out.print("Duração (anos): ");
        int duracao = lerInteiro();
        scanner.nextLine();
        System.out.print("Grau (Licenciatura/Mestrado/Doutoramento): ");
        String grau = scanner.nextLine();
        System.out.print("DepartamentoId: ");
        String departamentoId = scanner.nextLine();

        String json = """
                {
                  "nome": "%s",
                  "codigo": "%s",
                  "duracaoAnos": %d,
                  "grau": "%s"
                }
                """.formatted(nome, codigo, duracao, grau);

        HttpResponse<String> resp =
                post("/api/cursos?departamentoId=" + departamentoId, json);
        System.out.println("HTTP " + resp.statusCode());
        System.out.println(resp.body());
    }

    private static void listarEstudantesPorCursoREST() throws IOException, InterruptedException {
        System.out.print("ID do curso: ");
        String cursoId = scanner.nextLine();
        HttpResponse<String> resp = get("/api/estudantes/curso/" + cursoId);
        System.out.println("HTTP " + resp.statusCode());
        System.out.println(resp.body());
    }

    // =======================================================
    // FUNCIONALIDADES ESTUDANTE (REST)
    // =======================================================

    private static void consultarOfertasComFiltrosREST() throws IOException, InterruptedException {
        System.out.println("\n--- CONSULTAR OFERTAS (APROVADAS) ---");
        HttpResponse<String> resp = get("/api/ofertas/status/APROVADO");
        System.out.println("HTTP " + resp.statusCode());
        System.out.println(resp.body());
    }

    private static void listarCandidaturasEstudanteREST() throws IOException, InterruptedException {
        System.out.print("ID do estudante: ");
        String estId = scanner.nextLine();
        HttpResponse<String> resp = get("/api/candidaturas/estudante/" + estId);
        System.out.println("HTTP " + resp.statusCode());
        System.out.println(resp.body());
    }

    private static void criarCandidaturaREST() throws IOException, InterruptedException {
        System.out.println("\n--- CANDIDATAR A UMA OFERTA ---");
        System.out.print("ID do estudante: ");
        String estudanteId = scanner.nextLine();
        System.out.print("ID da oferta: ");
        String ofertaId = scanner.nextLine();
        System.out.print("Carta de motivação: ");
        String carta = scanner.nextLine();

        String json = """
                {
                  "cartaMotivacao": "%s"
                }
                """.formatted(carta);

        HttpResponse<String> resp =
                post("/api/candidaturas?estudanteId=" + estudanteId + "&ofertaId=" + ofertaId, json);
        System.out.println("HTTP " + resp.statusCode());
        System.out.println(resp.body());
    }

    // =======================================================
    // FUNCIONALIDADES REPRESENTANTE (REST)
    // =======================================================

    private static void criarPropostaREST() throws IOException, InterruptedException {
        System.out.println("\n--- CRIAR PROPOSTA DE ESTÁGIO ---");
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
        System.out.print("Requisitos: ");
        String requisitos = scanner.nextLine();
        System.out.print("Localização: ");
        String localizacao = scanner.nextLine();
        System.out.print("Duração (meses): ");
        int duracao = lerInteiro();
        scanner.nextLine();
        System.out.print("Vagas disponíveis: ");
        int vagas = lerInteiro();
        scanner.nextLine();
        System.out.print("Tipo (CURRICULAR/EXTRA_CURRICULAR): ");
        String tipo = scanner.nextLine();
        System.out.print("EmpresaId: ");
        String empresaId = scanner.nextLine();
        System.out.print("RepresentanteId: ");
        String representanteId = scanner.nextLine();

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

        HttpResponse<String> resp =
                post("/api/propostas?empresaId=" + empresaId + "&representanteId=" + representanteId, json);
        System.out.println("HTTP " + resp.statusCode());
        System.out.println(resp.body());
    }

    private static void listarPropostasRepresentanteREST() throws IOException, InterruptedException {
        System.out.print("ID do representante: ");
        String repId = scanner.nextLine();
        HttpResponse<String> resp = get("/api/propostas/representante/" + repId);
        System.out.println("HTTP " + resp.statusCode());
        System.out.println(resp.body());
    }

    // =======================================================
    // FUNCIONALIDADES EMPRESAS (REST)
    // =======================================================

    private static void criarEmpresaREST() throws IOException, InterruptedException {
        System.out.println("\n--- CRIAR EMPRESA ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("NIF: ");
        String nif = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Morada: ");
        String morada = scanner.nextLine();

        String json = """
                {
                  "nome": "%s",
                  "nif": "%s",
                  "email": "%s",
                  "morada": "%s",
                  "ativa": true
                }
                """.formatted(nome, nif, email, morada);

        HttpResponse<String> resp = post("/api/empresas", json);
        System.out.println("HTTP " + resp.statusCode());
        System.out.println(resp.body());
    }

    private static void alterarEstadoEmpresaREST(boolean ativar) throws IOException, InterruptedException {
        System.out.print("ID da empresa: ");
        String id = scanner.nextLine();
        String acao = ativar ? "ativar" : "desativar";
        HttpResponse<String> resp =
                post("/api/empresas/" + id + "/" + acao, "");
        System.out.println("HTTP " + resp.statusCode());
        System.out.println(resp.body());
    }

    // =======================================================
    // HELPERS HTTP
    // =======================================================

    private static HttpResponse<String> get(String path) throws IOException, InterruptedException {
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + path))
                .GET()
                .build();
        HttpResponse<String> resp = CLIENT.send(req, HttpResponse.BodyHandlers.ofString());
        return resp;
    }

    private static HttpResponse<String> post(String path, String jsonBody) throws IOException, InterruptedException {
        HttpRequest.Builder b = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + path))
                .header("Content-Type", "application/json");

        if (jsonBody != null && !jsonBody.isBlank()) {
            b = b.POST(HttpRequest.BodyPublishers.ofString(jsonBody));
        } else {
            b = b.POST(HttpRequest.BodyPublishers.noBody());
        }

        HttpRequest req = b.build();
        HttpResponse<String> resp = CLIENT.send(req, HttpResponse.BodyHandlers.ofString());
        return resp;
    }

    private static HttpResponse<String> put(String path, String jsonBody) throws IOException, InterruptedException {
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + path))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();
        HttpResponse<String> resp = CLIENT.send(req, HttpResponse.BodyHandlers.ofString());
        return resp;
    }

    private static HttpResponse<String> delete(String path) throws IOException, InterruptedException {
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + path))
                .DELETE()
                .build();
        HttpResponse<String> resp = CLIENT.send(req, HttpResponse.BodyHandlers.ofString());
        return resp;
    }

    // =======================================================
    // HELPERS DE LEITURA
    // =======================================================

    private static int lerInteiro() {
        while (!scanner.hasNextInt()) {
            System.out.print("Por favor, insira um número válido: ");
            scanner.next();
        }
        return scanner.nextInt();
    }
}
