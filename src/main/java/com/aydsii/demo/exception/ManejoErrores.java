package com.aydsii.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.aydsii.demo.model.*;

@RestControllerAdvice
public class ManejoErrores {

    // captua cuando se lanza de alguna fomra clientenoencontrado, que recupera el
    // mensaje generado en esa clase
    @ExceptionHandler(ClienteNoEncontradoException.class)
    public ResponseEntity<ApiResponse<Void>> clienteNoEncontrado(ClienteNoEncontradoException e) {
        int status = HttpStatus.NOT_FOUND.value();

        // devuelve la respuesta con el código HTTP 404 y, en el cuerpo, un ApiResponse
        // de error con el mensaje de la excepción.
        return ResponseEntity.status(status).body(ApiResponse.error(status, e.getMessage()));
    }

    @ExceptionHandler(PedidoNoEncontradoException.class)
    public ResponseEntity<ApiResponse<Void>> pedidoNoEncontrado(PedidoNoEncontradoException e) {
        int status = HttpStatus.NOT_FOUND.value();
        return ResponseEntity.status(status).body(ApiResponse.error(status, e.getMessage()));
    }

    // CU-01: se lanza cuando el DNI del vendedor que carga la promoción no existe.
    // Responde 404.
    @ExceptionHandler(UsuarioNoEncontradoException.class)
    public ResponseEntity<ApiResponse<Void>> usuarioNoEncontrado(UsuarioNoEncontradoException e) {
        int status = HttpStatus.NOT_FOUND.value();
        return ResponseEntity.status(status).body(ApiResponse.error(status, e.getMessage()));
    }

    // CU-01: se lanza cuando alguno de los códigos de producto de la promoción
    // no existe en el catálogo. Responde 404.
    @ExceptionHandler(ProductoNoEncontradoException.class)
    public ResponseEntity<ApiResponse<Void>> productoNoEncontrado(ProductoNoEncontradoException e) {
        int status = HttpStatus.NOT_FOUND.value();
        return ResponseEntity.status(status).body(ApiResponse.error(status, e.getMessage()));
    }

    // CU-01: se lanza cuando los datos violan una regla de negocio
    // (RN-12: porcentaje mayor a 100, RN-13: fecha de fin anterior a la de inicio,
    // o POR_CANTIDAD sin cantidad mínima). Responde 400 (Bad Request):
    // el problema está en lo que envió el cliente.
    @ExceptionHandler(PromocionInvalidaException.class)
    public ResponseEntity<ApiResponse<Void>> promocionInvalida(PromocionInvalidaException e) {
        int status = HttpStatus.BAD_REQUEST.value();
        return ResponseEntity.status(status).body(ApiResponse.error(status, e.getMessage()));
    }

    // CU-01 (FE-02 / RN-14): se lanza cuando algún producto ya tiene otra promoción
    // en el mismo período. Responde 409 (Conflict): los datos son válidos,
    // pero chocan con algo que ya existe en el sistema.
    @ExceptionHandler(PromocionSuperpuestaException.class)
    public ResponseEntity<ApiResponse<Void>> promocionSuperpuesta(PromocionSuperpuestaException e) {
        int status = HttpStatus.CONFLICT.value();
        return ResponseEntity.status(status).body(ApiResponse.error(status, e.getMessage()));
    }

    // Se dispara cuando falla alguna anotación de validación (@NotBlank, @Pattern,
    // @NotNull...)
    // en un @RequestBody marcado con @Valid. Responde 400 con el mensaje del primer
    // campo inválido.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> validacion(MethodArgumentNotValidException e) {
        int status = HttpStatus.BAD_REQUEST.value();
        String mensaje = e.getBindingResult().getFieldErrors().stream()
                .map(f -> f.getField() + ": " + f.getDefaultMessage())
                .collect(java.util.stream.Collectors.joining("; "));
        return ResponseEntity.status(status).body(ApiResponse.error(status, mensaje));
    }

}

/*
 * El camino de un error es:
 * Service lanza la excepción → Spring la intercepta
 * → busca el @ExceptionHandler que coincide → arma la respuesta HTTP
 * 
 * 
 * Cada método recibe
 * la excepción como parámetro (e), así que puede leer su mensaje con
 * e.getMessage().
 */