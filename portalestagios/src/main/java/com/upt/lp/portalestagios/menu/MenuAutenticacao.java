package com.upt.lp.portalestagios.menu;

import com.upt.lp.portalestagios.dto.auth.LoginRequestDTO;
import com.upt.lp.portalestagios.dto.auth.RegisterRequestDTO;
import com.upt.lp.portalestagios.entity.Utilizador;
import com.upt.lp.portalestagios.service.AuthService;
import com.upt.lp.portalestagios.util.SessaoUtil;

import java.util.Scanner;

public class MenuAutenticacao {

    private final AuthService authService;

    public MenuAutenticacao(AuthService authService) {
        this.authService = authService;
    }

    public void mostrar() {

        Scanner sc = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n--- AUTENTICAÇÃO ---");
            System.out.println("1 - Login");
            System.out.println("2 - Registar");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> login(sc);
                case 2 -> registar(sc);
                case 0 -> System.out.println("A voltar...");
                default -> System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }

    private void login(Scanner sc) {
        System.out.println("\n--- LOGIN ---");

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        LoginRequestDTO dto = new LoginRequestDTO();
        dto.setEmail(email);
        dto.setPassword(password);

        Utilizador u = authService.login(dto);

        if (u != null) {
            SessaoUtil.setUtilizadorLogado(u);
            System.out.println("\n✔ Login efetuado com sucesso! Bem-vindo, " + u.getNome());
        } else {
            System.out.println("\n✘ Credenciais inválidas.");
        }
    }

    private void registar(Scanner sc) {

        System.out.println("=== Registo de Utilizador ===");
        System.out.println("Escolha o tipo de utilizador:");
        System.out.println("1 - Estudante");
        System.out.println("2 - Coordenador");
        System.out.println("3 - Representante de Empresa");
        System.out.print("Opção: ");

        int opcao = Integer.parseInt(sc.nextLine());

        RegisterRequestDTO dto = new RegisterRequestDTO();

        // ---- CAMPOS COMUNS ----
        System.out.print("Nome: ");
        dto.setNome(sc.nextLine());

        System.out.print("Email: ");
        dto.setEmail(sc.nextLine());

        System.out.print("Password: ");
        dto.setPassword(sc.nextLine());

        // ---- CAMPOS ESPECÍFICOS POR TIPO ----
        switch (opcao) {

            case 1: // --- ESTUDANTE ---
                dto.setRole("ESTUDANTE");

                System.out.print("Número de estudante: ");
                dto.setNumeroEstudante(sc.nextLine());

                System.out.print("Ano de matrícula: ");
                dto.setAnoMatricula(Integer.parseInt(sc.nextLine()));

                // MOSTRAR CURSOS
                var cursos = authService.listarCursos();
                if (cursos.isEmpty()) {
                    System.out.println("⚠ Não existem cursos na base de dados!");
                    return;
                }

                System.out.println("\n--- Cursos Disponíveis ---");
                for (int i = 0; i < cursos.size(); i++) {
                    System.out.println((i + 1) + " - " + cursos.get(i).getNome()
                            + " (" + cursos.get(i).getId() + ")");
                }

                System.out.print("Escolha o curso (1-" + cursos.size() + "): ");
                int escolhaCurso = Integer.parseInt(sc.nextLine());

                if (escolhaCurso < 1 || escolhaCurso > cursos.size()) {
                    System.out.println("Opção inválida!");
                    return;
                }

                dto.setCursoId(cursos.get(escolhaCurso - 1).getId().toString());
                break;

            case 2: // --- COORDENADOR ---
                dto.setRole("COORDENADOR");

                // MOSTRAR DEPARTAMENTOS
                var deps = authService.listarDepartamentos();
                if (deps.isEmpty()) {
                    System.out.println("⚠ Não existem departamentos na base de dados!");
                    return;
                }

                System.out.println("\n--- Departamentos Disponíveis ---");
                for (int i = 0; i < deps.size(); i++) {
                    System.out.println((i + 1) + " - " + deps.get(i).getNome()
                            + " (" + deps.get(i).getId() + ")");
                }

                System.out.print("Escolha o departamento (1-" + deps.size() + "): ");
                int escolhaDep = Integer.parseInt(sc.nextLine());

                if (escolhaDep < 1 || escolhaDep > deps.size()) {
                    System.out.println("Opção inválida!");
                    return;
                }

                dto.setDepartamentoId(deps.get(escolhaDep - 1).getId().toString());
                break;


            case 3: // REPRESENTANTE EMPRESA
                dto.setRole("REPRESENTANTE");

                System.out.print("Cargo na Empresa: ");
                dto.setCargo(sc.nextLine());

                System.out.print("A empresa já existe? (s/n): ");
                String existe = sc.nextLine().trim().toLowerCase();

                if (existe.equals("s")) {

                    var empresas = authService.listarEmpresas();

                    System.out.println("\n--- Empresas Disponíveis ---");
                    for (int i = 0; i < empresas.size(); i++) {
                        System.out.println((i + 1) + " - " + empresas.get(i).getNome()
                                + " (" + empresas.get(i).getId() + ")");
                    }

                    System.out.print("Escolha a empresa (1-" + empresas.size() + "): ");
                    int escolhaEmpresa = Integer.parseInt(sc.nextLine());

                    dto.setEmpresaId(empresas.get(escolhaEmpresa - 1).getId().toString());

                } else {
                    System.out.println("=== Nova Empresa ===");
                    System.out.print("Nome: ");
                    dto.setEmpresaNome(sc.nextLine());
                    System.out.print("NIF: ");
                    dto.setEmpresaNif(sc.nextLine());
                    System.out.print("Email: ");
                    dto.setEmpresaEmail(sc.nextLine());
                    System.out.print("Morada: ");
                    dto.setEmpresaMorada(sc.nextLine());
                }
                break;

            default:
                System.out.println("Opção inválida!");
                return;
        }

        // chamar a API / serviço
        try {
            authService.register(dto);
            System.out.println("✔ Utilizador registado com sucesso!");
        } catch (Exception ex) {
            System.out.println("✘ Erro ao registar: " + ex.getMessage());
        }
    }

}
