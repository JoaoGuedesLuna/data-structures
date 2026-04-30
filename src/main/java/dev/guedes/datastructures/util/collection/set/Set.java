package dev.guedes.datastructures.util.collection.set;

import dev.guedes.datastructures.util.collection.Collection;

/**
 * A collection that contains no duplicate elements.
 *
 * <p>More formally, sets contain at most one occurrence of each element. They model
 * the mathematical concept of a set and are typically used when uniqueness is a requirement.
 *
 * <p>This interface extends {@link Collection} but does not declare new methods. Implementations
 * must override modification methods (like {@code add}, {@code set}, etc.) to enforce uniqueness.
 *
 * @param <E> the type of elements maintained by this set
 *
 * @see Collection
 *
 * @author João Guedes
 */
public interface Set<E> extends Collection<E> {}
