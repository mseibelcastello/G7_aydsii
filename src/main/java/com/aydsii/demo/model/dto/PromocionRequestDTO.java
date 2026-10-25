package com.aydsii.demo.model.dto;

import com.aydsii.demo.model.tablas.TipoPromocion;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

public record PromocionRequestDTO(

        @NotBlank(message = "El nombre es obligatorio") @Size(max = 45, message = "El nombre no puede superar los 45 caracteres") @Schema(example = "Promo Bandejas") String nombre,

        @NotNull(message = "El tipo es obligatorio (PORCENTAJE o POR_CANTIDAD)") @Schema(example = "PORCENTAJE") TipoPromocion tipo,

        @NotNull(message = "El valor del descuento es obligatorio") @Positive(message = "El descuento debe ser mayor a 0") @Schema(example = "15") Double valor,

        @Positive(message = "La cantidad mínima debe ser mayor a 0") @Schema(description = "solo para POR_CANTIDAD", example = "5") Integer cantidadMinima,

        @NotNull(message = "La fecha de inicio es obligatoria") @Schema(example = "2026-11-01") LocalDate fechaInicio,

        @NotNull(message = "La fecha de fin es obligatoria") @Schema(example = "2026-11-30") LocalDate fechaFin,

        @NotEmpty(message = "Debe seleccionar al menos un producto") @Schema(description = "códigos de los productos", example = "[2, 3]") List<Integer> productos,

        @NotBlank(message = "El DNI del vendedor es obligatorio") @Pattern(regexp = "\\d{8}", message = "El DNI debe tener exactamente 8 dígitos") @Schema(example = "30111222") String usuarioDni) {
}