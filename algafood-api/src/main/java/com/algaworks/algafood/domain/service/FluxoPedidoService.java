package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.model.StatusPedido;
import com.algaworks.algafood.domain.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class FluxoPedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private EnvioEmailService envioEmailService;

    @Transactional
    public void confirmar(String codigo) {
        Pedido pedido = pedidoRepository.findByCodigo(codigo).orElseThrow(() -> new NegocioException("Pedido não encontrado com o codigo"));
        if (pedido.getStatus() != StatusPedido.CRIADO) {
            throw new NegocioException(String.format("Pedido não pode ser confirmado pois está com status de %s", pedido.getStatus()));
        }
        pedido.setStatus(StatusPedido.CONFIRMADO);
        pedidoRepository.save(pedido);

        envioEmailService.enviar(EnvioEmailService.Mensagem.builder()
                .destinatario(pedido.getCliente().getEmail())
                .assunto(String.format("%s - Pedido confirmado", pedido.getRestaurante().getNome()))
                .corpo(String.format("O pedido de código <strong>%s</strong> foi confirmado!", pedido.getCodigo()))
                .build());
    }
}
