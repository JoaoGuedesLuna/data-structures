package main.java.std.guedes.datastructures.util.collections.list;

import java.util.Objects;

/**
 * A lista encadeada simples é um tipo básico de estrutura de dados que tem por foco a flexibilidade no
 * manuseio das informações nela contida. Ela é disposta por nós (Nodes) que se conectam a no máximo 1
 * outro nó e não possuem conhecimento se outro nó está ligado a eles.
 *
 * @param <T> Tipo de dado que a lista armazenará.
 *
 * @author João Guedes.
 */
public class _SinglyLinkedList<T> implements _List<T> {

    /**
     * Primeiro nó da estrutura.
     */
    private _Node<T> first;
    /**
     * Tamanho da lista. Considera as posições ocupadas e não o tamanho literal da estrutura.
     */
    private int size;

    public _SinglyLinkedList() {
        this.first = null;
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
        if (this.isEmpty()) {
            this.first = new _Node<>(element);
        }
        else {
            _Node<T> current = this.getNode(this.size-1);
            current.setNext(new _Node<>(element));
        }
        this.size++;
        return true;
    }

    @Override
    public void add(int index, T element) {
        if (index < 0 || index > this.size) {
            throw new IllegalArgumentException ("Index " + index + " out of bounds for length " + this.size);
        }
        if (index == 0) {
            this.first = new _Node<>(element, this.first);
        }
        else {
            _Node<T> current = this.getNode(index-1);
            current.setNext(new _Node<>(element, current.getNext()));
        }
        this.size++;
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
                this.first = current.getNext();
                current.setElement(null);
                current.setNext(null);
                this.size--;
                return true;
            }
            while (current.getNext() != null) {
                next = current.getNext();
                if (next.getElement() == null) {
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
                this.first = current.getNext();
                current.setElement(null);
                current.setNext(null);
                this.size--;
                return true;
            }
            while (current.getNext() != null) {
                next = current.getNext();
                if (element.equals(next.getElement())) {
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
    public T remove(int index) {
        this.indexCheck(index);
        T elementRemoved;
        if (index == 0) {
            _Node<T> oldFirst = this.first;
            elementRemoved = oldFirst.getElement();
            this.first = oldFirst.getNext();
            oldFirst.setElement(null);
            oldFirst.setNext(null);
        }
        else {
            _Node<T> current = this.getNode(index-1);
            _Node<T> next = current.getNext();
            elementRemoved = next.getElement();
            current.setNext(next.getNext());
            next.setElement(null);
            next.setNext(null);
        }
        this.size--;
        return elementRemoved;
    }

    @Override
    public T get(int index) {
        return this.getNode(index).getElement();
    }

    @Override
    public T set(int index, T element) {
        _Node<T> current = this.getNode(index);
        T oldElement = current.getElement();
        current.setElement(element);
        return oldElement;
    }

    @Override
    public int indexOf(T element) {
        _Node<T> current = this.first;
        if (element == null) {
            for (int i = 0; i < this.size; i++) {
                if (current.getElement() == null) {
                    return i;
                }
                current = current.getNext();
            }
        }
        else {
            for (int i = 0; i < this.size; i++) {
                if (element.equals(current.getElement())) {
                    return i;
                }
                current = current.getNext();
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T element) {
        int lastIndex = -1;
        _Node<T> current = this.first;
        if (element == null) {
            for (int i = 0; i < this.size; i++) {
                if (current.getElement() == null) {
                    lastIndex = i;
                }
                current = current.getNext();
            }
        }
        else {
            for (int i = 0; i < this.size; i++) {
                if (element.equals(current.getElement())) {
                    lastIndex = i;
                }
                current = current.getNext();
            }
        }
        return lastIndex;
    }

    @Override
    public boolean contains(T element) {
        return this.indexOf(element) >= 0;
    }

    @Override
    public void clear() {
        _Node<T> next;
        for (_Node<T> current = this.first; current != null;) {
            next = current.getNext();
            current.setElement(null);
            current.setNext(null);
            current = next;
        }
        this.first = null;
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

    /**
     * Retorna o nó de uma posição específica. Caso a posição seja inválida será lançada uma exceção.
     *
     * @param index Posição do nó buscado.
     *
     * @return Um nó contido na estrutura com base em sua posição.
     */
    private _Node<T> getNode(int index) {
        this.indexCheck(index);
        _Node<T> current = this.first;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current;
    }

    /**
     * Verifica se um índice (posição) passado como parâmetro é válido.
     *
     * @param index Índice que será verificado.
     */
    private void indexCheck(int index) {
        if (index < 0 || index >= this.size) {
            throw new IllegalArgumentException("Index " + index + " out of bounds for length " + this.size);
        }
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
            StringBuilder sb = new StringBuilder();
            sb.append(this.element);
            if (this.next != null) {
                sb.append(", ").append(this.next);
            }
            return sb.toString();
        }

    }

}