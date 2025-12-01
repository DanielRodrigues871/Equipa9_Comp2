package com.upt.pt.api.repository;

import com.upt.pt.api.entity.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface DepartamentoRepository extends JpaRepository<Departamento, String> {

    Optional<Departamento> findByCodigo(String codigo);

    Optional<Departamento> findByNome(String nome);
}
