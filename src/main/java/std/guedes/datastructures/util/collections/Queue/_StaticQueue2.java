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
public class _StaticQueue2<T> implements _Queue<T>{

    /**
     * Vetor interno da fila.
     */
    private T[] array;
    /**
     * Índice do primeiro elemento.
     */
    private int first;
    /**
     * Índice onde o último elemento pode ser inserido.
     */
    private int last;
    /**
     * Valor padrão para o tamanho do vetor interno.
     */
    private final int DEFAULT_SIZE = 10;
    /**
     * Valor inicial do tamanho do vetor interno.
     */
    private final int INITIAL_SIZE;

    public _StaticQueue2(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + size);
        }
        this.INITIAL_SIZE = size;
        this.array = (T[]) new Object [INITIAL_SIZE];
        this.first = 0;
        this.last = 0;
    }

    public _StaticQueue2() {
        this.INITIAL_SIZE = this.DEFAULT_SIZE;
        this.array = (T[]) new Object [INITIAL_SIZE];
        this.first = 0;
        this.last = 0;
    }

    @Override
    public int size() {
        return this.last - this.first;
    }

    /**
     * Caso a quantidade de elementos contidos na estrutura seja igual o tamanho do array interno,
     * este método fará com que o array interno "dobre" sua capacidade de armazenamento.
     */
    private void expandSize() {
        if (this.size() != this.array.length) {
            return;
        }
        int oldLength = this.array.length;
        int currentLength = oldLength > 0 ? oldLength*2 : this.DEFAULT_SIZE;
        T[] newArray = (T[]) new Object[currentLength];
        for (int i = 0, j = this.first; i < oldLength; i++, j++) {
            newArray[i] = this.array[j % oldLength];
            this.array[j % oldLength] = null;
        }
        this.array = newArray;
        this.first = 0;
        this.last = oldLength;
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
        int freeSize = this.array.length - this.size();
        if (freeSize > (int) Math.ceil(this.size()*1.25)) {
            int oldLength = this.array.length;
            int currentLength = oldLength/2;
            T[] newArray = (T[]) new Object[currentLength];
            for (int i = 0, j = this.first; i < currentLength; i++, j++) {
                newArray[i] = this.array[j % oldLength];
                this.array[j % oldLength] = null;
            }
            this.array = newArray;
            this.last = this.size();
            this.first = 0;
        }
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public boolean add(T element) {
        this.expandSize();
        this.array[this.last % this.array.length] = element;
        this.last++;
        return true;
    }

    @Override
    public boolean remove(T element) {
        if (element == null) {
            for (int i = this.first; i < this.last; i++) {
                if (this.array[i % this.array.length] == null) {
                    for (int j = i; j < this.last-1; j++) {
                        this.array[j % this.array.length] = this.array[(j+1) % this.array.length];
                    }
                    this.last--;
                    this.array[this.last % this.array.length] = null;
                    this.decreaseSize();
                    return true;
                }
            }
        }
        else {
            for (int i = this.first; i < this.last; i++) {
                if (element.equals(this.array[i % this.array.length])) {
                    for (int j = i; j < this.last-1; j++) {
                        this.array[j % this.array.length] = this.array[(j+1) % this.array.length];
                    }
                    this.last--;
                    this.array[this.last % this.array.length] = null;
                    this.decreaseSize();
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public T remove() {
        if (this.isEmpty()) {
            return null;
        }
        T first = this.peek();
        this.array[this.first++] = null;
        this.decreaseSize();
        return first;
    }

    @Override
    public T peek() {
        return !this.isEmpty() ? this.array[this.first % this.array.length] : null;
    }

    @Override
    public boolean contains(T element) {
        if (element == null) {
            for (int i = this.first; i < this.last; i++) {
                if (this.array[i % this.array.length] == null) {
                    return true;
                }
            }
        }
        else {
            for (int i = this.first; i < this.last; i++) {
                if (element.equals(this.array[i % this.array.length])) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void clear() {
        for (int i = this.first; i < this.last; i++) {
            this.array[i % this.array.length] = null;
        }
        this.array = (T[]) new Object[this.INITIAL_SIZE];
        this.first = 0;
        this.last = 0;
    }

    @Override
    public Object[] toArray() {
        Object[] arrayCopy = new Object[this.size()];
        for (int i = 0, j = this.first; i < arrayCopy.length; i++, j++) {
            arrayCopy[i] = this.array[j % this.array.length];
        }
        return arrayCopy;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = this.first; i < this.last-1; i++) {
            sb.append(this.array[i % this.array.length]).append(", ");
        }
        if (!this.isEmpty()) {
            sb.append(this.array[this.last-1]);
        }
        sb.append("]");
        return sb.toString();
    }

}