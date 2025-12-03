package com.upt.lp.componente2.repository;

import com.upt.lp.componente2.entity.OfertaEstagio;
import com.upt.lp.componente2.enums.StatusOferta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface OfertaEstagioRepository extends JpaRepository<OfertaEstagio, String> {

    List<OfertaEstagio> findByEmpresaId(String empresaId);

    List<OfertaEstagio> findByCursoId(String cursoId);

    List<OfertaEstagio> findByAreaId(String areaId);

    List<OfertaEstagio> findByStatus(StatusOferta status);

    List<OfertaEstagio> findByDataLimiteInscricaoAfter(LocalDate data);

    List<OfertaEstagio> findByCoordenadorResponsavelId(String coordenadorId);
}