package com.upt.lp.portalestagios.service;

import com.upt.lp.portalestagios.entity.Curso;
import com.upt.lp.portalestagios.entity.Departamento;
import com.upt.lp.portalestagios.repository.CursoRepository;
import com.upt.lp.portalestagios.repository.DepartamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return cursoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));
    }

    public Curso criar(String departamentoId, Curso c) {
        Departamento dept = deptRepo.findById(departamentoId)
                .orElseThrow(() -> new RuntimeException("Departamento não encontrado"));

        c.setDepartamento(dept);

        return cursoRepo.save(c);
    }

    public Curso atualizar(String id, Curso dados) {
        Curso c = buscar(id);
        c.setNome(dados.getNome());
        c.setCodigo(dados.getCodigo());
        return cursoRepo.save(c);
    }

    public void apagar(String id) {
        cursoRepo.deleteById(id);
    }
}
