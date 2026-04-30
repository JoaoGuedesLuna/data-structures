package dev.guedes.datastructures.util.collection.set;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for {@link ArraySet}.
 *
 * @author João Guedes
 */
class ArraySetTest extends SetTest {
    @Override
    protected Set<Integer> createSet() { return new ArraySet<>(1); }

    @Test
    void constructor_ShouldThrowException_WhenInitialCapacityIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> new ArraySet<>(-1));
    }

    @Test
    void constructor_ShouldCreateInstance_WhenInitialCapacityIsZero() {
        assertDoesNotThrow(() -> new ArraySet<>(0));
    }

    @Test
    void constructor_ShouldCreateInstance_WhenInitialCapacityIsPositive() {
        assertDoesNotThrow(() -> new ArraySet<>(1));
    }

    @Test
    void constructor_ShouldCreateSetWithDefaultCapacity_WhenNoArgsConstructorIsUsed() {
        assertDoesNotThrow(() -> new ArraySet<>());
    }

    @Test
    void addAtIndex_ShouldNotInsert_WhenElementAlreadyExists() {
        ArraySet<Integer> set = new ArraySet<>();

        set.add(1);
        set.add(2);
        set.add(11);

        set.add(1, 2);

        assertEquals(3, set.size());
    }

    @Test
    void addAtIndex_ShouldInsertElement_WhenElementIsNotPresent() {
        ArraySet<Integer> set = new ArraySet<>();

        set.add(1);
        set.add(2);

        set.add(1, 11);

        assertEquals(3, set.size());
        assertTrue(set.contains(11));
    }

    @Test
    void set_ShouldNotReplace_WhenElementAlreadyExists() {
        ArraySet<Integer> set = new ArraySet<>();

        set.add(1);
        set.add(2);
        set.add(11);

        int returned = set.set(1, 11);

        assertEquals(2, returned);
        assertTrue(set.contains(2));
    }

    @Test
    void set_ShouldReplaceElement_WhenNewElementIsNotPresent() {
        ArraySet<Integer> set = new ArraySet<>();

        set.add(1);
        set.add(2);
        set.add(11);

        int old = set.set(2, 3);

        assertEquals(11, old);
        assertFalse(set.contains(11));
        assertTrue(set.contains(3));
    }
}
