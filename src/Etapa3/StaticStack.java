package Etapa3;

public class StaticStack<E> implements Stack<E> {
    private Object[] elements;
    private int top;

    public StaticStack(int size) {
        int capacity = size > 0 ? size : 10;
        this.elements = new Object[capacity];
        this.top = -1;
    }

    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    @Override
    public boolean isFull() {
        return top == elements.length - 1;
    }

    @Override
    public int numElements() {
        return top + 1;
    }

    @Override
    public void push(E element) throws OverflowException {
        if (isFull()) {
            throw new OverflowException();
        }
        elements[++top] = element;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E pop() throws UnderflowException {
        if (isEmpty()) {
            throw new UnderflowException();
        }
        E value = (E) elements[top];
        elements[top] = null;
        top--;
        return value;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E top() throws UnderflowException {
        if (isEmpty()) {
            throw new UnderflowException();
        }
        return (E) elements[top];
    }
}
