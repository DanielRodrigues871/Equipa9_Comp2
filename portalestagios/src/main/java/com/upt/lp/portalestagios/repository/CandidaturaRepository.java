package com.upt.lp.portalestagios.repository;

import com.upt.lp.portalestagios.entity.Candidatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidaturaRepository extends JpaRepository<Candidatura, String> {
}

