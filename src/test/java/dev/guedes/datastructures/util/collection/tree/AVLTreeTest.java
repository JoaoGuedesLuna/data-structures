package dev.guedes.datastructures.util.collection.tree;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for {@link AVLTree}.
 *
 * @author João Guedes
 */
class AVLTreeTest extends BinaryTreeTest {
    static Stream<Arguments> rotationCases() {
        return Stream.of(
                Arguments.of("L", new int[]{1, 2, 3}),
                Arguments.of("R", new int[]{3, 2, 1}),
                Arguments.of("LR", new int[]{3, 1, 2}),
                Arguments.of("RL", new int[]{1, 3, 2})
        );
    }

    @Override
    protected BinaryTree<Integer> createBinaryTree() { return new AVLTree<>(); }

    @Override
    protected BinaryTree<Integer> createBinaryTree(Integer root) { return new AVLTree<>(root); }

    @Test
    void height_ShouldReturnCorrectHeight_WhenTreeIsUnbalanced() {
        BinaryTree<Integer> tree = createBinaryTree();

        tree.add(1);
        tree.add(2);
        tree.add(3);

        assertEquals(1, tree.height());
    }

    @ParameterizedTest()
    @MethodSource("rotationCases")
    void add_ShouldBalanceTree_ForAllRotationCases(String caseName, int[] values) {
        BinaryTree<Integer> tree = createBinaryTree();

        for (int value : values) tree.add(value);

        assertEquals("[1, 2, 3]", tree.inorderTraversal());
        assertEquals("[2, 1, 3]", tree.preorderTraversal());
        assertEquals(1, tree.height());
    }

    @Test
    void remove_ShouldPerformLeftRotation_WhenRightRightCaseOccurs() {
        BinaryTree<Integer> tree = createBinaryTree();

        tree.add(2);
        tree.add(1);
        tree.add(3);
        tree.add(4);

        tree.remove(1);

        assertEquals("[2, 3, 4]", tree.inorderTraversal());
        assertEquals("[3, 2, 4]", tree.preorderTraversal());
        assertEquals(1, tree.height());
    }

    @Test
    void remove_ShouldPerformRightRotation_WhenLeftLeftCaseOccurs() {
        BinaryTree<Integer> tree = createBinaryTree();

        tree.add(3);
        tree.add(2);
        tree.add(4);
        tree.add(1);

        tree.remove(4);

        assertEquals("[1, 2, 3]", tree.inorderTraversal());
        assertEquals("[2, 1, 3]", tree.preorderTraversal());
        assertEquals(1, tree.height());
    }

    @Test
    void remove_ShouldPerformLeftRightRotation_WhenLeftRightCaseOccurs() {
        BinaryTree<Integer> tree = createBinaryTree();

        tree.add(5);
        tree.add(2);
        tree.add(6);
        tree.add(1);
        tree.add(3);
        tree.add(7);
        tree.add(4);

        tree.remove(7);

        assertEquals("[1, 2, 3, 4, 5, 6]", tree.inorderTraversal());
        assertEquals("[3, 2, 1, 5, 4, 6]", tree.preorderTraversal());
    }

    @Test
    void remove_ShouldPerformRightLeftRotation_WhenRightLeftCaseOccurs() {
        BinaryTree<Integer> tree = createBinaryTree();

        tree.add(3);
        tree.add(2);
        tree.add(6);
        tree.add(1);
        tree.add(5);
        tree.add(7);
        tree.add(4);

        tree.remove(1);

        assertEquals("[2, 3, 4, 5, 6, 7]", tree.inorderTraversal());
        assertEquals("[5, 3, 2, 4, 6, 7]", tree.preorderTraversal());
    }
}
