package com.upt.lp.portalestagios.service;

import com.upt.lp.portalestagios.dto.estudante.EstudanteRequestDTO;
import com.upt.lp.portalestagios.entity.Curso;
import com.upt.lp.portalestagios.entity.Estudante;
import com.upt.lp.portalestagios.mapper.EstudanteMapper;
import com.upt.lp.portalestagios.repository.CursoRepository;
import com.upt.lp.portalestagios.repository.EstudanteRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EstudanteService {

    private final EstudanteRepository estudanteRepository;
    private final CursoRepository cursoRepository;

    public EstudanteService(EstudanteRepository estudanteRepository, 
                            CursoRepository cursoRepository) {
        this.estudanteRepository = estudanteRepository;
        this.cursoRepository = cursoRepository;
    }

    public List<Estudante> findAll() {
        return estudanteRepository.findAll();
    }

    public Optional<Estudante> findById(UUID id) {
        return estudanteRepository.findById(id);
    }

    public Estudante save(Estudante estudante) {
        return estudanteRepository.save(estudante);
    }

    public void delete(UUID id) {
        estudanteRepository.deleteById(id);
    }

    public Estudante create(EstudanteRequestDTO dto) {

        Curso curso = cursoRepository.findById(UUID.fromString(dto.getCursoId()))
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));

        Estudante novo = EstudanteMapper.toEntity(dto, curso);

        return estudanteRepository.save(novo);
    }
}
