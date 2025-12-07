package com.upt.pt.api.controller;

import com.upt.pt.api.dto.NotificacaoDTO;
import com.upt.pt.api.mapper.NotificacaoMapper;
import com.upt.pt.api.service.NotificacaoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Endpoints para gestão de notificações.
 */
@RestController
@RequestMapping("/api/notificacoes")
public class NotificacaoController {

    private final NotificacaoService service;

    public NotificacaoController(NotificacaoService service) {
        this.service = service;
    }

    @GetMapping("/utilizador/{id}")
    public List<NotificacaoDTO> listar(@PathVariable String id) {
        return service.listarPorUtilizador(id)
                .stream()
                .map(NotificacaoMapper::toDTO)
                .toList();
    }

    @PostMapping("/enviar")
    public NotificacaoDTO enviar(@RequestParam String utilizadorId,
                                 @RequestParam String titulo,
                                 @RequestParam String mensagem) {

        return NotificacaoMapper.toDTO(
                service.enviar(utilizadorId, titulo, mensagem)
        );
    }

    @PutMapping("/{id}/lida")
    public void marcarComoLida(@PathVariable String id) {
        service.marcarComoLida(id);
    }
}