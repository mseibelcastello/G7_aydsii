package com.aydsii.demo.exception;

// RN-14 / FE-02: producto con otra promoción en el mismo período -> 409
public class PromocionSuperpuestaException extends RuntimeException {
    public PromocionSuperpuestaException(String mensaje) {
        super(mensaje);
    }
}