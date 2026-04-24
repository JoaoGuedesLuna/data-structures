package dev.guedes.datastructures.util.collection.internal.validation;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test class for {@link Validator}.
 *
 * @author João Guedes
 */
class ValidatorTest {
    @Test
    void constructor_ShouldThrowAssertionError() throws Exception {
        Constructor<Validator> constructor = Validator.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        InvocationTargetException ex = assertThrows(InvocationTargetException.class, constructor::newInstance);
        assertInstanceOf(AssertionError.class, ex.getCause());
    }

    @Test
    void validateIndex_ShouldThrowException_WhenIndexIsNegative() {
        assertThrows(IndexOutOfBoundsException.class, () -> Validator.validateIndex(-1, 5));
    }

    @Test
    void validateIndex_ShouldThrowException_WhenIndexEqualsSize() {
        assertThrows(IndexOutOfBoundsException.class, () -> Validator.validateIndex(5, 5));
    }

    @Test
    void validateIndex_ShouldThrowException_WhenIndexGreaterThanSize() {
        assertThrows(IndexOutOfBoundsException.class, () -> Validator.validateIndex(6, 5));
    }

    @Test
    void validateIndex_ShouldNotThrowException_WhenIndexIsValid() {
        assertDoesNotThrow(() -> Validator.validateIndex(0, 5));
        assertDoesNotThrow(() -> Validator.validateIndex(4, 5));
    }

    @Test
    void validateIndexForAdd_ShouldThrowException_WhenIndexIsNegative() {
        assertThrows(IndexOutOfBoundsException.class, () -> Validator.validateIndexForAdd(-1, 5));
    }

    @Test
    void validateIndexForAdd_ShouldThrowException_WhenIndexGreaterThanSize() {
        assertThrows(IndexOutOfBoundsException.class, () -> Validator.validateIndexForAdd(6, 5));
    }

    @Test
    void validateIndexForAdd_ShouldNotThrowException_WhenIndexEqualsSize() {
        assertDoesNotThrow(() -> Validator.validateIndexForAdd(5, 5));
    }

    @Test
    void validateIndexForAdd_ShouldNotThrowException_WhenIndexIsValid() {
        assertDoesNotThrow(() -> Validator.validateIndexForAdd(0, 5));
        assertDoesNotThrow(() -> Validator.validateIndexForAdd(3, 5));
    }

    @Test
    void validateVerticesNotNull_ShouldThrowException_WhenSourceIsNull() {
        assertThrows(NullPointerException.class, () -> Validator.validateVerticesNotNull(null, "B"));
    }

    @Test
    void validateVerticesNotNull_ShouldThrowException_WhenDestinationIsNull() {
        assertThrows(NullPointerException.class, () -> Validator.validateVerticesNotNull("A", null));
    }

    @Test
    void validateVerticesNotNull_ShouldThrowException_WhenBothAreNull() {
        assertThrows(NullPointerException.class, () -> Validator.validateVerticesNotNull(null, null));
    }

    @Test
    void validateVerticesNotNull_ShouldNotThrowException_WhenBothAreValid() {
        assertDoesNotThrow(() -> Validator.validateVerticesNotNull("A", "B"));
    }

    @Test
    void validateVertexNotNull_ShouldThrowException_WhenVertexIsNull() {
        assertThrows(NullPointerException.class, () -> Validator.validateVertexNotNull(null));
    }

    @Test
    void validateVertexNotNull_ShouldNotThrowException_WhenVertexIsValid() {
        assertDoesNotThrow(() -> Validator.validateVertexNotNull("A"));
    }

    @Test
    void validatePositiveWeight_ShouldThrowException_WhenWeightIsZero() {
        assertThrows(IllegalArgumentException.class, () -> Validator.validatePositiveWeight(0));
    }

    @Test
    void validatePositiveWeight_ShouldThrowException_WhenWeightIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> Validator.validatePositiveWeight(-1));
    }

    @Test
    void validatePositiveWeight_ShouldNotThrowException_WhenWeightIsPositive() {
        assertDoesNotThrow(() -> Validator.validatePositiveWeight(1));
        assertDoesNotThrow(() -> Validator.validatePositiveWeight(100));
    }
}
