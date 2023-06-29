package main.java.std.guedes.datastructures.util.collections.graph;

import main.java.std.guedes.datastructures.util.collections.Queue._DynamicQueue;
import main.java.std.guedes.datastructures.util.collections.Queue._Queue;
import main.java.std.guedes.datastructures.util.collections.stack._DynamicStack;
import main.java.std.guedes.datastructures.util.collections.stack._Stack;

/**
 * Uma matriz de adjacência é uma das formas de se representar um grafo. Sua representação é mais eficiente para
 * casos de grafo não direcionado. Essa implementação diz respeito a grafo simples não-direcionado.
 *
 * @param <T> Tipo de dado que a matriz de adjacência armazenará.
 *
 * @author João Guedes.
 */
public class _AdjacencyMatrix<T> implements _Graph<T> {

    /**
     * Matriz interna.
     */
    private int[][] matrix;
    /**
     * Vetor de vértices.
     */
    private T[] vertices;
    /**
     * Número de vértices.
     */
    private int numVertices;

    public _AdjacencyMatrix(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + size);
        }
        this.matrix = new int[size][size];
        this.vertices = (T[]) new Object[size];
        this.numVertices = 0;
    }

    @Override
    public boolean isEmpty() {
        return this.numVertices == 0;
    }

    @Override
    public boolean isFull() {
        return this.numVertices == this.vertices.length;
    }

    @Override
    public boolean addVertex(T element) {
        if (this.isFull()) {
            return false;
        }
        this.vertices[numVertices] = element;
        this.numVertices++;
        return true;
    }

    @Override
    public boolean addEdge(T src, T dest) {
        if (src == null || dest == null) {
            throw new NullPointerException();
        }
        int srcIndex = this.indexOf(src);
        int destIndex = this.indexOf(dest);
        if (srcIndex == -1 || destIndex == -1) {
            return false;
        }
        this.matrix[srcIndex][destIndex] = 1;
        this.matrix[destIndex][srcIndex] = 1; // remover no caso de grafo direcionado.
        return true;
    }

    @Override
    public int degree(T element) {
        if (element == null) {
            throw new NullPointerException();
        }
        int index = this.indexOf(element);
        if (index == -1) {
            throw new IllegalArgumentException("Element not found: " + element);
        }
        int degree = 0;
        for (int c = 0; c < this.matrix[index].length; c++) {
            if (this.matrix[index][c] != 0) {
                degree++;
            }
        }
        return degree;
    }

    @Override
    public int indexOf(T element) {
        if (element == null) {
            throw new NullPointerException();
        }
        for (int i = 0; i < this.numVertices; i++) {
            if (element.equals(this.vertices[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean contains(T element) {
        return this.indexOf(element) >= 0;
    }

    @Override
    public boolean depthSearch(T src, T dest) {
        if (src == null || dest == null) {
            throw new NullPointerException();
        }
        if (this.indexOf(src) < 0 || this.indexOf(dest) < 0) {
            return false;
        }
        _Stack<T> queueVertices = new _DynamicStack<>();
        boolean[] visited = new boolean[this.numVertices];
        queueVertices.add(src);
        int index;
        while (true) {
            T current = queueVertices.pop();
            if (dest.equals(current)) {
                return true;
            }
            index = this.indexOf(current);
            for (int c = 0; c < this.numVertices; c++) {
                if (this.matrix[index][c] != 0 && !visited[c]) {
                    queueVertices.add(this.vertices[c]);
                    visited[c] = true;
                }
            }
            if (queueVertices.isEmpty()) {
                return false;
            }
        }
    }

    @Override
    public boolean breadthSearch(T src, T dest) {
        if (src == null || dest == null) {
            throw new NullPointerException();
        }
        if (this.indexOf(src) < 0 || this.indexOf(dest) < 0) {
            return false;
        }
        _Queue<T> queueVertices = new _DynamicQueue<>();
        boolean[] visited = new boolean[this.matrix.length];
        queueVertices.add(src);
        int index;
        while (true) {
            T current = queueVertices.remove();
            if (dest.equals(current)) {
                return true;
            }
            index = this.indexOf(current);
            for (int c = 0; c < this.numVertices; c++) {
                if (this.matrix[index][c] != 0 && !visited[c]) {
                    queueVertices.add(this.vertices[c]);
                    visited[c] = true;
                }
            }
            if (queueVertices.isEmpty()) {
                return false;
            }
        }
    }

    @Override
    public String vertices() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < this.numVertices-1; i++) {
            sb.append(this.vertices[i]).append(", ");
        }
        if (!this.isEmpty()) {
            sb.append(this.vertices[this.numVertices-1]);
        }
        return sb.append("]").toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        T vertex;
        for (int r = 0; r < this.matrix.length; r++) {
            vertex = this.vertices[r];
            if (vertex == null) {
                sb.append("           | ");
            }
            else {
                sb.append(vertex.toString().transform((v) -> {
                    StringBuilder s = new StringBuilder();
                    for (int i = 0; i < 10; i++) {
                        if (v.length() > i) {
                            s.append(v.charAt(i));
                        }
                        else {
                            s.append(" ");
                        }
                    }
                    return s.toString();
                }).toString()).append(" | ");
            }
            for (int c = 0; c < this.matrix[r].length; c++) {
                sb.append(this.matrix[r][c]).append(" ");
            }
            sb.append("|\n");
        }
        return sb.toString();
    }

}