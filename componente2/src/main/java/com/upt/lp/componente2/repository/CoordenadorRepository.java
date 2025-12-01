package com.upt.lp.componente2.repository;

import com.upt.lp.componente2.entity.Coordenador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CoordenadorRepository extends JpaRepository<Coordenador, String> {
    
    Optional<Coordenador> findByEmail(String email);
    
    List<Coordenador> findByDepartamentoId(String departamentoId);
    
    boolean existsByEmail(String email);
}
