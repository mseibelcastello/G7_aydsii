package com.aydsii.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.aydsii.demo.model.*;
import java.util.List;
import com.aydsii.demo.service.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;

import com.aydsii.demo.model.DTO.PedidoResumenDTO;

@RestController
@RequestMapping("/pedidos")
@Tag(name = "Pedidos", description = "Consulta de pedidos y su estado")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    // -----------------------------------------------------------
    // ------LISTAR TODOS LOS PEDIDOS DEL CLIENTE POR DNI
    // -----------------------------------------------------------
    @Operation(summary = "Listar pedidos del cliente", description = "Devuelve los pedidos del cliente con su estado actual, del más nuevo al más viejo")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Lista de pedidos (vacía si el cliente no tiene)"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "No existe un cliente con ese DNI")
    })
    @GetMapping
    public ResponseEntity<ApiResponse<List<PedidoResumenDTO>>> listarPedidos(
            @Parameter(description = "DNI del cliente") @RequestParam String dni) {
        return ResponseEntity.ok(ApiResponse.ok(pedidoService.listarPedidos(dni)));
    }
}
