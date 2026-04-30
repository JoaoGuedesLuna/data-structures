package dev.guedes.datastructures.util.collection.set;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for {@link LinkedSet}.
 *
 * @author João Guedes
 */
class LinkedSetTest extends SetTest {
    @Override
    protected Set<Integer> createSet() { return new LinkedSet<>(); }

    @Test
    void addAtIndex_ShouldNotInsert_WhenElementAlreadyExists() {
        LinkedSet<Integer> set = new LinkedSet<>();

        set.add(1);
        set.add(2);
        set.add(11);

        set.add(1, 2);

        assertEquals(3, set.size());
    }

    @Test
    void addAtIndex_ShouldInsertElement_WhenElementIsNotPresent() {
        LinkedSet<Integer> set = new LinkedSet<>();

        set.add(1);
        set.add(2);

        set.add(1, 11);

        assertEquals(3, set.size());
        assertTrue(set.contains(11));
    }

    @Test
    void set_ShouldNotReplace_WhenElementAlreadyExists() {
        LinkedSet<Integer> set = new LinkedSet<>();

        set.add(1);
        set.add(2);
        set.add(11);

        int returned = set.set(1, 11);

        assertEquals(2, returned);
        assertTrue(set.contains(2));
    }

    @Test
    void set_ShouldReplaceElement_WhenNewElementIsNotPresent() {
        LinkedSet<Integer> set = new LinkedSet<>();

        set.add(1);
        set.add(2);
        set.add(11);

        int old = set.set(2, 3);

        assertEquals(11, old);
        assertFalse(set.contains(11));
        assertTrue(set.contains(3));
    }
}
