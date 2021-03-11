package br.com.veiculos.exceptions;


public class ResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -6030733006897912418L;

    public ResourceNotFoundException() {
        super("O recurso solicitado não foi encontado");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}