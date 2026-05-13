package dev.guedes.datastructures.util.collection.list;

import dev.guedes.datastructures.util.Arrays;
import dev.guedes.datastructures.util.Objects;

import java.util.function.Consumer;

import static dev.guedes.datastructures.util.collection.internal.validation.Validator.validateIndex;
import static dev.guedes.datastructures.util.collection.internal.validation.Validator.validateIndexForAdd;

/**
 * A custom implementation of a dynamically resizing array-based list.
 *
 * <p>Provides typical list operations such as add, remove, get, set,
 * and supports automatic resizing (growth and shrinkage) based on usage.
 *
 * @param <E> the type of elements in this list
 *
 * @author João Guedes
 */
public class ArrayList<E> implements List<E> {
    private static final int DEFAULT_INITIAL_CAPACITY = 10;
    private static final double SHRINK_THRESHOLD_FACTOR = 1.25;

    private final int initialCapacity;
    private E[] elements;
    private int size;

    @SuppressWarnings("unchecked")
    public ArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException(
                    "Initial capacity cannot be negative. Provided value: " + initialCapacity + "."
            );
        }
        this.initialCapacity = initialCapacity;
        this.elements = (E[]) new Object[initialCapacity];
        this.size = 0;
    }

    public ArrayList() { this(DEFAULT_INITIAL_CAPACITY); }

    @Override
    public int size() { return size; }

    @Override
    public boolean isEmpty() { return size == 0; }

    @Override
    public boolean add(E element) {
        ensureCapacity();
        elements[size++] = element;
        return true;
    }

    @Override
    public void add(int index, E element) {
        validateIndexForAdd(index, size);
        ensureCapacity();
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = element;
        size++;
    }

    @Override
    public boolean remove(E element) {
        int index = indexOf(element);
        if (index < 0) return false;

        remove(index);
        return true;
    }

    @Override
    public E remove(int index) {
        E removedElement = get(index);

        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }

        elements[--size] = null;
        shrinkCapacity();

        return removedElement ;
    }

    @Override
    public E get(int index) {
        validateIndex(index, size);
        return elements[index];
    }

    @Override
    public E set(int index, E element) {
        E oldElement = get(index);
        elements[index] = element;
        return oldElement;
    }

    @Override
    public int indexOf(E element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(element, elements[i])) return i;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(E element) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(element, elements[i])) return i;
        }
        return -1;
    }

    @Override
    public boolean contains(E element) { return indexOf(element) >= 0; }

    @Override
    public void forEach(Consumer<E> action) {
        for (int i = 0; i < size; i++) {
            action.accept(elements[i]);
        }
    }

    @Override
    public Object[] toArray() { return Arrays.copyOfRange(elements, 0, size); }

    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        elements = (E[]) new Object[initialCapacity];
        size = 0;
    }

    @Override
    public String toString() {
        if (isEmpty()) return "[]";

        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size - 1; i++) {
            sb.append(elements[i]).append(", ");
        }
        sb.append(elements[size - 1]).append("]");

        return sb.toString();
    }

    /**
     * Ensures the internal array has enough capacity to accommodate new elements.
     * Doubles the current capacity if full.
     */
    private void ensureCapacity() {
        if (size != elements.length) return;

        int newCapacity = (elements.length > 0) ? elements.length * 2 : DEFAULT_INITIAL_CAPACITY;
        resizeArray(newCapacity);
    }

    /**
     * Shrinks the internal array size if it's significantly larger than the current number of elements.
     * Reduces the size by half if the current capacity is more than 25% greater than the number of elements,
     * while ensuring it does not shrink below the initial capacity.
     */
    private void shrinkCapacity() {
        int newCapacity = (int) Math.ceil(size * SHRINK_THRESHOLD_FACTOR);
        if (elements.length > initialCapacity && elements.length > newCapacity) {
            resizeArray(newCapacity);
        }
    }

    /**
     * Resizes the internal array to the given capacity.
     *
     * @param newCapacity the new capacity of the internal array
     */
    private void resizeArray(int newCapacity){ elements = Arrays.copyOf(elements, newCapacity); }
}
