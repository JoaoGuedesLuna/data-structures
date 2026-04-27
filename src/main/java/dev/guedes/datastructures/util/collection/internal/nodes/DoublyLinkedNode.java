package dev.guedes.datastructures.util.collection.internal.nodes;

import java.util.Objects;

/**
 * Represents a node with references to both previous and next nodes.
 * Suitable for doubly-linked lists and other structures requiring
 * bidirectional traversal.
 *
 * @param <E> The type of element stored in the node.
 *
 * @author João Guedes
 */
public class DoublyLinkedNode<E> {
    private E element;
    private DoublyLinkedNode<E> previous;
    private DoublyLinkedNode<E> next;

    public DoublyLinkedNode(DoublyLinkedNode<E> previous, E element, DoublyLinkedNode<E> next) {
        this.previous = previous;
        this.element = element;
        this.next = next;
    }

    public E getElement() { return element; }
    public void setElement(E element) { this.element = element; }

    public DoublyLinkedNode<E> getPrevious() { return previous; }
    public void setPrevious(DoublyLinkedNode<E> previous) { this.previous = previous; }

    public DoublyLinkedNode<E> getNext() { return next; }
    public void setNext(DoublyLinkedNode<E> next) { this.next = next; }

    /**
     * Clears all references in this node for garbage collection.
     */
    public void clear() {
        element = null;
        previous = null;
        next = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || this.getClass() != o.getClass()) return false;

        DoublyLinkedNode<?> node = (DoublyLinkedNode<?>) o;
        return Objects.equals(this.element, node.element)
                && Objects.equals(this.previous, node.previous)
                && Objects.equals(this.next, node.next);
    }

    @Override
    public int hashCode() { return Objects.hash(element, previous, next); }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(element);
        if (next != null) sb.append(", ").append(next);

        return sb.toString();
    }
}
