package com.upt.pt.api.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import com.upt.pt.api.entity.Candidatura;
import com.upt.pt.api.entity.Coordenador;
import com.upt.pt.api.entity.Estudante;
import com.upt.pt.api.entity.OfertaEstagio;
import com.upt.pt.api.entity.PropostaEstagio;
import com.upt.pt.api.enums.StatusCandidatura;
import com.upt.pt.api.repository.CandidaturaRepository;
import com.upt.pt.api.repository.CoordenadorRepository;
import com.upt.pt.api.repository.EstudanteRepository;
import com.upt.pt.api.repository.OfertaEstagioRepository;
import com.upt.pt.api.repository.PropostaEstagioRepository;

@Service
public class CandidaturaService {

    private final CandidaturaRepository candidaturaRepository;
    private final EstudanteRepository estudanteRepository;
    private final OfertaEstagioRepository ofertaEstagioRepository;
    private final PropostaEstagioRepository propostaEstagioRepository;
    private final CoordenadorRepository coordenadorRepository;
    private final NotificacaoService notificacaoService;

    public CandidaturaService(CandidaturaRepository candidaturaRepository, EstudanteRepository estudanteRepository,
            OfertaEstagioRepository ofertaEstagioRepository, PropostaEstagioRepository propostaEstagioRepository,
            CoordenadorRepository coordenadorRepository, NotificacaoService notificacaoService) {
        this.candidaturaRepository = candidaturaRepository;
        this.estudanteRepository = estudanteRepository;
        this.ofertaEstagioRepository = ofertaEstagioRepository;
        this.propostaEstagioRepository = propostaEstagioRepository;
        this.coordenadorRepository = coordenadorRepository;
        this.notificacaoService = notificacaoService;
    }

    public Candidatura criarCandidatura(String estudanteId, String ofertaId, String cartaMotivacao) {
        Candidatura c = new Candidatura();
        c.setCartaMotivacao(cartaMotivacao);
        return createCandidatura(c, estudanteId, ofertaId);
    }

    public Candidatura createCandidaturaSimples(String estudanteId, String ofertaId) {
        Candidatura c = new Candidatura();
        c.setObservacoes("Candidatura automática via JavaFX");
        return createCandidatura(c, estudanteId, ofertaId);
    }

    public Candidatura createCandidaturaPropostaSimples(String estudanteId, String propostaId) {
        Candidatura c = new Candidatura();
        c.setObservacoes("Candidatura automática via JavaFX");
        return createCandidaturaProposta(c, estudanteId, propostaId);
    }

    // --- MÉTODOS DE CRIAÇÃO (Direto com String) ---

    public Candidatura createCandidatura(Candidatura c, String estudanteId, String ofertaId) {
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

        // Notificação (apenas se houver coordenador)
        Coordenador coord = oferta.getCoordenadorResponsavel();
        if (coord != null) {
             notificacaoService.enviar(coord.getId(), "Nova candidatura", 
                 "O estudante " + est.getNome() + " candidatou-se à oferta " + oferta.getTitulo());
        }

        return candidatura;
    }

    public Candidatura createCandidaturaProposta(Candidatura c, String estudanteId, String propostaId) {
        validarDadosCandidaturaProposta(c, estudanteId, propostaId);

        Estudante est = estudanteRepository.findById(estudanteId)
                .orElseThrow(() -> new IllegalArgumentException("Estudante não encontrado."));

        PropostaEstagio proposta = propostaEstagioRepository.findById(propostaId)
                .orElseThrow(() -> new IllegalArgumentException("Proposta não encontrada."));

        c.setEstudante(est);
        // c.setProposta(proposta); // Comentado se não tiver o campo na entidade
        
        c.setStatus(StatusCandidatura.SUBMETIDA);
        c.setDataSubmissao(LocalDateTime.now());

        return candidaturaRepository.save(c);
    }

    // --- MÉTODOS DE LEITURA (String direta) ---

    public List<Candidatura> getAllCandidaturas() {
        return candidaturaRepository.findAll();
    }

    public Candidatura getCandidaturaById(String id) {
        return candidaturaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Candidatura não encontrada."));
    }

    public List<Candidatura> getCandidaturasByEstudante(String estudanteId) {
        return candidaturaRepository.findByEstudanteId(estudanteId);
    }

    public List<Candidatura> getCandidaturasByOferta(String ofertaId) {
        return candidaturaRepository.findByOfertaId(ofertaId);
    }

    // --- UPDATE E WORKFLOW ---

    public Candidatura updateCandidatura(String id, Candidatura dados) {
        Candidatura existente = getCandidaturaById(id);
        validarCartaMotivacao(dados.getCartaMotivacao());
        existente.setCartaMotivacao(dados.getCartaMotivacao());
        existente.setObservacoes(dados.getObservacoes());
        return candidaturaRepository.save(existente);
    }

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
        
        notificacaoService.enviar(c.getEstudante().getId(), "Candidatura aprovada!", String
                .format("A tua candidatura à oferta '%s' foi aprovada.", c.getOferta().getTitulo()));
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
        
        notificacaoService.enviar(c.getEstudante().getId(), "Candidatura rejeitada",
                String.format("A tua candidatura à oferta '%s' foi rejeitada.", c.getOferta().getTitulo()));
        return candidatura;
    }

    public void deleteCandidatura(String id) {
        Candidatura c = getCandidaturaById(id);
        candidaturaRepository.delete(c);
    }

    // --- VALIDAÇÕES ---

    private void validarDadosCandidatura(Candidatura c, String estudanteId, String ofertaId) {
        if (c == null) throw new IllegalArgumentException("Candidatura não pode ser nula.");
        if (estudanteId == null || estudanteId.isBlank()) throw new IllegalArgumentException("Estudante obrigatório.");
        if (!estudanteRepository.existsById(estudanteId)) throw new IllegalArgumentException("Estudante indicado não existe.");
        if (ofertaId == null || ofertaId.isBlank()) throw new IllegalArgumentException("Oferta obrigatória.");
        if (!ofertaEstagioRepository.existsById(ofertaId)) throw new IllegalArgumentException("Oferta indicada não existe.");
        validarCartaMotivacao(c.getCartaMotivacao());
    }

    private void validarDadosCandidaturaProposta(Candidatura c, String estudanteId, String propostaId) {
        if (c == null) throw new IllegalArgumentException("Candidatura não pode ser nula.");
        if (estudanteId == null || estudanteId.isBlank()) throw new IllegalArgumentException("Estudante obrigatório.");
        if (!estudanteRepository.existsById(estudanteId)) throw new IllegalArgumentException("Estudante indicado não existe.");
        if (propostaId == null || propostaId.isBlank()) throw new IllegalArgumentException("Proposta obrigatória.");
        if (!propostaEstagioRepository.existsById(propostaId)) throw new IllegalArgumentException("Proposta indicada não existe.");
        validarCartaMotivacao(c.getCartaMotivacao());
    }

    private void validarCartaMotivacao(String carta) {
        if (carta == null || carta.isBlank()) {
            throw new IllegalArgumentException("Deves submeter uma carta de motivação.");
        }
        if (carta.length() < 10) { 
            throw new IllegalArgumentException("A carta de motivação é muito curta.");
        }
    }
}