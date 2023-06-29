package main.java.std.guedes.datastructures.util.collections.Queue;

import main.java.std.guedes.datastructures.util.collections._Collection;

/**
 * Fila são estruturas de dados bastante utilizadas na computação, onde o primeiro elemento a ser inserido,
 * será também o primeiro a ser retirado. Desta forma, serão adicionados elementos no fim e removê-los pelo
 * início. A estrutura de dados fila segue um padrão conhecido como FIFO (first-in first-out), onde o
 * primeiro a entrar é o primeiro a sair.
 *
 * @param <T> Tipo de dados que a fila armazenará.
 *
 * @author João Guedes.
 */
public interface _Queue<T> extends _Collection<T> {

    /**
     * Remove o primeiro elemento da fila.
     *
     * @return Retorna o primeiro elemento da fila removido.
     */
    public T remove();

    /**
     * Retorna o elemento que está no início da fila.
     *
     * @return Elemento que está no início da fila.
     */
    public T peek();

}