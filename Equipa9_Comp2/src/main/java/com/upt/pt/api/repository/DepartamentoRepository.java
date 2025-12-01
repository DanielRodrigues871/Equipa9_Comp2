package com.upt.pt.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.upt.pt.api.entity.Departamento;

public interface DepartamentoRepository extends JpaRepository<Departamento, String> {
	
}
