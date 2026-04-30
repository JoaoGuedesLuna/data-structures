package dev.guedes.datastructures.util.collection.set;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for {@link HashSet}.
 *
 * @author João Guedes
 */
class HashSetTest extends SetTest {
    @Override
    protected Set<Integer> createSet() { return new HashSet<>(); }

    @Test
    void constructor_ShouldThrowException_WhenInitialCapacityIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> new HashSet<>(-1));
    }

    @Test
    void constructor_ShouldThrowException_WhenInitialCapacityIsZero() {
        assertThrows(IllegalArgumentException.class, () -> new HashSet<>(0));
    }

    @Test
    void constructor_ShouldCreateInstance_WhenInitialCapacityIsPositive() {
        assertDoesNotThrow(() -> new HashSet<>(1));
    }

    @Test
    void constructor_ShouldCreateSetWithDefaultCapacity_WhenNoArgsConstructorIsUsed() {
        assertDoesNotThrow(() -> new HashSet<>());
    }

    @Test
    void remove_ShouldReturnTrue_WhenElementExists() {
        Set<Integer> set = createSet();

        set.add(1);
        set.add(2);
        set.add(11);

        assertTrue(set.remove(1));
        assertTrue(set.remove(2));
        assertTrue(set.remove(11));
        assertTrue(set.isEmpty());
    }

    @Test
    void remove_ShouldReturnFalse_WhenElementDoesNotExist() {
        assertFalse(createSet().remove(1));
    }

    @Test
    void contains_ShouldReturnTrue_WhenElementExists() {
        Set<Integer> set = createSet();

        set.add(1);
        set.add(2);
        set.add(11);

        assertTrue(set.contains(1));
        assertTrue(set.contains(2));
        assertTrue(set.contains(11));
    }

    @Test
    void contains_ShouldReturnFalse_WhenElementDoesNotExist() {
        assertFalse(createSet().contains(1));
    }

    @Test
    void forEach_ShouldIterateAllElements_WhenSetHasElements() {
        Set<Integer> set = createSet();

        set.add(1);
        set.add(2);
        set.add(11);

        Set<Integer> result = createSet();

        set.forEach(result::add);

        assertEquals(set.size(), result.size());
        assertTrue(result.contains(1));
        assertTrue(result.contains(2));
        assertTrue(result.contains(11));
    }

    @Test
    void forEach_ShouldDoNothing_WhenSetIsEmpty() {
        Set<Integer> set = createSet();

        Set<Integer> result = createSet();

        set.forEach(result::add);

        assertTrue(result.isEmpty());
    }

    @Test
    void toArray_ShouldReturnEmptyArray_WhenSetIsEmpty() {
        Object[] array = createSet().toArray();

        assertEquals(0, array.length);
    }

    @Test
    void toArray_ShouldReturnAllElements_WhenSetHasElements() {
        Set<Integer> set = createSet();

        set.add(1);
        set.add(2);
        set.add(11);

        Object[] array = set.toArray();

        assertEquals(3, array.length);
    }

    @Test
    void toString_ShouldReturnEmptyRepresentation_WhenSetIsEmpty() {
        assertEquals("[]", createSet().toString());
    }

    @Test
    void toString_ShouldContainAllElements_WhenSetHasElements() {
        Set<Integer> set = createSet();

        set.add(1);
        set.add(2);
        set.add(11);

        String result = set.toString();

        assertTrue(result.startsWith("["));
        assertTrue(result.endsWith("]"));

        assertTrue(result.contains("1"));
        assertTrue(result.contains("2"));
        assertTrue(result.contains("11"));
    }

    @Test
    void clear_ShouldRemoveAllElements_WhenSetIsNotEmpty() {
        Set<Integer> set = createSet();

        set.add(1);
        set.add(2);
        set.add(11);

        set.clear();

        assertTrue(set.isEmpty());
    }
}
