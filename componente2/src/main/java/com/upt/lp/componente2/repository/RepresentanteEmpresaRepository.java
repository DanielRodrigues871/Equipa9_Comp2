package com.upt.lp.componente2.repository;

import com.upt.lp.componente2.entity.RepresentanteEmpresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RepresentanteEmpresaRepository extends JpaRepository<RepresentanteEmpresa, String> {
    
    Optional<RepresentanteEmpresa> findByEmail(String email);
    
    List<RepresentanteEmpresa> findByEmpresaId(String empresaId);
    
    List<RepresentanteEmpresa> findByCargo(String cargo);
    
    boolean existsByEmail(String email);
}
