package com.upt.lp.portalestagios.repository;

import com.upt.lp.portalestagios.entity.AreaEstagio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface AreaEstagioRepository extends JpaRepository<AreaEstagio, UUID> {
}


