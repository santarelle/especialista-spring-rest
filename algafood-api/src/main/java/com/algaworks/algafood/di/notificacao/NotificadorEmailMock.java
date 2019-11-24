package com.algaworks.algafood.di.notificacao;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.di.modelo.Cliente;

@TipoDoNotificador(NivelUrgencia.NORMAL)
@Component
public class NotificadorEmailMock implements Notificador {
	
	public NotificadorEmailMock() {
		System.out.println("NotificadorEmailMock iniciado");
	}

	@Override
	public void notificar(Cliente cliente, String mensagem) {
		System.out.printf("MOCK: Notificando %s atraves do e-mail %s: %s\n", 
				cliente.getNome(), cliente.getEmail(), mensagem);
	}
}
