package dev.guedes.datastructures.util.collection.internal.nodes;

import java.util.Objects;

/**
 * Represents a node in a graph with an associated element and weight.
 * <p>
 * This class is primarily used in graph-related operations (e.g., Dijkstra's algorithm),
 * where nodes are compared based on edge weights. Equality is determined solely by
 * the node's element, regardless of weight.
 *
 * @param <E> the type of the element stored in the node
 *
 * @author João Guedes
 */
public record GraphNode<E>(int index, E element, int weight) implements Comparable<GraphNode<E>> {

    public GraphNode(E element, int weight) { this(0, element, weight); }

    public GraphNode(int index, int weight) { this(index, null, weight); }

    public GraphNode(E element) { this(element, 0); }

    @Override
    public int compareTo(GraphNode<E> o) { return Integer.compare(this.weight, o.weight); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || this.getClass() != o.getClass()) return false;

        GraphNode<?> graphNode = (GraphNode<?>) o;
        return Objects.equals(this.element, graphNode.element);
    }

    @Override
    public int hashCode() { return Objects.hash(element, weight); }
}
