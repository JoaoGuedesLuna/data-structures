package main.java.std.guedes.datastructures.util.collections.tree;

/**
 * Em Ciência da computação, uma árvore binária de busca é uma estrutura de dados de árvore binária
 * baseada em nós, onde todos os nós da subárvore esquerda possuem um valor numérico inferior ao nó
 * raiz e todos os nós da subárvore direita possuem um valor superior ao nó raiz. O objetivo desta
 * árvore é estruturar os dados para permitir busca binária.
 *
 * @param <T> Tipo de dado que a árvore armazenará.
 *
 * @author João Guedes.
 */
public class _BinarySearchTree<T extends Comparable<T>> extends _BinaryTree<T> {

    private _BinarySearchTree(_Node<T> root) {
        super(root);
    }

    public _BinarySearchTree(T element) {
        super(element);
    }

    public _BinarySearchTree(){
        super();
    }

    @Override
    public boolean add(T element) {
        if (element == null) {
            throw new NullPointerException();
        }
        this.setRoot(this.add(element, this.getRoot()));
        return true;
    }

    /**
     * Adiciona um elemento na árvore.
     *
     * @param element Elemento que será adicionado na árvore.
     *
     * @param node Nó atual.
     *
     * @return Um nó.
     */
    protected _Node<T> add(T element, _Node<T> node) {
        if (node == null) {
            return new _Node<>(element);
        }
        if (element.compareTo(node.getElement()) < 0) {
            node.setLeft(this.add(element, node.getLeft()));
        }
        else {
            node.setRight(this.add(element, node.getRight()));
        }
        return node;
    }

    @Override
    public boolean remove(T element) {
        if (element == null) {
            throw new NullPointerException();
        }
        if (this.isEmpty()) {
            return false;
        }
        try {
            this.setRoot(this.remove(element, this.getRoot()));
            return true;
        } catch (ElementNotFoundException e) {
            return false;
        }
    }

    /**
     * Remove um elemento da árvore.
     *
     * @param element Elemento da árvore.
     *
     * @param node Nó atual.
     */
    private _Node<T> remove(T element, _Node<T> node) throws ElementNotFoundException {
        if (node == null) {
            throw new ElementNotFoundException();
        }
        if (element.compareTo(node.getElement()) < 0) {
            node.setLeft(this.remove(element, node.getLeft()));
        }
        else if (element.compareTo(node.getElement()) > 0) {
            node.setRight(this.remove(element, node.getRight()));
        }
        else {
            if (node.getLeft() == null) {
                return node.getRight();
            }
            if (node.getRight() == null) {
                return node.getLeft();
            }
            node.setElement(this.min(node.getRight()));
            node.setRight(this.remove(node.getElement(), node.getRight()));
        }
        return node;
    }

    @Override
    public T min() {
        if (this.isEmpty()) {
            throw new EmptyTreeException();
        }
        return this.min((this.getRoot()));
    }

    /**
     * Retorna o menor elemento da árvore.
     *
     * @param node Nó atual.
     *
     * @return Retorna o menor elemento da árvore.
     */
    protected T min(_Node<T> node) {
        _Node<T> current = node;
        while (current.getLeft() != null) {
            current = current.getLeft();
        }
        return current.getElement();
    }

    @Override
    public T max() {
        if (this.isEmpty()) {
            throw new EmptyTreeException();
        }
        return this.max(this.getRoot());
    }

    /**
     * Retorna o maior elemento da árvore.
     *
     * @param node Nó atual.
     *
     * @return Retorna o maior elemento da árvore.
     */
    private T max(_Node<T> node) {
        _Node<T> current = node;
        while (current.getRight() != null) {
            current = current.getRight();
        }
        return current.getElement();
    }

    @Override
    public _BinarySearchTree<T> search(T element) {
        if (element == null) {
            throw new NullPointerException();
        }
        return this.search(element, this.getRoot());
    }

    /**
     * Faz a busca de um elemento e retorna uma árvore a partir desse elemento.
     *
     * @param element Elemento buscado.
     *
     * @param node Nó atual.
     *
     * @return uma subárvore.
     */
    private _BinarySearchTree<T> search(T element, _Node<T> node) {
        if (node == null || element.compareTo(node.getElement()) == 0) {
            return new _BinarySearchTree<>(node);
        }
        if(element.compareTo(node.getElement()) < 0) {
            return this.search(element, node.getLeft());
        }
        return this.search(element, node.getRight());
    }

}