package dev.guedes.datastructures.util.collection.stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test class for {@link ArrayStack}.
 *
 * @author João Guedes
 */
class ArrayStackTest extends StackTest {
    @Override
    protected Stack<Integer> createStack() { return new ArrayStack<>(1); }

    @Test
    void constructor_ShouldThrowException_WhenInitialCapacityIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> new ArrayStack<>(-1));
    }

    @Test
    void constructor_ShouldCreateInstance_WhenInitialCapacityIsZero() {
        assertDoesNotThrow(() -> new ArrayStack<>(0));
    }

    @Test
    void constructor_ShouldCreateInstance_WhenInitialCapacityIsPositive() {
        assertDoesNotThrow(() -> new ArrayStack<>(1));
    }

    @Test
    void constructor_ShouldCreateListWithDefaultCapacity_WhenNoArgsConstructorIsUsed() {
        assertDoesNotThrow(() -> new ArrayStack<>());
    }
}
