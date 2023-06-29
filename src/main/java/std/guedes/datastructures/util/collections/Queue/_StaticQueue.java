package main.java.std.guedes.datastructures.util.collections.Queue;

/**
 * Uma fila é uma lista ordenada de elementos. Ela sempre funciona primeiro a entra primeiro a sair (FIFO).
 * Todos os elementos são inseridos na parte TRASEIRA e removidos da FRENTE da fila. Na implementação da
 * fila estática, uma array será usada para que todas as operações da fila sejam baseadas em índice, o
 * que a torna mais rápida para todas as operações, exceto a exclusão, porque a exclusão requer o
 * deslocamento de todos os elementos restantes para a frente em uma posição.
 *
 * @param <T> Tipo de dados que a fila armazenará.
 *
 * @author João Guedes.
 */
public class _StaticQueue<T> implements _Queue<T>{

    /**
     * Vetor interno da fila.
     */
    private T[] array;
    /**
     * Tamanho da fila. Considera as posições ocupadas e não o tamanho literal da estrutura.
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

    public _StaticQueue(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + size);
        }
        this.INITIAL_SIZE = size;
        this.array = (T[]) new Object [INITIAL_SIZE];
        this.size = 0;
    }

    public _StaticQueue() {
        this.INITIAL_SIZE = this.DEFAULT_SIZE;
        this.array = (T[]) new Object [INITIAL_SIZE];
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
        this.array[this.size] = element;
        this.size++;
        return true;
    }

    @Override
    public boolean remove(T element) {
        if (element == null) {
            for (int i = 0; i < this.size; i++) {
                if (this.array[i] == null) {
                    for (int j = i; j < this.size-1; j++) {
                        this.array[j] = this.array[j+1];
                    }
                    this.size--;
                    this.array[this.size] = null;
                    this.decreaseSize();
                    return true;
                }
            }
        }
        else {
            for (int i = 0; i < this.size; i++) {
                if (element.equals(this.array[i])) {
                    for (int j = i; j < this.size-1; j++) {
                        this.array[j] = this.array[j+1];
                    }
                    this.size--;
                    this.array[this.size] = null;
                    this.decreaseSize();
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public T remove() {
        T first = this.peek();
        this.remove(first);
        return first;
    }

    @Override
    public T peek() {
        return !this.isEmpty() ? this.array[0] : null;
    }

    @Override
    public boolean contains(T element) {
        if (element == null) {
            for (int i = 0; i < this.size; i++) {
                if (this.array[i] == null) {
                    return true;
                }
            }
        }
        else {
            for (int i = 0; i < this.size; i++) {
                if (element.equals(this.array[i])) {
                    return true;
                }
            }
        }
        return false;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < this.size-1; i++) {
            sb.append(this.array[i]).append(", ");
        }
        if (!this.isEmpty()) {
            sb.append(this.array[this.size-1]);
        }
        sb.append("]");
        return sb.toString();
    }

}