package com.upt.lp.portalestagios.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "representante_empresa")
public class RepresentanteEmpresa extends Utilizador {

    @Column(name = "cargo", length = 100)
    private String cargo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    public RepresentanteEmpresa() { super(); }
    public RepresentanteEmpresa(String nome, String email, String password, String cargo) {
        super(nome, email, password);
        this.cargo = cargo;
    }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }

    public Empresa getEmpresa() { return empresa; }
    public void setEmpresa(Empresa empresa) { this.empresa = empresa; }
}
