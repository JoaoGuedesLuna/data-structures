package dev.guedes.datastructures.util.collection.tree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Contract class for {@link BinaryTree} test classes.
 *
 * @author João Guedes
 */
abstract class BinaryTreeTest {
    protected abstract BinaryTree<Integer> createBinaryTree();
    protected abstract BinaryTree<Integer> createBinaryTree(Integer root);

    @Test
    void constructor_ShouldThrowException_WhenElementIsNull() {
        assertThrows(NullPointerException.class, () -> createBinaryTree(null));
    }

    @Test
    void constructor_ShouldInitializeRoot_WhenElementIsNotNull() {
        BinaryTree<Integer> tree = createBinaryTree(1);

        assertFalse(tree.isEmpty());
        assertEquals("[1]", tree.inorderTraversal());
    }

    @Test
    void height_ShouldReturnZero_WhenTreeIsEmpty() {
        assertEquals(0, createBinaryTree().height());
    }

    @Test
    void isEmpty_ShouldReturnTrue_WhenTreeIsEmpty() {
        assertTrue(createBinaryTree().isEmpty());
    }

    @Test
    void isEmpty_ShouldReturnFalse_WhenTreeHasElements() {
        BinaryTree<Integer> tree = createBinaryTree();

        tree.add(1);

        assertFalse(tree.isEmpty());
    }

    @Test
    void preorderTraversal_ShouldReturnEmpty_WhenTreeIsEmpty() {
        assertEquals("[]", createBinaryTree().preorderTraversal());
    }

    @Test
    void inorderTraversal_ShouldReturnEmpty_WhenTreeIsEmpty() {
        assertEquals("[]", createBinaryTree().inorderTraversal());
    }

    @Test
    void postorderTraversal_ShouldReturnEmpty_WhenTreeIsEmpty() {
        assertEquals("[]", createBinaryTree().postorderTraversal());
    }

    @Test
    void traversals_ShouldReturnCorrectOrder_WhenTreeHasElements() {
        BinaryTree<Integer> tree = createBinaryTree();

        tree.add(2);
        tree.add(1);
        tree.add(3);

        assertEquals("[2, 1, 3]", tree.preorderTraversal());
        assertEquals("[1, 2, 3]", tree.inorderTraversal());
        assertEquals("[1, 3, 2]", tree.postorderTraversal());
    }

    @Test
    void clear_ShouldRemoveAllElements_WhenTreeIsNotEmpty() {
        BinaryTree<Integer> tree = createBinaryTree();

        tree.add(1);
        tree.add(2);
        tree.add(3);

        tree.clear();

        assertTrue(tree.isEmpty());
        assertEquals(0, tree.height());
    }

    @Test
    void toString_ShouldReturnEmpty_WhenTreeIsEmpty() {
        assertEquals("[]", createBinaryTree().toString());
    }

    @Test
    void toString_ShouldReturnInorderTraversal_WhenTreeHasElements() {
        BinaryTree<Integer> tree = createBinaryTree();

        tree.add(2);
        tree.add(1);
        tree.add(3);

        assertEquals("[1, 2, 3]", tree.toString());
    }
}
