package com.upt.lp.portalestagios.service;

import com.upt.lp.portalestagios.dto.curso.CursoRequestDTO;
import com.upt.lp.portalestagios.dto.curso.CursoResponseDTO;
import com.upt.lp.portalestagios.entity.Curso;
import com.upt.lp.portalestagios.entity.Departamento;
import com.upt.lp.portalestagios.mapper.CursoMapper;
import com.upt.lp.portalestagios.repository.CursoRepository;
import com.upt.lp.portalestagios.repository.DepartamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CursoService {

    private final CursoRepository cursoRepo;
    private final DepartamentoRepository deptRepo;

    public CursoService(CursoRepository cursoRepo, DepartamentoRepository deptRepo) {
        this.cursoRepo = cursoRepo;
        this.deptRepo = deptRepo;
    }

    public List<CursoResponseDTO> listar() {
        return cursoRepo.findAll()
                .stream()
                .map(CursoMapper::toDTO)
                .toList();
    }

    public CursoResponseDTO buscar(String id) {
        UUID uuid = UUID.fromString(id);
        Curso c = cursoRepo.findById(uuid)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));
        return CursoMapper.toDTO(c);
    }

    public CursoResponseDTO criar(CursoRequestDTO dto) {

        UUID deptId = UUID.fromString(dto.getDepartamentoId());
        Departamento dept = deptRepo.findById(deptId)
                .orElseThrow(() -> new RuntimeException("Departamento não encontrado"));

        Curso novo = CursoMapper.toEntity(dto, dept);
        Curso salvo = cursoRepo.save(novo);

        return CursoMapper.toDTO(salvo);
    }

    public CursoResponseDTO atualizar(String id, CursoRequestDTO dto) {
        UUID uuid = UUID.fromString(id);
        Curso c = cursoRepo.findById(uuid)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));

        c.setNome(dto.getNome());
        c.setCodigo(dto.getCodigo());
        c.setDuracaoAnos(dto.getDuracaoAnos());
        c.setGrau(dto.getGrau());

        if (dto.getDepartamentoId() != null) {
            UUID deptId = UUID.fromString(dto.getDepartamentoId());
            Departamento d = deptRepo.findById(deptId)
                    .orElseThrow(() -> new RuntimeException("Departamento não encontrado"));
            c.setDepartamento(d);
        }

        return CursoMapper.toDTO(cursoRepo.save(c));
    }

    public void apagar(String id) {
        UUID uuid = UUID.fromString(id);
        cursoRepo.deleteById(uuid);
    }
}
