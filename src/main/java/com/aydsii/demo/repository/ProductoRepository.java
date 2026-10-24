package com.aydsii.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.aydsii.demo.model.tablas.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
}