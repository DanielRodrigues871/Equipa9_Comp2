package com.upt.pt.api.repository;

import com.upt.pt.api.entity.OfertaEstagio;
import com.upt.pt.api.enums.StatusOferta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface OfertaEstagioRepository extends JpaRepository<OfertaEstagio, String> {

    List<OfertaEstagio> findByEmpresaId(String empresaId);

    List<OfertaEstagio> findByCursoId(String cursoId);

    List<OfertaEstagio> findByAreaId(String areaId);

    List<OfertaEstagio> findByStatus(StatusOferta status);

    List<OfertaEstagio> findByDataLimiteInscricaoAfter(LocalDate data);

    List<OfertaEstagio> findByCoordenadorResponsavelId(String coordenadorId);
    
 // ===== ESTATÍSTICAS =====
    long countByStatus(StatusOferta status);

    @Query("""
        SELECT o FROM OfertaEstagio o
        WHERE o.candidaturas IS EMPTY
    """)
    List<OfertaEstagio> findOfertasSemCandidaturas();

}
