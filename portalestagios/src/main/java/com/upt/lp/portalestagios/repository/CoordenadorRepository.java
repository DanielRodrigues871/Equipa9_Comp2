package com.upt.lp.portalestagios.repository;

import com.upt.lp.portalestagios.entity.Coordenador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoordenadorRepository extends JpaRepository<Coordenador, String> {
}

