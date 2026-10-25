package com.aydsii.demo.exception;

// RN-12 y RN-13: datos que violan una regla de negocio -> 400
public class PromocionInvalidaException extends RuntimeException {
    public PromocionInvalidaException(String mensaje) {
        super(mensaje);
    }
}