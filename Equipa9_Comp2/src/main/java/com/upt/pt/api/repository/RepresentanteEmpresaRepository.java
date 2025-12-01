package com.upt.pt.api.repository;

import com.upt.pt.api.entity.RepresentanteEmpresa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RepresentanteEmpresaRepository extends JpaRepository<RepresentanteEmpresa, String> {

    // usado no service para validar unicidade do email
    Optional<RepresentanteEmpresa> findByEmail(String email);

    // usado no service e em endpoints para listar representantes de uma empresa
    List<RepresentanteEmpresa> findByEmpresaId(String empresaId);
}
