package com.upt.lp.portalestagios.repository;

import com.upt.lp.portalestagios.entity.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, String> {
}
