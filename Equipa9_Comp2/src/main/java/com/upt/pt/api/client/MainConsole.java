package com.upt.pt.api.client;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class MainConsole {

    private static final Scanner SC = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("  PORTAL DE GESTÃO DE ESTÁGIOS - UPT");
        System.out.println("========================================\n");

        while (true) {
            String perfil = iniciarSessaoOuRegistrar();
            if (perfil == null) {
                System.out.println("Falha no login/registo.\n");
                continue;
            }

            switch (perfil) {
                case "COORD" -> new CoordenadorMenuClient(SC).run();
                case "EST"   -> new EstudanteMenuClient(SC).run();
                case "REP"   -> new RepresentanteEmpresaMenuClient(SC).run();
                default      -> System.out.println("Perfil desconhecido.\n");
            }
        }
    }

    // ==============================
    // LOGIN / REGISTO
    // ==============================

    private static String iniciarSessaoOuRegistrar() {
        while (true) {
            System.out.println("=== BEM VINDO AO PORTAL DE ESTÁGIOS ===");
            System.out.println("1. Já tenho conta (Login)");
            System.out.println("2. Não tenho conta (Registrar)");
            System.out.println("3. Sair do Portal");
            System.out.print("Escolha uma opção: ");

            int opcao = lerInteiro(SC);
            SC.nextLine();

            try {
                switch (opcao) {
                    case 1 -> { return login(); }
                    case 2 -> { registarNovoUtilizador(); }
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

    // chama /api/auth/login e usa a senha
    private static String login() throws IOException, InterruptedException {
        System.out.println("\n=== LOGIN ===");
        System.out.print("Email: ");
        String email = SC.nextLine();
        System.out.print("Senha: ");
        String senha = SC.nextLine();

        String json = """
                {
                  "email": "%s",
                  "password": "%s"
                }
                """.formatted(email, senha);
        
        System.out.println("JSON LOGIN -> " + json);

        HttpResponse<String> resp = RestClientHelper.post("/api/auth/login", json);
        System.out.println("HTTP " + resp.statusCode());
        System.out.println(resp.body());

        if (resp.statusCode() != 200) {
            System.out.println("Credenciais inválidas.\n");
            return null;
        }

        String body = resp.body();
        String tipo = extrairCampoTipo(body);

        if (tipo == null) {
            System.out.println("Resposta de login sem campo 'tipo'.\n");
            return null;
        }

        return switch (tipo) {
            case "COORDENADOR"   -> "COORD";
            case "ESTUDANTE"     -> "EST";
            case "REPRESENTANTE" -> "REP";
            default -> null;
        };
    }

    // chama /api/auth/register com todos os campos do RegistroDTO
    private static void registarNovoUtilizador() throws IOException, InterruptedException {
        System.out.println("\n--- REGISTO DE NOVO UTILIZADOR ---");
        System.out.print("Nome: ");
        String nome = SC.nextLine();
        System.out.print("Email: ");
        String email = SC.nextLine();
        System.out.print("Senha: ");
        String senha = SC.nextLine();

        System.out.println("Selecione o tipo de utilizador:");
        System.out.println("1. Estudante");
        System.out.println("2. Coordenador");
        System.out.println("3. Representante de Empresa");
        System.out.print("Opção: ");
        int tipoOpcao = lerInteiro(SC);
        SC.nextLine();

        String tipo;
        String extraJson = "";

        switch (tipoOpcao) {
            case 1 -> {
                tipo = "ESTUDANTE";
                System.out.print("cursoId: ");
                String cursoId = SC.nextLine();
                System.out.print("Número de estudante (5 dígitos): ");
                String numero = SC.nextLine();
                System.out.print("Ano de matrícula: ");
                int ano = lerInteiro(SC);
                SC.nextLine();

                extraJson = """
                        ,
                        "cursoId": "%s",
                        "numeroEstudante": "%s",
                        "anoMatricula": %d
                        """.formatted(cursoId, numero, ano);
            }
            case 2 -> {
                tipo = "COORDENADOR";
                System.out.print("departamentoId: ");
                String depId = SC.nextLine();
                extraJson = """
                        ,
                        "departamentoId": "%s"
                        """.formatted(depId);
            }
            case 3 -> {
                tipo = "REPRESENTANTE";
                System.out.print("empresaId: ");
                String empId = SC.nextLine();
                System.out.print("Cargo: ");
                String cargo = SC.nextLine();
                System.out.print("Telefone (opcional): ");
                String telefone = SC.nextLine();

                extraJson = """
                        ,
                        "empresaId": "%s",
                        "cargo": "%s",
                        "telefone": "%s"
                        """.formatted(empId, cargo, telefone);
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

        HttpResponse<String> resp = RestClientHelper.post("/api/auth/register", json);
        System.out.println("HTTP " + resp.statusCode());
        System.out.println(resp.body());
        System.out.println();
    }

    // ==============================
    // HELPERS
    // ==============================

    static int lerInteiro(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.print("Por favor, insira um número válido: ");
            sc.next();
        }
        return sc.nextInt();
    }

    // parse simples ao JSON para apanhar o campo "tipo"
    private static String extrairCampoTipo(String json) {
        int idx = json.indexOf("\"tipo\"");
        if (idx == -1) return null;
        int colon = json.indexOf(":", idx);
        int firstQuote = json.indexOf("\"", colon + 1);
        int secondQuote = json.indexOf("\"", firstQuote + 1);
        if (firstQuote == -1 || secondQuote == -1) return null;
        return json.substring(firstQuote + 1, secondQuote);
    }
}
