package dev.guedes.datastructures.util.collection.stack;

import dev.guedes.datastructures.util.collection.list.ArrayList;

import java.util.EmptyStackException;

/**
 * A stack implementation backed by a dynamic array (ArrayList).
 * <p>
 * This class provides LIFO (last-in, first-out) stack operations
 * by extending a resizable array-based list. Elements are added and
 * removed from the end of the list to simulate a stack.
 *
 * @param <E> the type of elements in the stack
 *
 * @author João Guedes
 */
public class ArrayStack<E> extends ArrayList<E> implements Stack<E> {
    public ArrayStack(int initialCapacity) { super(initialCapacity); }

    public ArrayStack() { super(); }

    @Override
    public E push(E element) {
        add(element);
        return element;
    }

    @Override
    public E pop() {
        if (isEmpty()) throw new EmptyStackException();
        return remove(size() - 1);
    }

    @Override
    public E peek() {
        if (isEmpty()) throw new EmptyStackException();
        return get(size() - 1);
    }

    @Override
    public int search(E element) {
        int index = lastIndexOf(element);
        return (index >= 0) ? size() - index : -1;
    }
}
