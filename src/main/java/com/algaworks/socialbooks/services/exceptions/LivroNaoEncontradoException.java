package com.algaworks.socialbooks.services.exceptions;

public class LivroNaoEncontradoException extends RuntimeException {

    public LivroNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public LivroNaoEncontradoException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
