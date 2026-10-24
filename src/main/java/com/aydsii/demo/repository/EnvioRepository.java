package com.aydsii.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.aydsii.demo.model.tablas.Envio;

public interface EnvioRepository extends JpaRepository<Envio, Integer> {
}