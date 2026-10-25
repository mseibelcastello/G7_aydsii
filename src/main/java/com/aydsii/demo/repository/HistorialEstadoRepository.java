package com.aydsii.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.aydsii.demo.model.tablas.HistorialEstado;

public interface HistorialEstadoRepository extends
                JpaRepository<HistorialEstado, Long> {

        // devuelve los cambios de estado de un pedido, del más viejo al más nuevo
        List<HistorialEstado> findByPedidoIdOrderByFechaHoraAsc(Long pedidoId);
}