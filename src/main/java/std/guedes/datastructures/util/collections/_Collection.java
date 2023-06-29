package main.java.std.guedes.datastructures.util.collections;

 /**
 * Classe para representar e tratar grupos de dados como uma única unidade.
 *
 * @param <T> Tipo de dado que será armazenado na estrutura
 *
 * @author João Guedes.
 */
public interface _Collection<T> {

    /**
     * Retorna o número de posições ocupadas na estrutura.
     *
     * @return Retorna o número de posições ocupadas na estrutura.
     */
    int size();

    /**
     * Diz se a estrutura está vazia, se não contém elementos.
     *
     * @return Retorna true caso esteja vazia ou false caso não esteja vazia.
     */
    boolean isEmpty();

    /**
     * Adiciona um elemento na estrutura.
     *
     * @param element Elemento que será adicionado na estrutura.
     *
     * @return Retorna true caso o elemento tenha sido adicionado ou false caso não tenha
     * sido adicionado.
     */
    boolean add(T element);

    /**
     * Remove um elemento da estrutura. Caso o elemento não esteja contido na estrutura será retornado
     * o valor false.
     *
     * @param element Elemento que será removido.
     *
     * @return Retorna true caso o elemento tenha sido encontrado e removido ou false caso o elemento
     * não esteja contido na estrutura.
     */
    boolean remove(T element);

    /**
     * Verifica se um elemento está contido na estrutura.
     *
     * @param element Elemento buscado.
     *
     * @return true caso o elemento esteja contido na estrutura ou false caso ele não esteja contido
     * na estrutura.
     */
    boolean contains(T element);

    /**
     * Faz a limpeza da estrutura, removendo todos os elementos contidos.
     */
    void clear();

    /**
     * Retorna um array contendo todos os elementos da estrutura.
     *
     * @return Retorna um array contendo todos os elementos da estrutura.
     */
    Object[] toArray();

}