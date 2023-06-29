package main.java.std.guedes.datastructures.util.collections.list;

/**
 * Essa classe é uma estrutura de dados ArrayList. Uma coleção indexada de objetos às vezes chamada
 * 'sequência'. Como nos vetores, índices da ArrayList são baseados em zero, isto é, o índice do
 * primeiro elemento é zero. ArrayList fornece métodos para manipular elementos baseados na sua
 * posição (ou índice) numérica na lista, remover determinado elemento, procurar as ocorrências de
 * um dado elemento e percorrer sequencialmente (ListIterator) todos os elementos da lista.
 *
 * @param <T> Tipo de dado que a lista armazenará.
 *
 * @author João Guedes.
 */
public class _ArrayList<T> implements _List<T> {

    /**
     * Vetor interno da lista.
     */
    private T[] array;
    /**
     * Tamanho da lista. Considera as posições ocupadas e não o tamanho literal da estrutura.
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

    public _ArrayList(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + size);
        }
        this.INITIAL_SIZE = size;
        this.array = (T[]) new Object [INITIAL_SIZE];
        this.size = 0;
    }

    public _ArrayList() {
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