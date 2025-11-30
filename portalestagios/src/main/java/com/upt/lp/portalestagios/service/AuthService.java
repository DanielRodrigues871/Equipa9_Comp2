package com.upt.lp.portalestagios.service;

import com.upt.lp.portalestagios.entity.Utilizador;
import com.upt.lp.portalestagios.repository.UtilizadorRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UtilizadorRepository repo;

    public AuthService(UtilizadorRepository repo) {
        this.repo = repo;
    }

    public Utilizador login(String email, String password) {
        return repo.findByEmailAndPassword(email, password).orElse(null);
    }
}

