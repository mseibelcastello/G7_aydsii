package com.aydsii.demo.model.tablas;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "promociones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Promocion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "id de la promoción (autogenerado)", accessMode = Schema.AccessMode.READ_ONLY)
    private Integer id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 45, message = "El nombre no puede superar los 45 caracteres")
    @Column(nullable = false, length = 45)
    @Schema(description = "nombre de la promoción", example = "Promo Bandejas")
    private String nombre;

    @NotNull(message = "El tipo es obligatorio (PORCENTAJE o POR_CANTIDAD)")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 45)
    @Schema(description = "tipo de descuento", example = "PORCENTAJE")
    private TipoPromocion tipo;

    @NotNull(message = "El valor del descuento es obligatorio")
    @Positive(message = "El descuento debe ser mayor a 0")
    @Column(nullable = false)
    @Schema(description = "valor del descuento (porcentaje o monto)", example = "15")
    private Double valor;

    @Positive(message = "La cantidad mínima debe ser mayor a 0")
    @Schema(description = "cantidad mínima de packs; solo para POR_CANTIDAD", example = "5")
    private Integer cantidadMinima;

    @NotNull(message = "La fecha de inicio es obligatoria")
    @Column(nullable = false)
    @Schema(example = "2026-11-01")
    private LocalDate fechaInicio;

    @NotNull(message = "La fecha de fin es obligatoria")
    @Column(nullable = false)
    @Schema(example = "2026-11-30")
    private LocalDate fechaFin;

    @Column(nullable = false, length = 45)
    @Schema(description = "PROGRAMADA, VIGENTE o FINALIZADA (lo asigna el sistema)", accessMode = Schema.AccessMode.READ_ONLY)
    private String estado;

    // Muchas promociones -> un usuario interno (lado dueño de la relación, tiene la
    // FK)
    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_interno_dni")
    private UsuarioInterno usuario;

    // N:M con productos: Hibernate crea la tabla intermedia promocion_producto
    @ManyToMany
    @JoinTable(name = "promocion_producto", joinColumns = @JoinColumn(name = "promociones_id"), inverseJoinColumns = @JoinColumn(name = "productos_codigo"))
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Producto> productos = new HashSet<>();
}