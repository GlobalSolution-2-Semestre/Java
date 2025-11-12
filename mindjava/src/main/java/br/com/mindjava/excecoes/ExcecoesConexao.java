package br.com.mindjava.excecoes;

public class ExcecoesConexao extends Exception {

    // Construtor padrão
    public ExcecoesConexao() {
        super("Erro genérico de conexão com o banco de dados.");
    }

    // Construtor com mensagem personalizada
    public ExcecoesConexao(String mensagem) {
        super(mensagem);
    }


    public ExcecoesConexao(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }

    // Construtor apenas com causa
    public ExcecoesConexao(Throwable causa) {
        super(causa);
    }
}