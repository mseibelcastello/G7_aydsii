package com.aydsii.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.aydsii.demo.model.tablas.Pedido;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    // query que devuelve los pedidos del cliente con el dni ingresado del más nuevo
    // al más viejo
    List<Pedido> findByClienteDniOrderByFechaDesc(String dni);

}