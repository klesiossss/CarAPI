package br.com.veiculos.exceptions;

public class IllegalStateException extends RuntimeException  {


    public IllegalStateException() {
        super("Elemento na fipeAPI nao encontrado na lista");
    }

    public IllegalStateException(String message) {
        super(message);
    }
}
