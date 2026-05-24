package Etapa4;

public class PrincipalEtapa4 {
    public static void main(String[] args) {
        System.out.println("Teste 1: Contagem de Elementos na Pilha (LinkedStack)");
        LinkedStack<Integer> stack = new LinkedStack<>();

        System.out.println("Pilha esta vazia? " + stack.isEmpty());

        stack.push(10);
        stack.push(20);
        stack.push(30);
        System.out.println("Elemento no topo da pilha: " + stack.top());

        System.out.println("Desempilhando: " + stack.pop());
        System.out.println("Numero de elementos na pilha: " + stack.numElements());
        System.out.println("---------------------------------------------------");

        System.out.println("Teste 2: Verificacao de Elementos na Fila (LinkedQueue)");
        LinkedQueue<Integer> queue = new LinkedQueue<>();

        System.out.println("Fila esta vazia? " + queue.isEmpty());

        queue.enqueue(100);
        queue.enqueue(200);
        queue.enqueue(300);
        System.out.println("Primeiro da fila: " + queue.front());

        System.out.println("Desenfileirando: " + queue.dequeue());
        System.out.println("Numero de elementos na fila: " + queue.numElements());

        System.out.println("Ultimo da fila (back): " + queue.back());
        System.out.println("---------------------------------------------------");
    }
}
