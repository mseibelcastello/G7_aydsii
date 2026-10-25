package com.aydsii.demo.repository;

import com.aydsii.demo.model.tablas.Promocion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;

public interface PromocionRepository extends JpaRepository<Promocion, Integer> {

    // RN-14: promociones no finalizadas, de alguno de esos productos,
    // cuyo período se superpone con el que se quiere cargar
    @Query("""
            SELECT DISTINCT p FROM Promocion p JOIN p.productos pr
            WHERE pr.codigo IN :codigos
              AND p.estado <> 'FINALIZADA'
              AND p.fechaInicio <= :fin
              AND p.fechaFin >= :inicio
            """)
    List<Promocion> findSuperpuestas(@Param("codigos") List<Integer> codigos,
            @Param("inicio") LocalDate inicio,
            @Param("fin") LocalDate fin);
}