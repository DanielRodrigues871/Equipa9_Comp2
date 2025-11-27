package com.upt.lp.portalestagios.repository;

import com.upt.lp.portalestagios.entity.AreaEstagio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AreaEstagioRepository extends JpaRepository<AreaEstagio, String> {

    // Exemplo de método extra para buscar área por nome
    Optional<AreaEstagio> findByNome(String nome);

    // Outros métodos personalizados podem ser adicionados aqui conforme a necessidade

}

