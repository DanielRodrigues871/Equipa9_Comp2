package com.upt.pt.api.repository;

import com.upt.pt.api.entity.AreaEstagio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AreaEstagioRepository extends JpaRepository<AreaEstagio, String> {

    Optional<AreaEstagio> findByNome(String nome);
}
