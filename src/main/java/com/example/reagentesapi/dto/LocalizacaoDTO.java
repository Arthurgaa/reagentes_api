package com.example.reagentesapi.dto;

import com.example.reagentesapi.enums.TipoLocalizacaoEstoque;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LocalizacaoDTO {

    private UUID id;
    private String codigoLocal;
    private String descricao;
    private TipoLocalizacaoEstoque tipo;
    private String faixaTemperaturaNominal;
    private String setor;

    public LocalizacaoDTO() {}

    public LocalizacaoDTO(UUID id, String codigoLocal, String descricao, TipoLocalizacaoEstoque tipo, String faixaTemperaturaNominal, String setor) {
        this.id = id;
        this.codigoLocal = codigoLocal;
        this.descricao = descricao;
        this.tipo = tipo;
        this.faixaTemperaturaNominal = faixaTemperaturaNominal;
        this.setor = setor;
    }

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
}
