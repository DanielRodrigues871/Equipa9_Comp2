package com.upt.lp.componente2.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "representante_empresa")
public class RepresentanteEmpresa extends Utilizador {
    
    @Column(name = "cargo", nullable = false, length = 100)
    private String cargo;
    
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id", referencedColumnName = "id")
    private Empresa empresa;
    
    @Column(name = "telefone", length = 20)
    private String telefone;
    
    public RepresentanteEmpresa() {
        super();
    }
    
    public RepresentanteEmpresa(String nome, String email, String password, String cargo) {
        super(nome, email, password);
        if (cargo == null || cargo.isBlank()) {
            throw new IllegalArgumentException("O cargo é obrigatório!");
        }
        this.cargo = cargo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        if (cargo == null || cargo.isBlank()) {
            throw new IllegalArgumentException("O cargo é obrigatório!");
        }
        this.cargo = cargo;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
    
    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    @Override
    public String toString() {
        return "RepresentanteEmpresa{" +
                "id='" + getId() + '\'' +
                ", nome='" + getNome() + '\'' +
                ", cargo='" + cargo + '\'' +
                ", empresa=" + (empresa != null ? empresa.getNome() : "N/A") +
                '}';
    }
}