package main.java.std.guedes.datastructures.util.collections.hashtable;

/**
 * Essa ‘interface’ é um objeto que mapeia valores para chaves, ou seja, através da chave consegue ser
 * acessado o valor configurado, sendo que a chave não pode ser repetida ao contrário do valor, mas
 * se caso tiver uma chave repetida é sobrescrito pela última chamada.
 *
 * @param <K> Tipo do dado da chave.
 *
 * @param <V> Tipo de dado do valor.
 *
 * @author João Guedes.
 */
public interface _Map<K, V> {

    /**
     * Retorna o número de registros presentes na estrutura.
     *
     * @return Retorna o número de registros presentes na estrutura.
     */
    int size();

    /**
     * Retorna true caso a estrutura não contenha nenhum registro.
     *
     * @return Retorna true caso a estrutura não contenha nenhum registro.
     */
    boolean isEmpty();

    /**
     * Esse método retorna se uma determinada chave está contida na estrutura.
     *
     * @param key Chave procurada.
     *
     * @return Retorna se uma determinada chave está contida na estrutura.
     */
    boolean containsKey(K key);

    /**
     * Esse método retorna se um determinado valor está contido na estrutura.
     *
     * @param value Valor procurado.
     *
     * @return Retorna se um determinado valor está contido na estrutura.
     */
    boolean containsValue(V value);

    /**
     * Retorna um valor conforme a chave associada. Caso a chave não existe na estrutura será retornado
     * o valor null. A chave não pode ser null.
     *
     * @param key Chave pela qual se fará a busca.
     *
     * @return Retorna um valor conforme a chave associada.
     */
    V get(K key);

    /**
     * Método que insere um registro na estrutura. Caso já exista uma chave igual, apenas o valor do
     * registro já existente será substituído. A chave e o valor não podem ser null.
     *
     * @param key Chave do registro.
     *
     * @param value Valor do registro.
     */
    V put(K key, V value);

    /**
     * Remove um elemento da estrutura conforme a chave. A chave não deve ser null.
     *
     * @param key Chave que será procurada para remoção.
     *
     * @return Retorna o valor do elemento removido.
     */
    V remove(K key);

    /**
     * Faz a limpeza da estrutura, removendo todos os elementos.
     */
    void clear();

    /**
     * Remove um elemento da estrutura conforme a chave e o valor. A chave e o valor não deve ser null.
     *
     * @param key Chave que será procurada para remoção.
     *
     * @param value Valor que será procurado para remoção.
     *
     * @return Retorna true caso o elemento seja removido.
     */
    boolean remove (K key, V value);

    /**
     * Substitui o registro especificado pela chave e pelo valor mudando seu valor. A mudança só será
     * feita se já existir um registro com a chave e com o mesmo valor passado como parâmetro.
     *
     * @param key Chave especificada para busca.
     *
     * @param oldValue Valor especificado para busca.
     *
     * @param newValue Novo valor do registro.
     *
     * @return Retorna true caso alguma substituição seja feita.
     */
    boolean replace(K key, V oldValue, V newValue);

    /**
     * Substitui o registro especificado pela chave mudando seu valor por um novo valor. A mudança só
     * será feita se já existir um registro com a chave passada como parâmetro.
     *
     * @param key Chave especificada para busca.
     *
     * @param newValue Novo valor do registro.
     *
     * @return Retorna o antigo valor associado ao registro.
     */
    V replace(K key, V newValue);

}