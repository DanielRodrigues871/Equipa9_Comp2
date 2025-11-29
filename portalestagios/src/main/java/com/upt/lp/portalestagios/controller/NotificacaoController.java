package com.upt.lp.portalestagios.controller;

import com.upt.lp.portalestagios.dto.notificacao.NotificacaoRequestDTO;
import com.upt.lp.portalestagios.dto.notificacao.NotificacaoResponseDTO;
import com.upt.lp.portalestagios.service.NotificacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/notificacoes")
@CrossOrigin("*")
public class NotificacaoController {

    private final NotificacaoService service;

    public NotificacaoController(NotificacaoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<NotificacaoResponseDTO> criar(@RequestBody NotificacaoRequestDTO dto) {
        return ResponseEntity.ok(service.criar(dto));
    }

    @GetMapping("/utilizador/{id}")
    public ResponseEntity<List<NotificacaoResponseDTO>> listar(@PathVariable UUID id) {
        return ResponseEntity.ok(service.listarPorUtilizador(id));
    }

    @PutMapping("/{id}/lida")
    public ResponseEntity<Void> marcarComoLida(@PathVariable UUID id) {
        service.marcarComoLida(id);
        return ResponseEntity.noContent().build();
    }
}
