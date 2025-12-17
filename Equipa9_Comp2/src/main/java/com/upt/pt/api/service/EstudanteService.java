package com.upt.pt.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.upt.pt.api.dto.RegistoDTO;
import com.upt.pt.api.entity.Curso;
import com.upt.pt.api.entity.Estudante;
import com.upt.pt.api.repository.CursoRepository;
import com.upt.pt.api.repository.EstudanteRepository;
import com.upt.pt.api.security.PasswordUtils;

@Service
public class EstudanteService {

    private final EstudanteRepository estudanteRepository;
    private final CursoRepository cursoRepository;

    public EstudanteService(EstudanteRepository estudanteRepository,
                            CursoRepository cursoRepository) {
        this.estudanteRepository = estudanteRepository;
        this.cursoRepository = cursoRepository;
    }

    // CREATE (Usado por Admins, talvez?)
    // Aqui mantemos o hash porque assume-se que recebe password limpa
    public Estudante createEstudante(Estudante e, String cursoId) {
        validarDadosEstudante(e, cursoId, null);

        Curso curso = cursoRepository.findById(cursoId).get();
        e.setCurso(curso);

        if (e.getPassword() != null) {
            e.setPassword(PasswordUtils.hashPassword(e.getPassword()));
        }

        return estudanteRepository.save(e);
    }

    // READ todos
    public List<Estudante> getAllEstudantes() {
        return estudanteRepository.findAll();
    }
    
    public List<Estudante> getEstudantesByCurso(String cursoId) {
        return estudanteRepository.findByCursoId(cursoId);
    }

    // READ por id
    public Estudante getEstudanteById(String id) {
        return estudanteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Estudante não encontrado."));
    }

    // UPDATE
    public Estudante updateEstudante(String id, Estudante dados, String cursoId) {
        Estudante existente = getEstudanteById(id);

        validarDadosEstudante(dados, cursoId, id);

        Curso curso = cursoRepository.findById(cursoId).get();

        existente.setNome(dados.getNome());
        existente.setEmail(dados.getEmail());

        // Se estiver a atualizar a password, aí sim fazemos hash de novo
        if (dados.getPassword() != null && !dados.getPassword().isBlank()) {
            existente.setPassword(PasswordUtils.hashPassword(dados.getPassword()));
        }

        existente.setNumeroEstudante(dados.getNumeroEstudante());
        existente.setAnoMatricula(dados.getAnoMatricula());
        existente.setMedia(dados.getMedia());
        existente.setCurso(curso);
        existente.setCompetencias(dados.getCompetencias());

        return estudanteRepository.save(existente);
    }

    // DELETE
    public void deleteEstudante(String id) {
        Estudante e = getEstudanteById(id);
        estudanteRepository.delete(e);
    }

    public List<Estudante> getEstudantesComMediaMaiorQue9_5() {
        return estudanteRepository.findByMediaGreaterThanEqual(9.5);
    }

    public Estudante createFromRegister(RegistoDTO dto) {
        // Validações básicas...
        if (dto.getPassword() == null) {
            throw new IllegalArgumentException("Password é obrigatória.");
        }
        if (dto.getNumeroEstudante() == null || !dto.getNumeroEstudante().matches("\\d{5}")) {
            throw new IllegalArgumentException("O número de estudante deve ter exatamente 5 dígitos numéricos.");
        }
        if (dto.getAnoMatricula() == null || dto.getAnoMatricula() < 1) {
            throw new IllegalArgumentException("O ano de matrícula deve ser maior ou igual a 1.");
        }
        
        Curso curso = cursoRepository.findById(dto.getCursoId())
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado."));

        Estudante e = new Estudante();
        e.setNome(dto.getNome());
        e.setEmail(dto.getEmail());
        
        e.setPassword(dto.getPassword()); 
        
        e.setCurso(curso);
        e.setNumeroEstudante(dto.getNumeroEstudante());
        e.setAnoMatricula(dto.getAnoMatricula());
        e.setMedia(0.0);
        e.setCompetencias(new ArrayList<>());

        return estudanteRepository.save(e);
    }

    // Validação (mantive igual)
    private void validarDadosEstudante(Estudante e, String cursoId, String idAtual) {
        if (e == null) throw new IllegalArgumentException("Estudante não pode ser nulo.");
        if (e.getNome() == null || e.getNome().isBlank()) throw new IllegalArgumentException("O nome é obrigatório.");
        if (e.getEmail() == null || e.getEmail().isBlank() || !e.getEmail().contains("@")) 
            throw new IllegalArgumentException("O email é obrigatório e deve ser válido.");

        if (e.getPassword() != null) {
            // Se a password já começar por $2a$, é uma hash, não validamos a força
            if (!e.getPassword().startsWith("$2a$")) {
                PasswordUtils.validarPasswordForte(e.getPassword());
            }
        }

        if (e.getNumeroEstudante() == null || !e.getNumeroEstudante().matches("\\d{5}")) 
            throw new IllegalArgumentException("O número de estudante deve ter exatamente 5 dígitos numéricos.");
        if (e.getAnoMatricula() < 1) throw new IllegalArgumentException("O ano de matrícula deve ser maior ou igual a 1.");
        if (e.getMedia() < 0 || e.getMedia() > 20) throw new IllegalArgumentException("A média deve estar entre 0 e 20.");
        
        if (cursoId == null || cursoId.isBlank()) throw new IllegalArgumentException("O curso é obrigatório.");
        if (!cursoRepository.existsById(cursoId)) throw new IllegalArgumentException("O curso indicado não existe.");

        Optional<Estudante> existenteEmail = estudanteRepository.findByEmail(e.getEmail());
        if (existenteEmail.isPresent() && (idAtual == null || !existenteEmail.get().getId().equals(idAtual))) 
            throw new IllegalArgumentException("Já existe um estudante com esse email.");

        Optional<Estudante> existenteNumero = estudanteRepository.findByNumeroEstudante(e.getNumeroEstudante());
        if (existenteNumero.isPresent() && (idAtual == null || !existenteNumero.get().getId().equals(idAtual))) 
            throw new IllegalArgumentException("Já existe um estudante com esse número de estudante.");
    }
}