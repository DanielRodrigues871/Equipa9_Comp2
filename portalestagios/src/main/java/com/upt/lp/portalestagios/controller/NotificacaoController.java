package com.upt.lp.portalestagios.controller;

import com.upt.lp.portalestagios.entity.Notificacao;
import com.upt.lp.portalestagios.service.NotificacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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

    @GetMapping("/utilizador/{utilizadorId}")
    public ResponseEntity<List<Notificacao>> listar(@PathVariable String utilizadorId) {
        return ResponseEntity.ok(service.listarPorUtilizador(utilizadorId));
    }

    @GetMapping("/utilizador/{utilizadorId}/mes")
    public ResponseEntity<List<Notificacao>> listarMes(@PathVariable String utilizadorId) {
        return ResponseEntity.ok(service.notificacoesDoMes(utilizadorId));
    }

    @PostMapping("/{utilizadorId}")
    public ResponseEntity<Notificacao> criar(@PathVariable String utilizadorId,
                                             @RequestBody Notificacao n) {
        Notificacao criada = service.criar(utilizadorId, n);
        return ResponseEntity.created(URI.create("/api/notificacoes/" + criada.getId())).body(criada);
    }

    @PutMapping("/{id}/lida")
    public ResponseEntity<Notificacao> marcarComoLida(@PathVariable UUID id) {
        return ResponseEntity.ok(service.marcarComoLida(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagar(@PathVariable UUID id) {
        service.apagar(id);
        return ResponseEntity.noContent().build();
    }
}
