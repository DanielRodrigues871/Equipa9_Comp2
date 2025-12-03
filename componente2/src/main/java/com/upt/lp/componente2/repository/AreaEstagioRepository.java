package com.upt.lp.componente2.repository;

import com.upt.lp.componente2.entity.AreaEstagio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AreaEstagioRepository extends JpaRepository<AreaEstagio, String> {

    Optional<AreaEstagio> findByNome(String nome);
}
