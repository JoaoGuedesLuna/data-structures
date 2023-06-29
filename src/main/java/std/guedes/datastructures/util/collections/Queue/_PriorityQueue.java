package main.java.std.guedes.datastructures.util.collections.Queue;

/**
 * A fila com prioridade é muito semelhante à fila normal, com exceção de que ela mantém sempre na primeira
 * posição o elemento de maior prioridade armazenado, note que, a estrutura não mantém os elementos ordenados,
 * ela apenas garante que o elemento mais a frente é o de maior prioridade. O paradigma por trás da fila é o
 * FIFO — First In, First Out, ou “o primeiro a entrar é o primeiro a sair”, em tradução livre. Sendo assim,
 * há somente duas formas de se manipular uma fila: 1) Inserir um elemento no final da fila e 2) remover um
 * elemento do início da fila.
 *
 * @param <T> Tipo de dados que a fila armazenará.
 *
 * @author João Guedes.
 */
public class _PriorityQueue<T extends Comparable<T>> extends _DynamicQueue<T>{

    @Override
    public boolean add(T element) {
        if (element == null) {
            throw new NullPointerException();
        }
        if (this.isEmpty()) {
            super.add(element);
        }
        else if (element.compareTo(this.first.getElement()) < 0) {
            this.first = new _Node<>(element, this.first);
            this.size++;
        }
        else {
            _Node<T> current = this.first;
            while (current.getNext() != null) {
                if (element.compareTo(current.getNext().getElement()) < 0) {
                    break;
                }
                current = current.getNext();
            }
            current.setNext(new _Node<>(element, current.getNext()));
            if (current.getNext().getNext() == null) {
                this.last = current.getNext();
            }
            this.size++;
        }
        return true;
    }

}