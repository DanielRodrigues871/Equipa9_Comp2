package com.upt.lp.portalestagios.repository;

import com.upt.lp.portalestagios.entity.Utilizador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtilizadorRepository extends JpaRepository<Utilizador, String> {

    Optional<Utilizador> findByEmail(String email);

}
