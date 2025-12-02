package com.upt.pt.api.dto;

public class RegistoDTO {

    private String nome;
    private String email;
    private String password;
    private String tipo;          // ESTUDANTE, COORDENADOR, REPRESENTANTE

    // ESTUDANTE
    private String cursoId;
    private String numeroEstudante;
    private Integer anoMatricula;

    // COORDENADOR
    private String departamentoId;

    // REPRESENTANTE
    private String empresaId;
    private String cargo;
    private String telefone;

    public RegistoDTO() {
    }

    
    
	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getTipo() {
		return tipo;
	}

	public String getCursoId() {
		return cursoId;
	}

	public String getNumeroEstudante() {
		return numeroEstudante;
	}

	public Integer getAnoMatricula() {
		return anoMatricula;
	}

	public String getDepartamentoId() {
		return departamentoId;
	}

	public String getEmpresaId() {
		return empresaId;
	}

	public String getCargo() {
		return cargo;
	}

	public String getTelefone() {
		return telefone;
	} 
}
