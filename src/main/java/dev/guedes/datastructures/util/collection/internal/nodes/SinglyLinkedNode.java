package dev.guedes.datastructures.util.collection.internal.nodes;

import javax.annotation.processing.Generated;
import java.util.Objects;

/**
 * Represents a basic node structure with a single link/reference to the next node.
 * This can be used in singly-linked lists, queues, stacks, and other linear structures.
 *
 * @param <E> The type of element stored in the node.
 *
 * @author João Guedes
 */
public class SinglyLinkedNode<E> {
    private E element;
    private SinglyLinkedNode<E> next;

    public SinglyLinkedNode(E element, SinglyLinkedNode<E> next) {
        this.element = element;
        this.next = next;
    }

    public SinglyLinkedNode(E element) { this(element, null); }

    public E getElement() { return element; }
    public void setElement(E element) { this.element = element; }

    public SinglyLinkedNode<E> getNext() { return next; }
    public void setNext(SinglyLinkedNode<E> next) { this.next = next; }

    /**
     * Clears all references in this node for garbage collection.
     */
    public void clear() {
        element = null;
        next = null;
    }

    @Override
    @Generated({})
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || this.getClass() != o.getClass()) return false;

        SinglyLinkedNode<?> node = (SinglyLinkedNode<?>) o;
        return Objects.equals(this.element, node.element)
                && Objects.equals(this.next, node.next);
    }

    @Override
    @Generated({})
    public int hashCode() { return Objects.hash(element, next); }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(element);
        if (next != null) sb.append(", ").append(next);

        return sb.toString();
    }
}
