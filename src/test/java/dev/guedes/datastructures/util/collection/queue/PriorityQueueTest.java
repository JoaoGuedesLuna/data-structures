package dev.guedes.datastructures.util.collection.queue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for {@link PriorityQueue}.
 *
 * @author João Guedes
 */
class PriorityQueueTest {
    private PriorityQueue<Integer> createQueue() { return new PriorityQueue<>(); }

    @Test
    void add_shouldInsertElementAtBeginning_whenElementHasHighestPriority() {
        PriorityQueue<Integer> queue = createQueue();

        queue.add(2);
        queue.add(3);
        queue.add(1);

        assertEquals(1, queue.peek());
    }

    @Test
    void add_shouldInsertElementInMiddle_whenElementHasIntermediatePriority() {
        PriorityQueue<Integer> queue = createQueue();

        queue.add(1);
        queue.add(3);
        queue.add(2);

        assertEquals(1, queue.poll());
        assertEquals(2, queue.poll());
        assertEquals(3, queue.poll());
    }

    @Test
    void add_shouldInsertElementAtEnd_whenElementHasLowestPriority() {
        PriorityQueue<Integer> queue = createQueue();

        queue.add(1);
        queue.add(2);
        queue.add(3);

        assertEquals(1, queue.poll());
        assertEquals(2, queue.poll());
        assertEquals(3, queue.poll());
    }

    @Test
    void offer_shouldInsertElementsInSortedOrder_whenElementsAreAdded() {
        PriorityQueue<Integer> queue = createQueue();

        queue.offer(3);
        queue.offer(1);
        queue.offer(2);

        assertEquals(1, queue.poll());
        assertEquals(2, queue.poll());
        assertEquals(3, queue.poll());
    }
}
