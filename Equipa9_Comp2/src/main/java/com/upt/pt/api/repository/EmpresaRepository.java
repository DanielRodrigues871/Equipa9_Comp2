package com.upt.pt.api.repository;

import com.upt.pt.api.entity.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmpresaRepository extends JpaRepository<Empresa, String> {

    Optional<Empresa> findByNif(String nif);

    Optional<Empresa> findByEmail(String email);
}
