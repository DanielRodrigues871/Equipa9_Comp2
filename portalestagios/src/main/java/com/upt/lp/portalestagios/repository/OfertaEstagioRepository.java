package com.upt.lp.portalestagios.repository;

import com.upt.lp.portalestagios.entity.OfertaEstagio;
import com.upt.lp.portalestagios.enums.StatusOferta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OfertaEstagioRepository extends JpaRepository<OfertaEstagio, UUID> {

    // Listar apenas ofertas aprovadas e com vagas
    @Query("""
           SELECT o FROM OfertaEstagio o 
           WHERE o.status = com.upt.lp.portalestagios.enums.StatusOferta.APROVADO
           AND o.numeroVagas > 0
           """)
    List<OfertaEstagio> listarDisponiveis();

    // Listar ofertas de uma empresa específica
    @Query("SELECT o FROM OfertaEstagio o WHERE o.empresa.id = :empresaId")
    List<OfertaEstagio> listarPorEmpresa(UUID empresaId);

    // Ofertas geridas por um coordenador
    @Query("SELECT o FROM OfertaEstagio o WHERE o.coordenadorResponsavel.id = :coordId")
    List<OfertaEstagio> listarGeridasPorCoordenador(UUID coordId);

    // Alternativa sem JPQL
    List<OfertaEstagio> findByEmpresaId(UUID empresaId);
    
    // Spring Data cria a query automaticamente
    List<OfertaEstagio> findByStatus(StatusOferta status);

    // Apenas ofertas com status e com numeroVagas > 0
    List<OfertaEstagio> findByStatusAndNumeroVagasGreaterThan(StatusOferta status, int numeroVagas);
    
 // Filtrar por coordenador responsável
    List<OfertaEstagio> findByCoordenadorResponsavelId(UUID coordId);
    
    long countByStatus(StatusOferta status);

}
