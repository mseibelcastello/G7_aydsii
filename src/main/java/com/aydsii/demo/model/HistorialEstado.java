package com.aydsii.demo.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "historial_estado")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Registro de un cambio de estado de un pedido")
public class HistorialEstado {

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    @Schema(description = "Fecha y hora del cambio de estado (se genera automáticamente)")
    private LocalDateTime fechaHora;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "El estado es obligatorio.")
    @Schema(description = "Estado al que pasó el pedido")
    private TipoEstado estado;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    @NotNull(message = "El pedido es obligatorio.")
    @Schema(description = "Pedido al que pertenece el cambio de estado")
    private Long idPedido;

}
