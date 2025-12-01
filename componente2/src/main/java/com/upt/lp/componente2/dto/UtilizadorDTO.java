package com.upt.lp.componente2.dto;

import java.time.LocalDateTime;

public class UtilizadorDTO {
	private String id;
	private String nome;
	private String email;
	private LocalDateTime dataCriacao;
	private LocalDateTime dataAtualizacao;
	
	public UtilizadorDTO() {
	}

	public UtilizadorDTO(String id, String nome, String email, LocalDateTime dataCriacao, LocalDateTime dataAtualizacao) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.dataCriacao = dataCriacao;
		this.dataAtualizacao = dataAtualizacao;
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
	
}
