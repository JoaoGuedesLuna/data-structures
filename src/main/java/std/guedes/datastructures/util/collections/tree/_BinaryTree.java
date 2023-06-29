package main.java.std.guedes.datastructures.util.collections.tree;

import java.util.Objects;

/**
 * Essa classe representa uma árvore binária. Numa árvore binária cada elemento é representado por um
 * nó, e cada nó deve conter no máximo dois filhos.
 *
 * @param <T> Tipo de dado que a árvore armazenará.
 *
 * @author João Guedes.
 */
public abstract class _BinaryTree<T> {

    /**
     * Raiz da árvore.
     */
    private _Node<T> root;

    protected _BinaryTree(_Node<T> root) {
        this.root = root;
    }

    protected _BinaryTree(T element) {
        this.root = new _Node<>(element);
    }

    protected _BinaryTree(){
        this.root = null;
    }

    protected _Node<T> getRoot() {
        return this.root;
    }

    protected void setRoot(_Node<T> root) {
        this.root = root;
    }

    /**
     * Retorna a altura de toda árvore, começando pela raiz, com base na quantidade de arestas.
     *
     * @return Retorna a altura de toda árvore, começando pela raiz, com base na quantidade de arestas.
     */
    public int height() {
        if (this.isEmpty()) {
            return 0;
        }
        return this.height(this.root);
    }

    /**
     * Retorna a altura de uma subárvore da árvore, com base na quantidade de arestas. Caso o valor
     * passado como parâmetro seja a raiz o valor retornado será a altura de toda árvore.
     *
     * @param root Nó inicial.
     *
     * @return Retorna a altura de uma subárvore da árvore, com base na quantidade de arestas. Caso
     * o valor passado como parâmetro seja a raiz o valor retornado será a altura de toda árvore.
     */
    private int height(_Node<T> root) {
        int heightLeft = -1, heightRight = -1;
        if (root.getLeft() != null) {
            heightLeft = this.height(root.getLeft());
        }
        if (root.getRight() != null) {
            heightRight = this.height(root.getRight());
        }
        return heightRight > heightLeft ? ++heightRight : ++heightLeft;
    }

    /**
     * Retorna se a árvore está vazia.
     *
     * @return true caso a árvore não contenha elementos ou false caso ela contenha.
     */
    public boolean isEmpty() {
        return this.root == null;
    }

    /**
     * Adiciona um elemento na árvore.
     *
     * @param element Elemento que será adicionado na árvore.
     *
     * @return Retorna true caso o elemento tenha sido adicionado.
     */
    public abstract boolean add(T element);

    /**
     * Remove um elemento da árvore.
     *
     * @param element Elemento da árvore.
     *
     * @return Retorna true caso o elemento tenha sido removido.
     */
    public abstract boolean remove(T element);

    /**
     * Retorna o menor elemento da árvore.
     *
     * @return Retorna o menor elemento da árvore.
     */
    public abstract T min();

    /**
     * Retorna o maior elemento da árvore.
     *
     * @return Retorna o maior elemento da árvore.
     */
    public abstract T max();

    /**
     * Faz a busca de um elemento e retorna uma árvore a partir desse elemento.
     *
     * @param element Elemento buscado.
     *
     * @return uma subárvore.
     */
    public abstract _BinaryTree<T> search(T element);

    /**
     * Limpa toda a árvore, removendo todos os elementos.
     */
    public void clear() {
        this.setRoot(this.clear(this.root));
    }

    /**
     * Limpa toda a árvore, removendo todos os elementos.
     *
     * @param node Nó atual.
     */
    private _Node<T> clear(_Node<T> node) {
        if (node == null) {
            return null;
        }
        node.setLeft(this.clear(node.getLeft()));
        node.setRight(this.clear(node.getRight()));
        return null;
    }

    /**
     * Faz um percurso em pré ordem (primeiro a raiz, depois todos os da esquerda e por último os elementos
     * da direita).
     *
     * @return Retorna uma String contendo todos os elementos da árvore em pré ordem.
     */
    public String preorderTraversal() {
        return this.preorderTraversal(this.root);
    }

    /**
     * Faz um percurso em pré ordem (primeiro a raiz, depois todos os da esquerda e por último os elementos
     * da direita).
     *
     * @param node Nó atual.
     *
     * @return Retorna uma String contendo todos os elementos da árvore em pré ordem.
     */
    private String preorderTraversal(_Node<T> node) {
        if (this.isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        this.preorderTraversal(node, sb);
        sb.delete(sb.length()-2, sb.length());
        return sb.append("]").toString();
    }

    /**
     * Faz um percurso em pré ordem (primeiro a raiz, depois todos os da esquerda e por último os elementos
     * da direita).
     *
     * @param node Nó atual.
     *
     * @param sb StringBuilder que armazenará os dados.
     */
    private void preorderTraversal(_Node<T> node, StringBuilder sb) {
        if (node == null) {
            return;
        }
        sb.append(node.getElement()).append(", ");
        this.preorderTraversal(node.getLeft(), sb);
        this.preorderTraversal(node.getRight(), sb);
    }

    /**
     * Faz um percurso em ordem simétrica (todos os da esquerda primeiro, depois vem a raiz e por último
     * os elementos da direita).
     *
     * @return Todos os elementos da árvore em ordem simétrica.
     */
    public String inorderTraversal() {
        return this.inorderTraversal(this.root);
    }

    /**
     * Faz um percurso em ordem simétrica (todos os da esquerda primeiro, depois vem a raiz e por último
     * os elementos da direita).
     *
     * @param node Nó atual.
     *
     * @return Todos os elementos da árvore em ordem simétrica.
     */
    private String inorderTraversal(_Node<T> node) {
        if (this.isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        this.inorderTraversal(node, sb);
        sb.delete(sb.length()-2, sb.length());
        return sb.append("]").toString();
    }

    /**
     * Faz um percurso em ordem simétrica (todos os da esquerda primeiro, depois vem a raiz e por último
     * os elementos da direita).
     *
     * @param node Nó atual.
     *
     * @param sb StringBuilder que armazenará os dados.
     */
    private void inorderTraversal(_Node<T> node, StringBuilder sb) {
        if (node == null) {
            return;
        }
        this.inorderTraversal(node.getLeft(), sb);
        sb.append(node).append(", ");
        this.inorderTraversal(node.getRight(), sb);
    }

    /**
     * Faz um percurso em pós ordem (todos os da esquerda primeiro. Só se visita a raiz após se visitar
     * todos os outros nós).
     *
     * @return Todos os elementos da árvore em pós ordem.
     */
    public String postorderTraversal() {
        return this.postorderTraversal(this.root);
    }

    /**
     * Faz um percurso em pós ordem (todos os da esquerda primeiro. Só se visita a raiz após se visitar
     * todos os outros nós).
     *
     * @param node Nó atual.
     *
     * @return Todos os elementos da árvore em pós ordem.
     */
    private String postorderTraversal(_Node<T> node) {
        if (this.isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        this.postorderTraversal(node, sb);
        sb.delete(sb.length()-2, sb.length());
        return sb.append("]").toString();
    }

    /**
     * Faz um percurso em pós ordem (todos os da esquerda primeiro. Só se visita a raiz após se visitar
     * todos os outros nós).
     *
     * @param node Nó atual.
     *
     * @param sb StringBuilder que armazenará os dados.
     */
    private void postorderTraversal(_Node<T> node, StringBuilder sb) {
        if (node == null) {
            return;
        }
        this.postorderTraversal(node.getLeft(), sb);
        this.postorderTraversal(node.getRight(), sb);
        sb.append(node).append(", ");
    }

    @Override
    public String toString() {
        return this.inorderTraversal();
    }

    /**
     * Essa classe representa um nó.
     *
     * @param <T> Tipo de dado que estará contido dentro do nó.
     *
     * @author João Guedes.
     */
    protected static class _Node<T> {

        /**
         * Elemento contido dentro do nó.
         */
        private T element;
        /**
         * Nó da esquerda.
         */
        private _Node<T> left;
        /**
         * Nó da direita.
         */
        private _Node<T> right;
        /**
         * Altura do nó.
         */
        private int height;

        public _Node(T element, _Node<T> left, _Node<T> right) {
            this.element = element;
            this.left = left;
            this.right = right;
            this.height = 0;
        }

        public _Node(T element) {
            this.element = element;
            this.left = null;
            this.right = null;
            this.height = 0;
        }

        public T getElement() {
            return this.element;
        }

        public void setElement(T element) {
            this.element = element;
        }

        public _Node<T> getLeft() {
            return this.left;
        }

        public void setLeft(_Node<T> left) {
            this.left = left;
        }

        public _Node<T> getRight() {
            return this.right;
        }

        public void setRight(_Node<T> right) {
            this.right = right;
        }

        public int getHeight() {
            return this.height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            _Node<?> node = (_Node<?>) o;
            return this.height == node.height &&
                    Objects.equals(this.element, node.element) &&
                    Objects.equals(this.left, node.left) &&
                    Objects.equals(this.right, node.right);
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.element, this.left, this.right, this.height);
        }

        @Override
        public String toString() {
            return this.element.toString();
        }

    }

    /**
     * Exception personalizada para casos em que determinado elemento não for encontrado na estrutura.
     */
    protected static class ElementNotFoundException extends Exception {}

    /**
     * Exception personalizada para casos em que a árvore está vazia.
     */
    protected static class EmptyTreeException extends RuntimeException {}

}