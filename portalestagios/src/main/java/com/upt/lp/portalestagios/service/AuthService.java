package com.upt.lp.portalestagios.service;

import com.upt.lp.portalestagios.dto.auth.LoginRequestDTO;
import com.upt.lp.portalestagios.dto.auth.RegisterRequestDTO;
import com.upt.lp.portalestagios.entity.*;
import com.upt.lp.portalestagios.repository.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {

    private final UtilizadorRepository utilizadorRepo;
    private final EstudanteRepository estudanteRepo;
    private final CoordenadorRepository coordenadorRepo;
    private final RepresentanteEmpresaRepository representanteRepo;
    private final EmpresaRepository empresaRepo;
    private final DepartamentoRepository departamentoRepo;
    private final CursoRepository cursoRepo;

    private final BCryptPasswordEncoder encoder;

    public AuthService(UtilizadorRepository utilizadorRepo,
                       EstudanteRepository estudanteRepo,
                       CoordenadorRepository coordenadorRepo,
                       RepresentanteEmpresaRepository representanteRepo,
                       EmpresaRepository empresaRepo,
                       DepartamentoRepository departamentoRepo,
                       CursoRepository cursoRepo, BCryptPasswordEncoder encoder) {
        this.utilizadorRepo = utilizadorRepo;
        this.estudanteRepo = estudanteRepo;
        this.coordenadorRepo = coordenadorRepo;
        this.representanteRepo = representanteRepo;
        this.empresaRepo = empresaRepo;
        this.departamentoRepo = departamentoRepo;
        this.cursoRepo = cursoRepo;
        this.encoder = encoder;
    }

    // ---------------------------
    // LOGIN (mantive o mesmo comportamento)
    // ---------------------------
    public Utilizador login(LoginRequestDTO dto) {
        Optional<Utilizador> ou = utilizadorRepo.findByEmail(dto.getEmail());
        if (ou.isEmpty()) {
            System.out.println("Email não encontrado: " + dto.getEmail());
            return null;
        }
        Utilizador u = ou.get();

        System.out.println("Password digitada: [" + dto.getPassword() + "]");
        System.out.println("Password hash bd: [" + u.getPassword() + "]");
        System.out.println("Matches: " + encoder.matches(dto.getPassword(), u.getPassword()));

        if (!encoder.matches(dto.getPassword(), u.getPassword())) return null;
        return u;
    }

    // ---------------------------
    // REGISTER (cria Estudante | Coordenador | RepresentanteEmpresa)
    // ---------------------------
    public Utilizador register(RegisterRequestDTO dto) {
        // valida email
        if (utilizadorRepo.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email já registado");
        }

        String role = dto.getRole() == null ? "ESTUDANTE" : dto.getRole().toUpperCase().trim();

        switch (role) {
            case "COORDENADOR":
                return registerCoordenador(dto);

            case "REPRESENTANTE":
            case "REPRESENTANTEEMPRESA":
            case "REPRESENTANTE_EMPRESA":
                return registerRepresentante(dto);

            case "ESTUDANTE":
            default:
                return registerEstudante(dto);
        }
    }

    // ---------------------------
    // Helpers (registro por tipo)
    // ---------------------------
    private Utilizador registerEstudante(RegisterRequestDTO dto) {
        Estudante e = new Estudante();
        e.setNome(dto.getNome());
        e.setEmail(dto.getEmail());
        e.setPassword(encoder.encode(dto.getPassword()));
        e.setNumeroEstudante(dto.getNumeroEstudante());
        if (dto.getAnoMatricula() != null) e.setAnoMatricula(dto.getAnoMatricula());

        // associa curso se fornecido
        if (dto.getCursoId() != null && !dto.getCursoId().isBlank()) {
            UUID cursoId = UUID.fromString(dto.getCursoId());
            Curso curso = cursoRepo.findById(cursoId)
                    .orElseThrow(() -> new RuntimeException("Curso não encontrado"));
            e.setCurso(curso);
        }

        return estudanteRepo.save(e);
    }

    private Utilizador registerCoordenador(RegisterRequestDTO dto) {
        if (dto.getDepartamentoId() == null || dto.getDepartamentoId().isBlank()) {
            throw new RuntimeException("DepartamentoId é obrigatório para coordenador");
        }
        UUID depId = UUID.fromString(dto.getDepartamentoId());
        Departamento dep = departamentoRepo.findById(depId)
                .orElseThrow(() -> new RuntimeException("Departamento não encontrado"));

        Coordenador c = new Coordenador();
        c.setNome(dto.getNome());
        c.setEmail(dto.getEmail());
        c.setPassword(encoder.encode(dto.getPassword()));
        c.setDepartamento(dep);

        return coordenadorRepo.save(c);
    }

    private Utilizador registerRepresentante(RegisterRequestDTO dto) {
        // resolve empresa: se empresaId fornecido -> usa, senão cria nova com dados fornecidos
        Empresa empresa = null;
        if (dto.getEmpresaId() != null && !dto.getEmpresaId().isBlank()) {
            UUID empresaId = UUID.fromString(dto.getEmpresaId());
            empresa = empresaRepo.findById(empresaId)
                    .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));
        } else {
            // criar nova empresa se tiver nome pelo menos
            if (dto.getEmpresaNome() == null || dto.getEmpresaNome().isBlank()) {
                throw new RuntimeException("empresaId ou empresaNome são necessários para representante");
            }
            Empresa nova = new Empresa();
            nova.setNome(dto.getEmpresaNome());
            nova.setNif(dto.getEmpresaNif());
            nova.setEmail(dto.getEmpresaEmail());
            nova.setMorada(dto.getEmpresaMorada());
            empresa = empresaRepo.save(nova);
        }

        RepresentanteEmpresa r = new RepresentanteEmpresa();
        r.setNome(dto.getNome());
        r.setEmail(dto.getEmail());
        r.setPassword(encoder.encode(dto.getPassword()));
        r.setCargo(dto.getCargo());
        r.setEmpresa(empresa);

        return representanteRepo.save(r);
    }
    
    public List<Curso> listarCursos() {
        return cursoRepo.findAll();
    }

    public List<Departamento> listarDepartamentos() {
        return departamentoRepo.findAll();
    }

    public List<Empresa> listarEmpresas() {
        return empresaRepo.findAll();
    }

}
