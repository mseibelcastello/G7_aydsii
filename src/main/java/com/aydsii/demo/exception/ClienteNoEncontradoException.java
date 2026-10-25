package com.aydsii.demo.exception;

//excepcion propia (no verificada) que se lanza cuando no existe un cliente con el DNI dado; el mensaje se obtiene con getMessage().
public class ClienteNoEncontradoException extends RuntimeException {
    public ClienteNoEncontradoException(String dni) {
        super("No se encontró el cliente con DNI " + dni + ".");
    }
}
