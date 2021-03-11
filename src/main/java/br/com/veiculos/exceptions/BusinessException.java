package br.com.veiculos.exceptions;

public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = -1272687423112153059L;

    public BusinessException() {
        super("Erro ao completar a operaÃ§Ã£o, verifique os dados informados");
    }

    public BusinessException(String message) {
        super(message);
    }

}
