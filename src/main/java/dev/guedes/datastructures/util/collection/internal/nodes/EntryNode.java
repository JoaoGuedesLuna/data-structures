package dev.guedes.datastructures.util.collection.internal.nodes;

import java.util.Objects;

/**
 * A node representing a key-value pair in a singly linked list structure.
 * <p>
 * This node is commonly used in hash tables or other associative data structures
 * where entries are stored as linked elements to handle collisions.
 *
 * @param <K> the type of keys maintained by this node
 * @param <V> the type of mapped values
 *
 * @author João Guedes
 */
public class EntryNode<K, V> {
    private K key;
    private V value;
    private EntryNode<K, V> next;

    public EntryNode(K key, V value, EntryNode<K, V> next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }

    public K getKey() { return key; }

    public V getValue() { return value; }
    public void setValue(V value) { this.value = value; }

    public EntryNode<K, V> getNext() { return next; }
    public void setNext(EntryNode<K, V> next) { this.next = next; }

    /**
     * Clears all references in this node for garbage collection.
     */
    public void clear() {
        key = null;
        value = null;
        next = null;
    }

    @Override
    public int hashCode() { return Objects.hash(key, value, next); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || this.getClass() != o.getClass()) { return false; }

        EntryNode<?, ?> entry = (EntryNode<?, ?>) o;
        return  Objects.equals(this.key, entry.key)
                && Objects.equals(this.value, entry.value)
                && Objects.equals(this.next, entry.next);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(key).append("=").append(value);

        if (next != null) sb.append(", ").append(next);

        return sb.toString();
    }
}
