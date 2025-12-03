package com.upt.lp.componente2.repository;

import com.upt.lp.componente2.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CursoRepository extends JpaRepository<Curso, String> {

    Optional<Curso> findByCodigo(String codigo);

    Optional<Curso> findByNome(String nome);

    List<Curso> findByDepartamentoId(String departamentoId);

    List<Curso> findByCoordenadorId(String coordenadorId);
}
