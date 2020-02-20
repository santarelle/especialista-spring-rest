package com.algaworks.algafood.domain.exception;

public class CidadeNaoEncontradoException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 1L;

    public CidadeNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public CidadeNaoEncontradoException(Long cidadeId) {
        this(String.format("Nao existe um cadastro de cidade com codigo %d", cidadeId));
    }
}
