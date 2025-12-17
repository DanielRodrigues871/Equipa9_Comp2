package com.upt.pt.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.upt.pt.api.dto.OfertaEstagioDTO;
import com.upt.pt.api.entity.OfertaEstagio;
import com.upt.pt.api.enums.StatusOferta;
import com.upt.pt.api.mapper.OfertaEstagioMapper;
import com.upt.pt.api.service.OfertaEstagioService;

@RestController
@RequestMapping("/api/ofertas")
public class OfertaEstagioController {

    private final OfertaEstagioService ofertaService;

    public OfertaEstagioController(OfertaEstagioService ofertaService) {
        this.ofertaService = ofertaService;
    }

    // CREATE
    // POST /api/ofertas?empresaId=E&areaId=A&cursoId=C&coordenadorId=K
    @PostMapping
    public ResponseEntity<OfertaEstagioDTO> create(@RequestBody OfertaEstagioDTO dto, @RequestParam String empresaId,
                                                   @RequestParam(required = false) String areaId,
                                                   @RequestParam(required = false) String cursoId,
                                                   @RequestParam(required = false) String coordenadorId) {

        OfertaEstagio entidade = OfertaEstagioMapper.toEntity(dto);
        OfertaEstagio criada =
                ofertaService.createOferta(entidade, empresaId, areaId, cursoId, coordenadorId);

        OfertaEstagioDTO resposta = OfertaEstagioMapper.toDTO(criada);
        return new ResponseEntity<>(resposta, HttpStatus.CREATED);
    }

    // READ todos
    // GET /api/ofertas
    @GetMapping
    public List<OfertaEstagioDTO> getAll() {
        return ofertaService.getAllOfertas()
                .stream()
                .map(OfertaEstagioMapper::toDTO)
                .toList();
    }

    // READ por id
    // GET /api/ofertas/{id}
    @GetMapping("/{id}")
    public OfertaEstagioDTO getById(@PathVariable String id) {
        OfertaEstagio o = ofertaService.getOfertaById(id);
        return OfertaEstagioMapper.toDTO(o);
    }

    // READ por empresa
    // GET /api/ofertas/empresa/{empresaId}
    @GetMapping("/empresa/{empresaId}")
    public List<OfertaEstagioDTO> getByEmpresa(@PathVariable String empresaId) {
        return ofertaService.getOfertasByEmpresa(empresaId)
                .stream()
                .map(OfertaEstagioMapper::toDTO)
                .toList();
    }

    // READ por curso
    // GET /api/ofertas/curso/{cursoId}
    @GetMapping("/curso/{cursoId}")
    public List<OfertaEstagioDTO> getByCurso(@PathVariable String cursoId) {
        return ofertaService.getOfertasByCurso(cursoId)
                .stream()
                .map(OfertaEstagioMapper::toDTO)
                .toList();
    }

    // READ por area
    // GET /api/ofertas/area/{areaId}
    @GetMapping("/area/{areaId}")
    public List<OfertaEstagioDTO> getByArea(@PathVariable String areaId) {
        return ofertaService.getOfertasByArea(areaId)
                .stream()
                .map(OfertaEstagioMapper::toDTO)
                .toList();
    }

    // READ por status
    // GET /api/ofertas/status/{status}
    @GetMapping("/status/{status}")
    public List<OfertaEstagioDTO> getByStatus(@PathVariable StatusOferta status) {
        return ofertaService.getOfertasByStatus(status)
                .stream()
                .map(OfertaEstagioMapper::toDTO)
                .toList();
    }

    // UPDATE
    // PUT /api/ofertas/{id}?empresaId=E&areaId=A&cursoId=C&coordenadorId=K
    @PutMapping("/{id}")
    public OfertaEstagioDTO update(@PathVariable String id,
                                   @RequestBody OfertaEstagioDTO dto,
                                   @RequestParam String empresaId,
                                   @RequestParam(required = false) String areaId,
                                   @RequestParam(required = false) String cursoId,
                                   @RequestParam(required = false) String coordenadorId) {

        OfertaEstagio dados = OfertaEstagioMapper.toEntity(dto);
        OfertaEstagio atualizada =
                ofertaService.updateOferta(id, dados, empresaId, areaId, cursoId, coordenadorId);

        return OfertaEstagioMapper.toDTO(atualizada);
    }

    // WORKFLOW: aprovar
    // POST /api/ofertas/{id}/aprovar
    @PostMapping("/{id}/aprovar")
    public OfertaEstagioDTO aprovar(@PathVariable String id) {
        OfertaEstagio o = ofertaService.aprovarOferta(id);
        return OfertaEstagioMapper.toDTO(o);
    }

    // WORKFLOW: rejeitar
    // POST /api/ofertas/{id}/rejeitar
    @PostMapping("/{id}/rejeitar")
    public OfertaEstagioDTO rejeitar(@PathVariable String id) {
        OfertaEstagio o = ofertaService.rejeitarOferta(id);
        return OfertaEstagioMapper.toDTO(o);
    }

 // Endpoint genérico para alterar o status (usado para ARQUIVADA)
    // PUT /api/ofertas/{id}/status/{status}
    @PutMapping("/{id}/status/{status}")
    public OfertaEstagioDTO atualizarStatus(@PathVariable String id, @PathVariable String status) {
        // Converte a string (ex: "ARQUIVADA") para o Enum StatusOferta
        StatusOferta novoStatus = StatusOferta.valueOf(status.toUpperCase());
        
        // Chama o serviço
        OfertaEstagio o = ofertaService.atualizarStatus(id, novoStatus);
        
        return OfertaEstagioMapper.toDTO(o);
    }

    // DELETE
    // DELETE /api/ofertas/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        ofertaService.deleteOferta(id);
        return ResponseEntity.noContent().build();
    }
}
