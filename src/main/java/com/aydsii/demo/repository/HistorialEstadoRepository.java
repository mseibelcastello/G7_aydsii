package com.aydsii.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.aydsii.demo.model.tablas.HistorialEstado;

public interface HistorialEstadoRepository extends
        JpaRepository<HistorialEstado, Long> {
}
