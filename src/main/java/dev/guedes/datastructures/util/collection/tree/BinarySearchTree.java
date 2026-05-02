package dev.guedes.datastructures.util.collection.tree;

import dev.guedes.datastructures.util.Objects;
import dev.guedes.datastructures.util.collection.internal.exception.EmptyTreeException;
import dev.guedes.datastructures.util.collection.internal.nodes.BinaryTreeNode;

/**
 * Represents a generic Binary Search Tree (BST) implementation.
 * This tree maintains elements in a sorted structure,
 * allowing efficient insertion, deletion, search, and retrieval
 * of the minimum and maximum elements.
 *
 * @param <E> the type of elements maintained by this tree, which must be comparable.
 *
 * @author João Guedes
 */
public class BinarySearchTree<E extends Comparable<E>> extends BinaryTree<E> {
    public BinarySearchTree(E element) { super(element); }

    public BinarySearchTree() {}

    protected BinarySearchTree(BinaryTreeNode<E> root) { super(root); }

    @Override
    public boolean add(E element) {
        Objects.requireNonNull(element, "Element cannot be null.");
        root = insertElement(element, root);
        return true;
    }

    @Override
    public boolean remove(E element) {
        Objects.requireNonNull(element, "Element cannot be null.");

        BooleanWrapper removed = new BooleanWrapper();
        root = removeElement(element, root, removed);
        return removed.value;
    }

    @Override
    public BinaryTree<E> search(E element) {
        Objects.requireNonNull(element, "Element cannot be null.");
        return subtreeSearch(element, root);
    }

    @Override
    public E min() {
        if (isEmpty()) throw new EmptyTreeException();
        return findMinimumValue(root);
    }

    @Override
    public E max() {
        if (isEmpty()) throw new EmptyTreeException();
        return findMaximumValue(root);
    }

    /**
     * Recursively adds an element to the subtree rooted at the given node.
     *
     * @param element the element to be added
     * @param node the root of the subtree
     * @return the new root of the subtree
     */
    protected BinaryTreeNode<E> insertElement(E element, BinaryTreeNode<E> node) {
        if (node == null) return new BinaryTreeNode<>(element);

        if (element.compareTo(node.getElement()) < 0) {
            node.setLeft(insertElement(element, node.getLeft()));
        } else {
            node.setRight(insertElement(element, node.getRight()));
        }

        return node;
    }

    /**
     * Recursively removes an element from the subtree rooted at the given node.
     *
     * @param element the element to remove
     * @param node the root of the subtree
     * @return the new root of the subtree
     */
    protected BinaryTreeNode<E> removeElement(E element, BinaryTreeNode<E> node, BooleanWrapper removed) {
        if (node == null) return null;

        int comparison = element.compareTo(node.getElement());

        if (comparison < 0) {
            node.setLeft(removeElement(element, node.getLeft(), removed));
        } else if (comparison > 0) {
            node.setRight(removeElement(element, node.getRight(), removed));
        } else {
            return removeNode(node, removed);
        }

        return node;
    }

    /**
     * Removes a node with two children by replacing its value with the minimum
     * value from its right subtree.
     *
     * @param node the node to remove
     * @return the new subtree root after removal
     */
    private BinaryTreeNode<E> removeNode(BinaryTreeNode<E> node, BooleanWrapper removed) {
        removed.value = true;

        if (node.getLeft() == null) return node.getRight();
        if (node.getRight() == null) return node.getLeft();

        BinaryTreeNode<E> successorParent = node;
        BinaryTreeNode<E> successor = node.getRight();

        while (successor.getLeft() != null) {
            successorParent = successor;
            successor = successor.getLeft();
        }

        node.setElement(successor.getElement());

        if (successorParent == node) {
            successorParent.setRight(successor.getRight());
        } else {
            successorParent.setLeft(successor.getRight());
        }

        return node;
    }

    /**
     * Recursively searches for a node containing the given element.
     *
     * @param element the element to search
     * @param node the root of the current subtree
     * @return a BinaryTree rooted at the matching node, or null if not found
     */
    private BinaryTree<E> subtreeSearch(E element, BinaryTreeNode<E> node) {
        if (node == null || element.compareTo(node.getElement()) == 0) {
            return node == null ? null : createNewTreeInstance(node);
        }

        if (element.compareTo(node.getElement()) < 0) {
            return subtreeSearch(element, node.getLeft());
        }

        return subtreeSearch(element, node.getRight());
    }

    /**
     * Retrieves the minimum element from a subtree.
     *
     * @param node the root of the subtree
     * @return the minimum element
     */
    private E findMinimumValue(BinaryTreeNode<E> node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node.getElement();
    }

    /**
     * Retrieves the maximum element from a subtree.
     *
     * @param node the root of the subtree
     * @return the maximum element
     */
    private E findMaximumValue(BinaryTreeNode<E> node) {
        while (node.getRight() != null) {
            node = node.getRight();
        }
        return node.getElement();
    }

    /**
     * Creates a new instance of the current BinarySearchTree class using the
     * given node as the root.
     *
     * @param node the node to be used as root for the new instance
     * @return a new BinaryTree instance
     */
    @SuppressWarnings("unchecked")
    private BinaryTree<E> createNewTreeInstance(BinaryTreeNode<E> node) {
        try {
            return getClass()
                    .getDeclaredConstructor(BinaryTreeNode.class)
                    .newInstance(node);
        } catch (Exception e) {
            throw new RuntimeException("Could not instantiate tree of type " + getClass().getSimpleName(), e);
        }
    }

    protected static class BooleanWrapper { protected boolean value; }
}
