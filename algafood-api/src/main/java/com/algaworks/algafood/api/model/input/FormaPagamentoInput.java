package com.algaworks.algafood.api.model.input;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormaPagamentoInput {

    @Length(min = 2)
    private String descricao;
}
