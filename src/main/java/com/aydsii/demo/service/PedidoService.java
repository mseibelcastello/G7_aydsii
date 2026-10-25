package com.aydsii.demo.service;

import org.springframework.stereotype.Service;

import com.aydsii.demo.model.dto.CambioEstadoDTO;
import com.aydsii.demo.model.dto.HistorialPedidoDTO;
import com.aydsii.demo.model.dto.PedidoResumenDTO;
import com.aydsii.demo.model.tablas.HistorialEstado;
import com.aydsii.demo.model.tablas.Pedido;
import com.aydsii.demo.repository.ClienteRepository;
import com.aydsii.demo.repository.HistorialEstadoRepository;
import com.aydsii.demo.repository.PedidoRepository;
import com.aydsii.demo.exception.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final HistorialEstadoRepository historialRepository;

    public PedidoService(PedidoRepository pedidoRepository, ClienteRepository clienteRepository,
            HistorialEstadoRepository historialRepository) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.historialRepository = historialRepository;
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

    public HistorialPedidoDTO consultarHistorial(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new PedidoNoEncontradoException(id));

        List<HistorialEstado> cambios = historialRepository.findByPedidoIdOrderByFechaHoraAsc(id);
        List<CambioEstadoDTO> historial = new ArrayList<>();

        for (HistorialEstado h : cambios) {
            historial.add(new CambioEstadoDTO(h.getEstado(), h.getFechaHora()));
        }

        return new HistorialPedidoDTO(pedido.getId(), pedido.getEstado(),
                pedido.getMotivoCancelacion(), historial);
    }
}
