package br.com.veiculos.exceptions;

public class HttpServerErrorException extends RuntimeException {
    private static final long serialVersionUID = -1272687423114153059L;
    public HttpServerErrorException() {
        super("O elemento procurado nao existe na tabela fipe");
    }

    public HttpServerErrorException(String message) {
        super(message);
    }

}
