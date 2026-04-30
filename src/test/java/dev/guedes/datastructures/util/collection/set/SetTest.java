package dev.guedes.datastructures.util.collection.set;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Contract class for {@link Set} test classes.
 *
 * @author João Guedes
 */
abstract class SetTest {
    protected abstract Set<Integer> createSet();

    @Test
    void add_ShouldReturnTrue_WhenElementIsNew() {
        Set<Integer> set = createSet();

        assertTrue(set.add(1));
        assertFalse(set.isEmpty());
    }

    @Test
    void add_ShouldReturnTrue_WhenElementIsNewAndNull() {
        Set<Integer> set = createSet();

        assertTrue(set.add(null));
        assertFalse(set.isEmpty());
    }

    @Test
    void add_ShouldReturnFalse_WhenElementAlreadyExists() {
        Set<Integer> set = createSet();

        set.add(1);

        assertFalse(set.add(1));
    }

    @Test
    void add_ShouldReturnFalse_WhenElementIsNullAndAlreadyExists() {
        Set<Integer> set = createSet();

        set.add(null);

        assertFalse(set.add(null));
    }

    @Test
    void add_ShouldNotIncreaseSize_WhenDuplicateElementIsAdded() {
        Set<Integer> set = createSet();

        set.add(1);
        set.add(1);

        assertEquals(1, set.size());
    }
}
