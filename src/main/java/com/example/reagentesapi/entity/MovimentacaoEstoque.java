package com.example.reagentesapi.entity;

import com.example.reagentesapi.enums.TipoMovimentacao;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class MovimentacaoEstoque {
    @Id
    private UUID id = UUID.randomUUID();

    private LocalDateTime dataHoraMovimentacao;

    @Enumerated(EnumType.STRING)
    private TipoMovimentacao tipo;

    private Integer quantidadeMovimentada;
    private String observacao;

    @ManyToOne
    @JoinColumn(name = "reagente_id")
    private Reagente reagente;

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
    public Reagente getReagente() { return reagente; }
    public void setReagente(Reagente reagente) { this.reagente = reagente; }
}
