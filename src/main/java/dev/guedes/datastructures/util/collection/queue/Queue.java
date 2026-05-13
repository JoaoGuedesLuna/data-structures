package dev.guedes.datastructures.util.collection.queue;

import dev.guedes.datastructures.util.collection.Collection;

import java.util.NoSuchElementException;

/**
 * A generic interface representing a First-In-First-Out (FIFO) queue.
 * <p>
 * Elements are inserted at the end of the queue and removed from the front.
 * This interface defines core queue operations for adding, retrieving, and
 * removing elements, with behavior consistent with the Java Collections Framework.
 *
 * @param <E> the type of elements held in this queue
 *
 * @author João Guedes
 */
public interface Queue<E> extends Collection<E> {
    /**
     * Inserts the specified element into the queue.
     *
     * @param element the element to add
     * @return {@code true} if the element was successfully added
     */
    boolean offer(E element);

    /**
     * Retrieves and removes the head of the queue, or returns {@code null} if the queue is empty.
     *
     * @return the head of the queue, or {@code null} if empty
     */
    E poll();

    /**
     * Retrieves and removes the head of the queue.
     *
     * @return the head of the queue
     * @throws NoSuchElementException if the queue is empty
     */
    E remove() throws NoSuchElementException;

    /**
     * Retrieves, but does not remove, the head of the queue, or returns {@code null} if the queue is empty.
     *
     * @return the head of the queue, or {@code null} if empty
     */
    E peek();

    /**
     * Retrieves, but does not remove, the head of the queue.
     *
     * @return the head of the queue
     * @throws NoSuchElementException if the queue is empty
     */
    E element() throws NoSuchElementException;
}
