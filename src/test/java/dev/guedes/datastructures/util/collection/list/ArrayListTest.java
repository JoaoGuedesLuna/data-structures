package dev.guedes.datastructures.util.collection.list;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link ArrayList}.
 *
 * @author João Guedes
 */
class ArrayListTest extends ListTest {
    @Override
    protected List<Integer> createList() { return new ArrayList<>(1); }

    @Test
    void constructor_ShouldThrowException_WhenInitialCapacityIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> new ArrayList<>(-1));
    }

    @Test
    void constructor_ShouldCreateInstance_WhenInitialCapacityIsZero() {
        assertDoesNotThrow(() -> new ArrayList<>(0));
    }

    @Test
    void constructor_ShouldCreateInstance_WhenInitialCapacityIsPositive() {
        assertDoesNotThrow(() -> new ArrayList<>(1));
    }

    @Test
    void constructor_ShouldCreateListWithDefaultCapacity_WhenNoArgsConstructorIsUsed() {
        assertDoesNotThrow(() -> new ArrayList<>());
    }

    @Test
    void add_ShouldReturnTrue_WhenElementIsAddedAndInitialSizeIsZero() {
        List<Integer> list = new ArrayList<>(0);

        assertTrue(list.add(1));
    }
}
