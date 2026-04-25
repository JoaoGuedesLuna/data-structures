package dev.guedes.datastructures.util.collection.list;

/**
 * Test class for {@link SinglyLinkedList}.
 *
 * @author João Guedes
 */
class SinglyLinkedListTest extends ListTest {
    @Override
    protected List<Integer> createList() { return new SinglyLinkedList<>(); }
}
