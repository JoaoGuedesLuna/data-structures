package dev.guedes.datastructures.util.collection.stack;

import dev.guedes.datastructures.util.collection.list.DoublyLinkedList;

import java.util.EmptyStackException;

/**
 * A stack implementation backed by a doubly linked list.
 * <p>
 * This class provides typical stack (LIFO) operations by
 * extending a linked list where elements are added and
 * removed from the end of the list.
 *
 * @param <E> the type of elements stored in the stack
 *
 * @author João Guedes
 */
public class LinkedStack<E> extends DoublyLinkedList<E> implements Stack<E> {
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
    public E peek () {
        if (isEmpty()) throw new EmptyStackException();
        return get(size() - 1);
    }

    @Override
    public int search(E element) {
        int index = lastIndexOf(element);
        return (index >= 0) ? size() - index : -1;
    }
}
