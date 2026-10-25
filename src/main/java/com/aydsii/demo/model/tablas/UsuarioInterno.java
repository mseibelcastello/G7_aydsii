package com.aydsii.demo.model.tablas;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "usuarioInterno")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioInterno {

    @Id // el DNI es la PK y lo carga el usuario, por eso no hay @GeneratedValue
    @NotBlank(message = "El DNI es obligatorio")
    @Pattern(regexp = "\\d{8}", message = "El DNI debe tener exactamente 8 dígitos")
    @Column(length = 8)
    @Schema(description = "DNI del usuario", example = "30111222")
    private String dni;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 45, message = "El nombre no puede superar los 45 caracteres")
    @Schema(description = "nombre de usuario")
    @Column(nullable = false, length = 45)
    private String nombre;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email no tiene un formato válido")
    @Schema(description = "mail de usuario")
    @Column(nullable = false, length = 45, unique = true)
    private String email;

    @NotBlank(message = "El rol es obligatorio")
    @Column(nullable = false, length = 45)
    private String rol; // VENDEDOR, ENCARGADO_PRODUCCION, LOGISTICA

    @OneToMany(mappedBy = "usuario") // "usuario" es el nombre del atributo en Promocion
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private List<Promocion> promociones = new ArrayList<>();
}