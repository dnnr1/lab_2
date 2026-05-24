package Etapa3;

public class StaticList<E> implements List<E> {
    private Object[] elements;
    private int numElements;

    public StaticList(int size) {
        int capacity = size > 0 ? size : 10;
        this.elements = new Object[capacity];
        this.numElements = 0;
    }

    @Override
    public int numElements() {
        return numElements;
    }

    @Override
    public boolean isEmpty() {
        return numElements == 0;
    }

    @Override
    public boolean isFull() {
        return numElements == elements.length;
    }

    @Override
    public void insert(E element, int pos) {
        if (pos < 0 || pos > numElements) {
            throw new IndexOutOfBoundsException("Posicao invalida: " + pos);
        }
        if (isFull()) {
            throw new OverflowException();
        }
        for (int i = numElements; i > pos; i--) {
            elements[i] = elements[i - 1];
        }
        elements[pos] = element;
        numElements++;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E remove(int pos) {
        if (pos < 0 || pos >= numElements) {
            throw new IndexOutOfBoundsException("Posicao invalida: " + pos);
        }
        E removed = (E) elements[pos];
        for (int i = pos; i < numElements - 1; i++) {
            elements[i] = elements[i + 1];
        }
        elements[numElements - 1] = null;
        numElements--;
        return removed;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E get(int pos) {
        if (pos < 0 || pos >= numElements) {
            throw new IndexOutOfBoundsException("Posicao invalida: " + pos);
        }
        return (E) elements[pos];
    }

    @Override
    public int search(E element) {
        for (int i = 0; i < numElements; i++) {
            Object current = elements[i];
            if (element == null ? current == null : element.equals(current)) {
                return i;
            }
        }
        return -1;
    }

    public int contaElementos(Object el) {
        return contaElementosRecursivo(el, 0);
    }

    private int contaElementosRecursivo(Object el, int index) {
        if (index >= numElements) {
            return 0;
        }
        boolean match = el == null ? elements[index] == null : el.equals(elements[index]);
        return (match ? 1 : 0) + contaElementosRecursivo(el, index + 1);
    }
}
