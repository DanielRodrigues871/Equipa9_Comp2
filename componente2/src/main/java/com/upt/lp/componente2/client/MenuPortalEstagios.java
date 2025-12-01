package com.upt.lp.componente2.client;

import com.upt.lp.componente2.dto.*;
import com.upt.lp.componente2.enums.*;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

/**
 * Classe de interface do utilizador com menus interativos
 * Adaptada para trabalhar com a API REST
 */
public class MenuPortalEstagios {
    
    private static final String BASE_URL = "http://localhost:8080/api";
    private final RestTemplate restTemplate;
    private final Scanner scanner;
    
    public MenuPortalEstagios() {
        this.restTemplate = new RestTemplate();
        this.scanner = new Scanner(System.in);
    }
    
    public void iniciar() {
        System.out.println("========================================");
        System.out.println("  PORTAL DE GESTÃO DE ESTÁGIOS - UPT");
        System.out.println("========================================\n");
        
        // Menu principal sem autenticação
        while (true) {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Menu de Estudantes");
            System.out.println("2. Menu de Coordenadores");
            System.out.println("3. Menu de Representantes de Empresa");
            System.out.println("4. Menu de Empresas");
            System.out.println("5. Menu de Ofertas de Estágio");
            System.out.println("6. Menu de Candidaturas");
            System.out.println("7. Menu de Cursos");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            
            int opcao = lerInteiro();
            scanner.nextLine();
            
            switch (opcao) {
                case 1:
                    menuEstudantes();
                    break;
                case 2:
                    menuCoordenadores();
                    break;
                case 3:
                    menuRepresentantes();
                    break;
                case 4:
                    menuEmpresas();
                    break;
                case 5:
                    menuOfertas();
                    break;
                case 6:
                    menuCandidaturas();
                    break;
                case 7:
                    menuCursos();
                    break;
                case 0:
                    System.out.println("\nObrigado por utilizar o Portal de Estágios UPT!");
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
    
    // ============ MENU ESTUDANTES ============
    
    private void menuEstudantes() {
        while (true) {
            System.out.println("\n===== MENU ESTUDANTES =====");
            System.out.println("1. Listar todos os estudantes");
            System.out.println("2. Criar novo estudante");
            System.out.println("3. Editar estudante");
            System.out.println("4. Eliminar estudante");
            System.out.println("5. Ver detalhes de estudante");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            
            int opcao = lerInteiro();
            scanner.nextLine();
            
            switch (opcao) {
                case 1:
                    listarEstudantes();
                    break;
                case 2:
                    criarEstudante();
                    break;
                case 3:
                    editarEstudante();
                    break;
                case 4:
                    eliminarEstudante();
                    break;
                case 5:
                    verDetalhesEstudante();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
    
    private void listarEstudantes() {
        try {
            ResponseEntity<EstudanteDTO[]> response = restTemplate.getForEntity(
                BASE_URL + "/estudantes", 
                EstudanteDTO[].class
            );
            
            EstudanteDTO[] estudantes = response.getBody();
            if (estudantes == null || estudantes.length == 0) {
                System.out.println("Não existem estudantes registados.");
                return;
            }
            
            System.out.println("\n=== LISTA DE ESTUDANTES ===");
            System.out.println("Total: " + estudantes.length + " estudantes");
            System.out.println("--------------------------------------------------");
            
            for (int i = 0; i < estudantes.length; i++) {
                EstudanteDTO e = estudantes[i];
                System.out.printf("%d. %s (Nº: %s)%n", i + 1, e.getNome(), e.getNumeroEstudante());
                System.out.println("   Email: " + e.getEmail());
                System.out.println("   Curso: " + (e.getCursoNome() != null ? e.getCursoNome() : "N/A"));
                System.out.println("   Ano: " + e.getAnoMatricula());
                System.out.println("--------------------------------------------------");
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar estudantes: " + e.getMessage());
        }
    }
    
    private void criarEstudante() {
        System.out.println("\n--- CRIAR NOVO ESTUDANTE ---");
        
        try {
            EstudanteDTO estudanteDTO = new EstudanteDTO();
            
            System.out.print("Nome: ");
            estudanteDTO.setNome(scanner.nextLine());
            
            System.out.print("Email: ");
            estudanteDTO.setEmail(scanner.nextLine());
            
            System.out.print("Número de estudante: ");
            estudanteDTO.setNumeroEstudante(scanner.nextLine());
            
            System.out.print("Ano de matrícula: ");
            estudanteDTO.setAnoMatricula(lerInteiro());
            scanner.nextLine();
            
            System.out.print("Média (opcional, 0 para omitir): ");
            double media = lerDouble();
            scanner.nextLine();
            if (media > 0) {
                estudanteDTO.setMedia(media);
            }
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<EstudanteDTO> request = new HttpEntity<>(estudanteDTO, headers);
            
            ResponseEntity<EstudanteDTO> response = restTemplate.postForEntity(
                BASE_URL + "/estudantes",
                request,
                EstudanteDTO.class
            );
            
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                System.out.println("\n✓ Estudante criado com sucesso!");
                System.out.println("ID: " + response.getBody().getId());
            } else {
                System.out.println("\n✗ Erro ao criar estudante.");
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    
    // ============ MENU COORDENADORES ============
    
    private void menuCoordenadores() {
        while (true) {
            System.out.println("\n===== MENU COORDENADORES =====");
            System.out.println("1. Listar todos os coordenadores");
            System.out.println("2. Criar novo coordenador");
            System.out.println("3. Ver detalhes de coordenador");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            
            int opcao = lerInteiro();
            scanner.nextLine();
            
            switch (opcao) {
                case 1:
                    listarCoordenadores();
                    break;
                case 2:
                    criarCoordenador();
                    break;
                case 3:
                    verDetalhesCoordenador();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
    
    private void listarCoordenadores() {
        try {
            ResponseEntity<CoordenadorDTO[]> response = restTemplate.getForEntity(
                BASE_URL + "/coordenadores", 
                CoordenadorDTO[].class
            );
            
            CoordenadorDTO[] coordenadores = response.getBody();
            if (coordenadores == null || coordenadores.length == 0) {
                System.out.println("Não existem coordenadores registados.");
                return;
            }
            
            System.out.println("\n=== LISTA DE COORDENADORES ===");
            for (int i = 0; i < coordenadores.length; i++) {
                CoordenadorDTO c = coordenadores[i];
                System.out.printf("%d. %s%n", i + 1, c.getNome());
                System.out.println("   Email: " + c.getEmail());
                System.out.println("   Departamento: " + (c.getDepartamentoNome() != null ? c.getDepartamentoNome() : "N/A"));
                System.out.println("--------------------------------------------------");
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar coordenadores: " + e.getMessage());
        }
    }
    
    // ============ MENU REPRESENTANTES ============
    
    private void menuRepresentantes() {
        while (true) {
            System.out.println("\n===== MENU REPRESENTANTES =====");
            System.out.println("1. Listar todos os representantes");
            System.out.println("2. Criar novo representante");
            System.out.println("3. Ver detalhes de representante");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            
            int opcao = lerInteiro();
            scanner.nextLine();
            
            switch (opcao) {
                case 1:
                    listarRepresentantes();
                    break;
                case 2:
                    criarRepresentante();
                    break;
                case 3:
                    verDetalhesRepresentante();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
    
    // ============ MENU EMPRESAS ============
    
    private void menuEmpresas() {
        while (true) {
            System.out.println("\n===== GESTÃO DE EMPRESAS =====");
            System.out.println("1. Listar empresas");
            System.out.println("2. Criar nova empresa");
            System.out.println("3. Editar empresa");
            System.out.println("4. Eliminar empresa");
            System.out.println("5. Ver detalhes de empresa");
            System.out.println("6. Listar ofertas de uma empresa");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            
            int opcao = lerInteiro();
            scanner.nextLine();
            
            switch (opcao) {
                case 1:
                    listarEmpresas();
                    break;
                case 2:
                    criarEmpresa();
                    break;
                case 3:
                    editarEmpresa();
                    break;
                case 4:
                    eliminarEmpresa();
                    break;
                case 5:
                    verDetalhesEmpresa();
                    break;
                case 6:
                    listarOfertasPorEmpresa();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
    
    private void listarEmpresas() {
        try {
            ResponseEntity<EmpresaDTO[]> response = restTemplate.getForEntity(
                BASE_URL + "/empresas", 
                EmpresaDTO[].class
            );
            
            EmpresaDTO[] empresas = response.getBody();
            if (empresas == null || empresas.length == 0) {
                System.out.println("Não existem empresas registadas.");
                return;
            }
            
            System.out.println("\n=== LISTA DE EMPRESAS ===");
            System.out.println("Total: " + empresas.length + " empresas");
            System.out.println("--------------------------------------------------");
            
            for (int i = 0; i < empresas.length; i++) {
                EmpresaDTO e = empresas[i];
                System.out.printf("%d. %s%n", i + 1, e.getNome());
                System.out.println("   NIF: " + e.getNif());
                System.out.println("   Email: " + e.getEmail());
                System.out.println("   Telefone: " + (e.getTelefone() != null ? e.getTelefone() : "N/A"));
                System.out.println("   Ativa: " + (e.isAtiva() ? "Sim" : "Não"));
                System.out.println("--------------------------------------------------");
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar empresas: " + e.getMessage());
        }
    }
    
    private void criarEmpresa() {
        System.out.println("\n--- CRIAR NOVA EMPRESA ---");
        
        try {
            EmpresaDTO empresaDTO = new EmpresaDTO();
            
            System.out.print("Nome: ");
            empresaDTO.setNome(scanner.nextLine());
            
            System.out.print("NIF: ");
            empresaDTO.setNif(scanner.nextLine());
            
            System.out.print("Email: ");
            empresaDTO.setEmail(scanner.nextLine());
            
            System.out.print("Morada: ");
            empresaDTO.setMorada(scanner.nextLine());
            
            System.out.print("Telefone (opcional): ");
            String telefone = scanner.nextLine();
            if (!telefone.isEmpty()) {
                empresaDTO.setTelefone(telefone);
            }
            
            System.out.print("Website (opcional): ");
            String website = scanner.nextLine();
            if (!website.isEmpty()) {
                empresaDTO.setWebsite(website);
            }
            
            System.out.print("Descrição (opcional): ");
            String descricao = scanner.nextLine();
            if (!descricao.isEmpty()) {
                empresaDTO.setDescricao(descricao);
            }
            
            empresaDTO.setAtiva(true);
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<EmpresaDTO> request = new HttpEntity<>(empresaDTO, headers);
            
            ResponseEntity<EmpresaDTO> response = restTemplate.postForEntity(
                BASE_URL + "/empresas",
                request,
                EmpresaDTO.class
            );
            
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                System.out.println("\n✓ Empresa criada com sucesso!");
                System.out.println("ID: " + response.getBody().getId());
            } else {
                System.out.println("\n✗ Erro ao criar empresa.");
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    
    private void editarEmpresa() {
        try {
            // Primeiro listar empresas para escolher
            ResponseEntity<EmpresaDTO[]> response = restTemplate.getForEntity(
                BASE_URL + "/empresas", 
                EmpresaDTO[].class
            );
            
            EmpresaDTO[] empresas = response.getBody();
            if (empresas == null || empresas.length == 0) {
                System.out.println("Não existem empresas registadas.");
                return;
            }
            
            System.out.println("\nSelecione a empresa para editar:");
            for (int i = 0; i < empresas.length; i++) {
                System.out.printf("%d. %s%n", i + 1, empresas[i].getNome());
            }
            
            System.out.print("Escolha o número da empresa: ");
            int escolha = lerInteiro() - 1;
            scanner.nextLine();
            
            if (escolha < 0 || escolha >= empresas.length) {
                System.out.println("Escolha inválida!");
                return;
            }
            
            EmpresaDTO empresa = empresas[escolha];
            
            System.out.println("\n--- EDITAR EMPRESA ---");
            System.out.println("Deixe em branco para manter o valor atual.");
            
            System.out.print("Novo nome [" + empresa.getNome() + "]: ");
            String novoNome = scanner.nextLine();
            if (!novoNome.isEmpty()) {
                empresa.setNome(novoNome);
            }
            
            System.out.print("Novo email [" + empresa.getEmail() + "]: ");
            String novoEmail = scanner.nextLine();
            if (!novoEmail.isEmpty()) {
                empresa.setEmail(novoEmail);
            }
            
            System.out.print("Nova morada [" + empresa.getMorada() + "]: ");
            String novaMorada = scanner.nextLine();
            if (!novaMorada.isEmpty()) {
                empresa.setMorada(novaMorada);
            }
            
            System.out.print("Novo telefone [" + (empresa.getTelefone() != null ? empresa.getTelefone() : "") + "]: ");
            String novoTelefone = scanner.nextLine();
            if (!novoTelefone.isEmpty()) {
                empresa.setTelefone(novoTelefone);
            }
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<EmpresaDTO> request = new HttpEntity<>(empresa, headers);
            
            ResponseEntity<EmpresaDTO> updateResponse = restTemplate.exchange(
                BASE_URL + "/empresas/" + empresa.getId(),
                HttpMethod.PUT,
                request,
                EmpresaDTO.class
            );
            
            if (updateResponse.getStatusCode().is2xxSuccessful()) {
                System.out.println("\n✓ Empresa atualizada com sucesso!");
            } else {
                System.out.println("\n✗ Erro ao atualizar empresa.");
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    
    private void eliminarEmpresa() {
        try {
            // Primeiro listar empresas para escolher
            ResponseEntity<EmpresaDTO[]> response = restTemplate.getForEntity(
                BASE_URL + "/empresas", 
                EmpresaDTO[].class
            );
            
            EmpresaDTO[] empresas = response.getBody();
            if (empresas == null || empresas.length == 0) {
                System.out.println("Não existem empresas registadas.");
                return;
            }
            
            System.out.println("\nSelecione a empresa para eliminar:");
            for (int i = 0; i < empresas.length; i++) {
                System.out.printf("%d. %s%n", i + 1, empresas[i].getNome());
            }
            
            System.out.print("Escolha o número da empresa: ");
            int escolha = lerInteiro() - 1;
            scanner.nextLine();
            
            if (escolha < 0 || escolha >= empresas.length) {
                System.out.println("Escolha inválida!");
                return;
            }
            
            EmpresaDTO empresa = empresas[escolha];
            
            System.out.print("Tem a certeza que deseja eliminar a empresa '" + empresa.getNome() + "'? (S/N): ");
            String confirmacao = scanner.nextLine();
            
            if (confirmacao.equalsIgnoreCase("S")) {
                restTemplate.delete(BASE_URL + "/empresas/" + empresa.getId());
                System.out.println("\n✓ Empresa eliminada com sucesso!");
            } else {
                System.out.println("Operação cancelada.");
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    
    // ============ MENU OFERTAS ============
    
    private void menuOfertas() {
        while (true) {
            System.out.println("\n===== GESTÃO DE OFERTAS =====");
            System.out.println("1. Listar todas as ofertas");
            System.out.println("2. Listar ofertas por status");
            System.out.println("3. Criar nova oferta");
            System.out.println("4. Editar oferta");
            System.out.println("5. Eliminar oferta");
            System.out.println("6. Ver detalhes de oferta");
            System.out.println("7. Aprovar oferta");
            System.out.println("8. Rejeitar oferta");
            System.out.println("9. Listar candidaturas de oferta");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            
            int opcao = lerInteiro();
            scanner.nextLine();
            
            switch (opcao) {
                case 1:
                    listarOfertas();
                    break;
                case 2:
                    listarOfertasPorStatus();
                    break;
                case 3:
                    criarOferta();
                    break;
                case 4:
                    editarOferta();
                    break;
                case 5:
                    eliminarOferta();
                    break;
                case 6:
                    verDetalhesOferta();
                    break;
                case 7:
                    aprovarOferta();
                    break;
                case 8:
                    rejeitarOferta();
                    break;
                case 9:
                    listarCandidaturasOferta();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
    
    private void listarOfertas() {
        try {
            ResponseEntity<OfertaEstagioDTO[]> response = restTemplate.getForEntity(
                BASE_URL + "/ofertas", 
                OfertaEstagioDTO[].class
            );
            
            OfertaEstagioDTO[] ofertas = response.getBody();
            if (ofertas == null || ofertas.length == 0) {
                System.out.println("Não existem ofertas registadas.");
                return;
            }
            
            System.out.println("\n=== LISTA DE OFERTAS ===");
            System.out.println("Total: " + ofertas.length + " ofertas");
            System.out.println("--------------------------------------------------");
            
            for (int i = 0; i < ofertas.length; i++) {
                OfertaEstagioDTO o = ofertas[i];
                System.out.printf("%d. %s%n", i + 1, o.getTitulo());
                System.out.println("   Empresa: " + o.getEmpresaNome());
                System.out.println("   Tipo: " + o.getTipo());
                System.out.println("   Status: " + o.getStatus());
                System.out.println("   Vagas: " + o.getNumeroVagas());
                System.out.println("   Duração: " + o.getDuracaoMeses() + " meses");
                System.out.println("--------------------------------------------------");
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar ofertas: " + e.getMessage());
        }
    }
    
    private void criarOferta() {
        System.out.println("\n--- CRIAR NOVA OFERTA ---");
        
        try {
            // Primeiro listar empresas para escolher
            ResponseEntity<EmpresaDTO[]> empresasResponse = restTemplate.getForEntity(
                BASE_URL + "/empresas", 
                EmpresaDTO[].class
            );
            
            EmpresaDTO[] empresas = empresasResponse.getBody();
            if (empresas == null || empresas.length == 0) {
                System.out.println("Não existem empresas registadas. Crie uma empresa primeiro.");
                return;
            }
            
            OfertaEstagioDTO ofertaDTO = new OfertaEstagioDTO();
            
            System.out.print("Título: ");
            ofertaDTO.setTitulo(scanner.nextLine());
            
            System.out.print("Descrição: ");
            ofertaDTO.setDescricao(scanner.nextLine());
            
            System.out.println("\nSelecione a empresa:");
            for (int i = 0; i < empresas.length; i++) {
                System.out.printf("%d. %s%n", i + 1, empresas[i].getNome());
            }
            System.out.print("Escolha o número da empresa: ");
            int empresaEscolha = lerInteiro() - 1;
            scanner.nextLine();
            
            if (empresaEscolha < 0 || empresaEscolha >= empresas.length) {
                System.out.println("Escolha inválida!");
                return;
            }
            ofertaDTO.setEmpresaId(empresas[empresaEscolha].getId());
            
            System.out.println("\nTipo de estágio:");
            System.out.println("1. CURRICULAR");
            System.out.println("2. EXTRACURRICULAR");
            System.out.print("Escolha: ");
            int tipoOpcao = lerInteiro();
            scanner.nextLine();
            ofertaDTO.setTipo(tipoOpcao == 1 ? TipoEstagio.CURRICULAR : TipoEstagio.EXTRA_CURRICULAR);
            
            System.out.print("Localização: ");
            ofertaDTO.setLocalizacao(scanner.nextLine());
            
            System.out.print("Duração (meses): ");
            ofertaDTO.setDuracaoMeses(lerInteiro());
            scanner.nextLine();
            
            System.out.print("Requisitos: ");
            ofertaDTO.setRequisitos(scanner.nextLine());
            
            System.out.print("Número de vagas: ");
            ofertaDTO.setNumeroVagas(lerInteiro());
            scanner.nextLine();
            
            // Definir status inicial como PENDENTE
            ofertaDTO.setStatus(StatusOferta.PENDENTE);
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<OfertaEstagioDTO> request = new HttpEntity<>(ofertaDTO, headers);
            
            ResponseEntity<OfertaEstagioDTO> response = restTemplate.postForEntity(
                BASE_URL + "/ofertas",
                request,
                OfertaEstagioDTO.class
            );
            
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                System.out.println("\n✓ Oferta criada com sucesso!");
                System.out.println("ID: " + response.getBody().getId());
                System.out.println("Status: " + response.getBody().getStatus());
            } else {
                System.out.println("\n✗ Erro ao criar oferta.");
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    
    private void aprovarOferta() {
        try {
            // Listar ofertas pendentes
            ResponseEntity<OfertaEstagioDTO[]> response = restTemplate.getForEntity(
                BASE_URL + "/ofertas/pendentes", 
                OfertaEstagioDTO[].class
            );
            
            OfertaEstagioDTO[] ofertas = response.getBody();
            if (ofertas == null || ofertas.length == 0) {
                System.out.println("Não existem ofertas pendentes.");
                return;
            }
            
            System.out.println("\n=== OFERTAS PENDENTES ===");
            for (int i = 0; i < ofertas.length; i++) {
                System.out.printf("%d. %s (Empresa: %s)%n", 
                    i + 1, ofertas[i].getTitulo(), ofertas[i].getEmpresaNome());
            }
            
            System.out.print("Selecione a oferta para aprovar: ");
            int escolha = lerInteiro() - 1;
            scanner.nextLine();
            
            if (escolha < 0 || escolha >= ofertas.length) {
                System.out.println("Escolha inválida!");
                return;
            }
            
            String ofertaId = ofertas[escolha].getId();
            
            ResponseEntity<OfertaEstagioDTO> approveResponse = restTemplate.exchange(
                BASE_URL + "/ofertas/" + ofertaId + "/aprovar",
                HttpMethod.PUT,
                null,
                OfertaEstagioDTO.class
            );
            
            if (approveResponse.getStatusCode().is2xxSuccessful()) {
                System.out.println("\n✓ Oferta aprovada com sucesso!");
            } else {
                System.out.println("\n✗ Erro ao aprovar oferta.");
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    
    private void rejeitarOferta() {
        try {
            // Listar ofertas pendentes
            ResponseEntity<OfertaEstagioDTO[]> response = restTemplate.getForEntity(
                BASE_URL + "/ofertas/pendentes", 
                OfertaEstagioDTO[].class
            );
            
            OfertaEstagioDTO[] ofertas = response.getBody();
            if (ofertas == null || ofertas.length == 0) {
                System.out.println("Não existem ofertas pendentes.");
                return;
            }
            
            System.out.println("\n=== OFERTAS PENDENTES ===");
            for (int i = 0; i < ofertas.length; i++) {
                System.out.printf("%d. %s (Empresa: %s)%n", 
                    i + 1, ofertas[i].getTitulo(), ofertas[i].getEmpresaNome());
            }
            
            System.out.print("Selecione a oferta para rejeitar: ");
            int escolha = lerInteiro() - 1;
            scanner.nextLine();
            
            if (escolha < 0 || escolha >= ofertas.length) {
                System.out.println("Escolha inválida!");
                return;
            }
            
            String ofertaId = ofertas[escolha].getId();
            
            ResponseEntity<OfertaEstagioDTO> rejectResponse = restTemplate.exchange(
                BASE_URL + "/ofertas/" + ofertaId + "/rejeitar",
                HttpMethod.PUT,
                null,
                OfertaEstagioDTO.class
            );
            
            if (rejectResponse.getStatusCode().is2xxSuccessful()) {
                System.out.println("\n✓ Oferta rejeitada com sucesso!");
            } else {
                System.out.println("\n✗ Erro ao rejeitar oferta.");
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    
    // ============ MENU CANDIDATURAS ============
    
    private void menuCandidaturas() {
        while (true) {
            System.out.println("\n===== GESTÃO DE CANDIDATURAS =====");
            System.out.println("1. Listar todas as candidaturas");
            System.out.println("2. Listar candidaturas por status");
            System.out.println("3. Criar nova candidatura");
            System.out.println("4. Ver detalhes de candidatura");
            System.out.println("5. Aceitar candidatura");
            System.out.println("6. Rejeitar candidatura");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            
            int opcao = lerInteiro();
            scanner.nextLine();
            
            switch (opcao) {
                case 1:
                    listarCandidaturas();
                    break;
                case 2:
                    listarCandidaturasPorStatus();
                    break;
                case 3:
                    criarCandidatura();
                    break;
                case 4:
                    verDetalhesCandidatura();
                    break;
                case 5:
                    aceitarCandidatura();
                    break;
                case 6:
                    rejeitarCandidatura();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
    
    private void listarCandidaturas() {
        try {
            ResponseEntity<CandidaturaDTO[]> response = restTemplate.getForEntity(
                BASE_URL + "/candidaturas", 
                CandidaturaDTO[].class
            );
            
            CandidaturaDTO[] candidaturas = response.getBody();
            if (candidaturas == null || candidaturas.length == 0) {
                System.out.println("Não existem candidaturas.");
                return;
            }
            
            System.out.println("\n=== LISTA DE CANDIDATURAS ===");
            System.out.println("Total: " + candidaturas.length + " candidaturas");
            System.out.println("--------------------------------------------------");
            
            for (int i = 0; i < candidaturas.length; i++) {
                CandidaturaDTO c = candidaturas[i];
                System.out.printf("%d. %s -> %s%n", 
                    i + 1, c.getEstudanteNome(), c.getOfertaTitulo());
                System.out.println("   Status: " + c.getStatus());
                System.out.println("   Data: " + c.getDataSubmissao());
                System.out.println("--------------------------------------------------");
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar candidaturas: " + e.getMessage());
        }
    }
    
    private void criarCandidatura() {
        System.out.println("\n--- CRIAR NOVA CANDIDATURA ---");
        
        try {
            // Listar estudantes
            ResponseEntity<EstudanteDTO[]> estudantesResponse = restTemplate.getForEntity(
                BASE_URL + "/estudantes", 
                EstudanteDTO[].class
            );
            
            EstudanteDTO[] estudantes = estudantesResponse.getBody();
            if (estudantes == null || estudantes.length == 0) {
                System.out.println("Não existem estudantes registados.");
                return;
            }
            
            // Listar ofertas disponíveis
            ResponseEntity<OfertaEstagioDTO[]> ofertasResponse = restTemplate.getForEntity(
                BASE_URL + "/ofertas/disponiveis", 
                OfertaEstagioDTO[].class
            );
            
            OfertaEstagioDTO[] ofertas = ofertasResponse.getBody();
            if (ofertas == null || ofertas.length == 0) {
                System.out.println("Não existem ofertas disponíveis.");
                return;
            }
            
            CandidaturaDTO candidaturaDTO = new CandidaturaDTO();
            
            System.out.println("\nSelecione o estudante:");
            for (int i = 0; i < estudantes.length; i++) {
                System.out.printf("%d. %s (Nº: %s)%n", 
                    i + 1, estudantes[i].getNome(), estudantes[i].getNumeroEstudante());
            }
            System.out.print("Escolha o número do estudante: ");
            int estudanteEscolha = lerInteiro() - 1;
            scanner.nextLine();
            
            if (estudanteEscolha < 0 || estudanteEscolha >= estudantes.length) {
                System.out.println("Escolha inválida!");
                return;
            }
            candidaturaDTO.setEstudanteId(estudantes[estudanteEscolha].getId());
            
            System.out.println("\nSelecione a oferta:");
            for (int i = 0; i < ofertas.length; i++) {
                System.out.printf("%d. %s (Empresa: %s, Vagas: %d)%n", 
                    i + 1, ofertas[i].getTitulo(), ofertas[i].getEmpresaNome(), ofertas[i].getNumeroVagas());
            }
            System.out.print("Escolha o número da oferta: ");
            int ofertaEscolha = lerInteiro() - 1;
            scanner.nextLine();
            
            if (ofertaEscolha < 0 || ofertaEscolha >= ofertas.length) {
                System.out.println("Escolha inválida!");
                return;
            }
            candidaturaDTO.setOfertaId(ofertas[ofertaEscolha].getId());
            
            System.out.print("Carta de motivação: ");
            candidaturaDTO.setCartaMotivacao(scanner.nextLine());
            
            // Status inicial
            candidaturaDTO.setStatus(StatusCandidatura.EM_ANALISE);
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<CandidaturaDTO> request = new HttpEntity<>(candidaturaDTO, headers);
            
            ResponseEntity<CandidaturaDTO> response = restTemplate.postForEntity(
                BASE_URL + "/candidaturas",
                request,
                CandidaturaDTO.class
            );
            
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                System.out.println("\n✓ Candidatura criada com sucesso!");
                System.out.println("ID: " + response.getBody().getId());
            } else {
                System.out.println("\n✗ Erro ao criar candidatura.");
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    
    // ============ MENU CURSOS ============
    
    private void menuCursos() {
        while (true) {
            System.out.println("\n===== GESTÃO DE CURSOS =====");
            System.out.println("1. Listar todos os cursos");
            System.out.println("2. Criar novo curso");
            System.out.println("3. Ver detalhes de curso");
            System.out.println("4. Listar estudantes do curso");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            
            int opcao = lerInteiro();
            scanner.nextLine();
            
            switch (opcao) {
                case 1:
                    listarCursos();
                    break;
                case 2:
                    criarCurso();
                    break;
                case 3:
                    verDetalhesCurso();
                    break;
                case 4:
                    listarEstudantesCurso();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
    
    private void listarCursos() {
        try {
            ResponseEntity<CursoDTO[]> response = restTemplate.getForEntity(
                BASE_URL + "/cursos", 
                CursoDTO[].class
            );
            
            CursoDTO[] cursos = response.getBody();
            if (cursos == null || cursos.length == 0) {
                System.out.println("Não existem cursos registados.");
                return;
            }
            
            System.out.println("\n=== LISTA DE CURSOS ===");
            for (int i = 0; i < cursos.length; i++) {
                CursoDTO c = cursos[i];
                System.out.printf("%d. %s (Código: %s)%n", i + 1, c.getNome(), c.getCodigo());
                System.out.println("   Grau: " + c.getGrau());
                System.out.println("   Duração: " + c.getDuracaoAnos() + " anos");
                System.out.println("   Departamento: " + (c.getDepartamentoNome() != null ? c.getDepartamentoNome() : "N/A"));
                System.out.println("--------------------------------------------------");
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar cursos: " + e.getMessage());
        }
    }
    
    // ============ MÉTODOS AUXILIARES ============
    
    private int lerInteiro() {
        while (!scanner.hasNextInt()) {
            System.out.print("Por favor, insira um número válido: ");
            scanner.next();
        }
        return scanner.nextInt();
    }
    
    private double lerDouble() {
        while (!scanner.hasNextDouble()) {
            System.out.print("Por favor, insira um número válido: ");
            scanner.next();
        }
        return scanner.nextDouble();
    }
    
    // ============ MÉTODOS QUE PRECISAM SER IMPLEMENTADOS ============
    // (Apenas esqueletos - podem ser implementados conforme necessário)
    
    private void editarEstudante() {
        System.out.println("Funcionalidade em desenvolvimento...");
    }
    
    private void eliminarEstudante() {
        System.out.println("Funcionalidade em desenvolvimento...");
    }
    
    private void verDetalhesEstudante() {
        System.out.println("Funcionalidade em desenvolvimento...");
    }
    
    private void criarCoordenador() {
        System.out.println("Funcionalidade em desenvolvimento...");
    }
    
    private void verDetalhesCoordenador() {
        System.out.println("Funcionalidade em desenvolvimento...");
    }
    
    private void listarRepresentantes() {
        System.out.println("Funcionalidade em desenvolvimento...");
    }
    
    private void criarRepresentante() {
        System.out.println("Funcionalidade em desenvolvimento...");
    }
    
    private void verDetalhesRepresentante() {
        System.out.println("Funcionalidade em desenvolvimento...");
    }
    
    private void verDetalhesEmpresa() {
        System.out.println("Funcionalidade em desenvolvimento...");
    }
    
    private void listarOfertasPorEmpresa() {
        System.out.println("Funcionalidade em desenvolvimento...");
    }
    
    private void listarOfertasPorStatus() {
        System.out.println("Funcionalidade em desenvolvimento...");
    }
    
    private void editarOferta() {
        System.out.println("Funcionalidade em desenvolvimento...");
    }
    
    private void eliminarOferta() {
        System.out.println("Funcionalidade em desenvolvimento...");
    }
    
    private void verDetalhesOferta() {
        System.out.println("Funcionalidade em desenvolvimento...");
    }
    
    private void listarCandidaturasOferta() {
        System.out.println("Funcionalidade em desenvolvimento...");
    }
    
    private void listarCandidaturasPorStatus() {
        System.out.println("Funcionalidade em desenvolvimento...");
    }
    
    private void verDetalhesCandidatura() {
        System.out.println("Funcionalidade em desenvolvimento...");
    }
    
    private void aceitarCandidatura() {
        System.out.println("Funcionalidade em desenvolvimento...");
    }
    
    private void rejeitarCandidatura() {
        System.out.println("Funcionalidade em desenvolvimento...");
    }
    
    private void criarCurso() {
        System.out.println("Funcionalidade em desenvolvimento...");
    }
    
    private void verDetalhesCurso() {
        System.out.println("Funcionalidade em desenvolvimento...");
    }
    
    private void listarEstudantesCurso() {
        System.out.println("Funcionalidade em desenvolvimento...");
    }
}