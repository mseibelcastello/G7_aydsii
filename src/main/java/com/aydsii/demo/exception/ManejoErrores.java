package com.aydsii.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

}
