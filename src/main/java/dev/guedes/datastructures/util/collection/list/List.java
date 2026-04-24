package dev.guedes.datastructures.util.collection.list;

import dev.guedes.datastructures.util.collection.Collection;

/**
 * A generic interface that represents a linear collection of elements,
 * where elements can be inserted or accessed by their position (index) in the list.
 *
 * <p>
 * This interface extends the basic {@link Collection} interface and adds
 * methods for positional access, modification, and search within the list.
 * </p>
 *
 * @param <E> the type of elements in this list
 *
 * @author João Guedes
 */
public interface List<E> extends Collection<E> {
    /**
     * Inserts the specified element at the specified position in the list.
     * Shifts the element currently at that position (if any) and any subsequent
     * elements to the right (adds one to their indices).
     *
     * @param index the index at which the element is to be inserted
     * @param element the element to insert
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index > size())
     */
    void add(int index, E element);

    /**
     * Removes the element at the specified position in the list.
     * Shifts any subsequent elements to the left (subtracts one from their indices).
     *
     * @param index the index of the element to be removed
     * @return the element that was removed from the list
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     */
    E remove(int index);

    /**
     * Returns the element at the specified position in the list.
     *
     * @param index the index of the element to return
     * @return the element at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     */
    E get(int index);

    /**
     * Replaces the element at the specified position in the list with the specified element.
     *
     * @param index the index of the element to replace
     * @param element the element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     */
    E set(int index, E element);

    /**
     * Returns the index of the first occurrence of the specified element in the list,
     * or -1 if this list does not contain the element.
     *
     * @param element the element to search for
     * @return the index of the first occurrence of the specified element, or -1 if not found
     */
    int indexOf(E element);

    /**
     * Returns the index of the last occurrence of the specified element in the list,
     * or -1 if this list does not contain the element.
     *
     * @param element the element to search for
     * @return the index of the last occurrence of the specified element, or -1 if not found
     */
    int lastIndexOf(E element);
}
