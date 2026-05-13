package dev.guedes.datastructures.util;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for {@link Arrays}.
 *
 * @author João Guedes
 */
class ArraysTest {
    @Test
    void constructor_ShouldThrowAssertionError() throws Exception {
        Constructor<Arrays> constructor = Arrays.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        InvocationTargetException ex = assertThrows(InvocationTargetException.class, constructor::newInstance);
        assertInstanceOf(AssertionError.class, ex.getCause());
    }

    @Test
    void sequentialSearch_ShouldThrowException_WhenArrayIsNull() {
        assertThrows(NullPointerException.class, () -> Arrays.sequentialSearch(null, 5));
    }

    @Test
    void sequentialSearch_ShouldReturnMinusOne_WhenArrayIsEmpty() {
        assertEquals(-1, Arrays.sequentialSearch(new Integer[]{}, 5));
    }

    @Test
    void sequentialSearch_ShouldReturnCorrectIndex_WhenElementExists() {
        Integer[] array = {null, 1, 2, 3, 4, 5};

        assertEquals(0, Arrays.sequentialSearch(array, null));
        assertEquals(5, Arrays.sequentialSearch(array, 5));
    }

    @Test
    void sequentialSearch_ShouldReturnFirstOccurrence_WhenDuplicatesExist() {
        Integer[] array = {1, 2, 5, 5, 5};
        assertEquals(2, Arrays.sequentialSearch(array, 5));
    }

    @Test
    void sequentialSearch_ShouldReturnMinusOne_WhenElementDoesNotExist() {
        Integer[] array = {1, 2, 3};
        assertEquals(-1, Arrays.sequentialSearch(array, 5));
    }

    @Test
    void binarySearch_ShouldThrowException_WhenArrayIsNull() {
        assertThrows(NullPointerException.class, () -> Arrays.binarySearch(null, 5));
    }

    @Test
    void binarySearch_ShouldReturnMinusOne_WhenArrayIsEmpty() {
        assertEquals(-1, Arrays.binarySearch(new Integer[]{}, 5));
    }

    @Test
    void binarySearch_ShouldReturnCorrectIndex_WhenElementExists() {
        Integer[] array = {null, 1, 2, 3, 4, 5};

        assertEquals(0, Arrays.binarySearch(array, null));
        assertEquals(5, Arrays.binarySearch(array, 5));
    }

    @Test
    void binarySearch_ShouldReturnMinusOne_WhenArrayIsNotSorted() {
        Integer[] array = {5, 4, 3, 2, 1, null};
        assertEquals(-1, Arrays.binarySearch(array, 5));
    }

    @Test
    void binarySearch_ShouldReturnMinusOne_WhenElementDoesNotExist() {
        Integer[] array = {1, 2, 3};
        assertEquals(-1, Arrays.binarySearch(array, 5));
    }

    @Test
    void selectionSort_ShouldThrowException_WhenArrayIsNull() {
        assertThrows(NullPointerException.class, () -> Arrays.selectionSort(null));
    }

    @Test
    void selectionSort_ShouldSortArrayCorrectly_WhenArrayIsUnsorted() {
        Integer[] array = {5, 3, 1, 4, 2};

        Arrays.selectionSort(array);

        assertSorted(array);
    }

    @Test
    void bubbleSort_ShouldThrowException_WhenArrayIsNull() {
        assertThrows(NullPointerException.class, () -> Arrays.bubbleSort(null));
    }

    @Test
    void bubbleSort_ShouldSortArrayCorrectly_WhenArrayIsUnsorted() {
        Integer[] array = {5, 3, 1, 4, 2};

        Arrays.bubbleSort(array);

        assertSorted(array);
    }

    @Test
    void insertionSort_ShouldThrowException_WhenArrayIsNull() {
        assertThrows(NullPointerException.class, () -> Arrays.insertionSort(null));
    }

    @Test
    void insertionSort_ShouldSortArrayCorrectly_WhenArrayIsUnsorted() {
        Integer[] array = {5, 3, 1, 4, 2};

        Arrays.insertionSort(array);

        assertSorted(array);
    }

    @Test
    void mergeSort_ShouldThrowException_WhenArrayIsNull() {
        assertThrows(NullPointerException.class, () -> Arrays.mergeSort(null));
    }

    @Test
    void mergeSort_ShouldSortArrayCorrectly_WhenArrayIsUnsorted() {
        Integer[] array = {5, 3, 1, 4, 2};

        Arrays.mergeSort(array);

        assertSorted(array);
    }

    @Test
    void quickSort_ShouldThrowException_WhenArrayIsNull() {
        assertThrows(NullPointerException.class, () -> Arrays.quickSort(null));
    }

    @Test
    void quickSort_ShouldSortArrayCorrectly_WhenArrayIsUnsorted() {
        Integer[] array = {5, 3, 1, 4, 2};

        Arrays.quickSort(array);

        assertSorted(array);
    }

    @Test
    void copyOfRange_ShouldThrowException_WhenArrayIsNull() {
        assertThrows(NullPointerException.class, () -> Arrays.copyOfRange(null, 0, 3));
    }

    @Test
    void copyOfRange_ShouldThrowException_WhenFromGreaterThanTo() {
        Integer[] array = {1,2,3};

        assertThrows(IllegalArgumentException.class, () -> Arrays.copyOfRange(array, 4, 2));
    }

    @Test
    void copyOfRange_ShouldThrowException_WhenFromIsNegative() {
        Integer[] array = {1,2,3};

        assertThrows(IndexOutOfBoundsException.class, () -> Arrays.copyOfRange(array, -1, 2));
    }

    @Test
    void copyOfRange_ShouldCopyCorrectly() {
        Integer[] array = {1,2,3,4,5};

        assertArrayEquals(new Integer[]{}, Arrays.copyOfRange(array, 0, 0));
        assertArrayEquals(new Integer[]{1}, Arrays.copyOfRange(array, 0, 1));
        assertArrayEquals(new Integer[]{3}, Arrays.copyOfRange(array, 2, 3));
        assertArrayEquals(new Integer[]{5}, Arrays.copyOfRange(array, 4, 5));
        assertArrayEquals(new Integer[]{2,3,4}, Arrays.copyOfRange(array, 1, 4));
    }

    @Test
    void copyOf_ShouldThrowException_WhenLengthIsNegative() {
        Integer[] original = {1, 2, 3, 4, 5};

        assertThrows(IllegalArgumentException.class, () -> Arrays.copyOf(original, -1));
    }

    @Test
    void copyOf_ShouldThrowException_WhenArrayIsNull() {
        assertThrows(NullPointerException.class, () -> Arrays.copyOf(null, 3));
    }

    @Test
    void copyOf_ShouldReturnEmptyArray_WhenLengthIsZero() {
        Integer[] original = {1, 2, 3, 4, 5};

        Integer[] result = Arrays.copyOf(original, 0);

        assertArrayEquals(new Integer[]{}, result);
    }

    @Test
    void copyOf_ShouldCopyPartialArray_WhenNewLengthIsSmaller() {
        Integer[] original = {1, 2, 3, 4, 5};

        Integer[] result = Arrays.copyOf(original, 3);

        assertArrayEquals(new Integer[]{1, 2, 3}, result);
    }

    @Test
    void copyOf_ShouldCopyEntireArray_WhenSameLength() {
        Integer[] original = {1, 2, 3, 4, 5};

        Integer[] result = Arrays.copyOf(original, original.length);

        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, result);
    }

    @Test
    void copyOf_ShouldFillWithNull_WhenNewLengthIsGreater() {
        Integer[] original = {1, 2, 3, 4, 5};

        Integer[] result = Arrays.copyOf(original, original.length + 1);

        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5, null}, result);
    }

    @Test
    void copyOf_ShouldReturnNewInstance_NotSameReference() {
        Integer[] original = {1, 2, 3};

        Integer[] result = Arrays.copyOf(original, 3);

        assertNotSame(original, result);
    }

    @Test
    void arraycopy_ShouldThrowException_WhenSourceIsNull() {
        assertThrows(NullPointerException.class, () -> Arrays.arraycopy(null, 0, new Integer[5], 0, 3));
    }

    @Test
    void arraycopy_ShouldThrowException_WhenDestinationIsNull() {
        assertThrows(NullPointerException.class, () -> Arrays.arraycopy(new Integer[5], 0, null, 0, 3));
    }

    @Test
    void arraycopy_ShouldThrowException_WhenSourceIndexIsNegative() {
        Integer[] src = {1,2,3};
        Integer[] dst = new Integer[3];

        assertThrows(IndexOutOfBoundsException.class, () -> Arrays.arraycopy(src, -1, dst, 0, 3));
    }

    @Test
    void arraycopy_ShouldThrowException_WhenSourceIndexOutOfRange() {
        Integer[] src = {1,2,3};
        Integer[] dst = new Integer[3];

        assertThrows(IndexOutOfBoundsException.class, () -> Arrays.arraycopy(src, src.length, dst, 0, 3));
    }

    @Test
    void arraycopy_ShouldThrowException_WhenDestinationIndexIsNegative() {
        Integer[] src = {1,2,3};
        Integer[] dst = new Integer[3];

        assertThrows(IndexOutOfBoundsException.class, () -> Arrays.arraycopy(src, 0, dst, -1, 3));
    }

    @Test
    void arraycopy_ShouldThrowException_WhenDestinationIndexOutOfRange() {
        Integer[] src = {1,2,3};
        Integer[] dst = new Integer[3];

        assertThrows(IndexOutOfBoundsException.class, () -> Arrays.arraycopy(src, 0, dst, dst.length, 2));
    }

    @Test
    void arraycopy_ShouldThrowException_WhenLengthIsNegative() {
        Integer[] src = {1,2,3};
        Integer[] dst = new Integer[3];

        assertThrows(IllegalArgumentException.class, () -> Arrays.arraycopy(src, 0, dst, 0, -1));
    }

    @Test
    void arraycopy_ShouldThrowException_WhenSourceRangeExceedsArrayLength() {
        Integer[] src = {1, 2, 3};
        Integer[] dst = new Integer[5];

        assertThrows(IllegalArgumentException.class, () -> Arrays.arraycopy(src, 1, dst, 0, 3));
    }

    @Test
    void arraycopy_ShouldThrowException_WhenDestinationRangeExceedsArrayLength() {
        Integer[] src = {1, 2, 3};
        Integer[] dst = new Integer[3];

        assertThrows(IllegalArgumentException.class, () -> Arrays.arraycopy(src, 0, dst, 1, 3));
    }

    @Test
    void arraycopy_ShouldCopyCorrectly() {
        Integer[] src = {1,2,3,4,5};
        Integer[] dst = new Integer[5];

        Arrays.arraycopy(src, 0, dst, 0, 5);

        assertArrayEquals(new Integer[]{1,2,3,4,5}, dst);
    }

    @Test
    void arraycopy_ShouldHandleSameArray() {
        Integer[] array = {1,2,3,4,5};

        Arrays.arraycopy(array, 0, array, 2, 3);

        assertArrayEquals(new Integer[]{1,2,1,2,3}, array);
    }

    @Test
    void fill_ShouldThrowException_WhenArrayIsNull() {
        assertThrows(NullPointerException.class, () -> Arrays.fill(null, 1));
    }

    @Test
    void fill_ShouldFillArrayCorrectly() {
        Integer[] array = new Integer[3];

        Arrays.fill(array, 1);

        assertArrayEquals(new Integer[]{1,1,1}, array);
    }

    @Test
    void isEmpty_ShouldThrowException_WhenArrayIsNull() {
        assertThrows(NullPointerException.class, () -> Arrays.isEmpty(null));
    }

    @Test
    void isEmpty_ShouldReturnTrue_WhenEmpty() {
        assertTrue(Arrays.isEmpty(new Integer[]{}));
    }

    @Test
    void isEmpty_ShouldReturnFalse_WhenNotEmpty() {
        assertFalse(Arrays.isEmpty(new Integer[]{1}));
    }

    @Test
    void toString_ShouldThrowException_WhenArrayIsNull() {
        assertThrows(NullPointerException.class, () -> Arrays.toString(null));
    }

    @Test
    void toString_ShouldReturnCorrectFormat() {
        assertEquals("[]", Arrays.toString(new Integer[]{}));
        assertEquals("[1]", Arrays.toString(new Integer[]{1}));
        assertEquals("[1, 2, 3]", Arrays.toString(new Integer[]{1,2,3}));
    }

    private <T extends Comparable<T>> void assertSorted(T[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            assertTrue(array[i].compareTo(array[i + 1]) <= 0);
        }
    }
}
