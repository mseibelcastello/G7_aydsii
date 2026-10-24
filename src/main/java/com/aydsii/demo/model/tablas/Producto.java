package com.aydsii.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "productos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    @Id // el código lo carga el vendedor, por eso no hay @GeneratedValue
    @NotNull(message = "El código es obligatorio")
    @Positive(message = "El código debe ser mayor a 0")
    @Schema(description = "código del producto", example = "1")
    private Integer codigo;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 45, message = "El nombre no puede superar los 45 caracteres")
    @Column(nullable = false, length = 45)
    @Schema(description = "nombre del producto", example = "Vaso PET 250cc")
    private String nombre;

    @Size(max = 45, message = "La descripción no puede superar los 45 caracteres")
    @Column(length = 45)
    @Schema(description = "descripción breve (presentación)", example = "Pack 10 un.")
    private String descripcion;

    @Size(max = 45)
    @Column(length = 45)
    @Schema(description = "nombre del archivo de imagen")
    private String imagen;

    @NotBlank(message = "La categoría es obligatoria")
    @Size(max = 45)
    @Column(nullable = false, length = 45)
    @Schema(description = "categoría del producto", example = "Vasos")
    private String categoria;

    @NotNull(message = "El precio por pack es obligatorio")
    @Positive(message = "El precio por pack debe ser mayor a 0")
    @Column(nullable = false)
    @Schema(description = "precio por pack en pesos argentinos", example = "4500")
    private Double precioPack;

    @NotNull(message = "Las unidades por pack son obligatorias")
    @Positive(message = "Las unidades por pack deben ser mayor a 0")
    @Column(nullable = false)
    @Schema(example = "10")
    private Integer unidadesPack;

    @NotNull(message = "El stock físico es obligatorio")
    @PositiveOrZero(message = "El stock físico no puede ser negativo")
    @Column(nullable = false)
    @Schema(example = "100")
    private Integer stockFisico;

    @PositiveOrZero(message = "El stock reservado no puede ser negativo")
    @Column(nullable = false)
    @Schema(description = "lo gestiona el sistema; empieza en 0", example = "0")
    private Integer stockReservado = 0;

    @NotNull(message = "El stock mínimo es obligatorio")
    @PositiveOrZero(message = "El stock mínimo no puede ser negativo")
    @Column(nullable = false)
    @Schema(example = "20")
    private Integer stockMinimo;

    @Column(nullable = false, length = 45)
    @Schema(description = "DISPONIBLE, STOCK_BAJO o SIN_STOCK (lo calcula el sistema)", accessMode = Schema.AccessMode.READ_ONLY)
    private String estado;

    // N:M con promociones: este es el lado inverso, la tabla intermedia ya la
    // define Promocion
    @ManyToMany(mappedBy = "productos")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private Set<Promocion> promociones = new HashSet<>();

    // 1:N con detalle de pedido (se descomenta cuando exista la entidad
    // DetallePedido)
    // @OneToMany(mappedBy = "producto")
    // @ToString.Exclude @EqualsAndHashCode.Exclude @JsonIgnore
    // private List<DetallePedido> detalles = new ArrayList<>();

    // RN-02: stock disponible = stock físico - stock reservado (se calcula, no es
    // columna)
    @Transient
    @Schema(description = "stock físico menos stock reservado", accessMode = Schema.AccessMode.READ_ONLY)
    public Integer getStockDisponible() {
        return stockFisico - stockReservado;
    }
}
