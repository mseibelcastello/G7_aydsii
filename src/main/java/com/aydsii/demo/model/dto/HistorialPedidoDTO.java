package com.aydsii.demo.model.dto;

import java.util.List;

import com.aydsii.demo.model.tablas.TipoEstado;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Historial de cambios de estado de un pedido")
public class HistorialPedidoDTO {

    @Schema(description = "Número del pedido")
    private Long pedidoId;

    @Schema(description = "Estado actual del pedido")
    private TipoEstado estadoActual;

    @Schema(description = "Motivo de cancelación (solo si el pedido fue cancelado)")
    private String motivoCancelacion;

    @Schema(description = "Cambios de estado")
    private List<CambioEstadoDTO> historial;
}