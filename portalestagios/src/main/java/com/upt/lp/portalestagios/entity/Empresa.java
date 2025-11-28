package com.upt.lp.portalestagios.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "empresa")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false, length = 36)
    private UUID id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "nif", nullable = false, unique = true, length = 50)
    private String nif;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "morada")
    private String morada;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "website")
    private String website;

    @Column(name = "ativa", nullable = false)
    private boolean ativa = true;

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OfertaEstagio> ofertasSubmetidas = new ArrayList<>();

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RepresentanteEmpresa> representantes = new ArrayList<>();

    public Empresa() {}
    public Empresa(String nome, String nif, String email, String morada) {
        this.nome = nome;
        this.nif = nif;
        this.email = email;
        this.morada = morada;
    }

    public UUID getId() { return id; }
    public String getNome() { return nome; }
    public String getNif() { return nif; }
    public String getEmail() { return email; }
    public String getMorada() { return morada; }
    public String getTelefone() { return telefone; }
    public String getWebsite() { return website; }
    public boolean isAtiva() { return ativa; }

    public void setNome(String nome) { this.nome = nome; }
    public void setNif(String nif) { this.nif = nif; }
    public void setEmail(String email) { this.email = email; }
    public void setMorada(String morada) { this.morada = morada; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public void setWebsite(String website) { this.website = website; }
    public void setAtiva(boolean ativa) { this.ativa = ativa; }

    public List<OfertaEstagio> getOfertasSubmetidas() { return ofertasSubmetidas; }
    public List<RepresentanteEmpresa> getRepresentantes() { return representantes; }

    public void submeterOferta(OfertaEstagio oferta) {
        if (!ofertasSubmetidas.contains(oferta)) {
            ofertasSubmetidas.add(oferta);
            oferta.setEmpresa(this);
        }
    }

    public void adicionarRepresentante(RepresentanteEmpresa r) {
        if (!representantes.contains(r)) {
            representantes.add(r);
            r.setEmpresa(this);
        }
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Empresa)) return false;
        Empresa empresa = (Empresa) o;
        return Objects.equals(id, empresa.id);
    }
    @Override public int hashCode() { return Objects.hash(id); }
}
