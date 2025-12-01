package com.upt.lp.componente2.client;

import com.upt.lp.componente2.dto.*;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import java.util.*;

/**
 * Serviço cliente que faz chamadas REST à API
 * (Equivalente ao GerePortalEstagios, mas usando RestTemplate)
 */
public class ClientService {
    
    private static final String BASE_URL = "http://localhost:8080/api";
    private final RestTemplate restTemplate;
    
    public ClientService() {
        this.restTemplate = new RestTemplate();
    }
    
    // ============ MÉTODOS DE AUTENTICAÇÃO ============
    
    public UtilizadorDTO autenticarUtilizador(String email, String senha) {
        try {
            Map<String, String> credenciais = new HashMap<>();
            credenciais.put("email", email);
            credenciais.put("password", senha);
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Map<String, String>> request = new HttpEntity<>(credenciais, headers);
            
            ResponseEntity<UtilizadorDTO> response = restTemplate.postForEntity(
                BASE_URL + "/auth/login", 
                request, 
                UtilizadorDTO.class
            );
            
            return response.getBody();
        } catch (HttpClientErrorException.Unauthorized e) {
            System.out.println("Credenciais inválidas.");
            return null;
        } catch (Exception e) {
            System.out.println("Erro na autenticação: " + e.getMessage());
            return null;
        }
    }
    
    public EstudanteDTO criarEstudante(EstudanteDTO estudanteDTO) {
        return fazerPost("/estudantes", estudanteDTO, EstudanteDTO.class);
    }
    
    public CoordenadorDTO criarCoordenador(CoordenadorDTO coordenadorDTO) {
        return fazerPost("/coordenadores", coordenadorDTO, CoordenadorDTO.class);
    }
    
    public RepresentanteEmpresaDTO criarRepresentante(RepresentanteEmpresaDTO repDTO) {
        return fazerPost("/representantes", repDTO, RepresentanteEmpresaDTO.class);
    }
    
    // ============ MÉTODOS DE EMPRESAS ============
    
    public List<EmpresaDTO> listarEmpresas() {
        try {
            ResponseEntity<EmpresaDTO[]> response = restTemplate.getForEntity(
                BASE_URL + "/empresas", 
                EmpresaDTO[].class
            );
            return Arrays.asList(response.getBody());
        } catch (Exception e) {
            System.out.println("Erro ao listar empresas: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    public EmpresaDTO criarEmpresa(EmpresaDTO empresaDTO) {
        return fazerPost("/empresas", empresaDTO, EmpresaDTO.class);
    }
    
    public EmpresaDTO atualizarEmpresa(Long id, EmpresaDTO empresaDTO) {
        return fazerPut("/empresas/" + id, empresaDTO, EmpresaDTO.class);
    }
    
    public void eliminarEmpresa(Long id) {
        fazerDelete("/empresas/" + id);
    }
    
    // ============ MÉTODOS DE OFERTAS ============
    
    public List<OfertaEstagioDTO> listarOfertas() {
        try {
            ResponseEntity<OfertaEstagioDTO[]> response = restTemplate.getForEntity(
                BASE_URL + "/ofertas", 
                OfertaEstagioDTO[].class
            );
            return Arrays.asList(response.getBody());
        } catch (Exception e) {
            System.out.println("Erro ao listar ofertas: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    public List<OfertaEstagioDTO> listarOfertasPendentes() {
        try {
            ResponseEntity<OfertaEstagioDTO[]> response = restTemplate.getForEntity(
                BASE_URL + "/ofertas/pendentes", 
                OfertaEstagioDTO[].class
            );
            return Arrays.asList(response.getBody());
        } catch (Exception e) {
            System.out.println("Erro ao listar ofertas pendentes: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    public OfertaEstagioDTO criarOferta(OfertaEstagioDTO ofertaDTO) {
        return fazerPost("/ofertas", ofertaDTO, OfertaEstagioDTO.class);
    }
    
    public OfertaEstagioDTO atualizarOferta(Long id, OfertaEstagioDTO ofertaDTO) {
        return fazerPut("/ofertas/" + id, ofertaDTO, OfertaEstagioDTO.class);
    }
    
    public void eliminarOferta(Long id) {
        fazerDelete("/ofertas/" + id);
    }
    
    public OfertaEstagioDTO aprovarOferta(Long id) {
        return fazerPut("/ofertas/" + id + "/aprovar", null, OfertaEstagioDTO.class);
    }
    
    public OfertaEstagioDTO rejeitarOferta(Long id) {
        return fazerPut("/ofertas/" + id + "/rejeitar", null, OfertaEstagioDTO.class);
    }
    
    // ============ MÉTODOS DE CANDIDATURAS ============
    
    public List<CandidaturaDTO> listarCandidaturas() {
        try {
            ResponseEntity<CandidaturaDTO[]> response = restTemplate.getForEntity(
                BASE_URL + "/candidaturas", 
                CandidaturaDTO[].class
            );
            return Arrays.asList(response.getBody());
        } catch (Exception e) {
            System.out.println("Erro ao listar candidaturas: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    public List<CandidaturaDTO> listarCandidaturasPorEstudante(Long estudanteId) {
        try {
            ResponseEntity<CandidaturaDTO[]> response = restTemplate.getForEntity(
                BASE_URL + "/candidaturas/estudante/" + estudanteId, 
                CandidaturaDTO[].class
            );
            return Arrays.asList(response.getBody());
        } catch (Exception e) {
            System.out.println("Erro ao listar candidaturas do estudante: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    public CandidaturaDTO criarCandidatura(CandidaturaDTO candidaturaDTO) {
        return fazerPost("/candidaturas", candidaturaDTO, CandidaturaDTO.class);
    }
    
    public CandidaturaDTO aceitarCandidatura(Long id) {
        return fazerPut("/candidaturas/" + id + "/aceitar", null, CandidaturaDTO.class);
    }
    
    public CandidaturaDTO rejeitarCandidatura(Long id) {
        return fazerPut("/candidaturas/" + id + "/rejeitar", null, CandidaturaDTO.class);
    }
    
    // ============ MÉTODOS DE CURSOS ============
    
    public List<CursoDTO> listarCursos() {
        try {
            ResponseEntity<CursoDTO[]> response = restTemplate.getForEntity(
                BASE_URL + "/cursos", 
                CursoDTO[].class
            );
            return Arrays.asList(response.getBody());
        } catch (Exception e) {
            System.out.println("Erro ao listar cursos: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    public CursoDTO criarCurso(CursoDTO cursoDTO) {
        return fazerPost("/cursos", cursoDTO, CursoDTO.class);
    }
    
    // ============ MÉTODOS AUXILIARES ============
    
    private <T> T fazerPost(String endpoint, Object body, Class<T> responseType) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Object> request = new HttpEntity<>(body, headers);
            
            ResponseEntity<T> response = restTemplate.postForEntity(
                BASE_URL + endpoint, 
                request, 
                responseType
            );
            return response.getBody();
        } catch (Exception e) {
            System.out.println("Erro ao criar recurso: " + e.getMessage());
            return null;
        }
    }
    
    private <T> T fazerPut(String endpoint, Object body, Class<T> responseType) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Object> request = new HttpEntity<>(body, headers);
            
            return restTemplate.exchange(
                BASE_URL + endpoint, 
                HttpMethod.PUT, 
                request, 
                responseType
            ).getBody();
        } catch (Exception e) {
            System.out.println("Erro ao atualizar recurso: " + e.getMessage());
            return null;
        }
    }
    
    private void fazerDelete(String endpoint) {
        try {
            restTemplate.delete(BASE_URL + endpoint);
        } catch (Exception e) {
            System.out.println("Erro ao eliminar recurso: " + e.getMessage());
        }
    }
}
