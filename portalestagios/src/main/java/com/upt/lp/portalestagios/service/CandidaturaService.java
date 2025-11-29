package com.upt.lp.portalestagios.service;

import com.upt.lp.portalestagios.dto.candidatura.CandidaturaRequestDTO;
import com.upt.lp.portalestagios.dto.candidatura.CandidaturaResponseDTO;
import com.upt.lp.portalestagios.entity.Candidatura;
import com.upt.lp.portalestagios.entity.Estudante;
import com.upt.lp.portalestagios.entity.OfertaEstagio;
import com.upt.lp.portalestagios.mapper.CandidaturaMapper;
import com.upt.lp.portalestagios.repository.CandidaturaRepository;
import com.upt.lp.portalestagios.repository.EstudanteRepository;
import com.upt.lp.portalestagios.repository.OfertaEstagioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CandidaturaService {

    private final CandidaturaRepository repo;
    private final EstudanteRepository estudanteRepo;
    private final OfertaEstagioRepository ofertaRepo;

    public CandidaturaService(CandidaturaRepository repo,
                              EstudanteRepository estudanteRepo,
                              OfertaEstagioRepository ofertaRepo) {
        this.repo = repo;
        this.estudanteRepo = estudanteRepo;
        this.ofertaRepo = ofertaRepo;
    }

    // listar todas em DTO
    public List<CandidaturaResponseDTO> listar() {
        return repo.findAll().stream()
                .map(CandidaturaMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    // buscar por id (UUID)
    public CandidaturaResponseDTO buscar(UUID id) {
        Candidatura c = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidatura não encontrada"));
        return CandidaturaMapper.toResponseDTO(c);
    }

    // criar: recebe request DTO, resolve estudante/oferta, associa e guarda
    public CandidaturaResponseDTO criar(CandidaturaRequestDTO dto) {
        Estudante est = estudanteRepo.findById(dto.getEstudanteId())
                .orElseThrow(() -> new RuntimeException("Estudante não encontrado"));
        OfertaEstagio oferta = ofertaRepo.findById(dto.getOfertaId())
                .orElseThrow(() -> new RuntimeException("Oferta não encontrada"));

        Candidatura c = CandidaturaMapper.fromRequestDTO(dto);
        c.setEstudante(est);
        c.setOferta(oferta);

        // status e dataSubmissao são configurados no construtor de Candidatura
        Candidatura salvo = repo.save(c);
        return CandidaturaMapper.toResponseDTO(salvo);
    }

    // atualizar (parcial) — usa UUID
    public CandidaturaResponseDTO atualizar(UUID id, CandidaturaRequestDTO dto) {
        Candidatura c = repo.findById(id).orElseThrow(() -> new RuntimeException("Candidatura não encontrada"));

        if (dto.getCartaMotivacao() != null) c.setCartaMotivacao(dto.getCartaMotivacao());
        if (dto.getObservacoes() != null) c.setObservacoes(dto.getObservacoes());

        // se quiseres permitir alterar estudante/oferta via update, faz lookup e set aqui

        Candidatura atualizado = repo.save(c);
        return CandidaturaMapper.toResponseDTO(atualizado);
    }

    public void apagar(UUID id) {
        repo.deleteById(id);
    }

    // Ações de negócio
    public CandidaturaResponseDTO colocarEmAnalise(UUID id) {
        Candidatura c = repo.findById(id).orElseThrow();
        c.colocarEmAnalise();
        return CandidaturaMapper.toResponseDTO(repo.save(c));
    }

    public CandidaturaResponseDTO aprovar(UUID id) {
        Candidatura c = repo.findById(id).orElseThrow();
        c.aprovar();
        return CandidaturaMapper.toResponseDTO(repo.save(c));
    }

    public CandidaturaResponseDTO rejeitar(UUID id, String motivo) {
        Candidatura c = repo.findById(id).orElseThrow();
        c.rejeitar(motivo);
        return CandidaturaMapper.toResponseDTO(repo.save(c));
    }
}
