package main.java.std.guedes.datastructures.util.collections.tree;

/**
 * Árvore AVL é uma árvore binária de busca balanceada, ou seja, uma árvore balanceada são as árvores que
 * minimizam o número de comparações efetuadas no pior caso para uma busca com chaves de probabilidades
 * de ocorrências idênticas. Contudo, para garantir essa propriedade em aplicações dinâmicas, é preciso
 * reconstruir a árvore para seu estado ideal a cada operação sobre seus nós (inclusão ou exclusão),
 * para ser alcançado um custo de algoritmo com o tempo de pesquisa tendendo a O(log n).
 *
 * @param <T> Tipo de dado que a árvore armazenará.
 *
 * @author João Guedes.
 */
public class _AVLTree<T extends Comparable<T>> extends _BinarySearchTree<T> {

    public _AVLTree(T element) {
        super(element);
    }

    public _AVLTree() {
        super();
    }

    /**
     * Retorna a altura de um nó.
     *
     * @param node Nó verificado.
     *
     * @return Retorna a altura de um nó.
     */
    private int height(_Node<T> node) {
        return node != null ? node.getHeight() : -1;
    }

    /**
     * Atualiza a altura de um nó.
     *
     * @param node Nó que terá sua altura atualizada.
     */
    private void updateHeight(_Node<T> node) {
        if (node == null) {
            return;
        }
        int leftHeight = this.height(node.getLeft());
        int rightHeight = this.height(node.getRight());
        node.setHeight(Math.max(leftHeight, rightHeight) + 1);
    }

    /**
     * Retorna o fator de balanceamento de um nó.
     *
     * @param node Nó verificado.
     *
     * @return Retorna o fator de balanceamento de um nó.
     */
    private int balanceFactor(_Node<T> node) {
        if (node == null) {
            throw new NullPointerException();
        }
        return this.height(node.getRight()) - this.height(node.getLeft());
    }

    @Override
    public boolean add(T element) {
        if (element == null) {
            throw new NullPointerException();
        }
        super.setRoot(this.add(element, super.getRoot()));
        return true;
    }

    @Override
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
        return this.rebalance(node);
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
     *
     * @return Retorna o nó atual.
     */
    private _Node<T> remove(T element, _Node<T> node) throws ElementNotFoundException{
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
            node.setElement(super.min(node.getRight()));
            node.setRight(this.remove(node.getElement(), node.getRight()));
        }
        return this.rebalance(node);
    }

    /**
     * Esse método faz o balanceamento da raiz de uma árvore conforme o fator de balanceamento da raiz.
     * Caso a raiz já esteja balanceada não será feito nenhum balanceamento e será retornado a própria
     * raiz passada como parâmetro.
     *
     * @param node Nó que será balanceado.
     *
     * @return Retorna o nó balanceado.
     */
    private _Node<T> rebalance(_Node<T> node) {
        this.updateHeight(node);
        int balanceFactor = this.balanceFactor(node);
        if (balanceFactor < -1) {
            if (this.balanceFactor(node.getLeft()) <= 0) {
                node = this.rotateRight(node);
            }
            else {
                node = this.rotateLeftRight(node);
            }
        }
        else if (balanceFactor > 1) {
            if (this.balanceFactor(node.getRight()) >= 0) {
                node = this.rotateLeft(node);
            }
            else {
                node = this.rotateRightLeft(node);
            }
        }
        return node;
    }

    /**
     * Faz a rotação dos elementos da árvore para a esquerda.
     *
     * @param root Raiz.
     *
     * @return Retorna a nova raiz.
     */
    private _Node<T> rotateLeft(_Node<T> root) {
        _Node<T> right = root.getRight();
        root.setRight(right.getLeft());
        right.setLeft(root);
        this.updateHeight(root);
        this.updateHeight(right);
        return right;
    }

    /**
     * Faz a rotação dos elementos da árvore para a direita.
     *
     * @param root Raiz.
     *
     * @return Retorna a nova raiz.
     */
    private _Node<T> rotateRight(_Node<T> root) {
        _Node<T> left = root.getLeft();
        root.setLeft(left.getRight());
        left.setRight(root);
        this.updateHeight(root);
        this.updateHeight(left);
        return left;
    }

    /**
     * Faz uma rotação para esquerda com o filho da esquerda da árvore e depois uma rotação para direita
     * com a raiz da árvore.
     *
     * @param root Raiz.
     *
     * @return Retorna a nova raiz.
     */
    private _Node<T> rotateLeftRight(_Node<T> root) {
        _Node<T> newLeft = this.rotateLeft(root.getLeft());
        root.setLeft(newLeft);
        return this.rotateRight(root);
    }

    /**
     * Faz uma rotação para direita com o filho da direita da árvore e depois uma rotação para esquerda
     * com a raiz da árvore.
     *
     * @param root Raiz.
     *
     * @return Retorna a nova raiz.
     */
    private _Node<T> rotateRightLeft(_Node<T> root) {
        _Node<T> newRight = this.rotateRight(root.getRight());
        root.setRight(newRight);
        return this.rotateLeft(root);
    }

}