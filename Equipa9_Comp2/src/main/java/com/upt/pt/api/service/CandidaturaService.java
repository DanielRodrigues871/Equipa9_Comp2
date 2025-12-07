package com.upt.pt.api.service;

import java.time.LocalDateTime;
 
import java.util.List;

import org.springframework.stereotype.Service;

import com.upt.pt.api.entity.Candidatura;
import com.upt.pt.api.entity.Coordenador;
import com.upt.pt.api.entity.Estudante;
import com.upt.pt.api.entity.OfertaEstagio;
import com.upt.pt.api.enums.StatusCandidatura;
import com.upt.pt.api.repository.CandidaturaRepository;
import com.upt.pt.api.repository.CoordenadorRepository;
import com.upt.pt.api.repository.EstudanteRepository;
import com.upt.pt.api.repository.OfertaEstagioRepository;

@Service
public class CandidaturaService {

    private final CandidaturaRepository candidaturaRepository;
    private final EstudanteRepository estudanteRepository;
    private final OfertaEstagioRepository ofertaEstagioRepository;
    private final CoordenadorRepository coordenadorRepository;
    private final NotificacaoService notificacaoService;

    public CandidaturaService(CandidaturaRepository candidaturaRepository,
                              EstudanteRepository estudanteRepository,
                              OfertaEstagioRepository ofertaEstagioRepository,
                              CoordenadorRepository coordenadorRepository, NotificacaoService notificacaoService) {
        this.candidaturaRepository = candidaturaRepository;
        this.estudanteRepository = estudanteRepository;
        this.ofertaEstagioRepository = ofertaEstagioRepository;
        this.coordenadorRepository = coordenadorRepository;
        this.notificacaoService = notificacaoService;
    }

    // CREATE
    public Candidatura createCandidatura(Candidatura c,
                                         String estudanteId,
                                         String ofertaId) {
        validarDadosCandidatura(c, estudanteId, ofertaId);

        Estudante est = estudanteRepository.findById(estudanteId)
                .orElseThrow(() -> new IllegalArgumentException("Estudante não encontrado."));

        OfertaEstagio oferta = ofertaEstagioRepository.findById(ofertaId)
                .orElseThrow(() -> new IllegalArgumentException("Oferta não encontrada."));

        c.setEstudante(est);
        c.setOferta(oferta);
        c.setStatus(StatusCandidatura.SUBMETIDA);
        c.setDataSubmissao(LocalDateTime.now());

        
        
        Candidatura candidatura = candidaturaRepository.save(c);
        

     // ✅ NOTIFICAÇÃO: Coordenador responsável recebe notificação da nova candidatura
        Coordenador coord = oferta.getCoordenadorResponsavel();
        if (coord != null) {
            notificacaoService.enviar(
                coord.getId(),
                "Nova candidatura submetida",
                String.format("O estudante %s submeteu uma candidatura à oferta '%s'.", 
                             est.getNome(), oferta.getTitulo())
            );
        }

        return candidatura;
    }

    // READ todos
    public List<Candidatura> getAllCandidaturas() {
        return candidaturaRepository.findAll();
    }

    // READ por id
    public Candidatura getCandidaturaById(String id) {
        return candidaturaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Candidatura não encontrada."));
    }

    // READ por estudante
    public List<Candidatura> getCandidaturasByEstudante(String estudanteId) {
        return candidaturaRepository.findByEstudanteId(estudanteId);
    }

    // READ por oferta
    public List<Candidatura> getCandidaturasByOferta(String ofertaId) {
        return candidaturaRepository.findByOfertaId(ofertaId);
    }

    // UPDATE (dados base)
    public Candidatura updateCandidatura(String id, Candidatura dados) {
        Candidatura existente = getCandidaturaById(id);

        // só permitir alteração da carta/observações aqui
        validarCartaMotivacao(dados.getCartaMotivacao());
        existente.setCartaMotivacao(dados.getCartaMotivacao());
        existente.setObservacoes(dados.getObservacoes());

        return candidaturaRepository.save(existente);
    }

    // Ações de workflow

    public Candidatura colocarEmAnalise(String id, String coordenadorId) {
        Candidatura c = getCandidaturaById(id);

        Coordenador coord = coordenadorRepository.findById(coordenadorId)
                .orElseThrow(() -> new IllegalArgumentException("Coordenador não encontrado."));

        c.colocarEmAnalise();
        c.setCoordenadorResponsavel(coord);

        return candidaturaRepository.save(c);
    }

    public Candidatura aprovar(String id, String coordenadorId, String observacoes) {
        Candidatura c = getCandidaturaById(id);

        Coordenador coord = coordenadorRepository.findById(coordenadorId)
                .orElseThrow(() -> new IllegalArgumentException("Coordenador não encontrado."));

        c.aprovar();
        c.setCoordenadorResponsavel(coord);
        c.setObservacoes(observacoes);

        Candidatura candidatura = candidaturaRepository.save(c);

        // ✅ NOTIFICAÇÃO: Estudante é notificado da aprovação
        notificacaoService.enviar(
            c.getEstudante().getId(),
            "Candidatura aprovada!",
            String.format("A tua candidatura à oferta '%s' foi aprovada pelo coordenador.", 
                         c.getOferta().getTitulo())
        );

        return candidatura;
    }

    public Candidatura rejeitar(String id, String coordenadorId, String observacoes) {
        Candidatura c = getCandidaturaById(id);

        Coordenador coord = coordenadorRepository.findById(coordenadorId)
                .orElseThrow(() -> new IllegalArgumentException("Coordenador não encontrado."));

        if (observacoes == null || observacoes.isBlank()) {
            throw new IllegalArgumentException("Observações são obrigatórias para rejeitar.");
        }

        c.rejeitar(observacoes);
        c.setCoordenadorResponsavel(coord);

        Candidatura candidatura = candidaturaRepository.save(c);

        // ✅ NOTIFICAÇÃO: Estudante é notificado da rejeição
        notificacaoService.enviar(
            c.getEstudante().getId(),
            "Candidatura rejeitada",
            String.format("A tua candidatura à oferta '%s' foi rejeitada. Observações: %s", 
                         c.getOferta().getTitulo(), observacoes)
        );

        return candidatura;
    }

    // DELETE
    public void deleteCandidatura(String id) {
        Candidatura c = getCandidaturaById(id);
        candidaturaRepository.delete(c);
    }

    // =========================
    //   MÉTODOS DE VALIDAÇÃO
    // =========================
    private void validarDadosCandidatura(Candidatura c,
                                         String estudanteId,
                                         String ofertaId) {
        if (c == null) {
            throw new IllegalArgumentException("Candidatura não pode ser nula.");
        }

        if (estudanteId == null || estudanteId.isBlank()) {
            throw new IllegalArgumentException("Estudante obrigatório.");
        }
        if (!estudanteRepository.existsById(estudanteId)) {
            throw new IllegalArgumentException("Estudante indicado não existe.");
        }

        if (ofertaId == null || ofertaId.isBlank()) {
            throw new IllegalArgumentException("Oferta obrigatória.");
        }
        if (!ofertaEstagioRepository.existsById(ofertaId)) {
            throw new IllegalArgumentException("Oferta indicada não existe.");
        }

        validarCartaMotivacao(c.getCartaMotivacao());
    }

    private void validarCartaMotivacao(String carta) {
        if (carta == null || carta.isBlank()) {
            throw new IllegalArgumentException("Deves submeter uma carta de motivação.");
        }
        if (carta.length() < 50) {
            throw new IllegalArgumentException(
                    "A carta de motivação deve ter pelo menos 50 caracteres.");
        }
    }
}