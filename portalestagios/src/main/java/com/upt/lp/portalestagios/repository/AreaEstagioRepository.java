package com.upt.lp.portalestagios.repository;

import com.upt.lp.portalestagios.entity.AreaEstagio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import java.util.UUID;
import java.util.List;


@Repository
public interface AreaEstagioRepository extends JpaRepository<AreaEstagio, UUID> {

    boolean existsByNome(String nome);

    @Query("SELECT a FROM AreaEstagio a WHERE LOWER(a.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    List<AreaEstagio> searchByNome(String nome);
}


