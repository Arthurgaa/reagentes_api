package com.example.reagentesapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FabricanteDTO {

    private UUID id;
    private String nomeOficial;
    private String nomeFantasia;
    private String cnpj;
    private String paisOrigem;

    public FabricanteDTO() {}

    public FabricanteDTO(UUID id, String nomeOficial, String nomeFantasia, String cnpj, String paisOrigem) {
        this.id = id;
        this.nomeOficial = nomeOficial;
        this.nomeFantasia = nomeFantasia;
        this.cnpj = cnpj;
        this.paisOrigem = paisOrigem;
    }

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
}
