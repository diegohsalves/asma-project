package br.com.asmaproject.exception;

public class CpfIndisponivelException extends RuntimeException {

    public CpfIndisponivelException(String mensagem) {
        super(mensagem);
    }
}
