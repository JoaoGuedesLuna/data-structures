package dev.guedes.datastructures.util.collection.queue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for {@link CircularQueue}.
 *
 * @author João Guedes
 */
class CircularQueueTest extends QueueTest {
    @Override
    protected Queue<Integer> createQueue() { return new CircularQueue<>(1); }

    @Test
    void constructor_ShouldThrowException_WhenInitialCapacityIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> new CircularQueue<>(-1));
    }

    @Test
    void constructor_ShouldCreateInstance_WhenInitialCapacityIsZero() {
        assertDoesNotThrow(() -> new CircularQueue<>(0));
    }

    @Test
    void constructor_ShouldCreateInstance_WhenInitialCapacityIsPositive() {
        assertDoesNotThrow(() -> new CircularQueue<>(1));
    }

    @Test
    void constructor_ShouldCreateQueueWithDefaultCapacity_WhenNoArgsConstructorIsUsed() {
        assertDoesNotThrow(() -> new CircularQueue<>());
    }

    @Test
    void size_ShouldReturnZero_WhenQueueIsEmpty() {
        assertEquals(0, createQueue().size());
    }

    @Test
    void size_ShouldIncrease_WhenElementsAreAdded() {
        Queue<Integer> queue = createQueue();

        queue.add(1);
        queue.add(2);

        assertEquals(2, queue.size());
    }

    @Test
    void size_ShouldDecrease_WhenElementsAreRemoved() {
        Queue<Integer> queue = createQueue();

        queue.add(1);
        queue.add(2);
        queue.remove();

        assertEquals(1, queue.size());
    }

    @Test
    void size_ShouldReturnZero_WhenQueueIsCleared() {
        Queue<Integer> queue = createQueue();

        queue.add(1);
        queue.clear();

        assertEquals(0, queue.size());
    }

    @Test
    void isEmpty_ShouldReturnTrue_WhenQueueIsEmpty() {
        assertTrue(createQueue().isEmpty());
    }

    @Test
    void isEmpty_ShouldReturnFalse_WhenQueueHasElements() {
        Queue<Integer> queue = createQueue();

        queue.add(1);

        assertFalse(queue.isEmpty());
    }

    @Test
    void add_ShouldReturnTrue_WhenElementIsAddedAndInitialSizeIsZero() {
        Queue<Integer> queue = new CircularQueue<>(0);

        assertTrue(queue.add(1));
    }

    @Test
    void add_ShouldReturnTrue_WhenElementIsAdded() {
        assertTrue(createQueue().add(1));
    }

    @Test
    void add_ShouldAppendElement_WhenCalled() {
        Queue<Integer> queue = createQueue();

        queue.add(1);
        queue.add(2);

        assertEquals("[1, 2]", queue.toString());
    }

    @Test
    void remove_ShouldReturnFalse_WhenQueueIsEmpty() {
        Queue<Integer> queue = createQueue();

        assertFalse(queue.remove(Integer.valueOf(1)));
    }

    @Test
    void remove_ShouldReturnFalse_WhenElementDoesNotExist() {
        Queue<Integer> queue = createQueue();

        queue.add(1);

        assertFalse(queue.remove(Integer.valueOf(2)));
    }

    @Test
    void remove_ShouldRemoveElement_WhenQueueHasSingleElement() {
        Queue<Integer> queue = createQueue();

        queue.add(1);

        boolean removed = queue.remove(Integer.valueOf(1));

        assertTrue(removed);
        assertTrue(queue.isEmpty());
        assertEquals("[]", queue.toString());
    }

    @ParameterizedTest()
    @CsvSource({"1, '[2, 3]'", "2, '[1, 3]'", "3, '[1, 2]'"})
    void remove_ShouldRemoveElement_WhenElementExists(int element, String expectedQueue) {
        Queue<Integer> queue = createQueue();

        queue.add(1);
        queue.add(2);
        queue.add(3);

        boolean removed = queue.remove(Integer.valueOf(element));

        assertTrue(removed);
        assertEquals(expectedQueue, queue.toString());
    }

    @Test
    void remove_ShouldRemoveAllElements_FromEndUntilQueueIsEmpty() {
        Queue<Integer> queue = createQueue();

        for (int i = 0; i < 10; i++) queue.add(i);

        for (int i = 9; i >= 0; i--) assertTrue(queue.remove(Integer.valueOf(i)));

        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
        assertEquals("[]", queue.toString());
    }

    @Test
    void contains_ShouldReturnTrue_WhenElementExists() {
        Queue<Integer> queue = createQueue();

        queue.add(1);
        queue.add(2);

        assertTrue(queue.contains(1));
        assertTrue(queue.contains(2));
    }

    @Test
    void contains_ShouldReturnFalse_WhenElementDoesNotExist() {
        assertFalse(createQueue().contains(1));
    }

    @Test
    void forEach_ShouldIterateInOrder() {
        Queue<Integer> queue = createQueue();

        queue.add(1);
        queue.add(2);
        queue.add(3);

        Queue<Integer> result = createQueue();
        queue.forEach(result::add);

        assertEquals("[1, 2, 3]", result.toString());
    }

    @Test
    void toArray_ShouldReturnEmptyArray_WhenQueueIsEmpty() {
        assertArrayEquals(new Object[]{}, createQueue().toArray());
    }

    @Test
    void toArray_ShouldReturnElements_WhenQueueIsNotEmpty() {
        Queue<Integer> queue = createQueue();

        queue.add(1);
        queue.add(2);

        assertArrayEquals(new Object[]{1, 2}, queue.toArray());
    }

    @Test
    void clear_ShouldRemoveAllElements() {
        Queue<Integer> queue = createQueue();

        queue.add(1);
        queue.clear();

        assertTrue(queue.isEmpty());
    }

    @Test
    void toString_ShouldReturnEmptyRepresentation_WhenQueueIsEmpty() {
        assertEquals("[]", createQueue().toString());
    }

    @Test
    void toString_ShouldReturnFormattedString_WhenQueueHasElements() {
        Queue<Integer> queue = createQueue();

        queue.add(1);
        queue.add(2);
        queue.add(3);

        assertEquals("[1, 2, 3]", queue.toString());
    }
}
