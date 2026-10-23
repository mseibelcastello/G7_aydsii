package com.aydsii.demo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clientes")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Cliente {

    @Id
    @Schema(description = "DNI del cliente")
    @NotBlank(message = "El DNI es obligatorio.")
    @Pattern(regexp = "\\d{7,8}", message = "El DNI debe tener 7 u 8 dígitos.")
    private String dni;

    @Schema(description = "Nombre del cliente")
    @NotBlank(message = "El nombre es obligatorio.")
    private String nombre;

    @Schema(description = "Apellido del cliente")
    @NotBlank(message = "El apellido es obligatorio.")
    private String apellido;

    @Schema(description = "Email del cliente")
    @Email
    private String email;

    @Schema(description = "Telefono del cliente")
    private String telefono;

}
