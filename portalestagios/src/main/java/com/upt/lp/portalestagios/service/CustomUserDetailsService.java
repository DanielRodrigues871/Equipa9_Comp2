package com.upt.lp.portalestagios.service;

import com.upt.lp.portalestagios.entity.Utilizador;
import com.upt.lp.portalestagios.repository.UtilizadorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UtilizadorRepository repo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Utilizador u = repo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Email não encontrado"));

        return User.builder()
                .username(u.getEmail())
                .password(u.getPassword())
                .roles("USER")
                .build();
    }
}

