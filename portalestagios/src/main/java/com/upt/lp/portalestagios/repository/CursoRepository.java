package com.upt.lp.portalestagios.repository;

import com.upt.lp.portalestagios.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, String> {
}

