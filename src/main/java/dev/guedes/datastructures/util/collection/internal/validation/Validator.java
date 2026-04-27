package dev.guedes.datastructures.util.collection.internal.validation;

import dev.guedes.datastructures.util.Objects;

/**
 * Utility class for index validation in list implementations.
 *
 * @author João Guedes
 */
public final class Validator {
    private Validator() {
        throw new AssertionError(Validator.class.getSimpleName() +  " is a utility class and cannot be instantiated.");
    }

    /**
     * Validates that the given index is within the bounds of the list (0 inclusive to size exclusive).
     *
     * @param index the index to validate
     * @param size the current size of the list
     * @throws IndexOutOfBoundsException if the index is out of bounds
     */
    public static void validateIndex(int index, int size) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for size " + size + ".");
        }
    }

    /**
     * Validates that the given index is within the bounds for insertion (0 inclusive to size inclusive).
     *
     * @param index the index to validate for adding
     * @param size the current size of the list
     * @throws IndexOutOfBoundsException if the index is out of bounds
     */
    public static void validateIndexForAdd(int index, int size) throws IndexOutOfBoundsException {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for size " + size + ".");
        }
    }

    /**
     * Validates that two vertices are not null.
     *
     * @param <T> the type of the vertex
     * @param source the source vertex
     * @param destination the destination vertex
     * @throws NullPointerException if either vertex is null
     */
    public static <T> void validateVerticesNotNull(T source, T destination) {
        Objects.requireNonNull(source, "Source vertex cannot be null.");
        Objects.requireNonNull(destination, "Destination vertex cannot be null.");
    }

    /**
     * Validates that a vertex is not null.
     *
     * @param <T> the type of the vertex
     * @param vertex the vertex to validate
     * @throws NullPointerException if the vertex is null
     */
    public static <T> void validateVertexNotNull(T vertex) {
        Objects.requireNonNull(vertex, "Vertex cannot be null.");
    }

    /**
     * Validates that an edge weight is positive.
     *
     * @param weight the weight to validate
     * @throws IllegalArgumentException if weight is not positive
     */
    public static void validatePositiveWeight(int weight) {
        if (weight <= 0) throw new IllegalArgumentException("Weight must be positive.");
    }
}
