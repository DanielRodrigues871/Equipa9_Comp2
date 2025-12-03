package com.upt.lp.componente2.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.upt.lp.componente2.entity.Documento;
import com.upt.lp.componente2.entity.Estudante;
import com.upt.lp.componente2.repository.DocumentoRepository;
import com.upt.lp.componente2.repository.EstudanteRepository;

@Service
public class DocumentoService {

    private final DocumentoRepository documentoRepository;
    private final EstudanteRepository estudanteRepository;

    public DocumentoService(DocumentoRepository documentoRepository,
                            EstudanteRepository estudanteRepository) {
        this.documentoRepository = documentoRepository;
        this.estudanteRepository = estudanteRepository;
    }

    // CREATE
    public Documento createDocumento(Documento d, String estudanteId) {
        validarDadosDocumento(d, estudanteId, null);

        Estudante est = estudanteRepository.findById(estudanteId)
                .orElseThrow(() -> new IllegalArgumentException("Estudante não encontrado."));

        d.setEstudante(est);
        d.setDataUpload(LocalDateTime.now());

        return documentoRepository.save(d);
    }

    // READ todos
    public List<Documento> getAllDocumentos() {
        return documentoRepository.findAll();
    }

    // READ por id
    public Documento getDocumentoById(String id) {
        return documentoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Documento não encontrado."));
    }

    // READ por estudante
    public List<Documento> getDocumentosByEstudante(String estudanteId) {
        return documentoRepository.findByEstudanteId(estudanteId);
    }

    // UPDATE
    public Documento updateDocumento(String id, Documento dados, String estudanteId) {
        Documento existente = getDocumentoById(id);

        validarDadosDocumento(dados, estudanteId, id);

        Estudante est = estudanteRepository.findById(estudanteId)
                .orElseThrow(() -> new IllegalArgumentException("Estudante não encontrado."));

        existente.setNomeEmpresa(dados.getNomeEmpresa());
        existente.setContactoEmpresa(dados.getContactoEmpresa());
        existente.setObjetivoEstagio(dados.getObjetivoEstagio());
        existente.setEstudante(est);
        // dataUpload pode manter o original ou ser atualizado, consoante a regra
        return documentoRepository.save(existente);
    }

    // DELETE
    public void deleteDocumento(String id) {
        Documento d = getDocumentoById(id);
        documentoRepository.delete(d);
    }

    // =========================
    //   MÉTODO DE VALIDAÇÃO
    // =========================
    private void validarDadosDocumento(Documento d, String estudanteId, String idAtual) {
        if (d == null) {
            throw new IllegalArgumentException("Documento não pode ser nulo.");
        }

        if (d.getNomeEmpresa() == null || d.getNomeEmpresa().isBlank()) {
            throw new IllegalArgumentException("O nome da empresa é obrigatório.");
        }

        String tel = d.getContactoEmpresa();
        if (tel == null || tel.isBlank()) {
            throw new IllegalArgumentException("O contacto da empresa é obrigatório.");
        }
        tel = tel.replaceAll("\\s+", "");
        if (!tel.matches("^(251\\d{6}|9\\d{8})$")) {
            throw new IllegalArgumentException(
                    "O contacto deve ter 9 dígitos e começar por 251 ou por 9.");
        }

        String obj = d.getObjetivoEstagio();
        if (obj == null || obj.isBlank()) {
            throw new IllegalArgumentException("O objetivo do estágio é obrigatório.");
        }
        if (obj.length() > 1000) {
            throw new IllegalArgumentException(
                    "O objetivo do estágio deve ter no máximo 1000 caracteres.");
        }

        if (estudanteId == null || estudanteId.isBlank()) {
            throw new IllegalArgumentException("O estudante é obrigatório.");
        }
        if (!estudanteRepository.existsById(estudanteId)) {
            throw new IllegalArgumentException("O estudante indicado não existe.");
        }
    }
}