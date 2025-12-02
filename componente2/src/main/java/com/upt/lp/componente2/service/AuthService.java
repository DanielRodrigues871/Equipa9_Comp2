package com.upt.lp.componente2.service;

import com.upt.lp.componente2.entity.Coordenador;
import com.upt.lp.componente2.entity.Estudante;
import com.upt.lp.componente2.entity.RepresentanteEmpresa;
import com.upt.lp.componente2.repository.CoordenadorRepository;
import com.upt.lp.componente2.repository.EstudanteRepository;
import com.upt.lp.componente2.repository.RepresentanteEmpresaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    
    private final EstudanteRepository estudanteRepository;
    private final CoordenadorRepository coordenadorRepository;
    private final RepresentanteEmpresaRepository representanteRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    
    public AuthService(EstudanteRepository estudanteRepository,
                      CoordenadorRepository coordenadorRepository,
                      RepresentanteEmpresaRepository representanteRepository) {
        this.estudanteRepository = estudanteRepository;
        this.coordenadorRepository = coordenadorRepository;
        this.representanteRepository = representanteRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }
    
    public Estudante authenticateEstudante(String email, String password) {
        return estudanteRepository.findByEmail(email)
                .filter(estudante -> passwordEncoder.matches(password, estudante.getPassword()))
                .orElse(null);
    }
    
    public Coordenador authenticateCoordenador(String email, String password) {
        return coordenadorRepository.findByEmail(email)
                .filter(coordenador -> passwordEncoder.matches(password, coordenador.getPassword()))
                .orElse(null);
    }
    
    public RepresentanteEmpresa authenticateRepresentante(String email, String password) {
        return representanteRepository.findByEmail(email)
                .filter(rep -> passwordEncoder.matches(password, rep.getPassword()))
                .orElse(null);
    }
}
