package com.upt.lp.portalestagios.service;

import com.upt.lp.portalestagios.dto.area.AreaEstagioRequestDTO;
import com.upt.lp.portalestagios.dto.area.AreaEstagioResponseDTO;
import com.upt.lp.portalestagios.entity.AreaEstagio;
import com.upt.lp.portalestagios.mapper.AreaEstagioMapper;
import com.upt.lp.portalestagios.repository.AreaEstagioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AreaEstagioService {

    private final AreaEstagioRepository repo;

    public AreaEstagioService(AreaEstagioRepository repo) {
        this.repo = repo;
    }

    public List<AreaEstagioResponseDTO> listar() {
        return repo.findAll()
                .stream()
                .map(AreaEstagioMapper::toDTO)
                .toList();
    }

    public AreaEstagioResponseDTO buscar(UUID id) {
        AreaEstagio a = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Área não encontrada"));

        return AreaEstagioMapper.toDTO(a);
    }

    public AreaEstagioResponseDTO criar(AreaEstagioRequestDTO dto) {
        AreaEstagio novo = AreaEstagioMapper.toEntity(dto);
        return AreaEstagioMapper.toDTO(repo.save(novo));
    }

    public AreaEstagioResponseDTO atualizar(UUID id, AreaEstagioRequestDTO dto) {
        AreaEstagio existente = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Área não encontrada"));

        existente.setNome(dto.getNome());
        existente.setDescricao(dto.getDescricao());

        return AreaEstagioMapper.toDTO(repo.save(existente));
    }

    public void apagar(UUID id) {
        repo.deleteById(id);
    }
}
