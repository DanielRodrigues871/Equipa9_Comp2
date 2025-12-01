package com.upt.lp.componente2.service;

import com.upt.lp.componente2.entity.Utilizador;
import com.upt.lp.componente2.repository.UtilizadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilizadorService {
	private final UtilizadorRepository utilizadorRepository;
	
	public UtilizadorService(UtilizadorRepository utilizadorRepository) {
		this.utilizadorRepository = utilizadorRepository;
	}
	
	public List<Utilizador> getAllUtilizadores(){
		return utilizadorRepository.findAll();
	}
	
	public Utilizador getUtilizadorById(String id) {
		return utilizadorRepository.findById(id).orElseThrow(() -> new RuntimeException("Utilizador não encontrador com ID:" + id));
	}
	
	public Utilizador getUtilizadorByEmail(String email) {
		Utilizador utilizador = utilizadorRepository.findByEmail(email);
		if(utilizador == null) {
			throw new RuntimeException("Utilizador não encontrado com email: " + email);
		}
		return utilizador;
	}
	
	public Utilizador createUtilizador(Utilizador utilizador) {
		if(utilizadorRepository.existsByEmail(utilizador.getEmail())) {
			throw new RuntimeException("Já existe um utilizador com o email: " + utilizador.getEmail());
		}
		return utilizadorRepository.save(utilizador);
	}
	
	public Utilizador updateUtilizador(String id, Utilizador utilizadorAtualizado) {
		Utilizador utilizadorExistente = getUtilizadorById(id);
		
		if (!utilizadorExistente.getEmail().equals(utilizadorAtualizado.getEmail()) && utilizadorRepository.existsByEmail(utilizadorAtualizado.getEmail())) {
			throw new RuntimeException("já existe um utilizador com o email: " + utilizadorAtualizado.getEmail());
		}
		
		utilizadorExistente.setNome(utilizadorAtualizado.getNome());
		utilizadorExistente.setEmail(utilizadorAtualizado.getEmail());
		
		if (utilizadorAtualizado.getPassword() != null && !utilizadorAtualizado.getPassword().equals(utilizadorExistente.getPassword())) {
			utilizadorExistente.setPassword(utilizadorAtualizado.getPassword());
		}
		return utilizadorRepository.save(utilizadorExistente);
	}
	
	public void deleteUtilizador(String id) {
		if(!utilizadorRepository.existsById(id)) {
			throw new RuntimeException("Utilizador não encontrado com ID: " + id);
		}
		utilizadorRepository.deleteById(id);
	}
	
	public boolean emailExists(String email) {
		return utilizadorRepository.existsByEmail(email);
	}
}
