package com.aydsii.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.aydsii.demo.model.*;
import com.aydsii.demo.model.dto.HistorialPedidoDTO;
import com.aydsii.demo.model.dto.PedidoResumenDTO;

import java.util.List;
import com.aydsii.demo.service.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;

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

    // ------------------------------------------------------------------
    // ------LISTAR EL HISTORIAL DE ESTADOS DE PEDIDOS POR ID DE PEDIDO
    // -----------------------------------------------------------------
    @Operation(summary = "Consultar historial de un pedido", description = "Devuelve el estado actual del pedido y todos sus cambios de estado, del más viejo al más nuevo")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Historial del pedido"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "No existe un pedido con ese número")
    })
    @GetMapping("/{id}/historial") // se identifica un recurso concreto
    public ResponseEntity<ApiResponse<HistorialPedidoDTO>> consultarHistorial(
            @Parameter(description = "Número del pedido") @PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(pedidoService.consultarHistorial(id)));
    }
}
