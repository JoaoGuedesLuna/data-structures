package dev.guedes.datastructures.util.collection.list;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Contract class for {@link List} test classes.
 *
 * @author João Guedes
 */
abstract class ListTest {
    protected abstract List<Integer> createList();

    @Test
    void size_ShouldReturnZero_WhenListIsEmpty() {
        assertEquals(0, createList().size());
    }

    @Test
    void size_ShouldIncrease_WhenElementsAreAdded() {
        List<Integer> list = createList();

        list.add(1);
        list.add(2);

        assertEquals(2, list.size());
    }

    @Test
    void size_ShouldDecrease_WhenElementsAreRemoved() {
        List<Integer> list = createList();

        list.add(1);
        list.add(2);
        list.remove(0);

        assertEquals(1, list.size());
    }

    @Test
    void size_ShouldReturnZero_WhenListIsCleared() {
        List<Integer> list = createList();

        list.add(1);
        list.clear();

        assertEquals(0, list.size());
    }

    @Test
    void isEmpty_ShouldReturnTrue_WhenListIsEmpty() {
        assertTrue(createList().isEmpty());
    }

    @Test
    void isEmpty_ShouldReturnFalse_WhenListHasElements() {
        List<Integer> list = createList();

        list.add(1);

        assertFalse(list.isEmpty());
    }

    @Test
    void add_ShouldReturnTrue_WhenElementIsAdded() {
        assertTrue(createList().add(1));
    }

    @Test
    void add_ShouldAppendElement_WhenCalled() {
        List<Integer> list = createList();

        list.add(1);
        list.add(2);

        assertEquals("[1, 2]", list.toString());
    }

    @Test
    void addAtIndex_ShouldThrowException_WhenIndexIsNegative() {
        assertThrows(IndexOutOfBoundsException.class, () -> createList().add(-1, 1));
    }

    @Test
    void addAtIndex_ShouldThrowException_WhenIndexIsGreaterThanSize() {
        assertThrows(IndexOutOfBoundsException.class, () -> createList().add(1, 1));
    }

    @Test
    void addAtIndex_ShouldInsertAtBeginning_WhenIndexIsZero() {
        List<Integer> list = createList();

        list.add(1);
        list.add(2);

        list.add(0, 0);

        assertEquals("[0, 1, 2]", list.toString());
    }

    @Test
    void addAtIndex_ShouldInsertInMiddle_WhenIndexIsMiddle() {
        List<Integer> list = createList();

        list.add(1);
        list.add(3);

        list.add(1, 2);

        assertEquals("[1, 2, 3]", list.toString());
    }

    @Test
    void addAtIndex_ShouldInsertAtEnd_WhenIndexEqualsSize() {
        List<Integer> list = createList();

        list.add(1);
        list.add(2);

        list.add(2, 3);

        assertEquals("[1, 2, 3]", list.toString());
    }

    @Test
    void remove_ShouldReturnFalse_WhenElementDoesNotExist() {
        List<Integer> list = createList();

        list.add(1);

        assertFalse(list.remove(Integer.valueOf(2)));
    }

    @Test
    void remove_ShouldRemoveFirstOccurrence_WhenElementExists() {
        List<Integer> list = createList();

        list.add(1);
        list.add(2);
        list.add(1);

        list.remove(Integer.valueOf(1));

        assertEquals("[2, 1]", list.toString());
    }

    @Test
    void removeByIndex_ShouldThrowException_WhenIndexIsNegative() {
        assertThrows(IndexOutOfBoundsException.class, () -> createList().remove(-1));
    }

    @Test
    void removeByIndex_ShouldThrowException_WhenIndexIsOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> createList().remove(1));
    }

    @Test
    void removeByIndex_ShouldRemoveFirstElement_WhenIndexIsZero() {
        List<Integer> list = createList();

        list.add(1);
        list.add(2);
        list.add(3);

        int removed = list.remove(0);

        assertEquals(1, removed);
        assertEquals("[2, 3]", list.toString());
    }

    @Test
    void removeByIndex_ShouldRemoveMiddleElement_WhenIndexIsMiddle() {
        List<Integer> list = createList();

        list.add(1);
        list.add(2);
        list.add(3);

        int removed = list.remove(1);

        assertEquals(2, removed);
        assertEquals("[1, 3]", list.toString());
    }

    @Test
    void removeByIndex_ShouldRemoveLastElement_WhenIndexIsLast() {
        List<Integer> list = createList();

        list.add(1);
        list.add(2);
        list.add(3);

        int removed = list.remove(2);

        assertEquals(3, removed);
        assertEquals("[1, 2]", list.toString());
    }

    @Test
    void get_ShouldThrowException_WhenIndexIsNegative() {
        assertThrows(IndexOutOfBoundsException.class, () -> createList().get(-1));
    }

    @Test
    void get_ShouldThrowException_WhenIndexIsOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> createList().get(1));
    }

    @Test
    void get_ShouldReturnElement_WhenIndexIsValid() {
        List<Integer> list = createList();

        list.add(1);

        assertEquals(1, list.get(0));
    }

    @Test
    void set_ShouldThrowException_WhenIndexIsNegative() {
        assertThrows(IndexOutOfBoundsException.class, () -> createList().set(0, -1));
    }

    @Test
    void set_ShouldThrowException_WhenIndexIsOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> createList().set(0, 1));
    }

    @Test
    void set_ShouldReplaceElement_WhenIndexIsValid() {
        List<Integer> list = createList();

        list.add(1);

        int old = list.set(0, 2);

        assertEquals(1, old);
        assertEquals("[2]", list.toString());
    }

    @Test
    void indexOf_ShouldReturnZero_WhenElementIsAtBeginning() {
        List<Integer> list = createList();

        list.add(1);
        list.add(2);
        list.add(3);

        assertEquals(0, list.indexOf(1));
    }

    @Test
    void indexOf_ShouldReturnMiddleIndex_WhenElementIsInMiddle() {
        List<Integer> list = createList();

        list.add(1);
        list.add(2);
        list.add(3);

        assertEquals(1, list.indexOf(2));
    }

    @Test
    void indexOf_ShouldReturnLastIndex_WhenElementIsAtEnd() {
        List<Integer> list = createList();

        list.add(1);
        list.add(2);
        list.add(3);

        assertEquals(2, list.indexOf(3));
    }

    @Test
    void indexOf_ShouldReturnMinusOne_WhenElementDoesNotExist() {
        assertEquals(-1, createList().indexOf(1));
    }

    @Test
    void lastIndexOf_ShouldReturnLastOccurrence_WhenDuplicatesExist() {
        List<Integer> list = createList();

        list.add(1);
        list.add(2);
        list.add(1);
        list.add(3);

        assertEquals(2, list.lastIndexOf(1));
    }

    @Test
    void lastIndexOf_ShouldReturnLastIndex_WhenElementIsAtEnd() {
        List<Integer> list = createList();

        list.add(1);
        list.add(2);
        list.add(3);

        assertEquals(2, list.lastIndexOf(3));
    }

    @Test
    void lastIndexOf_ShouldReturnMiddleIndex_WhenElementIsInMiddle() {
        List<Integer> list = createList();

        list.add(1);
        list.add(2);
        list.add(3);

        assertEquals(1, list.lastIndexOf(2));
    }

    @Test
    void lastIndexOf_ShouldReturnZero_WhenElementIsAtBeginning() {
        List<Integer> list = createList();

        list.add(1);
        list.add(2);
        list.add(3);

        assertEquals(0, list.lastIndexOf(1));
    }

    @Test
    void lastIndexOf_ShouldReturnMinusOne_WhenElementDoesNotExist() {
        assertEquals(-1, createList().lastIndexOf(1));
    }

    @Test
    void contains_ShouldReturnTrue_WhenElementExists() {
        List<Integer> list = createList();

        list.add(1);

        assertTrue(list.contains(1));
    }

    @Test
    void contains_ShouldReturnFalse_WhenElementDoesNotExist() {
        assertFalse(createList().contains(1));
    }

    @Test
    void forEach_ShouldIterateInOrder() {
        List<Integer> list = createList();

        list.add(1);
        list.add(2);
        list.add(3);

        List<Integer> result = createList();
        list.forEach(result::add);

        assertEquals("[1, 2, 3]", result.toString());
    }

    @Test
    void toArray_ShouldReturnEmptyArray_WhenListIsEmpty() {
        assertArrayEquals(new Object[]{}, createList().toArray());
    }

    @Test
    void toArray_ShouldReturnElements_WhenListIsNotEmpty() {
        List<Integer> list = createList();

        list.add(1);
        list.add(2);

        assertArrayEquals(new Object[]{1, 2}, list.toArray());
    }

    @Test
    void clear_ShouldRemoveAllElements() {
        List<Integer> list = createList();

        list.add(1);
        list.clear();

        assertTrue(list.isEmpty());
    }

    @Test
    void toString_ShouldReturnEmptyRepresentation_WhenListIsEmpty() {
        assertEquals("[]", createList().toString());
    }

    @Test
    void toString_ShouldReturnFormattedString_WhenListHasElements() {
        List<Integer> list = createList();

        list.add(1);
        list.add(2);
        list.add(3);

        assertEquals("[1, 2, 3]", list.toString());
    }
}
