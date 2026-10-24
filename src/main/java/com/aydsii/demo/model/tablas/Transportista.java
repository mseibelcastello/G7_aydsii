package com.aydsii.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "transportista")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transportista {

    @Id // el CUIT lo carga el usuario, por eso no hay @GeneratedValue
    @NotBlank(message = "El CUIT es obligatorio")
    @Pattern(regexp = "\\d{11}", message = "El CUIT debe tener exactamente 11 dígitos, sin guiones")
    @Column(length = 11)
    @Schema(description = "CUIT del transportista", example = "30700000001")
    private String cuit;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 45, message = "El nombre no puede superar los 45 caracteres")
    @Column(nullable = false, length = 45)
    @Schema(description = "nombre o razón social", example = "Correo Argentino")
    private String nombre;

    @Size(max = 45, message = "El teléfono no puede superar los 45 caracteres")
    @Column(length = 45)
    @Schema(description = "teléfono de contacto", example = "0800-222-3333")
    private String telefono;

    @Size(max = 45, message = "La dirección no puede superar los 45 caracteres")
    @Column(length = 45)
    @Schema(description = "dirección del transportista")
    private String direccion;

    // 1:N con Envio (la FK transportista_cuit está en Envio). Se descomenta cuando
    // exista Envio.
    @OneToMany(mappedBy = "transportista")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private List<Envio> envios = new ArrayList<>();
}