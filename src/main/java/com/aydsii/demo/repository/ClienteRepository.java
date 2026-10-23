package com.aydsii.demo.repository;

import com.aydsii.demo.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, String> {
}/*
 * No hace falta escribir el método para guardar porque ClienteRepository hereda
 * de JpaRepository, el cual ya incluye el método save().
 * Cuando el Service llama a save(),
 * Hibernate lee las anotaciones @Entity de la clase Cliente y
 * genera automáticamente la sentencia INSERT INTO en MySQL.
 */