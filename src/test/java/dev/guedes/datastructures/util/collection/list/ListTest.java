package dev.guedes.datastructures.util.collection.list;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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
    void remove_ShouldReturnFalse_WhenListIsEmpty() {
        List<Integer> list = createList();

        assertFalse(list.remove(Integer.valueOf(1)));
    }

    @Test
    void remove_ShouldReturnFalse_WhenElementDoesNotExist() {
        List<Integer> list = createList();

        list.add(1);

        assertFalse(list.remove(Integer.valueOf(2)));
    }

    @Test
    void remove_ShouldRemoveElement_WhenListHasSingleElement() {
        List<Integer> list = createList();

        list.add(1);

        boolean removed = list.remove(Integer.valueOf(1));

        assertTrue(removed);
        assertTrue(list.isEmpty());
        assertEquals("[]", list.toString());
    }

    @ParameterizedTest()
    @CsvSource({"1, '[2, 3]'", "2, '[1, 3]'", "3, '[1, 2]'"})
    void remove_ShouldRemoveElement_WhenElementExists(int element, String expectedList) {
        List<Integer> list = createList();

        list.add(1);
        list.add(2);
        list.add(3);

        boolean removed = list.remove(Integer.valueOf(element));

        assertTrue(removed);
        assertEquals(expectedList, list.toString());
    }

    @Test
    void remove_ShouldRemoveAllElements_FromEndUntilListIsEmpty() {
        List<Integer> list = createList();

        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        for (int i = 9; i >= 0; i--) {
            int removed = list.remove(list.size() - 1);
            assertEquals(i, removed);
        }

        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
        assertEquals("[]", list.toString());
    }

    @Test
    void removeByIndex_ShouldThrowException_WhenIndexIsNegative() {
        assertThrows(IndexOutOfBoundsException.class, () -> createList().remove(-1));
    }

    @Test
    void removeByIndex_ShouldThrowException_WhenIndexIsOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> createList().remove(1));
    }

    @ParameterizedTest()
    @CsvSource({"0, 1, '[2, 3]'", "1, 2, '[1, 3]'", "2, 3, '[1, 2]'"})
    void removeByIndex_ShouldRemoveCorrectElement(int index, int expectedRemoved, String expectedList) {
        List<Integer> list = createList();

        list.add(1);
        list.add(2);
        list.add(3);

        int removed = list.remove(index);

        assertEquals(expectedRemoved, removed);
        assertEquals(expectedList, list.toString());
    }

    @Test
    void get_ShouldThrowException_WhenIndexIsNegative() {
        assertThrows(IndexOutOfBoundsException.class, () -> createList().get(-1));
    }

    @Test
    void get_ShouldThrowException_WhenIndexIsOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> createList().get(1));
    }

    @ParameterizedTest()
    @CsvSource({"0, 1", "1, 2", "2, 3"})
    void get_ShouldReturnCorrectElement(int index, int expectedValue) {
        List<Integer> list = createList();

        list.add(1);
        list.add(2);
        list.add(3);

        assertEquals(expectedValue, list.get(index));
    }

    @Test
    void get_ShouldTraverseFromEnd_WhenIndexIsInSecondHalf() {
        List<Integer> list = createList();

        for (int i = 0; i < 6; i++) list.add(i);

        assertEquals(4, list.get(4));
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
    void set_ShouldReplaceFirstElement_WhenIndexIsZero() {
        List<Integer> list = createList();

        list.add(0, 1);
        list.add(2);
        list.add(3);

        int old = list.set(0, 10);

        assertEquals(1, old);
        assertEquals("[10, 2, 3]", list.toString());
    }

    @Test
    void set_ShouldReplaceMiddleElement_WhenIndexIsMiddle() {
        List<Integer> list = createList();

        list.add(1);
        list.add(2);
        list.add(3);

        int old = list.set(1, 20);

        assertEquals(2, old);
        assertEquals("[1, 20, 3]", list.toString());
    }

    @Test
    void set_ShouldReplaceLastElement_WhenIndexIsLast() {
        List<Integer> list = createList();

        list.add(1);
        list.add(2);
        list.add(3);

        int old = list.set(2, 30);

        assertEquals(3, old);
        assertEquals("[1, 2, 30]", list.toString());
    }

    @ParameterizedTest
    @CsvSource({"1, 0", "2, 1", "3, 2"})
    void indexOf_ShouldReturnCorrectIndex(int element, int expectedIndex) {
        List<Integer> list = createList();

        list.add(1);
        list.add(2);
        list.add(3);

        assertEquals(expectedIndex, list.indexOf(element));
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

    @ParameterizedTest
    @CsvSource({"1, 0","2, 1", "3, 2"})
    void lastIndexOf_ShouldReturnCorrectIndex(int element, int expectedIndex) {
        List<Integer> list = createList();

        list.add(1);
        list.add(2);
        list.add(3);

        assertEquals(expectedIndex, list.lastIndexOf(element));
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
