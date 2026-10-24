package com.aydsii.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.aydsii.demo.model.tablas.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
}