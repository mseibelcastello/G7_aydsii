package com.aydsii.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.aydsii.demo.model.tablas.UsuarioInterno;

public interface UsuarioInternoRepository extends JpaRepository<UsuarioInterno, String> {
}