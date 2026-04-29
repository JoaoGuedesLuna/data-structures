package dev.guedes.datastructures.util.collection.queue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test class for {@link ArrayQueue}.
 *
 * @author João Guedes
 */
class ArrayQueueTest extends QueueTest {
    @Override
    protected Queue<Integer> createQueue() { return new ArrayQueue<>(1); }

    @Test
    void constructor_ShouldThrowException_WhenInitialCapacityIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> new ArrayQueue<>(-1));
    }

    @Test
    void constructor_ShouldCreateInstance_WhenInitialCapacityIsZero() {
        assertDoesNotThrow(() -> new ArrayQueue<>(0));
    }

    @Test
    void constructor_ShouldCreateInstance_WhenInitialCapacityIsPositive() {
        assertDoesNotThrow(() -> new ArrayQueue<>(1));
    }

    @Test
    void constructor_ShouldCreateListWithDefaultCapacity_WhenNoArgsConstructorIsUsed() {
        assertDoesNotThrow(() -> new ArrayQueue<>());
    }
}
