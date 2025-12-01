package com.upt.pt.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.upt.pt.api.entity.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, String> {
	
}
