package main.java.std.guedes.datastructures.util.collections.stack;

import java.util.EmptyStackException;

/**
 * A Pilha Estática é uma variante que utiliza da alocação estática (vetor) para reservar a memória
 * que será utilizada para armazenar dados, ou seja, não é necessário o uso de funções para reservar
 * memória durante a execução. Toda a memória utilizada pela estrutura será reservada em tempo de
 * compilação.
 *
 * @param <T> Tipo de dados que a pilha armazenará.
 *
 * @author João Guedes.
 */
public class _StaticStack<T> implements _Stack<T>{

    /**
     * Vetor interno da pilha.
     */
    private T [] array;
    /**
     * Tamanho da pilha. Considera as posições ocupadas e não o tamanho literal da estrutura.
     */
    private int size;
    /**
     * Valor padrão para o tamanho do vetor interno.
     */
    private final int DEFAULT_SIZE = 10;
    /**
     * Valor inicial do tamanho do vetor interno.
     */
    private final int INITIAL_SIZE;

    public _StaticStack(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + size);
        }
        this.INITIAL_SIZE = size;
        this.array = (T[]) new Object[this.INITIAL_SIZE];
        this.size = 0;
    }

    public _StaticStack() {
        this.INITIAL_SIZE = this.DEFAULT_SIZE;
        this.array = (T[]) new Object[this.INITIAL_SIZE];
        this.size = 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    /**
     * Caso a quantidade de elementos contidos na estrutura seja igual o tamanho do array interno,
     * este método fará com que o array interno "dobre" sua capacidade de armazenamento.
     */
    private void expandSize(){
        if (this.size != this.array.length) {
            return;
        }
        int length = this.array.length > 0 ? this.array.length*2 : this.DEFAULT_SIZE;
        T[] newArray = (T[]) new Object[length];
        for (int i = 0; i < this.array.length; i++) {
            newArray[i] = this.array[i];
            this.array[i] = null;
        }
        this.array = newArray;
    }

    /**
     * Caso o tamanho do array interno seja maior que o valor inicial, e o número de posições livres nele
     * seja maior que 125% das posições ocupadas o array interno terá sua capacidade de armazenamento
     * "reduzida" pela metade.
     */
    private void decreaseSize() {
        if (this.array.length <= this.INITIAL_SIZE){
            return;
        }
        int freeSize = this.array.length - this.size;
        if (freeSize > (int) Math.ceil(this.size*1.25)) {
            T[] newArray = (T[]) new Object[this.array.length/2];
            for (int i = 0; i < newArray.length; i++) {
                newArray[i] = this.array[i];
                this.array[i] = null;
            }
            this.array = newArray;
        }
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean add(T element) {
        this.expandSize();
        this.array[this.size++] = element;
        return true;
    }

    @Override
    public void add(int index, T element) {
        if (index < 0 || index > this.size) {
            throw new IllegalArgumentException ("Index " + index + " out of bounds for length " + this.size);
        }
        this.expandSize();
        for (int i = this.size; i > index; i--) {
            this.array[i] = this.array[i-1];
        }
        this.array[index] = element;
        this.size++;
    }

    @Override
    public T push(T element) {
        this.add(element);
        return element;
    }

    @Override
    public boolean remove(T element) {
        int index = this.indexOf(element);
        if (index < 0) {
            return false;
        }
        this.remove(index);
        return true;
    }

    @Override
    public T remove(int index) {
        T elementRemoved = this.get(index);
        for (int i = index; i < this.size-1; i++) {
            this.array[i] = this.array[i+1];
        }
        this.size--;
        this.array[this.size] = null;
        this.decreaseSize();
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
    public T peek() {
        if (this.isEmpty()) {
            throw new EmptyStackException();
        }
        return this.array[this.size-1];
    }

    @Override
    public T get(int index) {
        this.indexCheck(index);
        return this.array[index];
    }

    @Override
    public T set(int index, T element) {
        T oldElement = this.get(index);
        this.array[index] = element;
        return oldElement;
    }

    @Override
    public int indexOf(T element) {
        if (element == null) {
            for (int i = 0; i < this.size; i++) {
                if (this.array[i] == null) {
                    return i;
                }
            }
        }
        else {
            for (int i = 0; i < this.size; i++) {
                if (element.equals(this.array[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T element) {
        if (element == null) {
            for (int i = this.size-1; i >= 0; i--) {
                if (this.array[i] == null) {
                    return i;
                }
            }
        }
        else {
            for (int i = this.size-1; i >= 0; i--) {
                if (element.equals(this.array[i])) {
                    return i;
                }
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
        for (int i = 0; i < this.size; i++) {
            this.array[i] = null;
        }
        this.array = (T[]) new Object[this.INITIAL_SIZE];
        this.size = 0;
    }

    @Override
    public Object[] toArray() {
        Object[] arrayCopy = new Object[this.size];
        for (int i = 0; i < arrayCopy.length; i++) {
            arrayCopy[i] = this.array[i];
        }
        return arrayCopy;
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
        for(int i = 0; i < this.size-1; i++) {
            sb.append(this.array[i]).append(", ");
        }
        if (!this.isEmpty()) {
            sb.append(this.array[this.size-1]);
        }
        sb.append("]");
        return sb.toString();
    }

}