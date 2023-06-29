package main.java.std.guedes.datastructures.util.collections.graph;

/**
 * Um grafo é um tipo abstrato de dados, assim como listas, pilhas e filas, que permite representar relações
 * binárias entre dois objetos. Em um grafo, os objetos são representados por nodos (também chamados de
 * vértices) e as relações entre dois objetos por uma aresta.
 *
 * @param <T> Tipo de dado que o grafo armazenará.
 *
 * @author João Guedes.
 */
public interface _Graph<T> {

    /**
     * Retorna true caso a estrutura esteja vazia.
     *
     * @return Retorna true caso a estrutura esteja vazia.
     */
    boolean isEmpty();

    /**
     * Retorna true caso a estrutura esteja cheia.
     *
     * @return Retorna true caso a estrutura esteja cheia.
     */
    boolean isFull();

    /**
     * Adiciona um vertice à estrutura. Caso a estrutura esteja cheia será retorado o valor false.
     *
     * @param element Elemento que será inserido.
     *
     * @return Retorna o true caso e elemento seja adicionado.
     */
    boolean addVertex(T element);

    /**
     * Adiciona uma aresta.
     *
     * @param src Vértice origem.
     *
     * @param dest Vértice destino.
     *
     * @return Retorna true caso a aresta seja adicionada.
     */
    boolean addEdge(T src, T dest);

    /**
     * Retorna o grau de um vértice.
     *
     * @param element Elemento procurado.
     *
     * @return Retorna o grau de um vértice.
     */
    int degree(T element);

    /**
     * Retorna o índice do elemento ou -1 caso o elemento não seja encontrado.
     *
     * @param element Elemento procurado
     *
     * @return Retorna o índice do elemento ou -1 caso o elemento não seja encontrado.
     */
    int indexOf(T element);

    /**
     * Retorna true caso o elemento seja um vértice estrutura.
     *
     * @param element Elemento procurado.
     *
     * @return Retorna true caso o elemento seja um vértice estrutura.
     */
    boolean contains(T element);

    /**
     * Realiza um busca em profundidade.
     *
     * @param src Vértice origem.
     *
     * @param dest Vértice destino.
     *
     * @return Retorna true caso o caminho seja encontrado.
     */
    boolean depthSearch(T src, T dest);

    /**
     * Realiza uma busca em largura.
     *
     * @param src Vértice origem.
     *
     * @param dest Vértice destino.
     *
     * @return Retorna true caso o caminho seja encontrado.
     */
    boolean breadthSearch(T src, T dest);

    /**
     * Retorna todos os vértices da estrutura como String.
     *
     * @return Retorna todos os vértices da estrutura como String.
     */
    String vertices();

}