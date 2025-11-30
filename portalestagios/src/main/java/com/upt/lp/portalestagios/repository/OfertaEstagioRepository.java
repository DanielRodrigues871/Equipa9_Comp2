package com.upt.lp.portalestagios.repository;

import com.upt.lp.portalestagios.entity.OfertaEstagio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface OfertaEstagioRepository extends JpaRepository<OfertaEstagio, UUID> {

    @Query("SELECT o FROM OfertaEstagio o WHERE o.estado = 'APROVADA'")
    List<OfertaEstagio> listarDisponiveis();

    @Query("SELECT o FROM OfertaEstagio o WHERE o.empresa.id = :empresaId")
    List<OfertaEstagio> listarPorEmpresa(UUID empresaId);

    @Query("SELECT o FROM OfertaEstagio o WHERE o.coordenadorResponsavel.id = :coordId")
    List<OfertaEstagio> listarGeridasPorCoordenador(UUID coordId);
    
    List<OfertaEstagio> findByEmpresaId(UUID empresaId);

}
