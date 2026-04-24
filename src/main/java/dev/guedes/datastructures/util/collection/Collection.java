package dev.guedes.datastructures.util.collection;

import java.util.function.Consumer;

/**
 * A generic Collection interface defining the basic operations
 * for a collection of elements of type {@code E}.
 *<p>
 * This interface abstracts common behaviors such as adding,
 * removing, querying, and retrieving elements, similar to
 * standard Java Collection interfaces.
 * </p>
 *
 * @param <E> the type of elements held in this collection
 *
 * @author João Guedes
 */
public interface Collection<E> {
    /**
     * Returns the number of elements in this collection.
     *
     * @return the size (number of elements) of the collection
     */
    int size();

    /**
     * Checks whether this collection contains no elements.
     *
     * @return {@code true} if the collection is empty, {@code false} otherwise
     */
    boolean isEmpty();

    /**
     * Adds the specified element to this collection.
     *
     * @param element the element to be added
     * @return {@code true} if the collection changed as a result of the call, {@code false} if the element was not added
     */
    boolean add(E element);

    /**
     * Removes a single instance of the specified element from this collection, if present.
     *
     * @param element the element to be removed, if found
     * @return {@code true} if the collection contained the specified element, and it was removed, {@code false} otherwise
     */
    boolean remove(E element);

    /**
     * Checks if this collection contains the specified element.
     *
     * @param element the element whose presence is to be tested
     * @return {@code true} if this collection contains the element, {@code false} otherwise
     */
    boolean contains(E element);

    /**
     * Performs the given action for each element of the collection until all elements
     * have been processed or the action throws an exception.
     * <p>
     * The order in which the elements are processed is not guaranteed and may vary
     * depending on the underlying implementation of the collection.
     * </p>
     *
     * @param action the action to be performed for each element
     * @throws NullPointerException if the specified action is {@code null}
     */
    void forEach(Consumer<E> action);

    /**
     * Returns an array containing all the elements in this collection.
     * The returned array's runtime type is {@code Object[]} regardless of {@code E}.
     *
     * @return an array containing all elements of the collection
     */
    Object[] toArray();

    /**
     * Removes all elements from this collection.
     * After this call, the collection will be empty.
     */
    void clear();
}
