package Etapa4;

public class UnderflowException extends RuntimeException {
    public UnderflowException() {
        super("Pilha/Fila vazia! Nenhum elemento para remover.");
    }
}