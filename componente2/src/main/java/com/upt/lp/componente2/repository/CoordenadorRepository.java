package com.upt.lp.componente2.repository;

import com.upt.lp.componente2.entity.Coordenador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CoordenadorRepository extends JpaRepository<Coordenador, String> {

    // usado no service para validar unicidade do email
    Optional<Coordenador> findByEmail(String email);

    // opcional: listar coordenadores de um departamento
    List<Coordenador> findByDepartamentoId(String departamentoId);
}
