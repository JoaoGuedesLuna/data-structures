package main.java.std.guedes.datastructures.util.collections.stack;

import main.java.std.guedes.datastructures.util.collections.list._List;

/**
 * Stack é uma coleção de itens ou estrutura de dados, onde a adição e a remoção de um item é feita
 * pela mesma saída. Essa saída é comumente chamada “top” e o extremo oposto é chamado “base”. O
 * item mais recentemente adicionado será o primeiro removido em caso de remoção, já que estará
 * no topo (ou “top”).
 *
 * @param <T> Tipo de dados que a pilha armazenará.
 *
 * @author João Guedes.
 */
public interface _Stack<T> extends _List<T> {

    /**
     * Adiciona um item no topo da estrutura.
     *
     * @param element Elemento que será adicionado.
     *
     * @return Retorna o elemento adicionado.
     */
    public T push(T element);

    /**
     * Remove um elemento do topo da estrutura.
     *
     * @return Retorna o elemento removido.
     */
    public T pop();

    /**
     * Retorna o elemento que se encontra no topo da estrutura.
     *
     * @return Retorna o elemento que se encontra no topo da estrutura.
     */
    public T peek();

    /**
     * Retorna a posição do elemento na estrutura, considerando que o elemento mais ao topo na
     * pilha é o elemento 1. Caso o elemento não se encontre na pilha será retornado o valor -1.
     *
     * @param element Elemento procurado.
     *
     * @return Retorna a posição do elemento na estrutura, considerando que o elemento mais ao
     * topo na pilha é o elemento 1. Caso o elemento não se encontre na pilha será retornado
     * o valor -1.
     */
    public int search(T element);

}