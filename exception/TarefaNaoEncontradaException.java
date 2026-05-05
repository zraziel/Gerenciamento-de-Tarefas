package exception;

public class TarefaNaoEncontradaException extends RuntimeException {

    public TarefaNaoEncontradaException(int id) {
        super("Tarefa com ID " + id + " não encontrada.");
    }
}
