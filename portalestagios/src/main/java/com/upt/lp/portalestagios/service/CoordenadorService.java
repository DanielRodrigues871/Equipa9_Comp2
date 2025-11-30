package com.upt.lp.portalestagios.service;

import com.upt.lp.portalestagios.dto.coordenador.CoordenadorRequestDTO;
import com.upt.lp.portalestagios.dto.coordenador.CoordenadorResponseDTO;
import com.upt.lp.portalestagios.entity.Coordenador;
import com.upt.lp.portalestagios.entity.Departamento;
import com.upt.lp.portalestagios.mapper.CoordenadorMapper;
import com.upt.lp.portalestagios.repository.CoordenadorRepository;
import com.upt.lp.portalestagios.repository.DepartamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CoordenadorService {

    private final CoordenadorRepository repo;
    private final DepartamentoRepository deptRepo;

    public CoordenadorService(CoordenadorRepository repo, DepartamentoRepository deptRepo) {
        this.repo = repo;
        this.deptRepo = deptRepo;
    }

    public List<CoordenadorResponseDTO> listar() {
        return repo.findAll().stream().map(CoordenadorMapper::toDTO).toList();
    }

    public CoordenadorResponseDTO buscar(UUID id) {
        Coordenador c = repo.findById(id).orElseThrow(() -> new RuntimeException("Coordenador não encontrado"));
        return CoordenadorMapper.toDTO(c);
    }

    public CoordenadorResponseDTO criar(CoordenadorRequestDTO dto) {
        Departamento dept = null;
        if (dto.getDepartamentoId() != null) {
            dept = deptRepo.findById(dto.getDepartamentoId())
                    .orElseThrow(() -> new RuntimeException("Departamento não encontrado"));
        }

        Coordenador novo = CoordenadorMapper.toEntity(dto, dept);
        Coordenador salvo = repo.save(novo);
        return CoordenadorMapper.toDTO(salvo);
    }

    public CoordenadorResponseDTO atualizar(UUID id, CoordenadorRequestDTO dto) {
        Coordenador existente = repo.findById(id).orElseThrow(() -> new RuntimeException("Coordenador não encontrado"));

        if (dto.getNome() != null) existente.setNome(dto.getNome());
        if (dto.getEmail() != null) existente.setEmail(dto.getEmail());
        if (dto.getPassword() != null) existente.setPassword(dto.getPassword()); // vai hash
        if (dto.getDepartamentoId() != null) {
            Departamento dept = deptRepo.findById(dto.getDepartamentoId())
                    .orElseThrow(() -> new RuntimeException("Departamento não encontrado"));
            existente.setDepartamento(dept);
        }

        Coordenador salvo = repo.save(existente);
        return CoordenadorMapper.toDTO(salvo);
    }

    public void apagar(UUID id) {
        repo.deleteById(id);
    }
}
