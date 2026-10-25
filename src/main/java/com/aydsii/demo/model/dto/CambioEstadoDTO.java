package com.aydsii.demo.model.dto;

import java.time.LocalDateTime;

import com.aydsii.demo.model.tablas.TipoEstado;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Un cambio de estado dentro del historial de un pedido")
public class CambioEstadoDTO {

    @Schema(description = "Estado al que pasó el pedido")
    private TipoEstado estado;

    @Schema(description = "Fecha y hora del cambio de estado")
    private LocalDateTime fechaHora;
}