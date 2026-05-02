package dev.guedes.datastructures.util.collection.internal.exception;

/**
 * Unchecked exception thrown when an operation is attempted on an empty tree.
 * <p>
 * This runtime exception indicates that the tree contains no elements.
 *
 * @author João Guedes
 */
public class EmptyTreeException extends RuntimeException {
    public EmptyTreeException() { super("Operation not allowed on an empty tree."); }
}
