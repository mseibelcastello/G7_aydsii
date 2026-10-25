package com.aydsii.demo.model.dto;

import java.time.LocalDate;
import java.util.List;

public record PromocionRespuestaDTO(
        Integer id,
        String nombre,
        String tipo,
        Double valor,
        Integer cantidadMinima,
        LocalDate fechaInicio,
        LocalDate fechaFin,
        String estado,
        List<Integer> productos) {
}