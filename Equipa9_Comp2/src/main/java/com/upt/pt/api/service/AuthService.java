package com.upt.pt.api.service;

import org.springframework.stereotype.Service;

import com.upt.pt.api.dto.RegistroDTO;
import com.upt.pt.api.entity.Coordenador;
import com.upt.pt.api.entity.Estudante;
import com.upt.pt.api.entity.RepresentanteEmpresa;

// ajusta imports conforme os teus services reais
@Service
public class AuthService {

    private final EstudanteService estudanteService;
    private final CoordenadorService coordenadorService;
    private final RepresentanteEmpresaService representanteService;

    public AuthService(EstudanteService estudanteService,
                       CoordenadorService coordenadorService,
                       RepresentanteEmpresaService representanteService) {
        this.estudanteService = estudanteService;
        this.coordenadorService = coordenadorService;
        this.representanteService = representanteService;
    }

    public Object register(RegistroDTO dto) {
        if (dto.getTipo() == null) {
            throw new IllegalArgumentException("Tipo de utilizador é obrigatório.");
        }

        String tipo = dto.getTipo().toUpperCase();

        return switch (tipo) {
            case "ESTUDANTE" -> registerEstudante(dto);
            case "COORDENADOR" -> registerCoordenador(dto);
            case "REPRESENTANTE" -> registerRepresentante(dto);
            default -> throw new IllegalArgumentException("Tipo inválido. Use ESTUDANTE, COORDENADOR ou REPRESENTANTE.");
        };
    }

    private Estudante registerEstudante(RegistroDTO dto) {
        if (dto.getCursoId() == null || dto.getCursoId().isBlank()) {
            throw new IllegalArgumentException("cursoId é obrigatório para estudante.");
        }
        return estudanteService.createFromRegister(dto);
    }

    private Coordenador registerCoordenador(RegistroDTO dto) {
        if (dto.getDepartamentoId() == null || dto.getDepartamentoId().isBlank()) {
            throw new IllegalArgumentException("departamentoId é obrigatório para coordenador.");
        }
        return coordenadorService.createFromRegister(dto);
    }

    private RepresentanteEmpresa registerRepresentante(RegistroDTO dto) {
        if (dto.getEmpresaId() == null || dto.getEmpresaId().isBlank()) {
            throw new IllegalArgumentException("empresaId é obrigatório para representante.");
        }
        return representanteService.createFromRegister(dto);
    }
}
