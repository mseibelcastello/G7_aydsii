package com.aydsii.demo.repository;

import com.aydsii.demo.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    // CU-02: pedidos de un cliente, del más nuevo al más viejo
    List<Pedido> findByClienteDniOrderByFechaDesc(String dni);

    // CU-02 / RN-22: solo devuelve el pedido si pertenece a ese cliente
    Optional<Pedido> findByIdAndClienteDni(Integer id, String dni);
}