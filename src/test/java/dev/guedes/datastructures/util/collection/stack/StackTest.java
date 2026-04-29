package dev.guedes.datastructures.util.collection.stack;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Contract class for {@link Stack} test classes.
 *
 * @author João Guedes
 */
abstract class StackTest {
    protected abstract Stack<Integer> createStack();

    @Test
    void push_shouldReturnElement_whenElementIsPushed() {
        Stack<Integer> stack = createStack();

        int pushed1 = stack.push(1);
        int pushed2 = stack.push(2);
        int pushed3 = stack.push(3);

        assertEquals(1, pushed1);
        assertEquals(2, pushed2);
        assertEquals(3, pushed3);
        assertEquals(3, stack.size());
    }

    @Test
    void push_shouldFollowLifoOrder_whenMultipleElementsArePushed() {
        Stack<Integer> stack = createStack();

        stack.push(1);
        stack.push(2);
        stack.push(3);

        assertEquals(3, stack.pop());
        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
    }

    @Test
    void pop_shouldThrowException_whenStackIsEmpty() {
        assertThrows(EmptyStackException.class, () -> createStack().pop());
    }

    @Test
    void pop_shouldRemoveTopElement_whenStackHasElements() {
        Stack<Integer> stack = createStack();

        stack.push(1);
        stack.push(2);
        stack.push(3);

        int popped = stack.pop();

        assertEquals(3, popped);
        assertEquals(2, stack.size());
    }

    @Test
    void peek_shouldThrowException_whenStackIsEmpty() {
        assertThrows(EmptyStackException.class, () -> createStack().peek());
    }

    @Test
    void peek_shouldReturnTopElement_whenStackHasElements() {
        Stack<Integer> stack = createStack();

        stack.push(1);
        stack.push(2);
        stack.push(3);

        assertEquals(3, stack.peek());
    }

    @Test
    void peek_shouldNotRemoveElement_whenCalled() {
        Stack<Integer> stack = createStack();

        stack.push(1);
        stack.push(2);
        stack.push(3);

        stack.peek();

        assertEquals(3, stack.size());
    }

    @Test
    void peek_shouldAlwaysReturnLastPushedElement_whenMultiplePushesOccur() {
        Stack<Integer> stack = createStack();

        for (int i = 0; i < 5; i++) {
            stack.push(i);
            assertEquals(i, stack.peek());
        }
    }

    @ParameterizedTest
    @CsvSource({ "1, 3", "2, 2", "3, 1" })
    void search_shouldReturnCorrectPosition_whenElementExists(int element, int expectedPosition) {
        Stack<Integer> stack = createStack();

        stack.push(1);
        stack.push(2);
        stack.push(3);

        assertEquals(expectedPosition, stack.search(element));
    }

    @Test
    void search_shouldReturnMinusOne_whenElementDoesNotExist() {
        Stack<Integer> stack = createStack();

        stack.push(1);
        stack.push(2);
        stack.push(3);

        assertEquals(-1, stack.search(4));
    }

    @Test
    void search_shouldReturnClosestToTop_whenDuplicatesExist() {
        Stack<Integer> stack = createStack();

        stack.push(1);
        stack.push(2);
        stack.push(1);

        assertEquals(1, stack.search(1));
    }

    @Test
    void pop_shouldRemoveAllElements_whenStackIsConsumed() {
        Stack<Integer> stack = createStack();

        for (int i = 0; i < 10; i++) stack.push(i);

        for (int i = 9; i >= 0; i--) assertEquals(i, stack.pop());

        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
    }
}
