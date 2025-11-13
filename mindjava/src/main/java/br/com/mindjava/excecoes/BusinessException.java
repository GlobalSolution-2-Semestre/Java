package br.com.mindjava.excecoes;

public class BusinessException extends Exception {

    public BusinessException(String mensagem) {
        super(mensagem);
    }

    public BusinessException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
