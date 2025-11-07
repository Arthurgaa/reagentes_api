package com.example.reagentesapi.entity;

import com.example.reagentesapi.enums.StatusReagente;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Reagente {

    @Id
    private UUID id = UUID.randomUUID();

    private String nome;
    private String codigoSku;
    private String lote;
    private LocalDate dataValidade;
    private LocalDate dataRecebimento;
    private Integer quantidadeEmEstoque;

    @Enumerated(EnumType.STRING)
    private StatusReagente status;

    @ManyToOne(optional = true)
    @JoinColumn(name = "fabricante_id", nullable = true)
    private Fabricante fabricante;

    @ManyToOne(optional = true)
    @JoinColumn(name = "localizacao_id", nullable = true)
    private LocalizacaoEstoque localizacaoEstoque;

    @OneToMany(mappedBy = "reagente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MovimentacaoEstoque> movimentacoes = new ArrayList<>();

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

    public Fabricante getFabricante() { return fabricante; }
    public void setFabricante(Fabricante fabricante) { this.fabricante = fabricante; }

    public LocalizacaoEstoque getLocalizacaoEstoque() { return localizacaoEstoque; }
    public void setLocalizacaoEstoque(LocalizacaoEstoque localizacaoEstoque) { this.localizacaoEstoque = localizacaoEstoque; }

    public List<MovimentacaoEstoque> getMovimentacoes() { return movimentacoes; }
    public void setMovimentacoes(List<MovimentacaoEstoque> movimentacoes) { this.movimentacoes = movimentacoes; }
}
