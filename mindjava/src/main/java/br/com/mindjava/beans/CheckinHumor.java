package br.com.mindjava.beans;
import java.time.LocalDate;

public class CheckinHumor {
    private int id;
    private int idColaborador;
    private LocalDate dataRegistro;
    private String humor;
    private String comentario;

    public CheckinHumor() {}

    public CheckinHumor(int id, int idColaborador, LocalDate dataRegistro, String humor, String comentario) {
        this.id = id;
        this.idColaborador = idColaborador;
        this.dataRegistro = dataRegistro;
        this.humor = humor;
        this.comentario = comentario;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdColaborador() { return idColaborador; }
    public void setIdColaborador(int idColaborador) { this.idColaborador = idColaborador; }

    public LocalDate getDataRegistro() { return dataRegistro; }
    public void setDataRegistro(LocalDate dataRegistro) { this.dataRegistro = dataRegistro; }

    public String getHumor() { return humor; }
    public void setHumor(String humor) { this.humor = humor; }

    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }
}
