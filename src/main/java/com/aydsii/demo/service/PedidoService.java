package com.aydsii.demo.service;

import org.springframework.stereotype.Service;

import com.aydsii.demo.model.DTO.PedidoResumenDTO;
import com.aydsii.demo.model.tablas.Pedido;
import com.aydsii.demo.repository.ClienteRepository;
import com.aydsii.demo.repository.PedidoRepository;
import com.aydsii.demo.exception.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;

    public PedidoService(PedidoRepository pedidoRepository, ClienteRepository clienteRepository) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
    }

    public List<PedidoResumenDTO> listarPedidos(String dni) {
        if (!clienteRepository.existsById(dni)) {
            throw new ClienteNoEncontradoException(dni);
        }

        List<Pedido> pedidos = pedidoRepository.findByClienteDniOrderByFechaDesc(dni);
        List<PedidoResumenDTO> resultado = new ArrayList<>();

        for (Pedido p : pedidos) {
            resultado.add(new PedidoResumenDTO(p.getId(), p.getFecha(), p.getEstado(),
                    p.getModoEntrega(), p.getValorTotal()));
        }

        return resultado;
    }
}
