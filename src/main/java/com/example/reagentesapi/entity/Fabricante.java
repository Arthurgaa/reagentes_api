package com.example.reagentesapi.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Fabricante {
    @Id
    private UUID id = UUID.randomUUID();

    private String nomeOficial;
    private String nomeFantasia;
    private String cnpj;
    private String paisOrigem;

    @OneToMany(mappedBy = "fabricante", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reagente> reagentes = new ArrayList<>();

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getNomeOficial() { return nomeOficial; }
    public void setNomeOficial(String nomeOficial) { this.nomeOficial = nomeOficial; }
    public String getNomeFantasia() { return nomeFantasia; }
    public void setNomeFantasia(String nomeFantasia) { this.nomeFantasia = nomeFantasia; }
    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj; }
    public String getPaisOrigem() { return paisOrigem; }
    public void setPaisOrigem(String paisOrigem) { this.paisOrigem = paisOrigem; }
    public List<Reagente> getReagentes() { return reagentes; }
    public void setReagentes(List<Reagente> reagentes) { this.reagentes = reagentes; }
}
