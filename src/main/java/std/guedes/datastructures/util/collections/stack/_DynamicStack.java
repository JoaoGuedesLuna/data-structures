package main.java.std.guedes.datastructures.util.collections.stack;

import java.util.EmptyStackException;
import java.util.Objects;

/**
 * A Pilha Dinâmica é uma variante que utiliza da alocação dinâmica (nós) para reservar a memória que
 * será utilizada para armazenar dados. Nesse caso da implementação ela é disposta por nós (Nodes)
 * que se conectam a no máximo 1 outro nó e não possuem conhecimento se outro nó está ligado a eles.
 *
 * @param <T> Tipo de dados que a pilha armazenará.
 *
 * @author João Guedes.
 */
public class _DynamicStack<T> implements _Stack<T> {

    /**
     * Nó do topo estrutura.
     */
    private _Node<T> top;
    /**
     * Tamanho da pilha. Considera as posições ocupadas e não o tamanho literal da estrutura.
     */
    private int size;

    public _DynamicStack() {
        this.top = null;
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
        this.top = new _Node<>(element, this.top);
        this.size++;
        return true;
    }

    @Override
    public void add(int index, T element) {
        if (index < 0 || index > this.size) {
            throw new IllegalArgumentException ("Index " + index + " out of bounds for length " + this.size);
        }
        if (index == this.size) {
            this.top = new _Node<>(element, this.top);
        }
        else {
            _Node<T> current = this.getNode(index);
            current.setNext(new _Node<>(element, current.getNext()));
        }
        this.size++;
    }

    @Override
    public T push(T element) {
        this.add(element);
        return element;
    }

    @Override
    public boolean remove(T element) {
        if (this.isEmpty()) {
            return false;
        }
        _Node<T> current = this.top;
        _Node<T> next;
        if (element == null) {
            if (current.getElement() == null) {
                this.top = current.getNext();
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
                this.top = current.getNext();
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
        if (index == this.size-1) {
            _Node<T> oldTop = this.top;
            elementRemoved = oldTop.getElement();
            this.top = oldTop.getNext();
            oldTop.setElement(null);
            oldTop.setNext(null);
        }
        else {
            _Node<T> current = this.getNode(index+1);
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
    public T pop() {
        if (this.isEmpty()) {
            throw new EmptyStackException();
        }
        return this.remove(this.size-1);
    }

    @Override
    public T peek () {
        if (this.isEmpty()) {
            throw new EmptyStackException();
        }
        return this.top.getElement();
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
        int index = -1;
        _Node<T> current = this.top;
        if (element == null) {
            for (int i = 0; i < this.size; i++) {
                if (current.getElement() == null) {
                    index = this.size-i-1;
                }
                current = current.getNext();
            }
        }
        else {
            for (int i = 0; i < this.size; i++) {
                if (element.equals(current.getElement())) {
                    index = this.size-i-1;
                }
                current = current.getNext();
            }
        }
        return index;
    }

    @Override
    public int lastIndexOf(T element) {
        _Node<T> current = this.top;
        if (element == null) {
            for (int i = 0; i < this.size; i++) {
                if (current.getElement() == null) {
                    return this.size-i-1;
                }
                current = current.getNext();
            }
        }
        else {
            for (int i = 0; i < this.size; i++) {
                if (element.equals(current.getElement())) {
                    return this.size-i-1;
                }
                current = current.getNext();
            }
        }
        return -1;
    }

    @Override
    public boolean contains(T element) {
        return this.indexOf(element) >= 0;
    }

    @Override
    public int search(T element) {
        int index = this.lastIndexOf(element);
        if (index >= 0) {
            return this.size - index;
        }
        return -1;
    }

    @Override
    public void clear() {
        _Node<T> next;
        for (_Node<T> current = this.top; current != null;) {
            next = current.getNext();
            current.setElement(null);
            current.setNext(null);
            current = next;
        }
        this.top = null;
        this.size = 0;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[this.size];
        this.copyToArray(array, this.size-1, this.top);
        return array;
    }

    /**
     * Método auxiliar ao método toArray, adiciona cada elemento da estrutura no array específico
     * na possição de índice correspondente.
     *
     * @param array Array que será preenchido.
     *
     * @param index Índice que o elemento será inserido.
     *
     * @param current Nó atual.
     */
    private void copyToArray(Object[] array, int index, _Node<T> current) {
        if (current == null) {
            return;
        }
        this.copyToArray(array, index-1, current.getNext());
        array[index] = current.getElement();
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
        _Node<T> current = this.top;
        for (int i = 0; i < this.size-index-1; i++) {
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

    /**
     * Método auxiliar ao toString, responsável por fazer o append de cada elemento da pilha num
     * StringBuilder passado como parâmetro.
     *
     * @param sb StringBuilder que armazenar os elementos.
     *
     * @param current Nó atual.
     */
    private void stringBuilderAppend(StringBuilder sb, _Node<T> current) {
        if (current == null) {
            return;
        }
        this.stringBuilderAppend(sb, current.getNext());
        sb.append(current.getElement());
        if (current != this.top) {
            sb.append(", ");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        if (!this.isEmpty()) {
            this.stringBuilderAppend(sb, this.top);
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