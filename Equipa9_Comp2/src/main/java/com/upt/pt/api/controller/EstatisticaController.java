package com.upt.pt.api.controller;

import com.upt.pt.api.dto.EstatisticasDTO;
import com.upt.pt.api.service.EstatisticasService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/estatisticas")
public class EstatisticaController {

    private final EstatisticasService estatisticasService;

    public EstatisticaController(EstatisticasService estatisticasService) {
        this.estatisticasService = estatisticasService;
    }

    // GET /api/estatisticas
    @GetMapping
    public ResponseEntity<EstatisticasDTO> getEstatisticas() {
        // O Service já faz os cálculos todos e devolve o DTO pronto
        EstatisticasDTO stats = estatisticasService.obterEstatisticas();
        return ResponseEntity.ok(stats);
    }
}