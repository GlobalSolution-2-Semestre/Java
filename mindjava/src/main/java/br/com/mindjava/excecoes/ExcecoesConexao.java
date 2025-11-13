package br.com.mindjava.excecoes;

public class ExcecoesConexao extends Exception {

    public ExcecoesConexao(String mensagem) {
        super(mensagem);
    }

    public ExcecoesConexao(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }

    public ExcecoesConexao(Throwable causa) {
        super(causa);
    }
}
