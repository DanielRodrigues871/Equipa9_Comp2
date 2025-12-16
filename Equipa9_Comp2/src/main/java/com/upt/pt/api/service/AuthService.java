package com.upt.pt.api.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.upt.pt.api.dto.LoginRequestDTO;
import com.upt.pt.api.dto.LoginResponseDTO;
import com.upt.pt.api.dto.RegistoDTO;
import com.upt.pt.api.entity.Coordenador;
import com.upt.pt.api.entity.Estudante;
import com.upt.pt.api.entity.RepresentanteEmpresa;
import com.upt.pt.api.repository.CoordenadorRepository;
import com.upt.pt.api.repository.EstudanteRepository;
import com.upt.pt.api.repository.RepresentanteEmpresaRepository;
import com.upt.pt.api.security.PasswordUtils;

@Service
public class AuthService {

    private final EstudanteService estudanteService;
    private final CoordenadorService coordenadorService;
    private final RepresentanteEmpresaService representanteService;

    private final EstudanteRepository estudanteRepository;
    private final CoordenadorRepository coordenadorRepository;
    private final RepresentanteEmpresaRepository representanteRepository;

    public AuthService(EstudanteService estudanteService,
                       CoordenadorService coordenadorService,
                       RepresentanteEmpresaService representanteService,
                       EstudanteRepository estudanteRepository,
                       CoordenadorRepository coordenadorRepository,
                       RepresentanteEmpresaRepository representanteRepository) {
        this.estudanteService = estudanteService;
        this.coordenadorService = coordenadorService;
        this.representanteService = representanteService;
        this.estudanteRepository = estudanteRepository;
        this.coordenadorRepository = coordenadorRepository;
        this.representanteRepository = representanteRepository;
    }

    // =================================================================
    //  REGISTO (SOLUÇÃO AQUI)
    // =================================================================

    public Object register(RegistoDTO dto) {
        if (dto.getTipo() == null) {
            throw new IllegalArgumentException("Tipo de utilizador é obrigatório.");
        }

        // --- SOLUÇÃO CRÍTICA ---
        // 1. Geramos a Hash segura (BCrypt) usando a password limpa
        // O método hashPassword já valida se a senha é forte.
        String passwordHashed = PasswordUtils.hashPassword(dto.getPassword());
        
        // 2. Substituímos a password no DTO pela Hash
        // Assim, quando o 'estudanteService' salvar, guarda a Hash e não o texto limpo
        dto.setPassword(passwordHashed);

        String tipo = dto.getTipo().toUpperCase();

        return switch (tipo) {
            case "ESTUDANTE"      -> estudanteService.createFromRegister(dto);
            case "COORDENADOR"    -> coordenadorService.createFromRegister(dto);
            case "REPRESENTANTE"  -> representanteService.createFromRegister(dto);
            default -> throw new IllegalArgumentException("Tipo inválido. Use ESTUDANTE, COORDENADOR ou REPRESENTANTE.");
        };
    }

    // =================================================================
    //  LOGIN
    // =================================================================

    public LoginResponseDTO login(LoginRequestDTO dto) {
        if (dto.getEmail() == null || dto.getPassword() == null) {
            throw new IllegalArgumentException("Email e password são obrigatórios.");
        }

        // 1) Tentar ESTUDANTE
        Optional<Estudante> estOpt = estudanteRepository.findByEmail(dto.getEmail());
        if (estOpt.isPresent()) {
            Estudante e = estOpt.get();
            // verifyPassword compara a pass limpa (dto) com a hash da BD (e.getPassword)
            if (PasswordUtils.verifyPassword(dto.getPassword(), e.getPassword())) {
                // Sucesso! Convertemos o ID para String
                return new LoginResponseDTO(String.valueOf(e.getId()), e.getNome(), e.getEmail(), "ESTUDANTE");
            }
        }

        // 2) Tentar COORDENADOR
        Optional<Coordenador> coordOpt = coordenadorRepository.findByEmail(dto.getEmail());
        if (coordOpt.isPresent()) {
            Coordenador c = coordOpt.get();
            if (PasswordUtils.verifyPassword(dto.getPassword(), c.getPassword())) {
                return new LoginResponseDTO(String.valueOf(c.getId()), c.getNome(), c.getEmail(), "COORDENADOR");
            }
        }

        // 3) Tentar REPRESENTANTE
        Optional<RepresentanteEmpresa> repOpt = representanteRepository.findByEmail(dto.getEmail());
        if (repOpt.isPresent()) {
            RepresentanteEmpresa r = repOpt.get();
            if (PasswordUtils.verifyPassword(dto.getPassword(), r.getPassword())) {
                return new LoginResponseDTO(String.valueOf(r.getId()), r.getNome(), r.getEmail(), "REPRESENTANTE");
            }
        }

        // Se chegou aqui, não encontrou email ou a password não bateu certo com nenhum
        throw new IllegalArgumentException("Email ou password inválidos.");
    }
}