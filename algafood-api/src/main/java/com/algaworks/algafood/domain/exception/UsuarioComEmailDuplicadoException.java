package com.algaworks.algafood.domain.exception;

public class UsuarioComEmailDuplicadoException extends NegocioException {

    private static final long serialVersionUID = 1L;

    public UsuarioComEmailDuplicadoException(String email) {
        super(String.format("Já existe um usuário com o e-mail %s", email));
    }

}
