package com.upt.pt.api.repository;

import com.upt.pt.api.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CursoRepository extends JpaRepository<Curso, String> {

    // procurar por código (é único)
    Optional<Curso> findByCodigo(String codigo);

    // opcional: procurar por nome
    Optional<Curso> findByNome(String nome);
}
