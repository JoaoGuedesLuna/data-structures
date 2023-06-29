package main.java.std.guedes.datastructures.util.collections.hashtable;

import java.util.Objects;

/**
 * Uma tabela de dispersão (também conhecida por tabela de espalhamento ou tabela hash) é uma estrutura
 * de dados especial, que associa chaves de pesquisa a valores, por uma função. Seu objetivo é, a partir
 * de uma chave simples, fazer a busca rápida e obter o valor desejado.
 *
 * @param <K> Tipo do dado da chave.
 *
 * @param <V> Tipo de dado do valor.
 *
 * @author João Guedes.
 */
public class _Hashtable<K, V> implements _Map<K, V> {

    /**
     * Array interno da tabela hash.
     */
    private _Entry<K, V>[] table;
    /**
     * Tamanho literal do array interno da tabela hash.
     */
    private final int CAPACITY;
    /**
     * Tamanho da tabela hash. Considera as posições ocupadas e não o tamanho literal da estrutura.
     */
    private int size;

    public _Hashtable(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
        this.CAPACITY = initialCapacity;
        this.table = new _Entry[this.CAPACITY];
        this.size = 0;
    }

    public _Hashtable() {
        this.CAPACITY = 10;
        this.table = new _Entry[this.CAPACITY];
        this.size = 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean containsKey(K key) {
        return this.get(key) != null;
    }

    @Override
    public boolean containsValue(V value) {
        if (value == null) {
            throw new NullPointerException();
        }
        return this.getEntryByValue(value) != null;
    }

    @Override
    public V get(K key) {
        _Entry<K, V> entry = this.getEntryByKey(key);
        return entry == null ? null : entry.getValue();
    }

    @Override
    public V put(K key, V value) {
        if (value == null) {
            throw new NullPointerException();
        }
        int index = this.hash(key);
        _Entry<K, V> current = this.table[index];
        for (; current != null; current = current.getNext()) {
            if(key.equals(current.getKey())) {
                current.setValue(value);
                return value;
            }
        }
        current = this.table[index];
        this.table[index] = new _Entry<>(key, value, current);
        this.size++;
        return value;
    }

    @Override
    public V remove(K key) {
        int index = this.hash(key);
        _Entry<K, V> current = this.table[index];
        for (_Entry<K, V> previous = null; current != null;) {
            if (key.equals(current.getKey())) {
                V oldValue = current.getValue();
                if (previous == null) {
                    this.table[index] = current.getNext();
                }
                else {
                    previous.setNext(current.getNext());
                }
                current.setKey(null);
                current.setValue(null);
                current.setNext(null);
                this.size--;
                return oldValue;
            }
            previous = current;
            current = current.getNext();
        }
        return null;
    }

    @Override
    public void clear() {
        _Entry<K, V> current;
        _Entry<K, V> next;
        for (int i = 0; i < this.table.length; i++) {
            current = this.table[i];
            while (current != null) {
                next = current.getNext();
                current.setKey(null);
                current.setValue(null);
                current.setNext(null);
                current = next;
                this.size--;
            }
            this.table[i] = null;
        }
    }

    @Override
    public boolean remove(K key, V value) {
        int index = this.hash(key);
        _Entry<K, V> current = this.table[index];
        for (_Entry<K, V> previous = null; current != null;) {
            if (key.equals(current.getKey()) && value.equals(current.getValue())) {
                if (previous == null) {
                    this.table[index] = current.getNext();
                }
                else {
                    previous.setNext(current.getNext());
                }
                current.setKey(null);
                current.setValue(null);
                current.setNext(null);
                this.size--;
                return true;
            }
            previous = current;
            current = current.getNext();
        }
        return false;
    }

    @Override
    public boolean replace(K key, V oldValue, V newValue) {
        if (newValue == null) {
            throw new NullPointerException();
        }
        _Entry<K, V> entry = this.getEntry(key, oldValue);
        if (entry != null) {
            entry.setValue(newValue);
            return true;
        }
        return false;
    }

    @Override
    public V replace(K key, V newValue) {
        if (newValue == null) {
            throw new NullPointerException();
        }
        _Entry<K, V> entry = this.getEntryByKey(key);
        if (entry != null) {
            V oldValue = entry.getValue();
            entry.setValue(newValue);
            return oldValue;
        }
        return null;
    }

    /**
     * Esse método retorna se um determinado valor está contida na estrutura.
     *
     * @param value Valor procurada.
     *
     * @return Retorna se um determinado valor está contida na estrutura.
     */
    public boolean contains(V value) {
        return this.containsValue(value);
    }

    /**
     * Retorna um determinado registro conforme a chave e o valor passado como parâmetro.
     *
     * @param key Chave pela qual se fará a busca.
     *
     * @param value Valor pelo qual se fará a busca.
     *
     * @return Retorna um determinado registro conforme a chave e o valor passado como parâmetro.
     */
    private _Entry<K, V> getEntry(K key, V value) {
        _Entry<K, V> current = this.getEntryByKey(key);
        for (; current != null; current = current.getNext()) {
            if (key.equals(current.getKey()) && value.equals(current.getValue())) {
                return current;
            }
        }
        return null;
    }

    /**
     * Retorna um determinado registro conforme a chave passada como parâmetro.
     *
     * @param key Chave pela qual se fará a busca.
     *
     * @return Retorna um determinado registro conforme a chave passada como parâmetro.
     */
    private _Entry<K, V> getEntryByKey(K key) {
        _Entry<K, V> current = this.table[this.hash(key)];
        while (current != null) {
            if (key.equals(current.getKey())) {
                return current;
            }
            current = current.getNext();
        }
        return null;
    }

    /**
     * Retorna um determinado registro conforme o valor passado como parâmetro.
     *
     * @param value Valor pelo qual se fará a busca.
     *
     * @return Retorna um determinado registro conforme o valor passado como parâmetro.
     */
    private _Entry<K, V> getEntryByValue(V value) {
        for(_Entry<K, V> current : this.table) {
            for (; current != null; current = current.getNext()) {
                if (value.equals(current.getValue())) {
                    return current;
                }
            }
        }
        return null;
    }

    /**
     * Esse método gera o código hash conforme a capacidade do array interno.
     *
     * @return Retorna a posição hash de uma chave.
     */
    private int hash(K key) {
        int hash = key.hashCode() % this.CAPACITY;
        if (hash < 0) {
            hash *= -1;
        }
        return hash;
    }

    @Override
    public String toString() {
        if (this.isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        for (int i = 0; ; i++) {
            if (this.table[i] != null) {
                if (sb.length() > 1) {
                    sb.append(", ");
                }
                sb.append(this.table[i]);
            }
            if (i+1 == this.table.length) {
                return sb.append("}").toString();
            }
        }
    }

    /**
     * Essa Classe representa um registro que estará na tabela hash.
     *
     * @param <K> Tipo do dado da chave.
     *
     * @param <V> Tipo de dado do valor.
     *
     * @author João Guedes.
     */
    private static class _Entry<K, V> {

        /**
         * Chave do registro.
         */
        private K key;
        /**
         * Valor do registro.
         */
        private V value;
        /**
         * Próximo registro.
         */
        private _Entry<K, V> next;

        public _Entry(K key, V value, _Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public _Entry(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }

        public K getKey() {
            return this.key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return this.value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public _Entry<K, V> getNext() {
            return this.next;
        }

        public void setNext(_Entry<K, V> next) {
            this.next = next;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            _Entry<?, ?> entry = (_Entry<?, ?>) o;
            return  Objects.equals(this.key, entry.key) &&
                    Objects.equals(this.value, entry.value) &&
                    Objects.equals(this.next, entry.next);
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.key, this.value, this.next);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.key).append("=").append(this.value);
            if (this.next != null) {
                sb.append(", ").append(this.next);
            }
            return sb.toString();
        }

    }

}