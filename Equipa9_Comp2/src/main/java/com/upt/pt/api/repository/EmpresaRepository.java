package com.upt.pt.api.repository;

import com.upt.pt.api.entity.Empresa;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface EmpresaRepository extends JpaRepository<Empresa, String> {

    Optional<Empresa> findByNif(String nif);

    Optional<Empresa> findByEmail(String email);
    
    @Query("""
            SELECT CONCAT(e.nome, ' — ', COUNT(c.id), ' candidaturas')
            FROM Empresa e
            JOIN OfertaEstagio o ON o.empresa.id = e.id
            JOIN Candidatura c ON c.oferta.id = o.id
            GROUP BY e.nome
            ORDER BY COUNT(c.id) DESC
        """)
        List<String> rankingEmpresasMaisProcuradas();

        @Query("""
            SELECT CONCAT(e.nome, ' — ', COUNT(c.id), ' candidaturas')
            FROM Empresa e
            LEFT JOIN OfertaEstagio o ON o.empresa.id = e.id
            LEFT JOIN Candidatura c ON c.oferta.id = o.id
            GROUP BY e.nome
            ORDER BY COUNT(c.id) ASC
        """)
        List<String> rankingEmpresasMenosEscolhidas();
}