package exception;

public class TarefaInvalidaException extends RuntimeException {

    public TarefaInvalidaException(String mensagem) {
        super(mensagem);
    }
}
