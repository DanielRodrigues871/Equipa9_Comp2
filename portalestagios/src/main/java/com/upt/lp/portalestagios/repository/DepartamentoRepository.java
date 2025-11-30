package com.upt.lp.portalestagios.repository;

import com.upt.lp.portalestagios.entity.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface DepartamentoRepository extends JpaRepository<Departamento, UUID> {

    @Query("SELECT d FROM Departamento d WHERE d.codigo = :codigo")
    Optional<Departamento> findByCodigo(String codigo);
    
    boolean existsByNome(String nome);
}

