package com.aydsii.demo.controller;

import com.aydsii.demo.model.ApiResponse;
import com.aydsii.demo.model.dto.PromocionRequestDTO;
import com.aydsii.demo.model.dto.PromocionRespuestaDTO;
import com.aydsii.demo.service.PromocionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/promociones")
@Tag(name = "CU-01 Cargar Promoción")
public class PromocionController {

    private final PromocionService promocionService;

    public PromocionController(PromocionService promocionService) {
        this.promocionService = promocionService;
    }

    @Operation(summary = "Cargar una nueva promoción", description = "400: datos inválidos o regla de negocio (RN-11, RN-12, RN-13). "
            + "404: vendedor o producto inexistente. "
            + "409: un producto ya tiene otra promoción en ese período (RN-14).")
    @PostMapping
    public ResponseEntity<ApiResponse<PromocionRespuestaDTO>> cargarPromocion(
            @Valid @RequestBody PromocionRequestDTO dto) {
        PromocionRespuestaDTO creada = promocionService.cargarPromocion(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(201, "Promoción cargada con éxito", creada));
    }
}