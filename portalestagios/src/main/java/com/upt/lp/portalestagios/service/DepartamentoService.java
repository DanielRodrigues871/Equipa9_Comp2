package com.upt.lp.portalestagios.service;

import com.upt.lp.portalestagios.dto.departamento.DepartamentoRequestDTO;
import com.upt.lp.portalestagios.dto.departamento.DepartamentoResponseDTO;
import com.upt.lp.portalestagios.entity.Departamento;
import com.upt.lp.portalestagios.mapper.DepartamentoMapper;
import com.upt.lp.portalestagios.repository.DepartamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DepartamentoService {

    private final DepartamentoRepository repo;

    public DepartamentoService(DepartamentoRepository repo) {
        this.repo = repo;
    }

    public List<DepartamentoResponseDTO> listar() {
        return repo.findAll()
                .stream()
                .map(DepartamentoMapper::toDTO)
                .toList();
    }

    public DepartamentoResponseDTO buscar(String id) {
        UUID uuid = UUID.fromString(id);
        Departamento d = repo.findById(uuid)
                .orElseThrow(() -> new RuntimeException("Departamento não encontrado"));

        return DepartamentoMapper.toDTO(d);
    }

    public DepartamentoResponseDTO criar(DepartamentoRequestDTO dto) {
        Departamento novo = DepartamentoMapper.toEntity(dto);
        Departamento salvo = repo.save(novo);
        return DepartamentoMapper.toDTO(salvo);
    }

    public DepartamentoResponseDTO atualizar(String id, DepartamentoRequestDTO dto) {
        UUID uuid = UUID.fromString(id);
        Departamento existente = repo.findById(uuid)
                .orElseThrow(() -> new RuntimeException("Departamento não encontrado"));

        existente.setNome(dto.getNome());
        existente.setCodigo(dto.getCodigo());
        existente.setDescricao(dto.getDescricao());

        return DepartamentoMapper.toDTO(repo.save(existente));
    }

    public void apagar(String id) {
        UUID uuid = UUID.fromString(id);
        repo.deleteById(uuid);
    }
}
