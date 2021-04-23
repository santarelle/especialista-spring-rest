package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.core.email.EmailProperties;
import com.algaworks.algafood.domain.service.FluxoPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private FluxoPedidoService fluxoPedidoService;

    @PutMapping("/{codigo}/confirmacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirmarPedido(@PathVariable String codigo) {
        fluxoPedidoService.confirmar(codigo);
    }
}
