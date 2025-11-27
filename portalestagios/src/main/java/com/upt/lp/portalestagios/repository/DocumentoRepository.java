package com.upt.lp.portalestagios.repository;

import com.upt.lp.portalestagios.entity.Documento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, String> {
}
