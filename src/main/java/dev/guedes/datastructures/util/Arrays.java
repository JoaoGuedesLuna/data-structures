package dev.guedes.datastructures.util;

import java.lang.reflect.Array;

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

    /**
     * Sorts the array using the Selection Sort algorithm.
     * <p>
     * Selection sort divides the array into a sorted and unsorted region.
     * It repeatedly selects the smallest (or largest, depending on sorting order)
     * element from the unsorted region and swaps it with the first unsorted element,
     * effectively growing the sorted region by one element each iteration.
     * <p>
     * Time complexity: O(n²) in all cases.
     * Space complexity: O(1) (in-place).
     *
     * @param <T> the type of elements in the array (must be comparable)
     * @param array the array to sort (must not be null)
     * @throws NullPointerException if the array is null
     */
    public static <T extends Comparable<T>> void selectionSort(T[] array) {
        Objects.requireNonNull(array, "Array must not be null.");

        for (int i = 0; i < array.length - 1; i++){
            for (int j = i + 1; j < array.length; j++) {
                if (Objects.compare(array[i], array[j]) > 0) swap(array, i, j);
            }
        }
    }

    /**
     * Sorts the array using the Bubble Sort algorithm.
     * <p>
     * Bubble sort repeatedly steps through the array, compares adjacent elements,
     * and swaps them if they are in the wrong order.
     * This process "bubbles" the largest element to the end of the array on each pass.
     * The algorithm continues until a complete pass results in no swaps, indicating
     * the array is sorted.
     * <p>
     * Time complexity: O(n²) worst and average case.
     * Space complexity: O(1) (in-place).
     *
     * @param <T> the type of elements in the array (must be comparable)
     * @param array the array to sort (must not be null)
     * @throws NullPointerException if the array is null
     */
    public static <T extends Comparable<T>> void bubbleSort(T[] array) {
        Objects.requireNonNull(array, "Array must not be null.");

        boolean swappedSomething;

        do {
            swappedSomething = false;
            for (int i = 0; i < array.length - 1; i++) {
                if (Objects.compare(array[i], array[i + 1]) > 0) {
                    swap(array, i, i + 1);
                    swappedSomething = true;
                }
            }
        } while(swappedSomething);
    }

    /**
     * Sorts the array using the Insertion Sort algorithm.
     * <p>
     * Insertion sort builds the sorted array one element at a time by repeatedly
     * removing an element from the input and inserting it into the correct position
     * within the already sorted part.
     * <p>
     * Time complexity: O(n²) worst case, O(n) best case when the array is already sorted.
     * Space complexity: O(1) (in-place).
     *
     * @param <T> the type of elements in the array (must be comparable)
     * @param array the array to sort (must not be null)
     * @throws NullPointerException if the array is null
     */
    public static <T extends Comparable<T>> void insertionSort(T[] array) {
        Objects.requireNonNull(array, "Array must not be null.");

        int j;
        T currentValue;

        for (int i = 1; i < array.length; i++){
            currentValue = array[i];
            j = i - 1;
            while ((j >= 0) && (Objects.compare(currentValue, array[j]) < 0)) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = currentValue;
        }
    }

    /**
     * Sorts the array using the Merge Sort algorithm.
     * <p>
     * Merge sort is a divide-and-conquer algorithm that:
     * <ul>
     *   <li>Divides the array into two halves,</li>
     *   <li>Recursively sorts each half, and</li>
     *   <li>Merges the two sorted halves back into a sorted whole.</li>
     * </ul>
     * It guarantees O(n log n) time complexity in all cases.
     * <p>
     * Note: This implementation uses additional space proportional to the array size.
     *
     * @param <T> the type of elements in the array (must be comparable)
     * @param array the array to sort (must not be null)
     * @throws NullPointerException if the array is null
     */
    public static <T extends Comparable<T>> void mergeSort(T[] array) {
        Objects.requireNonNull(array, "Array must not be null.");

        if (array.length < 2) return;

        int middleIndex = array.length / 2;
        T[] leftHalf = copyOfRange(array, 0, middleIndex);
        T[] rightHalf = copyOfRange(array, middleIndex, array.length);

        mergeSort(leftHalf);
        mergeSort(rightHalf);

        merge(array, leftHalf, rightHalf);
    }

    /**
     * Sorts the array using the Quick Sort algorithm.
     * <p>
     * Quick sort is a divide-and-conquer algorithm that selects a "pivot" element
     * and partitions the array so that elements less than the pivot come before it,
     * and elements greater come after it. The process is recursively applied to the
     * partitions.
     * <p>
     * Average time complexity: O(n log n), worst case O(n²).
     * Space complexity: O(log n) due to recursion stack.
     *
     * @param <T> the type of elements in the array (must be comparable)
     * @param array the array to sort (must not be null)
     * @throws NullPointerException if the array is null
     */
    public static <T extends Comparable<T>> void quickSort(T[] array) {
        Objects.requireNonNull(array, "Array must not be null.");
        quickSort(array, 0, array.length - 1);
    }

    /**
     * Creates a new array by copying a range of the original array.
     * <p>
     * The returned array contains elements from index {@code from} (inclusive) to
     * {@code to} (exclusive).
     * <p>
     * If {@code to - from} is greater than the length of the original array from {@code from},
     * the extra elements in the new array will be null.
     *
     * @param <T> the type of elements in the array
     * @param original the original array to copy from (must not be null)
     * @param from the initial index of the range to be copied, inclusive
     * @param to the final index of the range to be copied, exclusive
     * @return a new array containing the specified range from the original array
     * @throws NullPointerException if the original array is null
     * @throws IllegalArgumentException if from > to
     */
    public static <T> T[] copyOfRange(T[] original, int from, int to) {
        Objects.requireNonNull(original, "Array must not be null.");

        if (from > to) {
            throw new IllegalArgumentException("From index must not be greater than to index.");
        }

        int length = to - from;
        T[] copy = (T[]) Array.newInstance(original.getClass().getComponentType(), length);

        arraycopy(original, from, copy, 0, Math.min(original.length - from, length));

        return copy;
    }

    /**
     * Creates a new array by copying the original array up to the specified new length.
     * <p>
     * If the new length is greater than the original array's length, the extra elements
     * will be null.
     *
     * @param <T> the type of elements in the array
     * @param original the original array to copy from
     * @param newLength the length of the new array
     * @return a new array containing the elements copied from the original array
     * @throws IllegalArgumentException if newLength is negative
     */
    public static <T> T[] copyOf(T[] original, int newLength) {
        if (newLength < 0) {
            throw new IllegalArgumentException("Length must be non-negative: " + newLength + ".");
        }

        return copyOfRange(original, 0, newLength);
    }

    /**
     * Copies elements from source array to destination array.
     * <p>
     * Handles the case when source and destination are the same array by
     * making a temporary copy to prevent overwriting during copying.
     *
     * @param <T> the type of elements in the arrays
     * @param src the source array (must not be null)
     * @param srcPos starting position in the source array
     * @param dst the destination array (must not be null)
     * @param dstPos starting position in the destination array
     * @param length the number of elements to copy
     * @throws NullPointerException if src or dst is null
     * @throws IndexOutOfBoundsException if srcPos or dstPos is out of range
     * @throws IllegalArgumentException if length is negative or copying range exceeds array bounds
     */
    public static <T> void arraycopy(T[] src, int srcPos, T[] dst, int dstPos, int length) {
        Objects.requireNonNull(src, "Source array must not be null.");
        Objects.requireNonNull(dst, "Destination array must not be null.");

        if (srcPos < 0 || (srcPos != 0 && srcPos >= src.length)) {
            throw new IndexOutOfBoundsException("Source index out of range: " + srcPos + ".");
        }

        if (dstPos < 0 || (dstPos != 0 && dstPos >= dst.length)) {
            throw new IndexOutOfBoundsException("Destination index out of range: " + dstPos + ".");
        }

        if (length < 0) {
            throw new IllegalArgumentException("Length must be non-negative: " + length + ".");
        }

        if ((srcPos + length) > src.length) {
            throw new IllegalArgumentException("Source range out of bounds: srcPos=" + srcPos + ", length=" + length + ".");
        }

        if ((dstPos + length) > dst.length) {
            throw new IllegalArgumentException("Destination range out of bounds: dstPos=" + dstPos + ", length=" + length + ".");
        }

        T[] sourceArray = (src == dst) ? copyOfRange(src, 0, src.length) : src;

        for (int i = 0; i < length; i++) {
            dst[dstPos + i] = sourceArray[srcPos + i];
        }
    }

    /**
     * Fills all elements of the array with the specified value.
     *
     * @param <T> the type of elements in the array
     * @param array the array to fill (must not be null)
     * @param value the value to fill the array with
     * @throws NullPointerException if the array is null
     */
    public static <T> void fill(T[] array, T value) {
        Objects.requireNonNull(array, "Array must not be null.");
        for (int i = 0; i < array.length; i++) array[i] = value;
    }

    /**
     * Checks if the given array is empty (i.e., has zero length).
     *
     * @param <T> the type of elements in the array
     * @param array the array to check (must not be null)
     * @return {@code true} if the array length is zero, {@code false} otherwise
     * @throws NullPointerException if the array is null
     */
    public static <T> boolean isEmpty(T[] array) {
        Objects.requireNonNull(array, "Array must not be null.");
        return array.length == 0;
    }

    /**
     * Returns a string representation of the array in the format "[elem1, elem2, ...]".
     * <p>
     * Returns "[]" if the array is empty.
     *
     * @param <T> the type of elements in the array
     * @param array the array to convert to string
     * @return a string representation of the array
     */
    public static <T> String toString(T[] array) {
        if (isEmpty(array)) return "[]";

        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < array.length - 1; i++) {
            sb.append(array[i]).append(", ");
        }
        sb.append(array[array.length - 1]).append("]");

        return sb.toString();
    }

    /**
     * Swaps two elements in an array.
     * <p>
     * This is a private helper method used internally by sorting algorithms.
     *
     * @param <T> the type of elements in the array
     * @param array the array where swapping occurs (must not be null)
     * @param index1 the first element index
     * @param index2 the second element index
     */
    private static <T> void swap(T[] array, int index1, int index2) {
        T temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    /**
     * Merges two sorted arrays into one sorted array.
     * <p>
     * This method takes two sorted subarrays (leftHalf and rightHalf) and merges
     * their elements into the main array in sorted order by comparing the elements
     * at the current indices of both subarrays.
     *
     * @param <T> the type of elements (must be comparable)
     * @param mainArray the array that will contain the merged result (must not be null)
     * @param leftHalf  the left sorted subarray
     * @param rightHalf the right sorted subarray
     */
    private static <T extends Comparable<T>> void merge(T[] mainArray, T[] leftHalf, T[] rightHalf) {
        int leftIndex = 0;
        int rightIndex = 0;

        for (int i = 0; i < mainArray.length; i++) {
            if (leftIndex >= leftHalf.length) {
                mainArray[i] = rightHalf[rightIndex++];
            }
            else if (rightIndex >= rightHalf.length) {
                mainArray[i] = leftHalf[leftIndex++];
            }
            else if (Objects.compare(leftHalf[leftIndex], rightHalf[rightIndex]) <= 0) {
                mainArray[i] = leftHalf[leftIndex++];
            }
            else {
                mainArray[i] = rightHalf[rightIndex++];
            }
        }
    }

    /**
     * Recursive helper method for Quick Sort.
     *
     * @param <T> the type of elements (must be comparable)
     * @param array the array to sort
     * @param lowIndex the starting index of the segment to sort
     * @param highIndex the ending index of the segment to sort
     */
    private static <T extends Comparable<T>> void quickSort(T[] array, int lowIndex, int highIndex) {
        if (lowIndex >= highIndex) return;

        T pivot = array[highIndex];
        int pivotIndex = partition(array, lowIndex, highIndex, pivot);

        quickSort(array, lowIndex, pivotIndex - 1);
        quickSort(array, pivotIndex + 1, highIndex);
    }

    /**
     * Partitions the array segment around the pivot element.
     * <p>
     * Elements less than or equal to the pivot are moved to the left,
     * elements greater than the pivot are moved to the right.
     * Returns the final index position of the pivot after partitioning.
     *
     * @param <T> the type of elements (must be comparable)
     * @param array the array to partition
     * @param lowIndex the starting index of the segment
     * @param highIndex the ending index of the segment (pivot position)
     * @param pivot the pivot element
     * @return the final index of the pivot element
     */
    private static <T extends Comparable<T>> int partition(T[] array, int lowIndex, int highIndex, T pivot) {
        int leftIndex = lowIndex;
        int rightIndex = highIndex;

        while (leftIndex < rightIndex) {
            while (Objects.compare(array[leftIndex], pivot) <= 0 && leftIndex < rightIndex) {
                leftIndex++;
            }
            while (Objects.compare(array[rightIndex], pivot) >= 0 && leftIndex < rightIndex) {
                rightIndex--;
            }
            swap(array, leftIndex, rightIndex);
        }
        swap(array, highIndex, leftIndex);

        return leftIndex;
    }
}
