package br.com.asmaproject.exception;

public class EmailIndisponivelException extends RuntimeException {

    public EmailIndisponivelException(String mensagem) {
        super(mensagem);
    }
}
