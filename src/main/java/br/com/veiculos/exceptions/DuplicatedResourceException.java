package br.com.veiculos.exceptions;


public class DuplicatedResourceException extends RuntimeException {
    private static final long serialVersionUID = 5266023531583283367L;

    public DuplicatedResourceException() {
        super("Erro ao salvar informaÃ§Ãµes, o recurso jÃ¡ existe");
    }

    public DuplicatedResourceException(String message) {
        super(message);
    }
}