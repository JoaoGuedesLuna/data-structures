package dev.guedes.datastructures.util.collection.queue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Contract class for {@link Queue} test classes.
 *
 * @author João Guedes
 */
abstract class QueueTest {
    protected abstract Queue<Integer> createQueue();

    @Test
    void offer_shouldReturnTrue_whenElementIsAdded() {
        Queue<Integer> queue = createQueue();

        boolean queued1 = queue.offer(1);
        boolean queued2 = queue.offer(2);
        boolean queued3 = queue.offer(3);

        assertTrue(queued1);
        assertTrue(queued2);
        assertTrue(queued3);
        assertEquals(3, queue.size());
    }

    @Test
    void offer_shouldFollowFifoOrder_whenMultipleElementsAreAdded() {
        Queue<Integer> queue = createQueue();

        queue.offer(1);
        queue.offer(2);
        queue.offer(3);

        assertEquals(1, queue.poll());
        assertEquals(2, queue.poll());
        assertEquals(3, queue.poll());
    }

    @Test
    void poll_shouldReturnNull_whenQueueIsEmpty() {
        assertNull(createQueue().poll());
    }

    @Test
    void poll_shouldRemoveHeadElement_whenQueueHasElements() {
        Queue<Integer> queue = createQueue();

        queue.offer(1);
        queue.offer(2);
        queue.offer(3);

        int polled = queue.poll();

        assertEquals(1, polled);
        assertEquals(2, queue.size());
    }

    @Test
    void poll_shouldRemoveAllElements_whenQueueIsConsumed() {
        Queue<Integer> queue = createQueue();

        for (int i = 0; i < 10; i++) queue.offer(i);

        for (int i = 0; i < 10; i++) assertEquals(i, queue.poll());

        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
    }

    @Test
    void remove_shouldThrowException_whenQueueIsEmpty() {
        assertThrows(NoSuchElementException.class, () -> createQueue().remove());
    }

    @Test
    void remove_shouldRemoveHeadElement_whenQueueHasElements() {
        Queue<Integer> queue = createQueue();

        queue.offer(1);
        queue.offer(2);
        queue.offer(3);

        int removed = queue.remove();

        assertEquals(1, removed);
        assertEquals(2, queue.size());
    }

    @Test
    void peek_shouldReturnNull_whenQueueIsEmpty() {
        assertNull(createQueue().peek());
    }

    @Test
    void peek_shouldReturnHeadElement_whenQueueHasElements() {
        Queue<Integer> queue = createQueue();

        queue.offer(1);
        queue.offer(2);
        queue.offer(3);

        assertEquals(1, queue.peek());
    }

    @Test
    void peek_shouldNotRemoveElement_whenCalled() {
        Queue<Integer> queue = createQueue();

        queue.offer(1);
        queue.offer(2);
        queue.offer(3);

        queue.peek();

        assertEquals(3, queue.size());
    }

    @Test
    void element_shouldThrowException_whenQueueIsEmpty() {
        assertThrows(NoSuchElementException.class, () -> createQueue().element());
    }

    @Test
    void element_shouldReturnHeadElement_whenQueueHasElements() {
        Queue<Integer> queue = createQueue();

        queue.offer(1);
        queue.offer(2);
        queue.offer(3);

        assertEquals(1, queue.element());
    }

    @Test
    void element_shouldNotRemoveElement_whenCalled() {
        Queue<Integer> queue = createQueue();

        queue.offer(1);
        queue.offer(2);
        queue.offer(3);

        queue.element();

        assertEquals(3, queue.size());
    }
}
