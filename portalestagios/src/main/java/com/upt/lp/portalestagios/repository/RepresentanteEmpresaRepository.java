package com.upt.lp.portalestagios.repository;

import com.upt.lp.portalestagios.entity.RepresentanteEmpresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepresentanteEmpresaRepository extends JpaRepository<RepresentanteEmpresa, String> {
}
