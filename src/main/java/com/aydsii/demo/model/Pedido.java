package com.aydsii.demo.model;

import java.time.*;

import org.hibernate.annotations.CreationTimestamp;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pedidos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador del pedido (se genera automáticamente)")
    private Long id;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    @Schema(description = "Fecha y hora del pedido (se genera automáticamente)")
    private LocalDateTime fecha;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "El estado es obligatorio.")
    @Schema(description = "Estado actual del pedido")
    private TipoEstado estado;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "El modo de entrega es obligatorio.")
    @Schema(description = "Modo de entrega del pedido")
    private TipoEntrega modoEntrega;

    @Schema(description = "Dirección de entrega. Solo aplica si el modo de entrega es envío")
    private String direccionEntrega;

    @NotNull(message = "El valor total es obligatorio.")
    @PositiveOrZero(message = "El valor no puede ser negativo.")
    @Column(precision = 12, scale = 2)
    @Schema(description = "Valor total del pedido")
    private Double valorTotal;

    @Schema(description = "Motivo de cancelación. Solo se completa si el pedido fue cancelado")
    private String motivoCancelacion;

    @ManyToOne
    @JoinColumn(name = "cliente_dni")
    @NotNull(message = "El cliente es obligatorio.")
    @Schema(description = "Cliente que realizó el pedido")
    private Cliente cliente;
}
