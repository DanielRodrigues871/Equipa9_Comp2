package com.upt.lp.componente2.service;

import com.upt.lp.componente2.entity.PropostaEstagio;
import com.upt.lp.componente2.entity.Empresa;
import com.upt.lp.componente2.entity.RepresentanteEmpresa;
import com.upt.lp.componente2.entity.AreaEstagio;
import com.upt.lp.componente2.repository.PropostaEstagioRepository;
import com.upt.lp.componente2.repository.EmpresaRepository;
import com.upt.lp.componente2.repository.RepresentanteEmpresaRepository;
import com.upt.lp.componente2.repository.AreaEstagioRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PropostaEstagioService {
    
    private final PropostaEstagioRepository propostaRepository;
    private final EmpresaRepository empresaRepository;
    private final RepresentanteEmpresaRepository representanteRepository;
    private final AreaEstagioRepository areaRepository;
    
    public PropostaEstagioService(PropostaEstagioRepository propostaRepository,
                                 EmpresaRepository empresaRepository,
                                 RepresentanteEmpresaRepository representanteRepository,
                                 AreaEstagioRepository areaRepository) {
        this.propostaRepository = propostaRepository;
        this.empresaRepository = empresaRepository;
        this.representanteRepository = representanteRepository;
        this.areaRepository = areaRepository;
    }
    
    public List<PropostaEstagio> getAllPropostas() {
        return propostaRepository.findAll();
    }
    
    public PropostaEstagio getPropostaById(String id) {
        return propostaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proposta não encontrada com ID: " + id));
    }
    
    public PropostaEstagio createProposta(PropostaEstagio proposta, String empresaId, 
                                         String representanteId, List<String> areasIds) {
        // Buscar empresa
        Empresa empresa = empresaRepository.findById(empresaId)
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada com ID: " + empresaId));
        proposta.setEmpresa(empresa);
        
        // Buscar representante
        RepresentanteEmpresa representante = representanteRepository.findById(representanteId)
                .orElseThrow(() -> new RuntimeException("Representante não encontrado com ID: " + representanteId));
        proposta.setRepresentante(representante);
        
        // Buscar áreas
        if (areasIds != null && !areasIds.isEmpty()) {
            List<AreaEstagio> areas = areasIds.stream()
                .map(areaId -> areaRepository.findById(areaId)
                    .orElseThrow(() -> new RuntimeException("Área não encontrada com ID: " + areaId)))
                .collect(Collectors.toList());
            proposta.setAreas(areas);
        }
        
        return propostaRepository.save(proposta);
    }
    
    public PropostaEstagio updateProposta(String id, PropostaEstagio propostaAtualizada) {
        PropostaEstagio propostaExistente = getPropostaById(id);
        
        propostaExistente.setTitulo(propostaAtualizada.getTitulo());
        propostaExistente.setDescricao(propostaAtualizada.getDescricao());
        propostaExistente.setRequisitos(propostaAtualizada.getRequisitos());
        propostaExistente.setBeneficios(propostaAtualizada.getBeneficios());
        propostaExistente.setLocalizacao(propostaAtualizada.getLocalizacao());
        propostaExistente.setDuracaoMeses(propostaAtualizada.getDuracaoMeses());
        propostaExistente.setRemunerado(propostaAtualizada.getRemunerado());
        propostaExistente.setValorRemuneracao(propostaAtualizada.getValorRemuneracao());
        propostaExistente.setVagasDisponiveis(propostaAtualizada.getVagasDisponiveis());
        propostaExistente.setTipo(propostaAtualizada.getTipo());
        propostaExistente.setStatus(propostaAtualizada.getStatus());
        
        // Atualizar empresa se fornecida
        if (propostaAtualizada.getEmpresa() != null) {
            propostaExistente.setEmpresa(propostaAtualizada.getEmpresa());
        }
        
        // Atualizar representante se fornecido
        if (propostaAtualizada.getRepresentante() != null) {
            propostaExistente.setRepresentante(propostaAtualizada.getRepresentante());
        }
        
        // Atualizar áreas se fornecidas
        if (propostaAtualizada.getAreas() != null) {
            propostaExistente.setAreas(propostaAtualizada.getAreas());
        }
        
        return propostaRepository.save(propostaExistente);
    }
    
    public void aprovarProposta(String id) {
        PropostaEstagio proposta = getPropostaById(id);
        proposta.aprovar();
        propostaRepository.save(proposta);
    }
    
    public void rejeitarProposta(String id) {
        PropostaEstagio proposta = getPropostaById(id);
        proposta.rejeitar();
        propostaRepository.save(proposta);
    }
    
    public void deleteProposta(String id) {
        if (!propostaRepository.existsById(id)) {
            throw new RuntimeException("Proposta não encontrada com ID: " + id);
        }
        propostaRepository.deleteById(id);
    }
    
    public List<PropostaEstagio> getPropostasByEmpresa(String empresaId) {
        return propostaRepository.findByEmpresaId(empresaId);
    }
    
    public List<PropostaEstagio> getPropostasByRepresentante(String representanteId) {
        return propostaRepository.findByRepresentanteId(representanteId);
    }
    
    public List<PropostaEstagio> getPropostasByStatus(String status) {
        return propostaRepository.findByStatus(status);
    }
    
    public List<PropostaEstagio> getPropostasByTipo(String tipo) {
        return propostaRepository.findByTipo(tipo);
    }
    
    public List<PropostaEstagio> searchPropostasByTitulo(String titulo) {
        return propostaRepository.findByTituloContainingIgnoreCase(titulo);
    }
    
    public List<PropostaEstagio> getPropostasPendentes() {
        return propostaRepository.findByStatus("PENDENTE");
    }
    
    public List<PropostaEstagio> getPropostasAprovadas() {
        return propostaRepository.findByStatus("APROVADA");
    }
    
    public long countPropostasByStatus(String status) {
        return propostaRepository.countByStatus(status);
    }
}