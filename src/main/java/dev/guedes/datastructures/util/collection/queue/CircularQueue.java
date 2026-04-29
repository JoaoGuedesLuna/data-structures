package dev.guedes.datastructures.util.collection.queue;

import dev.guedes.datastructures.util.Objects;

import java.util.NoSuchElementException;
import java.util.function.Consumer;

/**
 * A circular queue implementation that dynamically resizes its internal storage.
 * Supports FIFO (First-In-First-Out) operations with O(1) average time complexity.
 *
 * @param <E> the type of elements stored in the queue
 *
 * @author João Guedes
 */
public class CircularQueue<E> implements Queue<E> {
    private static final int DEFAULT_INITIAL_CAPACITY = 10;
    private static final double SHRINK_THRESHOLD_FACTOR = 1.25;

    private final int initialCapacity;
    private E[] elements;
    private int headIndex;
    private int tailIndex;

    @SuppressWarnings("unchecked")
    public CircularQueue(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Initial capacity cannot be negative. Provided value: " + initialCapacity + "." );
        }

        this.initialCapacity = initialCapacity;
        this.elements = (E[]) new Object[initialCapacity];
        this.headIndex = 0;
        this.tailIndex = 0;
    }

    public CircularQueue() { this(DEFAULT_INITIAL_CAPACITY); }

    @Override
    public boolean offer(E element) { return add(element); }

    @Override
    public E poll() { return !isEmpty() ? removeFirst() : null; }

    @Override
    public E remove() {
        if (isEmpty()) throw new NoSuchElementException("Queue is empty. Cannot remove element.");
        return removeFirst();
    }

    @Override
    public E peek() { return !isEmpty() ? elements[getCircularIndex(headIndex)] : null; }

    @Override
    public E element() {
        if (isEmpty()) throw new NoSuchElementException("Queue is empty. Cannot retrieve element.");
        return elements[getCircularIndex(headIndex)];
    }

    @Override
    public int size() { return tailIndex - headIndex; }

    @Override
    public boolean isEmpty() { return size() == 0; }

    @Override
    public boolean add(E element) {
        ensureCapacity();
        elements[getCircularIndex(tailIndex++)] = element;
        return true;
    }

    @Override
    public boolean remove(E element) {
        for (int i = headIndex; i < tailIndex; i++) {
            if (Objects.equals(element, elements[getCircularIndex(i)])) {
                shiftElementsLeft(i);
                shrinkCapacity();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(E element) {
        for (int i = headIndex; i < tailIndex; i++) {
            if (Objects.equals(element, elements[getCircularIndex(i)])) return true;
        }
        return false;
    }

    @Override
    public void clear() {
        for (int i = headIndex; i < tailIndex; i++) {
            elements[getCircularIndex(i)] = null;
        }
        elements = (E[]) new Object[initialCapacity];
        headIndex = tailIndex = 0;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size()];
        for (int i = 0, j = headIndex; i < array.length; i++, j++) {
            array[i] = elements[getCircularIndex(j)];
        }
        return array;
    }

    @Override
    public void forEach(Consumer<E> action) {
        for (int i = headIndex; i < tailIndex; i++) {
            action.accept(elements[getCircularIndex(i)]);
        }
    }

    @Override
    public String toString() {
        if (isEmpty()) return "[]";

        StringBuilder sb = new StringBuilder("[");
        for (int i = headIndex; i < tailIndex - 1; i++) {
            sb.append(elements[getCircularIndex(i)]).append(", ");
        }
        sb.append(elements[getCircularIndex(tailIndex - 1)]).append("]");

        return sb.toString();
    }

    /**
     * Computes the circular index within the bounds of the internal array.
     */
    private int getCircularIndex(int index) { return index % elements.length; }

    /**
     * Removes and returns the first element from the queue.
     *
     * @return the removed element from the front of the queue
     */
    private E removeFirst() {
        E removedElement = elements[getCircularIndex(headIndex)];

        elements[getCircularIndex(headIndex++)] = null;
        shrinkCapacity();

        return removedElement;
    }

    /**
     * Shifts elements left to fill the gap after removal.
     */
    private void shiftElementsLeft(int startIndex) {
        for (int i = startIndex; i < tailIndex - 1; i++) {
            elements[getCircularIndex(i)] = elements[getCircularIndex(i + 1)];
        }
        elements[getCircularIndex(--tailIndex)] = null;
    }

    /**
     * Ensures the internal array has enough capacity to accommodate new elements.
     * Doubles the current capacity if full.
     */
    private void ensureCapacity() {
        if (size() != elements.length) return;

        int newCapacity = (elements.length > 0) ? elements.length * 2 : DEFAULT_INITIAL_CAPACITY;
        resizeArray(newCapacity);
    }

    /**
     * Shrinks the internal array size if it's significantly larger than the current number of elements.
     * Reduces the size by half if the current capacity is more than 25% greater than the number of elements,
     * while ensuring it does not shrink below the initial capacity.
     */
    private void shrinkCapacity() {
        if (elements.length <= initialCapacity) return;

        int newCapacity = (int) Math.ceil(size() * SHRINK_THRESHOLD_FACTOR);
        if (elements.length <= newCapacity) return;

        resizeArray(newCapacity);
    }

    /**
     * Resizes the internal array to the given capacity.
     *
     * @param newCapacity the new capacity of the internal array
     */
    private void resizeArray(int newCapacity){
        E[] newArray = (E[]) new Object[newCapacity];
        int numElements = size();

        for (int i = 0, j = headIndex; i < numElements; i++, j++) {
            newArray[i] = elements[getCircularIndex(j)];
            elements[getCircularIndex(j)] = null;
        }

        elements = newArray;
        headIndex = 0;
        tailIndex = numElements;
    }
}
