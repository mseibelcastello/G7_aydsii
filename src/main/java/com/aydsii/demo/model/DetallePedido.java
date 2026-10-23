package com.aydsii.demo.model;

import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "detalle_pedido")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Línea de un pedido: un producto con su cantidad y precio")
public class DetallePedido {

    @MapsId("pedidoId")
    @ManyToOne
    @JoinColumn(name = "pedido_id")
    @Schema(description = "Pedido al que pertenece la línea")
    private Pedido pedido;

    @MapsId("productoCodigo")
    @ManyToOne
    @JoinColumn(name = "producto_codigo")
    @Schema(description = "Producto pedido")
    private Producto producto;

    @NotNull(message = "La cantidad es obligatoria.")
    @Positive(message = "La cantidad debe ser mayor a cero.")
    @Schema(description = "Cantidad de packs pedidos")
    private Integer cantidad;

    @NotNull(message = "El precio unitario es obligatorio.")
    @PositiveOrZero(message = "El precio no puede ser negativo.")
    @Schema(description = "Precio unitario al momento del pedido")
    private Double precioUnitario;

    @PositiveOrZero(message = "El descuento no puede ser negativo.")
    @Schema(description = "Descuento aplicado")
    private Integer descuentoAplicado;
}