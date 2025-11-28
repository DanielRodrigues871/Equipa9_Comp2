package com.upt.lp.portalestagios.service;

import com.upt.lp.portalestagios.entity.Curso;
import com.upt.lp.portalestagios.entity.Departamento;
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

    public List<Curso> listar() {
        return cursoRepo.findAll();
    }

    public Curso buscar(String id) {
        UUID uuid = UUID.fromString(id);
        return cursoRepo.findById(uuid)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));
    }

    public Curso criar(String departamentoId, Curso c) {
        UUID deptUuid = UUID.fromString(departamentoId);

        Departamento dept = deptRepo.findById(deptUuid)
                .orElseThrow(() -> new RuntimeException("Departamento não encontrado"));

        c.setDepartamento(dept);
        return cursoRepo.save(c);
    }

    public Curso atualizar(String id, Curso dados) {
        UUID uuid = UUID.fromString(id);

        Curso c = cursoRepo.findById(uuid)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));

        c.setNome(dados.getNome());
        c.setCodigo(dados.getCodigo());
        c.setDuracaoAnos(dados.getDuracaoAnos());
        c.setGrau(dados.getGrau());
        c.setCoordenador(dados.getCoordenador());

        return cursoRepo.save(c);
    }

    public void apagar(String id) {
        UUID uuid = UUID.fromString(id);
        cursoRepo.deleteById(uuid);
    }
}
