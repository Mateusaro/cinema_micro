package com.example.cinema.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "cinemas")
public class Cinema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cep;
    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;

    // Para auditoria
    private String ultimaAcao;
    private LocalDateTime dataUltimaAlteracao;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getUltimaAcao() {
        return ultimaAcao;
    }

    public void setUltimaAcao(String ultimaAcao) {
        this.ultimaAcao = ultimaAcao;
    }

    public LocalDateTime getDataUltimaAlteracao() {
        return dataUltimaAlteracao;
    }

    public void setDataUltimaAlteracao(LocalDateTime dataUltimaAlteracao) {
        this.dataUltimaAlteracao = dataUltimaAlteracao;
    }
}
