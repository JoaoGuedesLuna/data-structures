package dev.guedes.datastructures.util.collection.map;

import dev.guedes.datastructures.util.collection.Collection;
import dev.guedes.datastructures.util.collection.internal.nodes.EntryNode;
import dev.guedes.datastructures.util.collection.set.Set;

/**
 * A generic key-value mapping interface, similar to Java's {@link java.util.Map}.
 * <p>
 * Maps store unique keys associated with values, allowing fast lookup, insertion,
 * replacement, and removal. This interface provides methods to interact with the
 * collection of keys, values, or key-value pairs.
 *
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 *
 * @author João Guedes
 */
public interface Map<K, V> {
    /**
     * Returns the number of key-value mappings in the map.
     *
     * @return the number of entries
     */
    int size();

    /**
     * Returns {@code true} if the map contains no key-value mappings.
     *
     * @return {@code true} if empty; {@code false} otherwise
     */
    boolean isEmpty();

    /**
     * Associates the specified value with the specified key in the map.
     * If the map previously contained a mapping for the key, the old value is replaced.
     *
     * @param key the key with which the value is to be associated
     * @param value the value to associate
     * @return the previous value associated with the key, or {@code null} if none
     * @throws NullPointerException if the key is {@code null}
     */
    V put(K key, V value) throws NullPointerException;

    /**
     * Replaces the entry for the specified key only if it is currently mapped to the specified old value.
     *
     * @param key the key whose value is to be replaced
     * @param oldValue the expected current value
     * @param newValue the new value to be associated
     * @return {@code true} if the value was replaced
     * @throws NullPointerException if key, oldValue, or newValue is {@code null}
     */
    boolean replace(K key, V oldValue, V newValue) throws NullPointerException;

    /**
     * Replaces the value for the specified key if it is currently mapped to some value.
     *
     * @param key the key whose value is to be replaced
     * @param newValue the new value to be associated
     * @return the previous value associated with the key, or {@code null} if no mapping existed
     * @throws NullPointerException if key or newValue is {@code null}
     */
    V replace(K key, V newValue) throws NullPointerException;

    /**
     * Removes the entry for the specified key only if it is currently mapped to the specified value.
     *
     * @param key the key whose mapping is to be conditionally removed
     * @param value the value expected to be associated with the key
     * @return {@code true} if the entry was removed
     * @throws NullPointerException if either key or value is {@code null}
     */
    boolean remove (K key, V value) throws NullPointerException;

    /**
     * Removes the mapping for a key if it exists.
     *
     * @param key the key whose mapping is to be removed
     * @return the previous value associated with the key, or {@code null} if none
     * @throws NullPointerException if the key is {@code null}
     */
    V remove(K key) throws NullPointerException;

    /**
     * Returns the value to which the specified key is mapped, or {@code null} if not found.
     *
     * @param key the key whose associated value is to be returned
     * @return the value mapped to the key, or {@code null} if not found
     * @throws NullPointerException if the key is {@code null}
     */
    V get(K key) throws NullPointerException;

    /**
     * Returns {@code true} if the map contains a mapping for the specified key.
     *
     * @param key the key to check for presence
     * @return {@code true} if the key exists in the map
     * @throws NullPointerException if the key is {@code null}
     */
    boolean containsKey(K key) throws NullPointerException;

    /**
     * Returns {@code true} if the map maps one or more keys to the specified value.
     *
     * @param value the value to search for
     * @return {@code true} if the value exists in the map
     * @throws NullPointerException if the value is {@code null}
     */
    boolean containsValue(V value) throws NullPointerException;

    /**
     * Returns a {@link Set} view of the keys contained in this map.
     *
     * @return a set of all keys
     */
    Set<K> keySet();

    /**
     * Returns a {@link Collection} view of the values contained in this map.
     *
     * @return a collection of all values
     */
    Collection<V> values();

    /**
     * Returns a {@link Set} view of the entries (key-value pairs) contained in this map.
     *
     * @return a set of map entries
     */
    Set<EntryNode<K, V>> entrySet();

    /**
     * Removes all key-value mappings from the map.
     */
    void clear();
}
