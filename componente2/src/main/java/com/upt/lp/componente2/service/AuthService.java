package com.upt.lp.componente2.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.upt.lp.componente2.dto.LoginRequestDTO;
import com.upt.lp.componente2.dto.LoginResponseDTO;
import com.upt.lp.componente2.dto.RegistoDTO;
import com.upt.lp.componente2.entity.Coordenador;
import com.upt.lp.componente2.entity.Estudante;
import com.upt.lp.componente2.entity.RepresentanteEmpresa;
import com.upt.lp.componente2.repository.CoordenadorRepository;
import com.upt.lp.componente2.repository.EstudanteRepository;
import com.upt.lp.componente2.repository.RepresentanteEmpresaRepository;
import com.upt.lp.componente2.security.PasswordUtils;

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

    // -------- REGISTO  --------

    public Object register(RegistoDTO dto) {
        if (dto.getTipo() == null) {
            throw new IllegalArgumentException("Tipo de utilizador é obrigatório.");
        }

        String tipo = dto.getTipo().toUpperCase();

        return switch (tipo) {
            case "ESTUDANTE"     -> estudanteService.createFromRegister(dto);
            case "COORDENADOR"   -> coordenadorService.createFromRegister(dto);
            case "REPRESENTANTE" -> representanteService.createFromRegister(dto);
            default -> throw new IllegalArgumentException("Tipo inválido. Use ESTUDANTE, COORDENADOR ou REPRESENTANTE.");
        };
    }

    // -------- LOGIN --------

    public LoginResponseDTO login(LoginRequestDTO dto) {
        if (dto.getEmail() == null || dto.getPassword() == null) {
            throw new IllegalArgumentException("Email e password são obrigatórios.");
        }

        System.out.println("LOGIN DTO email=" + dto.getEmail());
        System.out.println("LOGIN DTO password=" + dto.getPassword());

        // 1) tentar estudante
        Optional<Estudante> estOpt = estudanteRepository.findByEmail(dto.getEmail());
        System.out.println("Encontrou estudante? " + estOpt.isPresent());

        if (estOpt.isPresent()) {
            Estudante e = estOpt.get();
            System.out.println("Password BD (estudante)=" + e.getPassword());
            boolean ok = PasswordUtils.verifyPassword(dto.getPassword(), e.getPassword());
            System.out.println("verify estudante = " + ok);
            if (ok) {
                return new LoginResponseDTO(e.getId(), e.getNome(), e.getEmail(), "ESTUDANTE");
            }
        }

        // 2) tentar coordenador
        Optional<Coordenador> coordOpt = coordenadorRepository.findByEmail(dto.getEmail());
        System.out.println("Encontrou coordenador? " + coordOpt.isPresent());

        if (coordOpt.isPresent()) {
            Coordenador c = coordOpt.get();
            System.out.println("Password BD (coord)=" + c.getPassword());
            boolean ok = PasswordUtils.verifyPassword(dto.getPassword(), c.getPassword());
            System.out.println("verify coord = " + ok);
            if (ok) {
                return new LoginResponseDTO(c.getId(), c.getNome(), c.getEmail(), "COORDENADOR");
            }
        }

        // 3) tentar representante
        Optional<RepresentanteEmpresa> repOpt = representanteRepository.findByEmail(dto.getEmail());
        System.out.println("Encontrou representante? " + repOpt.isPresent());

        if (repOpt.isPresent()) {
            RepresentanteEmpresa r = repOpt.get();
            System.out.println("Password BD (rep)=" + r.getPassword());
            boolean ok = PasswordUtils.verifyPassword(dto.getPassword(), r.getPassword());
            System.out.println("verify rep = " + ok);
            if (ok) {
                return new LoginResponseDTO(r.getId(), r.getNome(), r.getEmail(), "REPRESENTANTE");
            }
        }

        System.out.println("LOGIN FALHOU: email ou password inválidos.");
        throw new IllegalArgumentException("Email ou password inválidos.");
    }
}