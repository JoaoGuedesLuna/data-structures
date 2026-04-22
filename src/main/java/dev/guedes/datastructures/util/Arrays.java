package dev.guedes.datastructures.util;

/**
 * Utility class providing generic methods for array manipulation and sorting.
 * <p>
 * This class includes implementations of search algorithms (sequential and binary),
 * sorting algorithms (selection, bubble, insertion, merge, and quick sort), as well as
 * utility methods like copying arrays, filling, checking emptiness, and generating
 * string representations of arrays.
 * <p>
 *
 * @author João Guedes
 */
public final class Arrays {
    private Arrays() {
        throw new AssertionError(Arrays.class.getSimpleName() +  " is a utility class and cannot be instantiated.");
    }

    /**
     * Performs a sequential (linear) search on the given array.
     * <p>
     * This method iterates over the array from the start to the end, comparing
     * each element with the target element.
     * If the element is found, its index is returned immediately.
     * Otherwise, returns -1 to indicate the element was not found.
     *
     * @param <T> the type of elements in the array
     * @param array the array to search (must not be null)
     * @param element the element to find
     * @return the index of the element if found; -1 otherwise
     * @throws NullPointerException if the array is null
     */
    public static <T> int sequentialSearch(T[] array, T element) {
        Objects.requireNonNull(array, "Array must not be null.");

        for (int i = 0; i < array.length; i++) {
            if (Objects.equals(element, array[i])) return i;
        }

        return -1;
    }

    /**
     * Performs a binary search on a sorted array.
     * <p>
     * This method assumes the array is sorted according to the natural ordering
     * of its elements (i.e., elements implement {@link Comparable}).
     * The search algorithm repeatedly divides the search interval in half:
     * <ul>
     *   <li>Start with the whole array.</li>
     *   <li>Compare the middle element with the target element.</li>
     *   <li>If equal, return the index.</li>
     *   <li>If the middle element is less than the target, search the right half.</li>
     *   <li>If greater, search the left half.</li>
     * </ul>
     * If the element is not found, returns -1.
     *
     * @param <T> the type of elements in the array (must be comparable)
     * @param array the sorted array to search (must not be null)
     * @param element the element to find
     * @return the index of the element if found; -1 otherwise
     * @throws NullPointerException if the array is null
     */
    public static <T extends Comparable<T>> int binarySearch(T[] array, T element) {
        Objects.requireNonNull(array, "Array must not be null.");

        int beginning = 0;
        int middle;
        int end = array.length - 1;
        int comparisonResult;

        while (beginning <= end) {
            middle = (end + beginning) / 2;
            comparisonResult = Objects.compare(array[middle], element);

            if(comparisonResult == 0) return middle;

            if (comparisonResult < 0) {
                beginning = middle + 1;
            } else {
                end = middle - 1;
            }
        }

        return -1;
    }
}
