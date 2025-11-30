package com.upt.lp.portalestagios.service;

import com.upt.lp.portalestagios.dto.auth.LoginRequestDTO;
import com.upt.lp.portalestagios.entity.Utilizador;
import com.upt.lp.portalestagios.repository.UtilizadorRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UtilizadorRepository repo;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public AuthService(UtilizadorRepository repo) {
        this.repo = repo;
    }

    public Utilizador login(LoginRequestDTO dto) {

        Utilizador u = repo.findByEmail(dto.getEmail())
                .orElse(null);

        if (u == null)
            return null;

        if (!encoder.matches(dto.getPassword(), u.getPassword()))
            return null;

        return u;
    }
}

