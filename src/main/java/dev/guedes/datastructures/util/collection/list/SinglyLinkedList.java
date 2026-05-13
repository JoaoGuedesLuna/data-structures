package dev.guedes.datastructures.util.collection.list;

import dev.guedes.datastructures.util.Objects;
import dev.guedes.datastructures.util.collection.internal.nodes.SinglyLinkedNode;

import java.util.function.Consumer;

import static dev.guedes.datastructures.util.collection.internal.validation.Validator.validateIndex;
import static dev.guedes.datastructures.util.collection.internal.validation.Validator.validateIndexForAdd;

/**
 * A singly linked list implementation of the {@link List} interface.
 *
 * <p>Each element is stored in a node that holds a reference to the next node in the list.
 * This structure allows for efficient insertions and deletions at the beginning and middle
 * of the list, but requires linear traversal for indexing operations.
 *
 * @param <E> the type of elements held in this list
 *
 * @author João Guedes
 */
public class SinglyLinkedList<E> implements List<E> {
    protected SinglyLinkedNode<E> first;
    protected SinglyLinkedNode<E> last;
    protected int size;

    public SinglyLinkedList() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    @Override
    public int size() { return size; }

    @Override
    public boolean isEmpty() { return size == 0; }

    @Override
    public boolean add(E element) {
        linkLast(element);
        return true;
    }

    @Override
    public void add(int index, E element) {
        validateIndexForAdd(index, size);
        linkNode(index, element);
    }

    @Override
    public boolean remove(E element) {
        if (isEmpty()) return false;

        SinglyLinkedNode<E> current = first;

        if (Objects.equals(element, current.getElement())) {
            unlinkFirst();
            return true;
        }

        while (current.getNext() != null) {
            if (Objects.equals(element, current.getNext().getElement())) {
                unlinkNext(current);
                return true;
            }
            current = current.getNext();
        }

        return false;
    }

    @Override
    public E remove(int index) {
        validateIndex(index, size);
        return unlinkNode(index);
    }

    @Override
    public E get(int index) {
        validateIndex(index, size);
        return getNode(index).getElement();
    }

    @Override
    public E set(int index, E element) {
        validateIndex(index, size);

        SinglyLinkedNode<E> target = getNode(index);
        E oldElement = target.getElement();

        target.setElement(element);
        return oldElement;
    }

    @Override
    public int indexOf(E element) {
        SinglyLinkedNode<E> current = first;

        for (int i = 0; i < size; i++) {
            if (Objects.equals(element, current.getElement())) {
                return i;
            }
            current = current.getNext();
        }

        return -1;
    }

    @Override
    public int lastIndexOf(E element) {
        SinglyLinkedNode<E> current = first;
        int lastIndex = -1;

        for (int i = 0; i < size; i++) {
            if (Objects.equals(element, current.getElement())) {
                lastIndex = i;
            }
            current = current.getNext();
        }

        return lastIndex;
    }

    @Override
    public boolean contains(E element) { return indexOf(element) >= 0; }

    @Override
    public void forEach(Consumer<E> action) {
        SinglyLinkedNode<E> current = first;
        while (current != null) {
            action.accept(current.getElement());
            current = current.getNext();
        }
    }

    @Override
    public Object[] toArray() {
        SinglyLinkedNode<E> current = first;
        Object[] array = new Object[size];

        for (int i = 0; i < size; i++) {
            array[i] = current.getElement();
            current = current.getNext();
        }

        return array;
    }

    @Override
    public void clear() {
        for (SinglyLinkedNode<E> current = first, next; current != null; current = next) {
            next = current.getNext();
            current.clear();
        }
        first = last = null;
        size = 0;
    }

    @Override
    public String toString() { return isEmpty() ? "[]" : "[" + first + "]"; }

    /**
     * Inserts a new element at the specified index.
     *
     * @param index the index at which to insert the element
     * @param element the element to insert
     */
    private void linkNode(int index, E element) {
        if (index == 0) {
            linkFirst(element);
            return;
        }

        if (index == size) {
            linkLast(element);
            return;
        }

        SinglyLinkedNode<E> previous = getNode(index - 1);
        previous.setNext(new SinglyLinkedNode<>(element, previous.getNext()));
        size++;
    }

    /**
     * Inserts a new element at the beginning of the list.
     *
     * @param element the element to insert
     */
    private void linkFirst(E element) {
        first = new SinglyLinkedNode<>(element, first);

        if (last == null) last = first;

        size++;
    }

    /**
     * Inserts a new element at the end of the list.
     *
     * @param element the element to insert
     */
    private void linkLast(E element) {
        SinglyLinkedNode<E> newNode = new SinglyLinkedNode<>(element);
        if (first == null) {
            last = first = newNode;
        } else {
            last.setNext(newNode);
            last = newNode;
        }
        size++;
    }

    /**
     * Removes and returns the element at the specified index.
     *
     * @param index the index of the element to remove
     * @return the removed element
     */
    private E unlinkNode(int index) {
        if (index == 0) return unlinkFirst();
        return unlinkNext(getNode(index - 1));
    }

    /**
     * Removes and returns the first element of the list.
     *
     * @return the removed element
     */
    private E unlinkFirst() {
        SinglyLinkedNode<E> oldFirst = first;
        E elementRemoved = oldFirst.getElement();

        if (oldFirst == last) {
            last = first = null;
        } else {
            first = first.getNext();
        }

        oldFirst.clear();
        size--;
        return elementRemoved;
    }

    /**
     * Removes the node following the specified node and returns its element.
     *
     * @param node the node before the one to remove
     * @return the element of the removed node
     */
    private E unlinkNext(SinglyLinkedNode<E> node) {
        SinglyLinkedNode<E> nodeToRemove = node.getNext();
        E elementRemoved = nodeToRemove.getElement();

        if (nodeToRemove == last) last = node;

        node.setNext(nodeToRemove.getNext());
        nodeToRemove.clear();
        size--;

        return elementRemoved;
    }

    /**
     * Returns the node at the specified index.
     *
     * @param index the index of the node to retrieve
     * @return the node at the given index
     */
    private SinglyLinkedNode<E> getNode(int index) {
        SinglyLinkedNode<E> current = first;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current;
    }
}
