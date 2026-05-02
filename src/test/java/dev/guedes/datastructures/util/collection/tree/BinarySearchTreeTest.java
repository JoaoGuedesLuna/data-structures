package dev.guedes.datastructures.util.collection.tree;

import dev.guedes.datastructures.util.collection.internal.exception.EmptyTreeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for {@link BinarySearchTree}.
 *
 * @author João Guedes
 */
class BinarySearchTreeTest extends BinaryTreeTest {
    @Override
    protected BinaryTree<Integer> createBinaryTree() { return new BinarySearchTree<>(); }

    @Override
    protected BinaryTree<Integer> createBinaryTree(Integer root) { return new BinarySearchTree<>(root); }

    @Test
    void height_ShouldReturnCorrectHeight_WhenTreeIsBalanced() {
        BinaryTree<Integer> tree = createBinaryTree();

        tree.add(2);
        tree.add(1);
        tree.add(3);

        assertEquals(1, tree.height());
    }

    @Test
    void height_ShouldReturnCorrectHeight_WhenTreeIsUnbalanced() {
        BinaryTree<Integer> tree = createBinaryTree();

        tree.add(1);
        tree.add(2);
        tree.add(3);

        assertEquals(2, tree.height());
    }

    @Test
    void add_ShouldThrowException_WhenElementIsNull() {
        BinaryTree<Integer> tree = createBinaryTree();

        assertThrows(NullPointerException.class, () -> tree.add(null));
    }

    @Test
    void add_ShouldInsertElements_WhenCalled() {
        BinaryTree<Integer> tree = createBinaryTree();

        tree.add(2);
        tree.add(1);
        tree.add(3);

        assertEquals("[1, 2, 3]", tree.inorderTraversal());
    }

    @Test
    void remove_ShouldThrowException_WhenElementIsNull() {
        BinaryTree<Integer> tree = createBinaryTree();

        assertThrows(NullPointerException.class, () -> tree.remove(null));
    }

    @Test
    void remove_ShouldRemoveRoot_WhenTreeHasSingleElement() {
        BinaryTree<Integer> tree = createBinaryTree();

        tree.add(1);

        assertTrue(tree.remove(1));
        assertTrue(tree.isEmpty());
    }

    @Test
    void remove_ShouldRemoveLeafNode_WhenElementExists() {
        BinaryTree<Integer> tree = createBinaryTree();

        tree.add(2);
        tree.add(1);
        tree.add(3);

        assertTrue(tree.remove(1));
        assertEquals("[2, 3]", tree.inorderTraversal());
    }

    @Test
    void remove_ShouldRemoveNodeWithOneRightChild_WhenElementExists() {
        BinaryTree<Integer> tree = createBinaryTree();

        tree.add(2);
        tree.add(1);
        tree.add(3);
        tree.add(4);

        assertTrue(tree.remove(3));
        assertEquals("[1, 2, 4]", tree.inorderTraversal());
    }

    @Test
    void remove_ShouldRemoveNodeWithOneLeftChild_WhenElementExists() {
        BinaryTree<Integer> tree = createBinaryTree();

        tree.add(2);
        tree.add(1);
        tree.add(3);
        tree.add(5);
        tree.add(4);

        assertTrue(tree.remove(5));
        assertEquals("[1, 2, 3, 4]", tree.inorderTraversal());
    }

    @Test
    void remove_ShouldReplaceWithSuccessor_WhenSuccessorIsRightChild() {
        BinaryTree<Integer> tree = createBinaryTree();

        tree.add(5);
        tree.add(3);
        tree.add(7);
        tree.add(6);
        tree.add(8);

        assertTrue(tree.remove(7));

        assertEquals("[3, 5, 6, 8]", tree.inorderTraversal());
    }

    @Test
    void remove_ShouldReplaceWithSuccessor_WhenSuccessorIsLeftmostInRightSubtree() {
        BinaryTree<Integer> tree = createBinaryTree();

        tree.add(5);
        tree.add(3);
        tree.add(7);
        tree.add(6);
        tree.add(9);
        tree.add(8);

        assertTrue(tree.remove(7));

        assertEquals("[3, 5, 6, 8, 9]", tree.inorderTraversal());
    }

    @Test
    void remove_ShouldReturnFalse_WhenElementDoesNotExist() {
        BinaryTree<Integer> tree = createBinaryTree();

        tree.add(1);

        assertFalse(tree.remove(2));
    }

    @Test
    void search_ShouldThrowException_WhenElementIsNull() {
        BinaryTree<Integer> tree = createBinaryTree();

        assertThrows(NullPointerException.class, () -> tree.search(null));
    }

    @Test
    void search_ShouldReturnSubtree_WhenElementExists() {
        BinaryTree<Integer> tree = createBinaryTree();

        tree.add(2);
        tree.add(1);
        tree.add(3);

        BinaryTree<Integer> result = tree.search(1);

        assertNotNull(result);
        assertEquals("[1]", result.inorderTraversal());
    }

    @Test
    void search_ShouldReturnNull_WhenElementDoesNotExist() {
        BinaryTree<Integer> tree = createBinaryTree();

        tree.add(1);

        assertNull(tree.search(2));
    }

    @Test
    void min_ShouldThrowException_WhenTreeIsEmpty() {
        assertThrows(EmptyTreeException.class, () -> createBinaryTree().min());
    }

    @Test
    void min_ShouldReturnSmallestElement_WhenTreeHasElements() {
        BinaryTree<Integer> tree = createBinaryTree();

        tree.add(2);
        tree.add(1);
        tree.add(3);

        assertEquals(1, tree.min());
    }

    @Test
    void max_ShouldThrowException_WhenTreeIsEmpty() {
        assertThrows(EmptyTreeException.class, () -> createBinaryTree().max());
    }

    @Test
    void max_ShouldReturnLargestElement_WhenTreeHasElements() {
        BinaryTree<Integer> tree = createBinaryTree();

        tree.add(2);
        tree.add(1);
        tree.add(3);

        assertEquals(3, tree.max());
    }
}
