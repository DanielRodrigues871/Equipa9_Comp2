package com.upt.lp.portalestagios.service;

import com.upt.lp.portalestagios.entity.Utilizador;
import com.upt.lp.portalestagios.repository.UtilizadorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UtilizadorService {

    @Autowired
    private UtilizadorRepository utilizadorRepository;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    /** Buscar utilizador por email */
    public Optional<Utilizador> findByEmail(String email) {
        return utilizadorRepository.findByEmail(email);
    }

    /** Validar se email já está registado */
    public boolean emailExiste(String email) {
        return utilizadorRepository.findByEmail(email).isPresent();
    }

    /** Guardar utilizador (qualquer subclasse) */
    public Utilizador save(Utilizador utilizador) {
        return utilizadorRepository.save(utilizador);
    }

    /** Verificar se password enviada no login é válida */
    public boolean validarPassword(String passwordDigitada, String passwordGuardadaHash) {
        return encoder.matches(passwordDigitada, passwordGuardadaHash);
    }

    /** Alterar password */
    public void alterarPassword(Utilizador u, String novaPassword) {
        u.setPassword(encoder.encode(novaPassword));
        utilizadorRepository.save(u);
    }
}

