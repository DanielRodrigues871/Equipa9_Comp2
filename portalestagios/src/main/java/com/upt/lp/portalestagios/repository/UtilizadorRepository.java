package com.upt.lp.portalestagios.repository;

import com.upt.lp.portalestagios.entity.Utilizador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UtilizadorRepository extends JpaRepository<Utilizador, UUID> {

    Optional<Utilizador> findByEmail(String email);

}
