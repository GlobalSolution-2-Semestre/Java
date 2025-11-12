package br.com.mindjava.beans;
import java.time.LocalDate;

public class Relatorio {
    private int id;
    private int idColaborador;
    private LocalDate dataGeracao;
    private String resumoAnalise;
    private double mediaHumor;

    public Relatorio() {}

    public Relatorio(int id, int idColaborador, LocalDate dataGeracao, String resumoAnalise, double mediaHumor) {
        this.id = id;
        this.idColaborador = idColaborador;
        this.dataGeracao = dataGeracao;
        this.resumoAnalise = resumoAnalise;
        this.mediaHumor = mediaHumor;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdColaborador() { return idColaborador; }
    public void setIdColaborador(int idColaborador) { this.idColaborador = idColaborador; }

    public LocalDate getDataGeracao() { return dataGeracao; }
    public void setDataGeracao(LocalDate dataGeracao) { this.dataGeracao = dataGeracao; }

    public String getResumoAnalise() { return resumoAnalise; }
    public void setResumoAnalise(String resumoAnalise) { this.resumoAnalise = resumoAnalise; }

    public double getMediaHumor() { return mediaHumor; }
    public void setMediaHumor(double mediaHumor) { this.mediaHumor = mediaHumor; }
}
