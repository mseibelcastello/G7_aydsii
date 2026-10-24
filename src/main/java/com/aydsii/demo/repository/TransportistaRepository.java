package com.aydsii.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.aydsii.demo.model.tablas.Transportista;

public interface TransportistaRepository extends JpaRepository<Transportista, String> {
}