package dev.guedes.datastructures.util;

/**
 * Utility class that provides commonly used object operations, including null-safe equality and comparison checks.
 *
 * @author João Guedes
 */
public final class Objects {
    private Objects() {
        throw new AssertionError(Objects.class.getSimpleName() +  " is a utility class and cannot be instantiated.");
    }

    /**
     * Performs a null-safe equality check between two objects.
     *
     * @param a the first object to compare
     * @param b the second object to compare
     * @return {@code true} if both are equal or both are {@code null}, otherwise {@code false}
     */
    public static boolean equals(Object a, Object b) { return (a == b) || (a != null && a.equals(b)); }

    /**
     * Compares two {@link Comparable} objects, handling {@code null} values.
     * <ul>
     *   <li>If both are {@code null}, returns 0.</li>
     *   <li>If only {@code a} is {@code null}, returns -1.</li>
     *   <li>If only {@code b} is {@code null}, returns 1.</li>
     *   <li>Otherwise, delegates to {@code a.compareTo(b)}.</li>
     * </ul>
     *
     * @param <T> the type of objects being compared
     * @param a the first comparable object
     * @param b the second comparable object
     * @return a negative integer, zero, or a positive integer as {@code a} is less than, equal to, or greater than {@code b}
     */
    public static <T extends Comparable<T>> int compare(T a, T b) {
        if (a == null && b == null) return 0;
        if (a == null) return -1;
        if (b == null) return 1;
        return a.compareTo(b);
    }

    /**
     * Checks that the specified object reference is not {@code null}.
     *
     * @param obj the object reference to check
     * @param message the detail message to be used in the event that a {@link NullPointerException} is thrown
     * @throws NullPointerException if {@code obj} is {@code null}
     */
    public static void requireNonNull(Object obj, String message) throws NullPointerException {
        if (obj == null) throw new NullPointerException(message);
    }

    /**
     * Checks that the specified object reference is not {@code null}.
     *
     * @param obj the object reference to check
     * @throws NullPointerException if {@code obj} is {@code null}
     */
    public static void requireNonNull(Object obj) throws NullPointerException {
        if (obj == null) throw new NullPointerException();
    }
}
