package dev.guedes.datastructures.util.collection.internal.nodes;

import java.util.Objects;

/**
 * Represents a generic node in a binary tree.
 *
 * <p>This class is typically used in tree-based data structures like binary search trees (BST),
 * AVL trees, or any other tree that maintains left and right child nodes.</p>
 *
 * @param <E> the type of element stored in the node, which must be comparable
 *
 * @author João Guedes
 */
public class BinaryTreeNode<E> {
    private E element;
    private BinaryTreeNode<E> left;
    private BinaryTreeNode<E> right;
    private int height;

    public BinaryTreeNode(E element, BinaryTreeNode<E> left, BinaryTreeNode<E> right) {
        this.element = element;
        this.left = left;
        this.right = right;
        this.height = 0;
    }

    public BinaryTreeNode(E element) { this(element, null, null); }

    public E getElement() { return element; }
    public void setElement(E element) { this.element = element; }

    public BinaryTreeNode<E> getLeft() { return left; }
    public void setLeft(BinaryTreeNode<E> left) { this.left = left; }

    public BinaryTreeNode<E> getRight() {return right; }
    public void setRight(BinaryTreeNode<E> right) { this.right = right; }

    public int getHeight() { return height; }

    public void setHeight(int height) { this.height = height; }

    /**
     * Clears all references in this node for garbage collection.
     */
    public void clear() {
        this.element = null;
        this.left = null;
        this.right = null;
        this.height = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || this.getClass() != o.getClass()) return false;

        BinaryTreeNode<?> node = (BinaryTreeNode<?>) o;
        return Objects.equals(this.element, node.element)
                && Objects.equals(this.left, node.left)
                && Objects.equals(this.right, node.right)
                && Objects.equals(this.height, node.height);
    }

    @Override
    public int hashCode() { return Objects.hash(element, left, right, height); }

    @Override
    public String toString() { return element.toString(); }
}
