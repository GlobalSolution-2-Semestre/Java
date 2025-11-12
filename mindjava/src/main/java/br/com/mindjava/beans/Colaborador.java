package br.com.mindjava.beans;

import br.com.mindjava.beans.Pessoa;

public class Colaborador extends Pessoa {
    private String cargo;


    public Colaborador() {}

    public Colaborador(String cargo) {
        this.cargo = cargo;
    }

    public Colaborador(int id, String nome, String email, String cargo) {
        super(id, nome, email);
        this.cargo = cargo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
