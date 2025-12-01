package com.upt.lp.componente2.mapper;

import com.upt.lp.componente2.dto.UtilizadorDTO;
import com.upt.lp.componente2.entity.Utilizador;

public class UtilizadorMapper {
    
    public static UtilizadorDTO toDTO(Utilizador utilizador) {
        if (utilizador == null) return null;
        
        return new UtilizadorDTO(
            utilizador.getId(),
            utilizador.getNome(),
            utilizador.getEmail(),
            utilizador.getDataCriacao(), // CORRIGIDO: getDataCriacao() não getDatacriacao()
            utilizador.getDataAtualizacao()
        );
    }
    
    // Nota: A conversão de DTO para Entity será tratada no Service
    // pois a password precisa de validação e hashing específico
}
