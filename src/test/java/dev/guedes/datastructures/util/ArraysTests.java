package dev.guedes.datastructures.util;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test class for {@link Arrays}.
 *
 * @author João Guedes
 */
class ArraysTests {
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
}
