package main.java.std.guedes.datastructures.util.collections.list;

import java.util.Objects;

/**
 * As listas duplamente encadeadas são estruturas de dados e sua alocação da memória é feita durante a
 * execução. A conexão entre os elementos é feita através de dois ponteiros (um que aponta para o
 * elemento anterior e o outro para o seguinte). O ponteiro anterior ao primeiro elemento deve
 * apontar para NULL (o início da lista), bem como o ponteiro seguinte ao último elemento.
 *
 * @param <T> Tipo de dado que a lista armazenará.
 *
 * @author João Guedes.
 */
public class _DoublyLinkedList<T> implements _List<T> {

    /**
     * Primeiro nó da estrutura.
     */
    private _Node<T> first;
    /**
     * Último nó da estrutura.
     */
    private _Node<T> last;
    /**
     * Tamanho da lista. Considera as posições ocupadas e não o tamanho literal da estrutura.
     */
    private int size;

    public _DoublyLinkedList() {
        this.first = null;
        this.last = null;
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
        _Node<T> oldLast = this.last;
        _Node<T> newNode = new _Node<>(this.last, element, null);
        this.last = newNode;
        if (oldLast == null) {
            this.first = newNode;
        }
        else {
            oldLast.setNext(newNode);
        }
        this.size++;
        return true;
    }

    @Override
    public void add(int index, T element) {
        if (index < 0 || index > this.size) {
            throw new IllegalArgumentException ("Index " + index + " out of bounds for length " + this.size);
        }
        if (index == size) {
            this.add(element);
            return;
        }
        _Node<T> next = this.getNode(index);
        _Node<T> previous = next.getPrevious();
        _Node<T> newNode = new _Node<>(previous, element, next);
        next.setPrevious(newNode);
        if (previous == null) {
            this.first = newNode;
        }
        else {
            previous.setNext(newNode);
        }
        this.size++;
    }

    @Override
    public boolean remove(T element) {
        if (element == null) {
            for (_Node<T> current = this.first; current != null;) {
                if (current.getElement() == null) {
                    this.unlink(current);
                    return true;
                }
                current = current.getNext();
            }
        }
        else {
            for (_Node<T> current = this.first; current != null;) {
                if (element.equals(current.getElement())) {
                    this.unlink(current);
                    return true;
                }
                current = current.getNext();
            }
        }
        return false;
    }

    @Override
    public T remove(int index) {
        this.indexCheck(index);
        return this.unlink(this.getNode(index));
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
        _Node<T> current = this.last;
        if (element == null) {
            for (int i = this.size-1; i > 0; i--) {
                if (current.getElement() == null) {
                    return i;
                }
                current = current.getPrevious();
            }
        }
        else {
            for (int i = this.size-1; i > 0; i--) {
                if (element.equals(current.getElement())) {
                    return i;
                }
                current = current.getPrevious();
            }
        }
        return -1;
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
            current.setPrevious(null);
            current.setElement(null);
            current.setNext(null);
            current = next;
        }
        this.first = this.last = null;
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
     * Faz o desligamento do primeiro nó da estrutura.
     *
     * @param first Primeiro nó da estrutura.
     *
     * @return Elemento do primeiro nó.
     */
    private T unlinkFirst(_Node<T> first) {
        T element = first.getElement();
        _Node<T> next = first.getNext();
        first.setElement(null);
        first.setNext(null);
        this.first = next;
        if (this.first == null) {
            this.last = null;
        }
        else {
            this.first.setPrevious(null);
        }
        this.size--;
        return element;
    }

    /**
     * Faz o desligamento do último nó da estrutura.
     *
     * @param last Último nó da estrutura.
     *
     * @return Elemento do último nó.
     */
    private T unlinkLast(_Node<T> last) {
        T element = last.getElement();
        _Node<T> previous = last.getPrevious();
        last.setElement(null);
        last.setPrevious(null);
        this.last = previous;
        if (this.last == null) {
            this.first = null;
        }
        else {
            this.last.setNext(null);
        }
        this.size--;
        return element;
    }

    /**
     * Faz o desligamento de um nó da estrutura.
     *
     * @param node Nó que será removido da estrutura.
     *
     * @return Elemento do nó removido.
     */
    private T unlink(_Node<T> node) {
        _Node<T> previous = node.getPrevious();
        T element = node.getElement();
        _Node<T> next = node.getNext();
        if (previous == null) {
            this.first = next;
        }
        else {
            previous.setNext(next);
            node.setPrevious(null);
        }
        if (next == null) {
            this.last = previous;
        }
        else {
            next.setPrevious(previous);
            node.setNext(null);
        }
        node.setElement(null);
        this.size--;
        return element;
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
        _Node<T> current;
        if (index <= (this.size-1)/2) {
            current = this.first;
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
        }
        else {
            current = this.last;
            for (int i = this.size-1; i > index; i--) {
                current = current.getPrevious();
            }
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
     * Essa classe representa um nó duplo.
     *
     * @param <T> Tipo de dado que estará contido dentro do nó.
     *
     * @author João Guedes.
     */
    private static class _Node<T> {

        /**
         * Nó anterior.
         */
        private _Node<T> previous;
        /**
         * Elemento contido dentro do nó.
         */
        private T element;
        /**
         * Próximo nó.
         */
        private _Node<T> next;

        public _Node(_Node<T> previous, T element, _Node<T> next) {
            this.previous = previous;
            this.element = element;
            this.next = next;
        }

        public _Node(T element, _Node<T> next) {
            this.previous = null;
            this.element = element;
            this.next = next;
        }

        public _Node(T element) {
            this.previous = null;
            this.element = element;
            this.next = null;
        }

        public _Node<T> getPrevious() {
            return this.previous;
        }

        public void setPrevious(_Node<T> previous) {
            this.previous = previous;
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
                    Objects.equals(this.previous, node.previous) &&
                    Objects.equals(this.next, node.next);
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.element, this.previous, this.next);
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