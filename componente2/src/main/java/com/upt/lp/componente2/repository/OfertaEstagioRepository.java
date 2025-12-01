package com.upt.lp.componente2.repository;

import com.upt.lp.componente2.entity.OfertaEstagio;
import com.upt.lp.componente2.enums.StatusOferta;
import com.upt.lp.componente2.enums.TipoEstagio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface OfertaEstagioRepository extends JpaRepository<OfertaEstagio, String> {
    
    List<OfertaEstagio> findByEmpresaId(String empresaId);
    
    List<OfertaEstagio> findByCursoId(String cursoId);
    
    List<OfertaEstagio> findByAreaId(String areaId);
    
    List<OfertaEstagio> findByStatus(StatusOferta status);
    
    List<OfertaEstagio> findByTipo(TipoEstagio tipo);
    
    List<OfertaEstagio> findByCoordenadorResponsavelId(String coordenadorId);
    
    List<OfertaEstagio> findByTituloContainingIgnoreCase(String titulo);
    
    List<OfertaEstagio> findByDataLimiteInscricaoAfter(LocalDate data);
    
    List<OfertaEstagio> findByStatusAndDataLimiteInscricaoAfter(StatusOferta status, LocalDate data);
    
    long countByStatus(StatusOferta status);
}