package dev.guedes.datastructures.util.collection.stack;

import dev.guedes.datastructures.util.collection.list.List;

import java.util.EmptyStackException;

/**
 * A generic Stack interface that extends a List.
 * <p>
 * Defines the standard stack operations: push, pop, peek, and search.
 * A stack is a Last-In-First-Out (LIFO) data structure.
 *
 * @param <E> the type of elements stored in the stack
 *
 * @author João Guedes
 */
public interface Stack<E> extends List<E> {
    /**
     * Pushes (adds) an element onto the top of the stack.
     *
     * @param element the element to be added to the stack
     * @return the same element that was pushed
     */
    E push(E element);

    /**
     * Removes and returns the element at the top of the stack.
     *
     * @return the element at the top of the stack
     * @throws EmptyStackException if the stack is empty
     */
    E pop() throws EmptyStackException;

    /**
     * Retrieves, but does not remove, the element at the top of the stack.
     *
     * @return the element at the top of the stack
     * @throws EmptyStackException if the stack is empty
     */
    E peek() throws EmptyStackException;

    /**
     * Searches for the specified element in the stack.
     * <p>
     * The method returns the 1-based position from the top of the stack
     * where the element is located. If the element is not found, -1 is returned.
     *
     * @param element the element to search for
     * @return the 1-based position from the top of the stack, or -1 if not found
     */
    int search(E element);
}
