package com.upt.lp.portalestagios.repository;

import com.upt.lp.portalestagios.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

import java.util.List;

public interface CursoRepository extends JpaRepository<Curso, UUID> {

    @Query("SELECT c FROM Curso c WHERE c.codigo = :codigo")
    Optional<Curso> findByCodigo(String codigo);
    
    List<Curso> findByDepartamentoId(UUID departamentoId);
}



