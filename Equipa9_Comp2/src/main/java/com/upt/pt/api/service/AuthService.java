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

// --- IMPORTAR OS MAPPERS AQUI ---
import com.upt.pt.api.mapper.CoordenadorMapper;
// Certifique-se que cria estes dois abaixo também:
import com.upt.pt.api.mapper.EstudanteMapper;
import com.upt.pt.api.mapper.RepresentanteEmpresaMapper;

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
    //  REGISTO (CORRIGIDO PARA EVITAR RECURSÃO)
    // =================================================================

    public Object register(RegistoDTO dto) {
        if (dto.getTipo() == null) {
            throw new IllegalArgumentException("Tipo de utilizador é obrigatório.");
        }

        String passwordHashed = PasswordUtils.hashPassword(dto.getPassword());
        dto.setPassword(passwordHashed);

        String tipo = dto.getTipo().toUpperCase();

        // O segredo é converter para DTO antes de devolver ao Controller.
        // Assim o Jackson não tenta serializar o Departamento/Curso/Empresa completos.
        return switch (tipo) {
            case "ESTUDANTE" -> {
                Estudante e = estudanteService.createFromRegister(dto);
                yield EstudanteMapper.toDTO(e); // Converter para DTO
            }
            case "COORDENADOR" -> {
                Coordenador c = coordenadorService.createFromRegister(dto);
                yield CoordenadorMapper.toDTO(c); // Converter para DTO
            }
            case "REPRESENTANTE" -> {
                RepresentanteEmpresa r = representanteService.createFromRegister(dto);
                yield RepresentanteEmpresaMapper.toDTO(r); // Converter para DTO
            }
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
            if (PasswordUtils.verifyPassword(dto.getPassword(), e.getPassword())) {
                return new LoginResponseDTO(String.valueOf(e.getId()), e.getNome(), e.getEmail(), "ESTUDANTE", null);
            }
        }

        // 2) Tentar COORDENADOR
        Optional<Coordenador> coordOpt = coordenadorRepository.findByEmail(dto.getEmail());
        if (coordOpt.isPresent()) {
            Coordenador c = coordOpt.get();
            if (PasswordUtils.verifyPassword(dto.getPassword(), c.getPassword())) {
                return new LoginResponseDTO(String.valueOf(c.getId()), c.getNome(), c.getEmail(), "COORDENADOR", null);
            }
        }

        // 3) Tentar REPRESENTANTE
        Optional<RepresentanteEmpresa> repOpt = representanteRepository.findByEmail(dto.getEmail());
        if (repOpt.isPresent()) {
            RepresentanteEmpresa r = repOpt.get();
            if (PasswordUtils.verifyPassword(dto.getPassword(), r.getPassword())) {
                
                String empresaId = null;
                if (r.getEmpresa() != null) {
                    empresaId = String.valueOf(r.getEmpresa().getId());
                }

                return new LoginResponseDTO(String.valueOf(r.getId()), r.getNome(), r.getEmail(), "REPRESENTANTE", empresaId);
            }
        }

        throw new IllegalArgumentException("Email ou password inválidos.");
    }
}