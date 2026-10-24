package com.aydsii.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.aydsii.demo.model.tablas.Promocion;

public interface PromocionRepository extends JpaRepository<Promocion, Integer> {

}