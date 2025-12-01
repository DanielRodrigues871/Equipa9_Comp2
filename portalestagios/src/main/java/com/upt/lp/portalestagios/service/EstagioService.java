package com.upt.lp.portalestagios.service;

import com.upt.lp.portalestagios.dto.estagio.EstagioRequestDTO;
import com.upt.lp.portalestagios.dto.estagio.EstagioResponseDTO;
import com.upt.lp.portalestagios.entity.*;
import com.upt.lp.portalestagios.mapper.EstagioMapper;
import com.upt.lp.portalestagios.repository.*;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EstagioService {

    private final EstagioRepository repo;
    private final EstudanteRepository estudanteRepo;
    private final PropostaEstagioRepository propostaRepo;

    public EstagioService(
            EstagioRepository repo,
            EstudanteRepository estudanteRepo,
            PropostaEstagioRepository propostaRepo
    ) {
        this.repo = repo;
        this.estudanteRepo = estudanteRepo;
        this.propostaRepo = propostaRepo;
    }

    public List<EstagioResponseDTO> listar() {
        return repo.findAll()
                .stream()
                .map(EstagioMapper::toDTO)
                .toList();
    }

    public EstagioResponseDTO buscar(UUID id) {
        Estagio e = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Estágio não encontrado"));
        return EstagioMapper.toDTO(e);
    }

    public EstagioResponseDTO criar(EstagioRequestDTO dto) {
        Estudante est = estudanteRepo.findById(dto.getEstudanteId())
                .orElseThrow(() -> new RuntimeException("Estudante não encontrado"));

        PropostaEstagio prop = propostaRepo.findById(dto.getPropostaId())
                .orElseThrow(() -> new RuntimeException("Proposta não encontrada"));

        Estagio novo = new Estagio(est, prop);

        novo.setDataInicio(dto.getDataInicio());
        novo.setDataFim(dto.getDataFim());

        return EstagioMapper.toDTO(repo.save(novo));
    }

    public void apagar(UUID id) {
        repo.deleteById(id);
    }
}
