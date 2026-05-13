package dev.guedes.datastructures.util.collection.queue;

import dev.guedes.datastructures.util.Objects;
import dev.guedes.datastructures.util.collection.internal.nodes.SinglyLinkedNode;

/**
 * A priority queue implementation based on a singly linked list.
 * <p>
 * Elements are ordered according to their natural ordering (defined by {@link Comparable}).
 * Lower values have higher priority and are placed closer to the front of the queue.
 * This class overrides the {@code add} method to ensure elements are always inserted in sorted order.
 *
 * @param <E> the type of elements in the queue, which must be {@link Comparable}
 *
 * @author João Guedes
 */
public class PriorityQueue<E extends Comparable<E>> extends LinkedQueue<E> {
    /**
     * Inserts the specified element into the queue, maintaining ascending order.
     * <p>
     * If the queue is empty or the element has higher priority than the current head,
     * it is inserted at the front. Otherwise, the element is inserted at the first
     * position where its priority is less than that of the next node.
     *
     * @param element the element to be inserted
     * @return {@code true} after the element is successfully inserted
     */
    @Override
    public boolean add(E element) {
        if (isEmpty() || Objects.compare(element, first.getElement()) < 0) {
            add(0, element);
            return true;
        }

        SinglyLinkedNode<E> current = first;
        SinglyLinkedNode<E> next;

        while ((next = current.getNext()) != null) {
            if (Objects.compare(element, next.getElement()) < 0) {
                break;
            }
            current = next;
        }

        current.setNext(new SinglyLinkedNode<>(element, next));

        if (next == null) last = current.getNext();

        size++;
        return true;
    }
}
