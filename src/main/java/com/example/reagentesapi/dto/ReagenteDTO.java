package com.example.reagentesapi.dto;

import com.example.reagentesapi.enums.StatusReagente;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReagenteDTO {

    private UUID id;
    private String nome;
    private String codigoSku;
    private String lote;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataValidade;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataRecebimento;

    private Integer quantidadeEmEstoque;
    private StatusReagente status;

    private UUID fabricanteId;
    private UUID localizacaoId;

    public ReagenteDTO() {}

    public ReagenteDTO(UUID id, String nome, String codigoSku, String lote,
                       LocalDate dataValidade, LocalDate dataRecebimento,
                       Integer quantidadeEmEstoque, StatusReagente status,
                       UUID fabricanteId, UUID localizacaoId) {
        this.id = id;
        this.nome = nome;
        this.codigoSku = codigoSku;
        this.lote = lote;
        this.dataValidade = dataValidade;
        this.dataRecebimento = dataRecebimento;
        this.quantidadeEmEstoque = quantidadeEmEstoque;
        this.status = status;
        this.fabricanteId = fabricanteId;
        this.localizacaoId = localizacaoId;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCodigoSku() { return codigoSku; }
    public void setCodigoSku(String codigoSku) { this.codigoSku = codigoSku; }

    public String getLote() { return lote; }
    public void setLote(String lote) { this.lote = lote; }

    public LocalDate getDataValidade() { return dataValidade; }
    public void setDataValidade(LocalDate dataValidade) { this.dataValidade = dataValidade; }

    public LocalDate getDataRecebimento() { return dataRecebimento; }
    public void setDataRecebimento(LocalDate dataRecebimento) { this.dataRecebimento = dataRecebimento; }

    public Integer getQuantidadeEmEstoque() { return quantidadeEmEstoque; }
    public void setQuantidadeEmEstoque(Integer quantidadeEmEstoque) { this.quantidadeEmEstoque = quantidadeEmEstoque; }

    public StatusReagente getStatus() { return status; }
    public void setStatus(StatusReagente status) { this.status = status; }

    public UUID getFabricanteId() { return fabricanteId; }
    public void setFabricanteId(UUID fabricanteId) { this.fabricanteId = fabricanteId; }

    public UUID getLocalizacaoId() { return localizacaoId; }
    public void setLocalizacaoId(UUID localizacaoId) { this.localizacaoId = localizacaoId; }
}
