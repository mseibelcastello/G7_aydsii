package com.aydsii.demo.model.DTO;

import java.time.LocalDateTime;

import com.aydsii.demo.model.tablas.TipoEntrega;
import com.aydsii.demo.model.tablas.TipoEstado;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Resumen de un pedido con su estado actual")
public class PedidoResumenDTO {

    @Schema(description = "Número del pedido")
    private Long pedidoId;

    @Schema(description = "Fecha y hora en que se hizo el pedido")
    private LocalDateTime fecha;

    @Schema(description = "Estado actual del pedido")
    private TipoEstado estado;

    @Schema(description = "Modo de entrega del pedido")
    private TipoEntrega modoEntrega;

    @Schema(description = "Valor total del pedido en pesos")
    private Double valorTotal;

}
