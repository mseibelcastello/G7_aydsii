package com.aydsii.demo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "envios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Envio {

    // PK = FK al pedido: no se autogenera, se toma del pedido asociado (@MapsId)
    @Id
    @Column(name = "pedido_id")
    @Schema(description = "id del pedido al que pertenece el envío", accessMode = Schema.AccessMode.READ_ONLY)
    private Integer pedidoId;

    @OneToOne(optional = false)
    @MapsId
    @JoinColumn(name = "pedido_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Pedido pedido;

    // Muchos envíos -> un transportista (lado dueño, tiene la FK)
    @NotNull(message = "El transportista es obligatorio")
    @ManyToOne(optional = false)
    @JoinColumn(name = "transportista_cuit")
    private Transportista transportista;

    // RN-25: se completa recién cuando Logística registra el despacho
    @Size(max = 45, message = "El número de seguimiento no puede superar los 45 caracteres")
    @Column(length = 45)
    @Schema(description = "número de seguimiento del transportista", example = "CA123456789")
    private String numeroSeguimiento;

    @NotBlank(message = "El estado del envío es obligatorio")
    @Column(nullable = false, length = 45)
    @Schema(description = "A_DESPACHAR, EN_CAMINO, ENTREGADO o NO_ENTREGADO", example = "EN_CAMINO")
    private String estado;

    @Schema(description = "fecha en que se entregó al transportista", example = "2026-10-17")
    private LocalDate fechaDespacho;

    @Schema(description = "fecha de entrega al cliente (null hasta que se entrega)")
    private LocalDate fechaEntrega;
}