package dev.guedes.datastructures.util.collection.map;

import dev.guedes.datastructures.util.Objects;
import dev.guedes.datastructures.util.collection.Collection;
import dev.guedes.datastructures.util.collection.internal.nodes.EntryNode;
import dev.guedes.datastructures.util.collection.list.SinglyLinkedList;
import dev.guedes.datastructures.util.collection.set.HashSet;
import dev.guedes.datastructures.util.collection.set.Set;

/**
 * A hash table implementation of the Map interface.
 * This implementation uses separate chaining for collision resolution.
 *
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 *
 * @author João Guedes
 */
public class Hashtable<K, V> implements Map<K, V> {
    private static final int DEFAULT_CAPACITY = 10;

    private final EntryNode<K, V>[] buckets;
    private final int capacity;
    private int size;

    @SuppressWarnings("unchecked")
    public Hashtable(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Initial capacity must be positive. Provided value: " + capacity + ".");
        }

        this.capacity = capacity;
        this.buckets = (EntryNode<K, V>[]) new EntryNode[capacity];
        this.size = 0;
    }

    public Hashtable() { this(DEFAULT_CAPACITY); }

    @Override
    public int size() { return size; }

    @Override
    public boolean isEmpty() { return size == 0; }

    @Override
    public V put(K key, V value) {
        Objects.requireNonNull(value, "Value cannot be null.");

        int bucketIndex = calculateBucketIndex(key);

        EntryNode<K, V> node = findEntry(key, bucketIndex);

        if (node != null) return updateEntry(node, value);

        buckets[bucketIndex] = new EntryNode<>(key, value, buckets[bucketIndex]);
        size++;

        return null;
    }

    @Override
    public boolean replace(K key, V oldValue, V newValue) throws NullPointerException {
        Objects.requireNonNull(oldValue, "Oldvalue cannot be null.");
        Objects.requireNonNull(newValue, "NewValue cannot be null.");

        EntryNode<K, V> entry = findEntry(key, calculateBucketIndex(key));

        if (entry != null && oldValue.equals(entry.getValue())) {
            entry.setValue(newValue);
            return true;
        }

        return false;
    }

    @Override
    public V replace(K key, V newValue) throws NullPointerException {
        Objects.requireNonNull(newValue, "NewValue cannot be null.");

        EntryNode<K, V> node = findEntry(key, calculateBucketIndex(key));

        return node != null ? updateEntry(node, newValue) : null;
    }

    @Override
    public boolean remove (K key, V value) throws NullPointerException {
        Objects.requireNonNull(value, "Value cannot be null.");

        int bucketIndex = calculateBucketIndex(key);

        EntryNode<K, V>[] entries = findEntryWithPrevious(key, bucketIndex);

        if (entries != null && value.equals(entries[1].getValue())) {
            removeNodeFromChain(bucketIndex, entries[0], entries[1]);
            return true;
        }

        return false;
    }

    @Override
    public V remove(K key) {
        int bucketIndex = calculateBucketIndex(key);

        EntryNode<K, V>[] entries = findEntryWithPrevious(key, bucketIndex);

        return entries != null ? removeNodeFromChain(bucketIndex, entries[0], entries[1]) : null;
    }

    @Override
    public V get(K key) {
        EntryNode<K, V> entry = findEntry(key, calculateBucketIndex(key));
        return entry != null ? entry.getValue() : null;
    }

    @Override
    public boolean containsKey(K key) { return findEntry(key, calculateBucketIndex(key)) != null; }

    @Override
    public boolean containsValue(V value) {
        Objects.requireNonNull(value, "Value cannot be null.");

        EntryNode<K, V> current;

        for (EntryNode<K, V> head : buckets) {
            current = head;
            while (current != null) {
                if (value.equals(current.getValue())) {
                    return true;
                }
                current = current.getNext();
            }
        }

        return false;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keySet = new HashSet<>();
        EntryNode<K, V> current;

        for (EntryNode<K, V> head : buckets) {
            current = head;
            while (current != null) {
                keySet.add(current.getKey());
                current = current.getNext();
            }
        }

        return keySet;
    }

    @Override
    public Collection<V> values() {
        Collection<V> valuesCollection = new SinglyLinkedList<>();
        EntryNode<K, V> current;

        for (EntryNode<K, V> head : buckets) {
            current = head;
            while (current != null) {
                valuesCollection.add(current.getValue());
                current = current.getNext();
            }
        }

        return valuesCollection;
    }

    @Override
    public Set<EntryNode<K, V>> entrySet() {
        Set<EntryNode<K, V>> entrySet = new HashSet<>();
        EntryNode<K, V> current;

        for (EntryNode<K, V> head : buckets) {
            current = head;
            while (current != null) {
                entrySet.add(new EntryNode<>(current.getKey(), current.getValue(), null));
                current = current.getNext();
            }
        }

        return entrySet;
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
        if (isEmpty()) return "{}";

        StringBuilder sb = new StringBuilder();
        for (EntryNode<K, V> head : buckets) {
            if (head != null) sb.append(head).append(", ");
        }
        sb.setLength(sb.length() - 2);

        return "{" + sb + "}";
    }

    /**
     * Computes the bucket index for a given key.
     *
     * @param key the key to hash
     * @return the bucket index (0 ≤ index < capacity)
     */
    private int calculateBucketIndex(K key) {
        Objects.requireNonNull(key, "Key cannot be null.");
        return Math.abs(key.hashCode() % capacity);
    }

    /**
     * Searches for an entry with the specified key within a given bucket.
     * <p>
     * This method traverses the linked list (chain) at the provided bucket index,
     * comparing each entry's key with the given key using {@code equals()}.
     *
     * @param key the key to search for (must not be null)
     * @param bucketIndex the index of the bucket where the search will be performed
     * @return the {@link EntryNode} containing the specified key, or {@code null} if no matching entry is found in the bucket
     * @throws NullPointerException if the key is null
     */
    private EntryNode<K, V> findEntry(K key, int bucketIndex) {
        EntryNode<K, V> current = buckets[bucketIndex];

        while (current != null) {
            if (key.equals(current.getKey())) {
                return current;
            }
            current = current.getNext();
        }

        return null;
    }

    /**
     * Finds an entry by key in the specified bucket and also returns its previous node.
     *
     * @param key the key to search for (must not be null)
     * @param bucketIndex the bucket index to search in
     * @return an array where index 0 is the previous node (null if head) and index 1 is the matching node, or {@code null} if not found
     * @throws NullPointerException if the key is null
     */
    @SuppressWarnings("unchecked")
    private EntryNode<K, V>[] findEntryWithPrevious(K key, int bucketIndex) {
        EntryNode<K, V> current = buckets[bucketIndex];
        EntryNode<K, V> previous = null;

        while (current != null) {
            if (key.equals(current.getKey())) {
                return new EntryNode[] { previous, current };
            }
            previous = current;
            current = current.getNext();
        }

        return null;
    }

    /**
     * Updates the value of an existing entry and returns the old value.
     *
     * @param entry the entry to update
     * @param newValue the new value to set
     * @return the previous value associated with the entry
     */
    private V updateEntry(EntryNode<K, V> entry, V newValue) {
        V oldValue = entry.getValue();
        entry.setValue(newValue);
        return oldValue;
    }

    /**
     * Removes a node from a chain in a bucket.
     *
     * @param bucketIndex the index of the bucket containing the chain
     * @param previous the node preceding the node to remove, or null if removing the head
     * @param toRemove the node to be removed from the chain
     * @return the previous value associated with the entry
     */
    private V removeNodeFromChain(int bucketIndex, EntryNode<K, V> previous, EntryNode<K, V> toRemove) {
        V oldValue = toRemove.getValue();

        if (previous == null) {
            buckets[bucketIndex] = toRemove.getNext();
        } else {
            previous.setNext(toRemove.getNext());
        }

        toRemove.clear();
        size--;
        return oldValue;
    }

    /**
     * Clears all nodes in a chain, helping with garbage collection.
     *
     * @param head the first node in the chain to clear
     */
    private void clearChain(EntryNode<K, V> head) {
        EntryNode<K, V> current = head;
        EntryNode<K, V> next;

        while (current != null) {
            next = current.getNext();
            current.clear();
            current = next;
        }
    }
}
