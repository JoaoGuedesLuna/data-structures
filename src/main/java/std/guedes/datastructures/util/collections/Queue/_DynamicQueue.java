package main.java.std.guedes.datastructures.util.collections.Queue;

import java.util.Objects;

/**
 * Uma fila é uma lista ordenada de elementos. Ela sempre funciona primeiro a entra primeiro a sair (FIFO).
 * Todos os elementos são inseridos na parte TRASEIRA e removidos da FRENTE da fila. Nesse caso da
 * implementação da fila dinâmica ela é disposta por nós (Nodes) que se conectam a no máximo 1 outro
 * nó e não possuem conhecimento se outro nó está ligado a eles.
 *
 * @param <T> Tipo de dados que a fila armazenará.
 *
 * @author João Guedes.
 */
public class _DynamicQueue<T> implements _Queue<T> {

    /**
     * Primeiro nó da estrutura.
     */
    protected _Node<T> first;
    /**
     * Último nó da estrutura.
     */
    protected _Node<T> last;
    /**
     * Tamanho da fila. Considera as posições ocupadas e não o tamanho literal da estrutura.
     */
    protected int size;

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
        if (this.last != null) {
            this.last.setNext(newNode);
        }
        this.last = newNode;
        if (this.first == null) {
            this.first = newNode;
        }
        this.size++;
        return true;
    }

    @Override
    public boolean remove(T element) {
        if (this.isEmpty()) {
            return false;
        }
        _Node<T> current = this.first;
        _Node<T> next;
        if (element == null) {
            if (current.getElement() == null) {
                this.remove();
                return true;
            }
            while (current.getNext() != null) {
                next = current.getNext();
                if (next.getElement() == null) {
                    if (next == this.last) {
                        this.last = null;
                    }
                    current.setNext(next.getNext());
                    next.setElement(null);
                    next.setNext(null);
                    this.size--;
                    return true;
                }
                current = next;
            }
        }
        else {
            if (element.equals(current.getElement())) {
                this.remove();
                return true;
            }
            while (current.getNext() != null) {
                next = current.getNext();
                if (element.equals(next.getElement())) {
                    if (next == this.last) {
                        this.last = null;
                    }
                    current.setNext(next.getNext());
                    next.setElement(null);
                    next.setNext(null);
                    this.size--;
                    return true;
                }
                current = next;
            }
        }
        return false;
    }

    @Override
    public T remove() {
        if (this.isEmpty()) {
            return null;
        }
        _Node<T> oldFirst = this.first;
        T elementRemoved = oldFirst.getElement();
        this.first = oldFirst.getNext();
        oldFirst.setElement(null);
        oldFirst.setNext(null);
        if (this.first == null) {
            this.last = null;
        }
        this.size--;
        return elementRemoved;
    }

    @Override
    public T peek() {
        return !this.isEmpty() ? this.first.getElement() : null;
    }

    @Override
    public boolean contains(T element) {
        if (element == null) {
            for (_Node<T> current = this.first; current != null;) {
                if (current.getElement() == null) {
                    return true;
                }
                current = current.getNext();
            }
        }
        else {
            for (_Node<T> current = this.first; current != null;) {
                if (element.equals(current.getElement())) {
                    return true;
                }
                current = current.getNext();
            }
        }
        return false;
    }

   @Override
    public void clear() {
        for (_Node<T> current = this.first; current != null;) {
            _Node<T> next = current.getNext();
            current.setElement(null);
            current.setNext(null);
            current = next;
        }
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[this.size];
        _Node<T> current = this.first;
        for (int i = 0; i < array.length; i++) {
            array[i] = current.getElement();
            current = current.getNext();
        }
        return array;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        if (!this.isEmpty()) {
            sb.append(this.first);
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
    protected static class _Node<T> {

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
            StringBuilder sb = new StringBuilder();
            sb.append(this.element);
            if (this.next != null) {
                sb.append(", ").append(this.next);
            }
            return sb.toString();
        }

    }

}