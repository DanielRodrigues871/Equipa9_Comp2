package com.upt.lp.componente2.repository;

import com.upt.lp.componente2.entity.AreaEstagio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface AreaEstagioRepository extends JpaRepository<AreaEstagio, String> {
    
    Optional<AreaEstagio> findByNome(String nome);
    
    List<AreaEstagio> findByNomeContainingIgnoreCase(String nome);
}
