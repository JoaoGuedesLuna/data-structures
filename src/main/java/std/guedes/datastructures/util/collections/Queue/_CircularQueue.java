package main.java.std.guedes.datastructures.util.collections.Queue;

import java.util.Objects;

/**
 * Fila circular é apenas uma variação da fila linear em que a frente e a traseira são conectadas entre si.
 *
 * @param <T> Tipo de dados que a fila circular armazenará.
 *
 * @author João Guedes.
 */
public class _CircularQueue<T> implements _Queue<T> {

    /**
     * Nó da frente da estrutura.
     */
    private _Node<T> front;
    /**
     * Nó da traseira da estrutura.
     */
    private _Node<T> rear;
    /**
     * Tamanho da lista circular. Considera as posições ocupadas e não o tamanho literal da estrutura.
     */
    private int size;

    public _CircularQueue() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean add(T element) {
        _Node<T> newNode = new _Node<>(element);
        if (this.front == null) {
            this.rear = this.front = newNode;
        }
        else {
            this.rear.setNext(newNode);
            this.rear = newNode;
        }
        this.rear.setNext(this.front);
        this.size++;
        return true;
    }

    @Override
    public boolean remove(T element) {
        if (!this.isEmpty()) {
            _Node<T> current = this.front;
            _Node<T> next;
            if (element == null) {
                if (current.getElement() == null) {
                    this.remove();
                    return true;
                }
                do {
                    next = current.getNext();
                    if (next.getElement() == null) {
                        if (next == this.rear) {
                            this.rear = current;
                        }
                        current.setNext(next.getNext());
                        next.setElement(null);
                        next.setNext(null);
                        this.size--;
                        return true;
                    }
                    current = next;
                } while (current != this.front);
            }
            else {
                if (element.equals(current.getElement())) {
                    this.remove();
                    return true;
                }
                do {
                    next = current.getNext();
                    if (element.equals(next.getElement())) {
                        if (next == this.rear) {
                            this.rear = current;
                        }
                        current.setNext(next.getNext());
                        next.setElement(null);
                        next.setNext(null);
                        this.size--;
                        return true;
                    }
                    current = next;
                } while (current != this.front);
            }
        }
        return false;
    }

    @Override
    public T remove() {
        if (this.isEmpty()) {
            return null;
        }
        _Node<T> oldHead = this.front;
        T elementRemoved = oldHead.getElement();
        if (oldHead == this.rear) {
            this.front = this.rear = null;
        }
        else {
            this.front = this.front.next;
            this.rear.setNext(this.front);
        }
        oldHead.setElement(null);
        oldHead.setNext(null);
        this.size--;
        return elementRemoved;
    }

    @Override
    public T peek() {
        return this.front.getElement();
    }

    @Override
    public boolean contains(T element) {
        if (!this.isEmpty()) {
            _Node<T> current = this.front;
            if (element == null) {
                do {
                    if (current.getElement() == null) {
                        return true;
                    }
                    current = current.getNext();
                } while (current != this.front);
            }
            else {
                do {
                    if (element.equals(current.getElement())) {
                        return true;
                    }
                    current = current.getNext();
                } while (current != this.front);
            }
        }
        return false;
    }

    @Override
    public void clear() {
        _Node<T> next;
        for (_Node<T> current = this.front; current != null;) {
            next = current.getNext();
            current.setElement(null);
            current.setNext(null);
            current = next;
        }
        this.rear = this.front = null;
        this.size = 0;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[this.size];
        _Node<T> current = this.front;
        for  (int i = 0; i < array.length; i++) {
            array[i] = current.getElement();
            current = current.getNext();
        }
        return array;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        _Node<T> current = this.front;
        while (current != this.rear) {
            sb.append(current).append(", ");
            current = current.getNext();
        }
        if (!this.isEmpty()) {
            sb.append(current.getElement());
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * Essa classe representa um nó simples.
     *
     * @param <T> Tipo de dado que estará contido dentro do nó.
     *
     * @author João Guedes.
     */
    private static class _Node<T> {

        /**
         * Elemento contido dentro do nó.
         */
        private T element;
        /**
         * Próximo nó.
         */
        private _Node<T> next;

        public _Node(T element, _Node<T> next) {
            this.element = element;
            this.next = next;
        }

        public _Node(T element) {
            this.element = element;
            this.next = null;
        }

        public T getElement() {
            return this.element;
        }

        public void setElement(T element) {
            this.element = element;
        }

        public _Node<T> getNext() {
            return this.next;
        }

        public void setNext(_Node<T> next) {
            this.next = next;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            _Node<?> node = (_Node<?>) o;
            return  Objects.equals(this.element, node.element) &&
                    Objects.equals(this.next, node.next);
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.element, this.next);
        }

        @Override
        public String toString() {
            return this.element != null ? this.element.toString() : "null";
        }

    }

}