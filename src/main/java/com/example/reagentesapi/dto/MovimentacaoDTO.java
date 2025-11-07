package com.example.reagentesapi.dto;

import com.example.reagentesapi.enums.TipoMovimentacao;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovimentacaoDTO {

    private UUID id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataHoraMovimentacao;

    private TipoMovimentacao tipo;
    private Integer quantidadeMovimentada;
    private String observacao;
    private UUID reagenteId;

    public MovimentacaoDTO() {}

    public MovimentacaoDTO(UUID id, LocalDateTime dataHoraMovimentacao, TipoMovimentacao tipo, Integer quantidadeMovimentada, String observacao, UUID reagenteId) {
        this.id = id;
        this.dataHoraMovimentacao = dataHoraMovimentacao;
        this.tipo = tipo;
        this.quantidadeMovimentada = quantidadeMovimentada;
        this.observacao = observacao;
        this.reagenteId = reagenteId;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public LocalDateTime getDataHoraMovimentacao() { return dataHoraMovimentacao; }
    public void setDataHoraMovimentacao(LocalDateTime dataHoraMovimentacao) { this.dataHoraMovimentacao = dataHoraMovimentacao; }

    public TipoMovimentacao getTipo() { return tipo; }
    public void setTipo(TipoMovimentacao tipo) { this.tipo = tipo; }

    public Integer getQuantidadeMovimentada() { return quantidadeMovimentada; }
    public void setQuantidadeMovimentada(Integer quantidadeMovimentada) { this.quantidadeMovimentada = quantidadeMovimentada; }

    public String getObservacao() { return observacao; }
    public void setObservacao(String observacao) { this.observacao = observacao; }

    public UUID getReagenteId() { return reagenteId; }
    public void setReagenteId(UUID reagenteId) { this.reagenteId = reagenteId; }
}
