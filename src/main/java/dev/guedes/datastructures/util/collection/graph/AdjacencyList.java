package dev.guedes.datastructures.util.collection.graph;

import dev.guedes.datastructures.util.Objects;
import dev.guedes.datastructures.util.collection.Collection;
import dev.guedes.datastructures.util.collection.internal.nodes.GraphNode;
import dev.guedes.datastructures.util.collection.internal.validation.Validator;
import dev.guedes.datastructures.util.collection.list.List;
import dev.guedes.datastructures.util.collection.list.SinglyLinkedList;
import dev.guedes.datastructures.util.collection.map.Hashtable;
import dev.guedes.datastructures.util.collection.map.Map;
import dev.guedes.datastructures.util.collection.queue.LinkedQueue;
import dev.guedes.datastructures.util.collection.queue.PriorityQueue;
import dev.guedes.datastructures.util.collection.queue.Queue;
import dev.guedes.datastructures.util.collection.set.ArraySet;
import dev.guedes.datastructures.util.collection.set.LinkedSet;
import dev.guedes.datastructures.util.collection.set.Set;
import dev.guedes.datastructures.util.collection.stack.LinkedStack;
import dev.guedes.datastructures.util.collection.stack.Stack;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * An implementation of a graph using an adjacency list representation.
 * This class supports both directed and undirected graphs with weighted edges.
 *
 * @param <E> the type of elements (vertices) maintained by this graph
 *
 * @author João Guedes
 */
public class AdjacencyList<E> implements Graph<E> {
    private static final int DEFAULT_INITIAL_CAPACITY = 5;

    private final Map<E, List<GraphNode<E>>> adjacencyList;
    private final boolean isDirected;

    public AdjacencyList(int initialCapacity, boolean isDirected) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Initial capacity must be positive. Provided value: " + initialCapacity + ".");
        }

        this.isDirected = isDirected;
        this.adjacencyList = new Hashtable<>(initialCapacity);
    }

    public AdjacencyList(boolean isDirected) { this(DEFAULT_INITIAL_CAPACITY, isDirected); }

    @Override
    public int size() { return adjacencyList.size(); }

    @Override
    public boolean isEmpty() { return adjacencyList.isEmpty(); }

    @Override
    public boolean addVertex(E vertex) {
        if (containsVertex(vertex)) return false;

        adjacencyList.put(vertex, new LinkedSet<>());
        return true;
    }

    @Override
    public boolean removeVertex(E vertex) {
        Validator.validateVertexNotNull(vertex);

        if (adjacencyList.remove(vertex) == null) return false;

        GraphNode<E> vertexNode = new GraphNode<>(vertex);

        adjacencyList.values()
                .forEach(edge -> edge.remove(vertexNode));

        return true;
    }

    @Override
    public boolean containsVertex(E vertex) {
        Validator.validateVertexNotNull(vertex);
        return adjacencyList.containsKey(vertex);
    }

    @Override
    public Set<E> vertices() { return adjacencyList.keySet(); }

    @Override
    public boolean addEdge(E source, E destination, int weight) {
        Validator.validateVerticesNotNull(source, destination);
        Validator.validatePositiveWeight(weight);

        if (source.equals(destination)) return false;

        List<GraphNode<E>> sourceEdges = adjacencyList.get(source);
        List<GraphNode<E>> destinationEdges = adjacencyList.get(destination);

        if (sourceEdges == null || destinationEdges == null) return false;

        boolean added = sourceEdges.add(new GraphNode<>(destination, weight));
        if (added && !isDirected) destinationEdges.add(new GraphNode<>(source, weight));

        return added;
    }

    @Override
    public boolean addEdge(E source, E destination) { return addEdge(source, destination, 1); }

    @Override
    public boolean removeEdge(E source, E destination) {
        Validator.validateVerticesNotNull(source, destination);

        List<GraphNode<E>> sourceEdges = adjacencyList.get(source);
        List<GraphNode<E>> destinationEdges = adjacencyList.get(destination);

        if (sourceEdges == null || destinationEdges == null) return false;

        boolean removed = sourceEdges.remove(new GraphNode<>(destination));
        if (removed && !isDirected) destinationEdges.remove(new GraphNode<>(source));

        return removed;
    }

    @Override
    public boolean containsEdge(E source, E destination) {
        Validator.validateVerticesNotNull(source, destination);

        List<GraphNode<E>> sourceEdges = adjacencyList.get(source);
        if (sourceEdges == null) return false;

        return adjacencyList.containsKey(destination)
                && adjacencyList.get(source).contains(new GraphNode<>(destination));
    }

    @Override
    public int degree(E vertex) {
        if (!containsVertex(vertex)) return -1;

        int degree = calculateOutDegree(vertex);
        if (isDirected) degree += calculateInDegree(vertex);

        return degree;
    }

    @Override
    public boolean hasPathDepthFirst(E source, E destination) {
        return hasPath(source, destination, new LinkedStack<>());
    }

    @Override
    public boolean hasPathBreadthFirst(E source, E destination) {
        return hasPath(source, destination, new LinkedQueue<>());
    }

    @Override
    public boolean hasPathDijkstra(E source, E destination) {
        return !findShortestPathDijkstra(source, destination).isEmpty();
    }

    @Override
    public List<E> findPathDepthFirst(E source, E destination) {
        return findPath(source, destination, new LinkedStack<>());
    }

    @Override
    public List<E> findPathBreadthFirst(E source, E destination) {
        return findPath(source, destination, new LinkedQueue<>());
    }

    @Override
    public List<E> findPathDijkstra(E source, E destination) {
        return findShortestPathDijkstra(source, destination);
    }

    @Override
    public int calculatePathDistance(List<E> path) {
        Objects.requireNonNull(path, "Path cannot be null.");

        if (path.isEmpty()) return -1;

        int totalDistance = 0;
        E current = path.get(0);

        if (!containsVertex(current)) return -1;

        for (int i = 1; i < path.size(); i++) {
            E next = path.get(i);

            if (!containsVertex(next)) return -1;

            List<GraphNode<E>> edges = adjacencyList.get(current);
            GraphNode<E> nextNode = new GraphNode<>(next);
            int edgeIndex = edges.indexOf(nextNode);

            if (edgeIndex == -1) return -1;

            totalDistance += edges.get(edgeIndex).weight();
            current = next;
        }

        return totalDistance;
    }

    @Override
    public void clear() { adjacencyList.clear(); }

    @Override
    public String toString() {
        if (isEmpty()) return "Empty Graph";

        StringBuilder sb = new StringBuilder();

        for (E vertex : (E[]) adjacencyList.keySet().toArray()) {
            sb.append(formatVertexWithEdges(vertex)).append("\n");
        }

        sb.setLength(sb.length() - 1);

        return sb.toString();
    }

    /**
     * Calculates the out-degree (number of outgoing edges) for a vertex.
     *
     * @param vertex the vertex to check
     * @return out-degree count
     */
    private int calculateOutDegree(E vertex) { return adjacencyList.get(vertex).size(); }

    /**
     * Calculates the in-degree (number of incoming edges) for a vertex.
     *
     * @param vertex the vertex to check
     * @return in-degree count
     */
    private int calculateInDegree(E vertex) {
        GraphNode<E> vertexNode = new GraphNode<>(vertex);
        AtomicInteger degree = new AtomicInteger();

        adjacencyList.values().forEach(edge -> {
            if (edge.contains(vertexNode)) degree.getAndIncrement();
        });

        return degree.get();
    }

    /**
     * Checks if a path exists between two vertices using either DFS or BFS.
     * The algorithm used depends on the type of collection provided (Stack for DFS, Queue for BFS).
     *
     * @param source the starting vertex
     * @param destination the target vertex
     * @param collection the collection to use (Stack for DFS, Queue for BFS)
     * @return true if a path exists, false otherwise
     * @throws NullPointerException if either vertex is null
     */
    private boolean hasPath(E source, E destination, Collection<E> collection) {
        Validator.validateVerticesNotNull(source, destination);

        if (!containsVertex(source) || !containsVertex(destination)) return false;

        Set<E> visited = new ArraySet<>(adjacencyList.size());

        collection.add(source);
        visited.add(source);

        while (!collection.isEmpty()) {
            E current = removeNextElement(collection);

            if (destination.equals(current)) return true;

            exploreNeighbors(current, visited, collection);
        }

        return false;
    }

    /**
     * Explores all neighbors of a vertex and adds them to the collection if not visited.
     *
     * @param vertex the vertex whose neighbors to explore
     * @param visited set of already visited vertices
     * @param collection the collection to add neighbors to (Stack/Queue)
     */
    private void exploreNeighbors(E vertex, Set<E> visited, Collection<E> collection) {
        List<GraphNode<E>> vertexEdges = adjacencyList.get(vertex);
        for (int i = 0; i < vertexEdges.size(); i++) {
            E neighbor = vertexEdges.get(i).element();
            if (!visited.contains(neighbor)) {
                collection.add(neighbor);
                visited.add(neighbor);
            }
        }
    }

    /**
     * Finds a path between two vertices using either DFS or BFS.
     * The algorithm used depends on the type of collection provided.
     *
     * @param source the starting vertex
     * @param destination the target vertex
     * @param collection the collection to use (Stack for DFS, Queue for BFS)
     * @return list representing the path from source to destination, empty if no path exists
     * @throws NullPointerException if either vertex is null
     */
    private List<E> findPath(E source, E destination, Collection<E> collection) {
        Validator.validateVerticesNotNull(source, destination);

        if (!containsVertex(source) || !containsVertex(destination)) return new SinglyLinkedList<>();

        Set<E> visited = new ArraySet<>(adjacencyList.size());
        Map<E, E> predecessors = new Hashtable<>();

        collection.add(source);
        visited.add(source);

        while (!collection.isEmpty()) {
            E current = removeNextElement(collection);

            if (current.equals(destination)) break;

            recordPredecessors(current, visited, predecessors, collection);
        }

        return reconstructPath(source, destination, predecessors);
    }

    /**
     * Records predecessors while traversing the graph to enable path reconstruction.
     *
     * @param vertex the current vertex being processed
     * @param visited set of visited vertices
     * @param predecessors map storing each vertex's predecessor in the traversal
     * @param collection the collection being used (Stack/Queue)
     */
    private void recordPredecessors(E vertex, Set<E> visited, Map<E, E> predecessors, Collection<E> collection) {
        List<GraphNode<E>> vertexEdges = adjacencyList.get(vertex);
        for (int i = 0; i < vertexEdges.size(); i++) {
            E neighbor = vertexEdges.get(i).element();
            if (!visited.contains(neighbor)) {
                predecessors.put(neighbor, vertex);
                collection.add(neighbor);
                visited.add(neighbor);
            }
        }
    }

    /**
     * Removes and returns the next element from the collection based on its type.
     * For Stack: removes last element (LIFO)
     * For Queue: removes first element (FIFO)
     *
     * @param collection the collection to remove from
     * @return the next element
     * @throws IllegalArgumentException if collection type is not supported
     */
    private E removeNextElement(Collection<E> collection) {
        return switch (collection) {
            case Stack<E> stack -> stack.pop();
            case Queue<E> queue -> queue.poll();
            default -> throw new IllegalArgumentException("Unsupported collection type: " + collection.getClass());
        };
    }

    /**
     * Finds the shortest path between two vertices using Dijkstra's algorithm.
     *
     * @param source the starting vertex
     * @param destination the target vertex
     * @return list representing the shortest path, empty if no path exists
     * @throws NullPointerException if either vertex is null
     */
    private List<E> findShortestPathDijkstra(E source, E destination) {
        Validator.validateVerticesNotNull(source, destination);

        if (!adjacencyList.containsKey(source) || !adjacencyList.containsKey(destination)) return new SinglyLinkedList<>();

        Set<E> visited = new ArraySet<>(adjacencyList.size());
        Map<E, E> predecessors = new Hashtable<>();
        Map<E, Integer> distances = new Hashtable<>();

        for (E key : (E[]) adjacencyList.keySet().toArray()) {
            distances.put(key, Integer.MAX_VALUE);
        }
        distances.put(source, 0);

        PriorityQueue<GraphNode<E>> queue = new PriorityQueue<>();
        queue.add(new GraphNode<>(source));

        while (!queue.isEmpty()) {
            E current = queue.poll().element();

            if (visited.contains(current)) continue;

            visited.add(current);

            if (current.equals(destination)) {
                return reconstructPath(source, destination, predecessors);
            }

            updateNeighborDistances(current, distances, predecessors, queue);
        }

        return new SinglyLinkedList<>();
    }

    /**
     * Updates distances to neighboring vertices during Dijkstra's algorithm.
     *
     * @param vertex the current vertex being processed
     * @param distances map of current shortest distances to each vertex
     * @param predecessors map storing each vertex's predecessor in the shortest path
     * @param queue the priority queue used in Dijkstra's algorithm
     */
    private void updateNeighborDistances(E vertex, Map<E, Integer> distances, Map<E, E> predecessors, PriorityQueue<GraphNode<E>> queue) {
        List<GraphNode<E>> vertexEdges = adjacencyList.get(vertex);
        for (int i = 0; i < vertexEdges.size(); i++) {
            GraphNode<E> neighborNode = vertexEdges.get(i);
            E neighbor = neighborNode.element();
            int edgeWeight = neighborNode.weight();
            int newDistance = distances.get(vertex) + edgeWeight;

            if (newDistance < distances.get(neighbor)) {
                distances.put(neighbor, newDistance);
                predecessors.put(neighbor, vertex);
                queue.add(new GraphNode<>(neighbor, newDistance));
            }
        }
    }

    /**
     * Reconstructs a path from source to destination using predecessors map.
     *
     * @param source the starting vertex
     * @param destination the target vertex
     * @param predecessors map storing each vertex's predecessor
     * @return list representing the path, empty if no valid path exists
     */
    private List<E> reconstructPath(E source, E destination, Map<E, E> predecessors) {
        List<E> path = new SinglyLinkedList<>();
        E current = destination;

        while (current != null && !current.equals(source)) {
            path.add(0, current);
            current = predecessors.get(current);
        }

        if (current == null) return new SinglyLinkedList<>();

        path.add(0, source);
        return path;
    }

    /**
     * Formats a vertex and its edges in a readable string form.
     * Example output:
     * [A] -> [B] -> [C]
     *
     * @param vertex The vertex whose edges are to be formatted
     * @return A string representing the vertex and its adjacent vertices
     */
    private String formatVertexWithEdges(E vertex) {
        List<GraphNode<E>> neighbors = adjacencyList.get(vertex);
        StringBuilder sb = new StringBuilder();

        sb.append("[").append(vertex).append("]");

        for (int i = 0; i < neighbors.size(); i++) {
            sb.append(" -> [").append(neighbors.get(i).element()).append("]");
        }

        return sb.toString();
    }
}
