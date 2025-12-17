package com.upt.pt.api.dto;

public class LoginResponseDTO {

	private String id;
	private String nome;
	private String email;
	private String tipo;
	private String empresaId; 

	public LoginResponseDTO() {
	}

	// Atualize o construtor
	public LoginResponseDTO(String id, String nome, String email, String tipo, String empresaId) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.tipo = tipo;
		this.empresaId = empresaId; 
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getEmpresaId() {
		return empresaId;
	}

	public void setEmpresaId(String empresaId) {
		this.empresaId = empresaId;
	}
}