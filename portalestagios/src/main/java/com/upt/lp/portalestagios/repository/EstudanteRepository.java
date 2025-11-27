package com.upt.lp.portalestagios.repository;

import com.upt.lp.portalestagios.entity.Estudante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudanteRepository extends JpaRepository<Estudante, String> {
}

