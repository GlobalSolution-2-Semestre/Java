package br.com.mindjava.beans;

import java.time.LocalDateTime;

public class Alerta {

    private int id;
    private int idColaborador;
    private String tipoAlerta;
    private String descricao;
    private LocalDateTime dataEnvio;

    // Getters e Setters

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getIdColaborador() { return idColaborador; }
    public void setIdColaborador(int idColaborador) { this.idColaborador = idColaborador; }
    public String getTipoAlerta() { return tipoAlerta; }
    public void setTipoAlerta(String tipoAlerta) { this.tipoAlerta = tipoAlerta; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public LocalDateTime getDataEnvio() { return dataEnvio; }
    public void setDataEnvio(LocalDateTime dataEnvio) { this.dataEnvio = dataEnvio; }
}

