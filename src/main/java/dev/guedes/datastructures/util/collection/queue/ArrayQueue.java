package dev.guedes.datastructures.util.collection.queue;

import dev.guedes.datastructures.util.collection.list.ArrayList;

import java.util.NoSuchElementException;

/**
 * A queue implementation backed by a dynamic array (ArrayList).
 * <p>
 * This class provides FIFO (First-In-First-Out) queue behavior,
 * where elements are added at the end and removed from the front.
 *
 * @param <E> the type of elements stored in the queue
 *
 * @author João Guedes
 */
public class ArrayQueue<E> extends ArrayList<E> implements Queue<E> {
    public ArrayQueue(int initialCapacity) { super(initialCapacity); }

    public ArrayQueue() { super(); }

    @Override
    public boolean offer(E element) { return add(element); }

    @Override
    public E poll() { return !isEmpty() ? remove(0) : null; }

    @Override
    public E remove() {
        if (isEmpty()) throw new NoSuchElementException("Queue is empty. Cannot remove element.");
        return remove(0);
    }

    @Override
    public E peek() { return !isEmpty() ? get(0) : null; }

    @Override
    public E element() {
        if (isEmpty()) throw new NoSuchElementException("Queue is empty. Cannot retrieve element.");
        return get(0);
    }
}
