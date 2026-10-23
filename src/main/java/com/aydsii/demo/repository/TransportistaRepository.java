package com.aydsii.demo.repository;

import com.aydsii.demo.model.Transportista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransportistaRepository extends JpaRepository<Transportista, String> {
}