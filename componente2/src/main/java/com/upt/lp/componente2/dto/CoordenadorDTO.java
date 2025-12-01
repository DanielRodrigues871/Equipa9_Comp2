package com.upt.lp.componente2.dto;

import java.time.LocalDateTime;
import java.util.List;

public class CoordenadorDTO {
	private String id;
	private String nome;
	private String email;
	private LocalDateTime dataCriacao;
	private LocalDateTime dataAtualizacao;
	private String departamentoId;
	private String departamentoNome;
	private List<String> cursosGeridosIds;
	private List<String> ofertasRegistadasIds;
	private List<String> candidaturasGeridasIds;
	
	public CoordenadorDTO() {
	}

	public CoordenadorDTO(String id, String nome, String email, LocalDateTime dataCriacao, LocalDateTime dataAtualizacao, String departamentoId, String departamentoNome) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.dataCriacao = dataCriacao;
		this.dataAtualizacao = dataAtualizacao;
		this.departamentoId = departamentoId;
		this.departamentoNome = departamentoNome;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public LocalDateTime getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public String getDepartamentoId() {
		return departamentoId;
	}

	public void setDepartamentoId(String departamentoId) {
		this.departamentoId = departamentoId;
	}

	public String getDepartamentoNome() {
		return departamentoNome;
	}

	public void setDepartamentoNome(String departamentoNome) {
		this.departamentoNome = departamentoNome;
	}

	public List<String> getCursosGeridosIds() {
		return cursosGeridosIds;
	}

	public void setCursosGeridosIds(List<String> cursosGeridosIds) {
		this.cursosGeridosIds = cursosGeridosIds;
	}

	public List<String> getOfertasRegistadasIds() {
		return ofertasRegistadasIds;
	}

	public void setOfertasRegistadasIds(List<String> ofertasRegistadasIds) {
		this.ofertasRegistadasIds = ofertasRegistadasIds;
	}

	public List<String> getCandidaturasGeridasIds() {
		return candidaturasGeridasIds;
	}

	public void setCandidaturasGeridasIds(List<String> candidaturasGeridasIds) {
		this.candidaturasGeridasIds = candidaturasGeridasIds;
	}
	
}
