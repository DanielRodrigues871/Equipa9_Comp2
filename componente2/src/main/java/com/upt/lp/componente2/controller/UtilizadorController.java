package com.upt.lp.componente2.controller;

import com.upt.lp.componente2.dto.UtilizadorDTO;
import com.upt.lp.componente2.entity.Utilizador;
import com.upt.lp.componente2.service.UtilizadorService;
import com.upt.lp.componente2.mapper.UtilizadorMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/componente2/utilizadores")
public class UtilizadorController {
	private final UtilizadorService UtilizadorService;
	
	public UtilizadorController(UtilizadorService utilizadorService) {
		this.UtilizadorService = utilizadorService;
	}
	
	@GetMapping
	public List<UtilizadorDTO> getAllUtilizadores(){
		return UtilizadorService.getAllUtilizadores().stream().map(UtilizadorMapper::toDTO).collect(Collectors.toList());
	}
	
	@GetMapping("/{id}")
	public UtilizadorDTO getUtilizadorById(@PathVariable String id) {
		Utilizador utilizador = UtilizadorService.getUtilizadorById(id);
		return UtilizadorMapper.toDTO(utilizador);
	}
	
	@GetMapping("/email/{email}")
    public UtilizadorDTO getUtilizadorByEmail(@PathVariable String email) {
        Utilizador utilizador = UtilizadorService.getUtilizadorByEmail(email);
        return UtilizadorMapper.toDTO(utilizador);
    }
	
	@PostMapping
	public UtilizadorDTO createUtilizador(@RequestBody Utilizador utilizador) {
		Utilizador novoUtilizador = UtilizadorService.createUtilizador(utilizador);
		return UtilizadorMapper.toDTO(novoUtilizador);
	}
	
	@PutMapping("/{id}")
	public UtilizadorDTO updateUtilizador(@PathVariable String id, @RequestBody Utilizador utilizador) {
		Utilizador utilizadorAtualizado = UtilizadorService.updateUtilizador(id, utilizador);
		return UtilizadorMapper.toDTO(utilizadorAtualizado);
	}
	
	@DeleteMapping("/{id}")
	public void deleteUtilizador(@PathVariable String id) {
		UtilizadorService.deleteUtilizador(id);
	}
	
	@GetMapping("/email/exists/{email}")
	public boolean checkEmailExists(@PathVariable String email) {
		return UtilizadorService.emailExists(email);
	}
	
	
}
