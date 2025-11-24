package com.upt.pt.api.entity;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "estudante")
public class Estudante extends Utilizador {

	@Column(name = "numero_estudante", nullable = false, unique = true)
	private String numeroEstudante;
	
	@ManyToOne(optional = false)
    @JoinColumn(name = "curso_id", referencedColumnName = "id")
    private Curso curso;
	
	@Column(name = "ano_matricula", nullable = false)
    private int anoMatricula;
	
	@Column(name = "media")
    private double media;
	 
	@ElementCollection
	@CollectionTable(name = "competencia_estudante", joinColumns = @JoinColumn(name = "estudante_id"))
	@Column(name = "competencia")
    private List<String> competencias;
	
	@OneToMany(mappedBy = "estudante", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Candidatura> candidaturas;
	
	@OneToMany(mappedBy = "estudante", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Documento> documentos;
	
    public Estudante() {
    	super();
    	this.competencias = new ArrayList<>();
        this.candidaturas = new ArrayList<>();
        this.documentos = new ArrayList<>();
    }

    public Estudante(String nome, String email, String password, String numeroEstudante, int anoMatricula) {
        super(nome, email, password);
        
        if (numeroEstudante == null || !numeroEstudante.matches("\\d{5}")) {
            throw new IllegalArgumentException("O número de estudante deve ter exatamente 5 dígitos numéricos.");
        }
        if (anoMatricula < 1) {
            throw new IllegalArgumentException("O ano de matrícula deve ser maior ou igual a 1.");
        }
        
        this.numeroEstudante = numeroEstudante;
        this.anoMatricula = anoMatricula;
    }

    public void adicionarCompetencia(String competencia) {
        this.competencias.add(competencia);
    }

    public void adicionarCandidatura(Candidatura candidatura) {
        this.candidaturas.add(candidatura);
    }

    public void adicionarDocumento(Documento documento) {
        this.documentos.add(documento);
    }
    
	public String getNumeroEstudante() {
		return numeroEstudante;
	}

	public void setNumeroEstudante(String numeroEstudante) {
		if (numeroEstudante == null || !numeroEstudante.matches("\\d{5}")) {
            throw new IllegalArgumentException("O número de estudante deve ter exatamente 5 dígitos numéricos.");
        }
		
		this.numeroEstudante = numeroEstudante;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public int getAnoMatricula() {
		return anoMatricula;
	}

	public void setAnoMatricula(int anoMatricula) {
		if (anoMatricula < 1) {
	        throw new IllegalArgumentException("O ano de matrícula deve ser maior ou igual a 1.");
	    }
		
		this.anoMatricula = anoMatricula;
	}

	public double getMedia() {
		return media;
	}

	public void setMedia(double media) {
		this.media = media;
	}

	public List<String> getCompetencias() {
        return competencias;
    }

    public void setCompetencias(List<String> competencias) {
        this.competencias = competencias;
    }

    public List<Candidatura> getCandidaturas() {
        return candidaturas;
    }

    public List<Documento> getDocumentos() {
        return documentos;
    }

	public void setCandidaturas(ArrayList<Candidatura> candidaturas) {
		this.candidaturas = candidaturas;
	}

	@Override
    public String toString() {
        return "Estudante{" +
                "id='" + getId() + '\'' +
                ", nome='" + getNome() + '\'' +
                ", numeroEstudante='" + numeroEstudante + '\'' +
                ", curso=" + (curso != null ? curso.getNome() : "N/A") +
                ", anoMatricula=" + anoMatricula +
                ", media=" + media +
                '}';
    }
}
