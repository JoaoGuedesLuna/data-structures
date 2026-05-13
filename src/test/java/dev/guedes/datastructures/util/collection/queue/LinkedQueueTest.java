package dev.guedes.datastructures.util.collection.queue;

/**
 * Test class for {@link LinkedQueue}.
 *
 * @author João Guedes
 */
class LinkedQueueTest extends QueueTest {
    @Override
    protected Queue<Integer> createQueue() { return new LinkedQueue<>(); }
}
