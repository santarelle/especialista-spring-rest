package com.algaworks.algafood.di.service;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.di.modelo.Cliente;

@Component
public class AtivacaoClienteService {

	public void ativar(Cliente cliente) {
		cliente.ativar();

		// emitir evento para o container dizendo que o cliente esta ativo
	}

}
