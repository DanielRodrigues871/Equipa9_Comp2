package com.upt.lp.portalestagios.repository;

import com.upt.lp.portalestagios.entity.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, UUID> {

    boolean existsByNif(String nif);

    boolean existsByEmail(String email);

    @Query("SELECT e FROM Empresa e WHERE LOWER(e.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    List<Empresa> searchByNome(String nome);
}


