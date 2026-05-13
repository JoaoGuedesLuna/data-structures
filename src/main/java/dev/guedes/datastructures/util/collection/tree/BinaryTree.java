package dev.guedes.datastructures.util.collection.tree;

import dev.guedes.datastructures.util.Objects;
import dev.guedes.datastructures.util.collection.internal.exception.EmptyTreeException;
import dev.guedes.datastructures.util.collection.internal.nodes.BinaryTreeNode;

/**
 * Abstract class representing a generic binary tree structure.
 *
 * <p>Subclasses should implement operations like {@code add}, {@code remove}, and {@code search}
 * according to the desired binary tree behavior (e.g., binary search tree, AVL tree).</p>
 *
 * @param <E> the type of elements stored in the tree nodes
 *
 * @author João Guedes
 */
public abstract class BinaryTree<E> {
    protected BinaryTreeNode<E> root;

    protected BinaryTree(E element) {
        Objects.requireNonNull(element, "Element cannot be null.");
        this.root = new BinaryTreeNode<>(element);
    }

    protected BinaryTree(BinaryTreeNode<E> root) { this.root = root; }

    protected BinaryTree() {}

    /**
     * Adds a non-null element to the tree.
     *
     * @param element the non-null element to add
     * @return {@code true} if the tree was modified
     * @throws NullPointerException if the element is null
     */
    public abstract boolean add(E element);

    /**
     * Removes the specified non-null element from the tree.
     *
     * @param element the non-null element to remove
     * @return {@code true} if the element was found and removed
     * @throws NullPointerException if the element is null
     */
    public abstract boolean remove(E element);

    /**
     * Searches for a node containing the specified non-null element.
     *
     * @param element the non-null element to search for
     * @return a subtree rooted at the found node, or {@code null} if not found
     * @throws NullPointerException if the element is null
     */
    public abstract BinaryTree<E> search(E element);

    /**
     * Returns the smallest element in the tree.
     *
     * @return the minimum element
     * @throws EmptyTreeException if the tree is empty
     */
    public abstract E min() throws EmptyTreeException;

    /**
     * Returns the largest element in the tree.
     *
     * @return the maximum element
     * @throws EmptyTreeException if the tree is empty
     */
    public abstract E max() throws EmptyTreeException;

    /**
     * Returns the height of the tree.
     *
     * @return the height, or 0 if empty
     */
    public int height() { return !isEmpty() ? calculateHeight(root) : 0; }

    /**
     * Checks whether the tree is empty.
     *
     * @return {@code true} if the tree has no nodes
     */
    public boolean isEmpty() { return root == null; }

    /**
     * Returns a string representing the preorder traversal of the tree.
     *
     * @return a string containing the elements in preorder
     */
    public String preorderTraversal() { return buildTraversalString(TraversalOrder.PREORDER); }

    /**
     * Returns a string representing the inorder traversal of the tree.
     *
     * @return a string containing the elements in inorder
     */
    public String inorderTraversal() { return buildTraversalString(TraversalOrder.INORDER); }

    /**
     * Returns a string representing the postorder traversal of the tree.
     *
     * @return a string containing the elements in postorder
     */
    public String postorderTraversal() { return buildTraversalString(TraversalOrder.POSTORDER); }

    /**
     * Removes all elements from the tree.
     */
    public void clear() { root = clearSubtree(root); }

    @Override
    public String toString() { return inorderTraversal(); }

    /**
     * Recursively computes the height of a subtree rooted at the given node.
     *
     * @param root the root of the subtree
     * @return the height of the subtree
     */
    private int calculateHeight(BinaryTreeNode<E> root) {
        int heightLeft = -1;
        int heightRight = -1;

        if (root.getLeft() != null) heightLeft = calculateHeight(root.getLeft());
        if (root.getRight() != null) heightRight = calculateHeight(root.getRight());

        return heightRight > heightLeft ? ++heightRight : ++heightLeft;
    }

    /**
     * Builds a string representation of the tree using the specified traversal order.
     *
     * @param order the traversal order to use
     * @return formatted traversal string
     */
    private String buildTraversalString(TraversalOrder order) {
        if (isEmpty()) { return "[]"; }

        StringBuilder traversalBuilder = new StringBuilder();

        performTraversal(root, order, traversalBuilder);

        traversalBuilder.setLength(traversalBuilder.length() - 2);

        return "[" + traversalBuilder + "]";
    }

    /**
     * Performs traversal of the subtree rooted at the given node.
     *
     * @param currentNode root of the subtree to traverse
     * @param order traversal order to use
     * @param builder StringBuilder to accumulate results
     */
    private void performTraversal(BinaryTreeNode<E> currentNode, TraversalOrder order, StringBuilder builder) {
        if (currentNode == null) return;

        switch (order) {
            case PREORDER -> {
                builder.append(currentNode.getElement()).append(", ");
                performTraversal(currentNode.getLeft(), order, builder);
                performTraversal(currentNode.getRight(), order, builder);
            }
            case INORDER -> {
                performTraversal(currentNode.getLeft(), order, builder);
                builder.append(currentNode.getElement()).append(", ");
                performTraversal(currentNode.getRight(), order, builder);
            }
            case POSTORDER -> {
                performTraversal(currentNode.getLeft(), order, builder);
                performTraversal(currentNode.getRight(), order, builder);
                builder.append(currentNode.getElement()).append(", ");
            }
        }
    }

    /**
     * Recursively clears a subtree rooted at the given node.
     *
     * @param node the root of the subtree to clear
     * @return always {@code null}
     */
    private BinaryTreeNode<E> clearSubtree(BinaryTreeNode<E> node) {
        if (node != null) {
            node.setLeft(clearSubtree(node.getLeft()));
            node.setRight(clearSubtree(node.getRight()));
        }
        return null;
    }

    private enum TraversalOrder {
        PREORDER,
        INORDER,
        POSTORDER
    }
}
