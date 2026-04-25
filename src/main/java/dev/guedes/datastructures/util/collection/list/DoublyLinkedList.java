package dev.guedes.datastructures.util.collection.list;

import dev.guedes.datastructures.util.Objects;
import dev.guedes.datastructures.util.collection.internal.nodes.DoublyLinkedNode;

import java.util.function.Consumer;

import static dev.guedes.datastructures.util.collection.internal.validation.Validator.validateIndex;
import static dev.guedes.datastructures.util.collection.internal.validation.Validator.validateIndexForAdd;

/**
 * A doubly linked list implementation of the {@link List} interface.
 *
 * <p>Each node maintains references to both its previous and next nodes,
 * allowing efficient insertions and deletions at both ends and within the list.
 *
 * @param <E> the type of elements stored in the list
 *
 * @author João Guedes
 */
public class DoublyLinkedList<E> implements List<E> {
    private DoublyLinkedNode<E> first;
    private DoublyLinkedNode<E> last;
    private int size;

    public DoublyLinkedList() {
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
        DoublyLinkedNode<E> current = first;

        while (current != null) {
            if (Objects.equals(element, current.getElement())) {
                unlinkNode(current);
                return true;
            }
            current = current.getNext();
        }

        return false;
    }

    @Override
    public E remove(int index) {
        validateIndex(index, size);
        return unlinkNode(getNode(index));
    }

    @Override
    public E get(int index) {
        validateIndex(index, size);
        return getNode(index).getElement();
    }

    @Override
    public E set(int index, E element) {
        validateIndex(index, size);

        DoublyLinkedNode<E> target = getNode(index);
        E oldElement = target.getElement();

        target.setElement(element);
        return oldElement;
    }

    @Override
    public int indexOf(E element) {
        DoublyLinkedNode<E> current = first;

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
        DoublyLinkedNode<E> current = last;

        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(element, current.getElement())) {
                return i;
            }
            current = current.getPrevious();
        }

        return -1;
    }

    @Override
    public boolean contains(E element) { return indexOf(element) >= 0; }

    @Override
    public void forEach(Consumer<E> action) {
        DoublyLinkedNode<E> current = first;
        while (current != null) {
            action.accept(current.getElement());
            current = current.getNext();
        }
    }

    @Override
    public Object[] toArray() {
        DoublyLinkedNode<E> current = first;
        Object[] array = new Object[size];

        for (int i = 0; i < size; i++) {
            array[i] = current.getElement();
            current = current.getNext();
        }

        return array;
    }

    @Override
    public void clear() {
        for (DoublyLinkedNode<E> current = first, next; current != null; current = next) {
            next = current.getNext();
            current.clear();
        }
        first = last = null;
        size = 0;
    }

    @Override
    public String toString() { return isEmpty() ? "[]" : "[" + first + "]"; }

    /**
     * Inserts a new node at the specified index in the list.
     * Adjusts node references accordingly to maintain list integrity.
     *
     * @param index the position where the element should be inserted
     * @param element the element to insert
     */
    private void linkNode(int index, E element) {
        if (index == size) {
            linkLast(element);
            return;
        }

        DoublyLinkedNode<E> current = getNode(index);
        DoublyLinkedNode<E> newNode = new DoublyLinkedNode<>(current.getPrevious(), element, current);

        if (newNode.getPrevious() == null) {
            first = newNode;
        } else {
            newNode.getPrevious().setNext(newNode);
        }

        current.setPrevious(newNode);
        size++;
    }

    /**
     * Appends an element at the end of the list.
     *
     * @param element the element to insert at the end
     */
    private void linkLast(E element) {
        DoublyLinkedNode<E> newNode = new DoublyLinkedNode<>(last, element, null);
        last = newNode;

        if (isEmpty()) {
            first = newNode;
        } else {
            newNode.getPrevious().setNext(newNode);
        }

        size++;
    }

    /**
     * Unlinks the specified node from the list and returns its element.
     * Adjusts adjacent node references and updates list size accordingly.
     *
     * @param node the node to remove
     * @return the element previously stored in the removed node
     */
    private E unlinkNode(DoublyLinkedNode<E> node) {
        E removedElement = node.getElement();
        DoublyLinkedNode<E> previous = node.getPrevious();
        DoublyLinkedNode<E> next = node.getNext();

        if (previous == null) {
            first = next;
        } else {
            previous.setNext(next);
        }

        if (next == null) {
            last = previous;
        } else {
            next.setPrevious(previous);
        }

        node.clear();
        size--;

        return removedElement;
    }

    /**
     * Retrieves the node at the given index.
     * Traverses from the nearest end (head or tail) for efficiency.
     *
     * @param index the index of the node to retrieve
     * @return the node at the specified index
     */
    private DoublyLinkedNode<E> getNode(int index) {
        DoublyLinkedNode<E> current;

        if (index <= size / 2) {
            current = first;
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
        } else {
            current = last;
            for (int i = size - 1; i > index; i--) {
                current = current.getPrevious();
            }
        }

        return current;
    }
}
