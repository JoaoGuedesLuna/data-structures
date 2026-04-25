package dev.guedes.datastructures.util.collection.list;

/**
 * Test class for {@link DoublyLinkedList}.
 *
 * @author João Guedes
 */
class DoublyLinkedListTest extends ListTest {
    @Override
    protected List<Integer> createList() { return new DoublyLinkedList<>(); }
}
