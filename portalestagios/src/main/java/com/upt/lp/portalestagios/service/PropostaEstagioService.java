package com.upt.lp.portalestagios.service;

import com.upt.lp.portalestagios.dto.proposta.*;
import com.upt.lp.portalestagios.entity.*;
import com.upt.lp.portalestagios.enums.StatusProposta;
import com.upt.lp.portalestagios.mapper.PropostaEstagioMapper;
import com.upt.lp.portalestagios.repository.*;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class PropostaEstagioService {

    private final PropostaEstagioRepository repo;
    private final AreaEstagioRepository areaRepo;
    private final EmpresaRepository empresaRepo;

    public PropostaEstagioService(
            PropostaEstagioRepository repo,
            AreaEstagioRepository areaRepo,
            EmpresaRepository empresaRepo
    ) {
        this.repo = repo;
        this.areaRepo = areaRepo;
        this.empresaRepo = empresaRepo;
    }

    public List<PropostaEstagioResponseDTO> listar() {
        return repo.findAll()
                .stream()
                .map(PropostaEstagioMapper::toDTO)
                .toList();
    }

    public PropostaEstagioResponseDTO buscar(UUID id) {
        PropostaEstagio p = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Proposta não encontrada"));
        return PropostaEstagioMapper.toDTO(p);
    }

    public PropostaEstagioResponseDTO criar(PropostaEstagioRequestDTO dto) {
        AreaEstagio area = areaRepo.findById(dto.getAreaId())
                .orElseThrow(() -> new RuntimeException("Área não encontrada"));

        Empresa empresa = empresaRepo.findById(dto.getEmpresaId())
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));

        PropostaEstagio nova = new PropostaEstagio(area, empresa, dto.getTitulo(), dto.getDescricao());

        return PropostaEstagioMapper.toDTO(repo.save(nova));
    }

    public void alterarStatus(UUID id, StatusProposta novoStatus) {
        PropostaEstagio p = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Proposta não encontrada"));

        p.setStatus(novoStatus);
        repo.save(p);
    }

    public void apagar(UUID id) {
        repo.deleteById(id);
    }
}
