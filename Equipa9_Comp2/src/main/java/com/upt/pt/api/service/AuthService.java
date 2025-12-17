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
    //  REGISTO
    // =================================================================

    public Object register(RegistoDTO dto) {
        if (dto.getTipo() == null) {
            throw new IllegalArgumentException("Tipo de utilizador é obrigatório.");
        }

        String passwordHashed = PasswordUtils.hashPassword(dto.getPassword());
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
    //  LOGIN (CORRIGIDO AQUI!)
    // =================================================================

    public LoginResponseDTO login(LoginRequestDTO dto) {
        if (dto.getEmail() == null || dto.getPassword() == null) {
            throw new IllegalArgumentException("Email e password são obrigatórios.");
        }

        // 1) Tentar ESTUDANTE
        Optional<Estudante> estOpt = estudanteRepository.findByEmail(dto.getEmail());
        if (estOpt.isPresent()) {
            Estudante e = estOpt.get();
            if (PasswordUtils.verifyPassword(dto.getPassword(), e.getPassword())) {
                // Estudante não tem empresa, passamos null no fim
                return new LoginResponseDTO(String.valueOf(e.getId()), e.getNome(), e.getEmail(), "ESTUDANTE", null);
            }
        }

        // 2) Tentar COORDENADOR
        Optional<Coordenador> coordOpt = coordenadorRepository.findByEmail(dto.getEmail());
        if (coordOpt.isPresent()) {
            Coordenador c = coordOpt.get();
            if (PasswordUtils.verifyPassword(dto.getPassword(), c.getPassword())) {
                // Coordenador não tem empresa, passamos null no fim
                return new LoginResponseDTO(String.valueOf(c.getId()), c.getNome(), c.getEmail(), "COORDENADOR", null);
            }
        }

        // 3) Tentar REPRESENTANTE
        Optional<RepresentanteEmpresa> repOpt = representanteRepository.findByEmail(dto.getEmail());
        if (repOpt.isPresent()) {
            RepresentanteEmpresa r = repOpt.get();
            if (PasswordUtils.verifyPassword(dto.getPassword(), r.getPassword())) {
                
                // --- CORREÇÃO: Extrair o ID da empresa ---
                String empresaId = null;
                if (r.getEmpresa() != null) {
                    empresaId = String.valueOf(r.getEmpresa().getId());
                }

                // Passamos o empresaId para o DTO
                return new LoginResponseDTO(String.valueOf(r.getId()), r.getNome(), r.getEmail(), "REPRESENTANTE", empresaId);
            }
        }

        throw new IllegalArgumentException("Email ou password inválidos.");
    }
}