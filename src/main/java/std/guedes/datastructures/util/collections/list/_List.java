package main.java.std.guedes.datastructures.util.collections.list;

import main.java.std.guedes.datastructures.util.collections._Collection;

/**
 * Uma lista é uma coleção ordenada de elementos do mesmo tipo, conhecida por sequência. Os elementos
 * de uma lista podem ser acessados pela sua posição, isto é, seu índice.
 *
 * @param <T> Tipo de dado que a lista armazenará.
 *
 * @author João Guedes.
 */
public interface _List<T> extends _Collection<T> {

    /**
     * Adiciona um elemento na estrutura na posição indicada. Caso a posição indicada seja inválida
     * será lançada uma exceção.
     *
     * @param index Posição em que o elemento será adicionado.
     *
     * @param element Elemento que será adicionado na estrutura.
     */
    void add(int index, T element);

    /**
     * Remove um elemento da estrutura com base em sua posição. Caso a posição indicada seja inválida
     * será lançada uma exceção.
     *
     * @param index Posição do elemento que será removido.
     *
     * @return Retorna o elemento removido.
     */
    T remove(int index);

    /**
     * Retorna um elemento contido na estrutura com base em sua posição. Caso a posição seja inválida
     * será lançada uma exceção.
     *
     * @param index Posição do elemento na estrutura que está sendo buscado.
     *
     * @return Retorna um elemento contido na estrutura com base em sua posição.
     */
     T get(int index);

    /**
     * Faz a substituição de um elemento da estrutura com base em sua posição. Caso a posição seja
     * inválida será lançada uma exceção.
     *
     * @param index Posição do elemento que será substituído.
     *
     * @param element Novo elemento que ocupará a posição substituída.
     *
     * @return Retorna o elemento que estava contido na estrutura anteriormente.
     */
     T set(int index, T element);

    /**
     * Retorna a primeira posição de um elemento contido na estrutura. Caso o elemento não esteja
     * contido na estrutura o valor retornado será -1.
     *
     * @param element Elemento pela qual está se buscado a posição.
     *
     * @return Primeira posição do elemento na estrutura. Caso ele não esteja contido na estrutura
     * o valor retornado será -1.
     */
    int indexOf(T element);

    /**
     * Retorna a última posição de um elemento contido na estrutura. Caso o elemento não esteja contido
     * na estrutura o valor retornado será -1.
     *
     * @param element Elemento pela qual está se buscado a posição.
     *
     * @return Última posição do elemento na estrutura. Caso ele não esteja contido na estrutura o valor
     * retornado será -1.
     */
    int lastIndexOf(T element);

}