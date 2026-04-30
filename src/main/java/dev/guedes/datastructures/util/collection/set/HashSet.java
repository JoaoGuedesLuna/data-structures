package dev.guedes.datastructures.util.collection.set;

import dev.guedes.datastructures.util.Objects;
import dev.guedes.datastructures.util.collection.internal.nodes.SinglyLinkedNode;

import java.util.function.Consumer;

/**
 * A hash table implementation of the Set interface. This implementation uses separate chaining
 * with singly-linked nodes to handle hash collisions. The set does not permit duplicate elements.
 *
 * @param <E> the type of elements maintained by this set
 *
 * @author João Guedes
 */
public class HashSet<E> implements Set<E> {
    private static final int DEFAULT_CAPACITY = 10;

    private final SinglyLinkedNode<E>[] buckets;
    private final int capacity;
    private int size;

    @SuppressWarnings("unchecked")
    public HashSet(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Initial capacity must be positive. Provided value: " + capacity + ".");
        }

        this.capacity = capacity;
        this.buckets = (SinglyLinkedNode<E>[]) new SinglyLinkedNode[capacity];
        this.size = 0;
    }

    public HashSet() { this(DEFAULT_CAPACITY); }

    @Override
    public int size() { return size; }

    @Override
    public boolean isEmpty() { return size == 0; }

    /**
     * Adds the specified element if it is not already present in the set.
     *
     * @param element the element to be added
     * @return {@code true} if the element was successfully added, {@code false} if it was a duplicate
     */
    @Override
    public boolean add(E element) {
        int bucketIndex = calculateBucketIndex(element);

        if (containsInChain(buckets[bucketIndex], element)) return false;

        buckets[bucketIndex] = new SinglyLinkedNode<>(element, buckets[bucketIndex]);
        size++;

        return true;
    }

    @Override
    public boolean remove(E element) {
        int bucketIndex = calculateBucketIndex(element);

        SinglyLinkedNode<E> current  = buckets[bucketIndex];
        SinglyLinkedNode<E> previous = null;

        while (current != null) {
            if (Objects.equals(element, current.getElement())) {
                removeNodeFromChain(bucketIndex, previous, current);
                return true;
            }
            previous = current;
            current = current.getNext();
        }

        return false;
    }

    @Override
    public boolean contains(E element) {
        return containsInChain(buckets[calculateBucketIndex(element)], element);
    }

    @Override
    public void forEach(Consumer<E> action) {
        for (SinglyLinkedNode<E> bucket : buckets) {
            SinglyLinkedNode<E> current = bucket;
            while (current != null) {
                action.accept(current.getElement());
                current = current.getNext();
            }
        }
    }

    @Override
    public Object[] toArray() {
        SinglyLinkedNode<E> current;
        Object[] array = new Object[size];
        int index = 0;

        for (SinglyLinkedNode<E> head : buckets) {
            current = head;
            while (current != null) {
                array[index++] = current.getElement();
                current = current.getNext();
            }
        }

        return array;
    }

    @Override
    public void clear() {
        for (int i = 0; i < buckets.length; i++) {
            clearChain(buckets[i]);
            buckets[i] = null;
        }
        size = 0;
    }

    @Override
    public String toString() {
        if (isEmpty()) return "[]";

        StringBuilder sb = new StringBuilder();
        for (SinglyLinkedNode<E> head : buckets) {
            if (head != null) sb.append(head).append(", ");
        }
        sb.setLength(sb.length() - 2);

        return "[" + sb + "]";
    }

    /**
     * Calculates the bucket index for a given element using its hash code.
     *
     * @param element the element to calculate bucket index for
     * @return the bucket index for the element
     */
    private int calculateBucketIndex(E element) {
        return element != null ? Math.abs(element.hashCode() % capacity) : 0;
    }

    /**
     * Checks if an element exists in a chain of nodes.
     *
     * @param headNode the first node in the chain
     * @param element the element to search for
     * @return true if the element is found in the chain, false otherwise
     */
    private boolean containsInChain(SinglyLinkedNode<E> headNode, E element) {
        SinglyLinkedNode<E> current = headNode;
        while (current != null) {
            if (Objects.equals(element, current.getElement())) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    /**
     * Removes a node from a chain in a bucket.
     *
     * @param bucketIndex the index of the bucket containing the chain
     * @param previous the node preceding the node to remove, or null if removing the head
     * @param toRemove the node to be removed from the chain
     */
    private void removeNodeFromChain(int bucketIndex, SinglyLinkedNode<E> previous, SinglyLinkedNode<E> toRemove) {
        if (previous == null) {
            buckets[bucketIndex] = toRemove.getNext();
        } else {
            previous.setNext(toRemove.getNext());
        }
        toRemove.clear();
        size--;
    }

    /**
     * Clears all nodes in a chain, helping with garbage collection.
     *
     * @param head the first node in the chain to clear
     */
    private void clearChain(SinglyLinkedNode<E> head) {
        SinglyLinkedNode<E> current = head;
        SinglyLinkedNode<E> next;

        while (current != null) {
            next = current.getNext();
            current.clear();
            current = next;
        }
    }
}
