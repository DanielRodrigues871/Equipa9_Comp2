package com.upt.lp.portalestagios.repository;

import com.upt.lp.portalestagios.entity.Estagio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstagioRepository extends JpaRepository<Estagio, String> {
}

