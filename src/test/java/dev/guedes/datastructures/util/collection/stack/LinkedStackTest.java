package dev.guedes.datastructures.util.collection.stack;

/**
 * Test class for {@link LinkedStack}.
 *
 * @author João Guedes
 */
class LinkedStackTest extends StackTest {
    @Override
    protected Stack<Integer> createStack() { return new LinkedStack<>(); }
}
