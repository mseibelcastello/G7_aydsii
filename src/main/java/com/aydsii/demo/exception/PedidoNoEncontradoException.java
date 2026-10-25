package com.aydsii.demo.exception;

//excepcion propia que se lanza cuando no existe un pedido con el número dado
public class PedidoNoEncontradoException extends RuntimeException {
    public PedidoNoEncontradoException(Long id) {
        super("No se encontró el pedido " + id + ".");
    }
}
