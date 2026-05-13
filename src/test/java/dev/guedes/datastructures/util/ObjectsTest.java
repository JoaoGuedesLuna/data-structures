package dev.guedes.datastructures.util;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for {@link Objects}.
 *
 * @author João Guedes
 */
class ObjectsTest {
    @Test
    void constructor_ShouldThrowAssertionError() throws Exception {
        Constructor<Objects> constructor = Objects.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        InvocationTargetException ex = assertThrows(InvocationTargetException.class, constructor::newInstance);
        assertInstanceOf(AssertionError.class, ex.getCause());
    }

    @Test
    void equals_ShouldReturnTrue_WhenBothAreNull() {
        assertTrue(Objects.equals(null, null));
    }

    @Test
    void equals_ShouldReturnTrue_WhenSameReference() {
        String value = "test";
        assertTrue(Objects.equals(value, value));
    }

    @Test
    void equals_ShouldReturnTrue_WhenDifferentObjectsButEqual() {
        String a = new String("test");
        String b = new String("test");

        assertTrue(Objects.equals(a, b));
    }

    @Test
    void equals_ShouldReturnFalse_WhenOneIsNull() {
        assertFalse(Objects.equals("test", null));
        assertFalse(Objects.equals(null, "test"));
    }

    @Test
    void equals_ShouldReturnFalse_WhenObjectsAreDifferent() {
        assertFalse(Objects.equals("test", "tset"));
    }

    @Test
    void compare_ShouldReturnZero_WhenBothAreNull() {
        assertEquals(0, Objects.compare(null, null));
    }

    @Test
    void compare_ShouldReturnNegative_WhenFirstIsNull() {
        assertEquals(-1, Objects.compare(null, 1));
    }

    @Test
    void compare_ShouldReturnPositive_WhenSecondIsNull() {
        assertEquals(1, Objects.compare(1, null));
    }

    @Test
    void compare_ShouldReturnZero_WhenValuesAreEqual() {
        assertEquals(0, Objects.compare(10, 10));
    }

    @Test
    void compare_ShouldReturnNegative_WhenFirstIsLessThanSecond() {
        assertTrue(Objects.compare(1, 10) < 0);
    }

    @Test
    void compare_ShouldReturnPositive_WhenFirstIsGreaterThanSecond() {
        assertTrue(Objects.compare(10, 1) > 0);
    }

    @Test
    void requireNonNull_ShouldThrowExceptionWithNullMessage_WhenObjectIsNullAndMessageIsNull() {
        NullPointerException exception = assertThrows(
                NullPointerException.class,
                () -> Objects.requireNonNull(null, null)
        );

        assertNull(exception.getMessage());
    }

    @Test
    void requireNonNull_ShouldThrowExceptionWithEmptyMessage_WhenObjectIsNullAndMessageIsEmpty() {
        NullPointerException exception = assertThrows(
                NullPointerException.class,
                () -> Objects.requireNonNull(null, "")
        );

        assertEquals("", exception.getMessage());
    }

    @Test
    void requireNonNull_ShouldThrowExceptionWithCustomMessage_WhenObjectIsNull() {
        String message = "Object must not be null.";

        NullPointerException exception = assertThrows(
                NullPointerException.class,
                () -> Objects.requireNonNull(null, message)
        );

        assertEquals(message, exception.getMessage());
    }

    @Test
    void requireNonNull_ShouldNotThrow_WhenObjectIsNotNull_WithMessage() {
        assertDoesNotThrow(() -> Objects.requireNonNull("Non-null", "Object must not be null."));
    }

    @Test
    void requireNonNull_ShouldThrowExceptionWithoutMessage_WhenObjectIsNull() {
        NullPointerException exception = assertThrows(
                NullPointerException.class,
                () -> Objects.requireNonNull(null)
        );

        assertNull(exception.getMessage());
    }

    @Test
    void requireNonNull_ShouldNotThrow_WhenObjectIsNotNull() {
        assertDoesNotThrow(() -> Objects.requireNonNull("Non-null"));
    }
}
