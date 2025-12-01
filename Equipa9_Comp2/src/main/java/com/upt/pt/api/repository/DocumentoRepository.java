package com.upt.pt.api.repository;

import com.upt.pt.api.entity.Documento;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, String> {
	
	List<Documento> findByEstudanteId(String estudanteId);
}