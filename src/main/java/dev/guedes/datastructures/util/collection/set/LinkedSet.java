package dev.guedes.datastructures.util.collection.set;

import dev.guedes.datastructures.util.collection.list.SinglyLinkedList;

/**
 * A set implementation backed by a singly linked list, ensuring element uniqueness.
 *
 * <p>Overrides list methods to ensure no duplicate elements are stored.
 *
 * @param <E> the type of elements maintained by this set
 *
 * @author João Guedes
 */
public class LinkedSet<E> extends SinglyLinkedList<E> implements Set<E> {
    /**
     * Adds the specified element if it is not already present in the set.
     *
     * @param element the element to be added
     * @return {@code true} if the element was successfully added, {@code false} if it was a duplicate
     */
    @Override
    public boolean add(E element) { return !contains(element) && super.add(element); }

    /**
     * Inserts the specified element at the specified position if it is not already present in the set.
     *
     * @param index the position at which to insert the element
     * @param element the element to insert
     */
    @Override
    public void add(int index, E element) {
        if (!contains(element)) super.add(index, element);
    }

    /**
     * Replaces the element at the specified index if the new element is not already present.
     *
     * @param index the index of the element to replace
     * @param element the new element
     * @return the previous element if replacement occurred, or the same element if not
     */
    @Override
    public E set(int index, E element) {
        if (!contains(element)) {
            return super.set(index, element);
        }
        return get(index);
    }
}
