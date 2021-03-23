package com.algaworks.algafood.domain.exception;

public class FormaPagamentoNaoEncontratoException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 1L;

    public FormaPagamentoNaoEncontratoException(String mensagem) {
        super(mensagem);
    }

    public FormaPagamentoNaoEncontratoException(Long formaPagamentoId) {
        this(String.format("Nao existe um cadastro de Forma Pagamento com codigo %d", formaPagamentoId));
    }

}
