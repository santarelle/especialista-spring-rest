package com.algaworks.algafood.di.notificacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.di.modelo.Cliente;

@TipoDoNotificador(NivelUrgencia.NORMAL)
@Component
public class NotificadorEmail implements Notificador {
	
	@Autowired
	private NotificadorProperties properties;

	public NotificadorEmail() {
		System.out.println("NotificadorEmail iniciado");
	}

	@Override
	public void notificar(Cliente cliente, String mensagem) {
		System.out.printf("Notificando %s atraves do e-mail %s: %s\n",
				cliente.getNome(), cliente.getEmail(), mensagem);
		System.out.printf("Host %s \n", properties.getHostServidor());
		System.out.printf("Port %d \n", properties.getPortaServidor());
	}
}
