package com.example.reagentesapi.entity;

import com.example.reagentesapi.enums.TipoLocalizacaoEstoque;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class LocalizacaoEstoque {
    @Id
    private UUID id = UUID.randomUUID();

    private String codigoLocal;
    private String descricao;

    @Enumerated(EnumType.STRING)
    private TipoLocalizacaoEstoque tipo;

    private String faixaTemperaturaNominal;
    private String setor;

    @OneToMany(mappedBy = "localizacaoEstoque", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reagente> reagentes = new ArrayList<>();

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getCodigoLocal() { return codigoLocal; }
    public void setCodigoLocal(String codigoLocal) { this.codigoLocal = codigoLocal; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public TipoLocalizacaoEstoque getTipo() { return tipo; }
    public void setTipo(TipoLocalizacaoEstoque tipo) { this.tipo = tipo; }
    public String getFaixaTemperaturaNominal() { return faixaTemperaturaNominal; }
    public void setFaixaTemperaturaNominal(String faixaTemperaturaNominal) { this.faixaTemperaturaNominal = faixaTemperaturaNominal; }
    public String getSetor() { return setor; }
    public void setSetor(String setor) { this.setor = setor; }
    public List<Reagente> getReagentes() { return reagentes; }
    public void setReagentes(List<Reagente> reagentes) { this.reagentes = reagentes; }
}
