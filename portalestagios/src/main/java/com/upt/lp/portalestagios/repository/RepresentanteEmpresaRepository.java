package com.upt.lp.portalestagios.repository;

import com.upt.lp.portalestagios.entity.RepresentanteEmpresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.List;

@Repository
public interface RepresentanteEmpresaRepository extends JpaRepository<RepresentanteEmpresa, UUID> {

    boolean existsByEmail(String email);

    List<RepresentanteEmpresa> findByEmpresaId(UUID empresaId);
}
