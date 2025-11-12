package br.com.mindjava.beans;

public class Empresa {
    private int id;
    private String nome;
    private String cnpj;
    private String setorAtuacao;

    public Empresa() {}

    public Empresa(int id, String nome, String cnpj, String setorAtuacao) {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
        this.setorAtuacao = setorAtuacao;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj; }

    public String getSetorAtuacao() { return setorAtuacao; }
    public void setSetorAtuacao(String setorAtuacao) { this.setorAtuacao = setorAtuacao; }
}
