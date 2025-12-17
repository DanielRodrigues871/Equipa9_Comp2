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

    // ==========================================
    // LISTAR NOTIFICAÇÕES
    // ==========================================
    
    // O JavaFX chama "/estudante/{id}", então precisamos deste mapeamento:
    @GetMapping("/estudante/{id}")
    public List<NotificacaoDTO> listarPorEstudante(@PathVariable String id) {
        return listar(id); // Reutiliza a lógica
    }

    // Adicione isto no NotificacaoController.java
    @GetMapping("/coordenador/{id}")
    public List<NotificacaoDTO> listarPorCoordenador(@PathVariable String id) {
        // Reutiliza a lógica de buscar por utilizador, pois o ID é único
        return listar(id); 
    }
    
    // Adicione isto para evitar o erro 404
    @GetMapping("/representante/{id}")
    public List<NotificacaoDTO> listarPorRepresentante(@PathVariable String id) {
        return listar(id); // Reutiliza a lógica genérica
    }
    
    // Mantemos este também caso queira usar para Coordenadores/Representantes no futuro
    @GetMapping("/utilizador/{id}")
    public List<NotificacaoDTO> listar(@PathVariable String id) {
        return service.listarPorUtilizador(id)
                .stream()
                .map(NotificacaoMapper::toDTO)
                .toList();
    }

    // ==========================================
    // MARCAR COMO LIDA
    // ==========================================

    // O JavaFX faz POST para "/{id}/ler", então ajustamos aqui:
    @PostMapping("/{id}/ler")
    public void marcarUma(@PathVariable String id) {
        service.marcarComoLida(id);
    }

    // ==========================================
    // MARCAR TODAS (EXTRA)
    // ==========================================
    
    @PostMapping("/utilizador/{id}/marcar-todas")
    public void marcarTodas(@PathVariable String id) {
        service.marcarTodasComoLidas(id);
    }
}