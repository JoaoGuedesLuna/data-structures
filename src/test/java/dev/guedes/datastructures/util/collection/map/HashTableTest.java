package dev.guedes.datastructures.util.collection.map;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test class for {@link Hashtable}.
 *
 * @author João Guedes
 */
class HashTableTest extends MapTest {
    @Override
    protected Map<String, Integer> createMap() { return new Hashtable<>(); }

    @Test
    void constructor_ShouldThrowException_WhenInitialCapacityIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> new Hashtable<>(-1));
    }

    @Test
    void constructor_ShouldThrowException_WhenInitialCapacityIsZero() {
        assertThrows(IllegalArgumentException.class, () -> new Hashtable<>(0));
    }

    @Test
    void constructor_ShouldCreateInstance_WhenInitialCapacityIsPositive() {
        assertDoesNotThrow(() -> new Hashtable<>(1));
    }

    @Test
    void constructor_ShouldCreateMapWithDefaultCapacity_WhenNoArgsConstructorIsUsed() {
        assertDoesNotThrow(() -> new Hashtable<>());
    }
}
