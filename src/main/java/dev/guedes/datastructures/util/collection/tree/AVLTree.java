package dev.guedes.datastructures.util.collection.tree;

import dev.guedes.datastructures.util.collection.internal.nodes.BinaryTreeNode;

/**
 * Represents an AVL Tree, a self-balancing binary search tree where the difference
 * between heights of left and right subtrees cannot be more than one for all nodes.
 *
 * @param <E> the type of elements maintained by this tree, which must be comparable
 *
 * @author João Guedes
 */
public class AVLTree<E extends Comparable<E>> extends BinarySearchTree<E> {
    public AVLTree(E element) { super(element); }

    public AVLTree() { super(); }

    /**
     * Inserts an element into the AVL tree and balances the tree if needed.
     *
     * @param element the element to insert
     * @param node the current node in recursive insertion
     * @return the updated node after insertion and balancing
     */
    @Override
    protected BinaryTreeNode<E> insertElement(E element, BinaryTreeNode<E> node) {
        return balanceTree(super.insertElement(element, node));
    }

    /**
     * Removes an element from the AVL tree and balances the tree if needed.
     *
     * @param element the element to remove
     * @param node the current node in recursive deletion
     * @return the updated node after removal and balancing
     */
    @Override
    protected BinaryTreeNode<E> removeElement(E element, BinaryTreeNode<E> node, BooleanWrapper removed) {
        return balanceTree(super.removeElement(element, node, removed));
    }

    /**
     * Balances the tree at the given node using appropriate rotations.
     *
     * @param node the node to balance
     * @return the new root of the subtree after balancing
     */
    private BinaryTreeNode<E> balanceTree(BinaryTreeNode<E> node) {
        int balance = calculateBalanceFactor(node);

        if (balance < -1) {
            if (calculateBalanceFactor(node.getLeft()) <= 0) {
                return performRightRotation(node);
            }
            return performLeftRightRotation(node);
        }

        if (balance > 1) {
            if (calculateBalanceFactor(node.getRight()) >= 0) {
                return performLeftRotation(node);
            }
            return performRightLeftRotation(node);
        }

        return node;
    }

    /**
     * Calculates the balance factor of a node.
     *
     * @param node the node whose balance factor is to be calculated
     * @return the balance factor (right subtree height - left subtree height)
     */
    private int calculateBalanceFactor(BinaryTreeNode<E> node) {
        if (node == null) return 0;

        updateNodeHeight(node);
        return getNodeHeight(node.getRight()) - getNodeHeight(node.getLeft());
    }

    /**
     * Performs a left rotation on the subtree rooted at the given node.
     *
     * @param root the root of the subtree to rotate
     * @return the new root of the rotated subtree
     */
    private BinaryTreeNode<E> performLeftRotation(BinaryTreeNode<E> root) {
        BinaryTreeNode<E> newRoot = root.getRight();

        root.setRight(newRoot.getLeft());
        newRoot.setLeft(root);

        updateNodeHeight(root);
        updateNodeHeight(newRoot);

        return newRoot;
    }

    /**
     * Performs a right rotation on the subtree rooted at the given node.
     *
     * @param root the root of the subtree to rotate
     * @return the new root of the rotated subtree
     */
    private BinaryTreeNode<E> performRightRotation(BinaryTreeNode<E> root) {
        BinaryTreeNode<E> newRoot = root.getLeft();

        root.setLeft(newRoot.getRight());
        newRoot.setRight(root);

        updateNodeHeight(root);
        updateNodeHeight(newRoot);

        return newRoot;
    }

    /**
     * Performs a left-right double rotation to balance a left-heavy tree.
     *
     * @param root the root of the unbalanced subtree
     * @return the new root after rotations
     */
    private BinaryTreeNode<E> performLeftRightRotation(BinaryTreeNode<E> root) {
        root.setLeft(performLeftRotation(root.getLeft()));
        return performRightRotation(root);
    }

    /**
     * Performs a right-left double rotation to balance a right-heavy tree.
     *
     * @param root the root of the unbalanced subtree
     * @return the new root after rotations
     */
    private BinaryTreeNode<E> performRightLeftRotation(BinaryTreeNode<E> root) {
        root.setRight(performRightRotation(root.getRight()));
        return performLeftRotation(root);
    }

    /**
     * Updates the height of a node based on the heights of its children.
     *
     * @param node the node to update
     */
    private void updateNodeHeight(BinaryTreeNode<E> node) {
        if (node == null) return;

        int leftHeight = getNodeHeight(node.getLeft());
        int rightHeight = getNodeHeight(node.getRight());

        node.setHeight(Math.max(leftHeight, rightHeight) + 1);
    }

    /**
     * Returns the height of a given node.
     *
     * @param node the node whose height is to be retrieved
     * @return the height of the node or -1 if the node is null
     */
    private int getNodeHeight(BinaryTreeNode<E> node) { return node != null ? node.getHeight() : -1; }
}
