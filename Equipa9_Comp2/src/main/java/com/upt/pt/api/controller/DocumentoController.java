package com.upt.pt.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.upt.pt.api.dto.DocumentoDTO;
import com.upt.pt.api.entity.Documento;
import com.upt.pt.api.mapper.DocumentoMapper;
import com.upt.pt.api.service.DocumentoService;

@RestController
@RequestMapping("/api/documentos")
public class DocumentoController {

    private final DocumentoService documentoService;

    public DocumentoController(DocumentoService documentoService) {
        this.documentoService = documentoService;
    }

    // CREATE
    // POST /api/documentos?estudanteId=XYZ
    @PostMapping
    public ResponseEntity<DocumentoDTO> create(@RequestBody DocumentoDTO dto,
                                               @RequestParam String estudanteId) {

        Documento entidade = DocumentoMapper.toEntity(dto);
        Documento criado = documentoService.createDocumento(entidade, estudanteId);
        DocumentoDTO resposta = DocumentoMapper.toDTO(criado);

        return new ResponseEntity<>(resposta, HttpStatus.CREATED);
    }

    // READ todos
    // GET /api/documentos
    @GetMapping
    public List<DocumentoDTO> getAll() {
        return documentoService.getAllDocumentos()
                .stream()
                .map(DocumentoMapper::toDTO)
                .toList();
    }

    // READ por id
    // GET /api/documentos/{id}
    @GetMapping("/{id}")
    public DocumentoDTO getById(@PathVariable String id) {
        Documento d = documentoService.getDocumentoById(id);
        return DocumentoMapper.toDTO(d);
    }

    // READ por estudante
    // GET /api/documentos/estudante/{estudanteId}
    @GetMapping("/estudante/{estudanteId}")
    public List<DocumentoDTO> getByEstudante(@PathVariable String estudanteId) {
        return documentoService.getDocumentosByEstudante(estudanteId)
                .stream()
                .map(DocumentoMapper::toDTO)
                .toList();
    }

    // UPDATE
    // PUT /api/documentos/{id}?estudanteId=XYZ
    @PutMapping("/{id}")
    public DocumentoDTO update(@PathVariable String id, @RequestBody DocumentoDTO dto, @RequestParam String estudanteId) {

        Documento dados = DocumentoMapper.toEntity(dto);
        Documento atualizado =
                documentoService.updateDocumento(id, dados, estudanteId);

        return DocumentoMapper.toDTO(atualizado);
    }

    // DELETE
    // DELETE /api/documentos/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        documentoService.deleteDocumento(id);
        return ResponseEntity.noContent().build();
    }
}
