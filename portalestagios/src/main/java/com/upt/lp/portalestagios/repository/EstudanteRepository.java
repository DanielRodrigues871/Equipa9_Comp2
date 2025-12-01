package com.upt.lp.portalestagios.repository;

import com.upt.lp.portalestagios.entity.Estudante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EstudanteRepository extends JpaRepository<Estudante, UUID> {

    // Buscar por email (herdado de Utilizador)
    Optional<Estudante> findByEmail(String email);

    // Buscar por número de estudante
    Optional<Estudante> findByNumeroEstudante(String numeroEstudante);

    // Buscar estudantes pelo curso
    List<Estudante> findByCursoId(UUID cursoId);

    // Buscar estudantes por departamento (via curso)
    @Query("""
            SELECT e FROM Estudante e
            WHERE e.curso.departamento.id = :departamentoId
            """)
    List<Estudante> findByDepartamentoId(UUID departamentoId);

    // Buscar estudantes por ano de matrícula
    List<Estudante> findByAnoMatricula(Integer anoMatricula);

    // Buscar estudantes que já fizeram candidaturas
    @Query("""
            SELECT DISTINCT e FROM Estudante e
            JOIN e.candidaturas c
            """)
    List<Estudante> findComCandidaturas();

    // Buscar estudantes sem candidaturas
    @Query("""
            SELECT e FROM Estudante e
            WHERE e.candidaturas IS EMPTY
            """)
    List<Estudante> findSemCandidaturas();
}
