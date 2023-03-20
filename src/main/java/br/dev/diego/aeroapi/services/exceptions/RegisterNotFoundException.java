package br.dev.diego.aeroapi.services.exceptions;

public class RegisterNotFoundException extends RuntimeException {

    public RegisterNotFoundException(String message) {
        super(message);
    }
}
