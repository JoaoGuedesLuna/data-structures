package dev.guedes.datastructures.util.collection.graph;

import dev.guedes.datastructures.util.Objects;
import dev.guedes.datastructures.util.collection.Collection;
import dev.guedes.datastructures.util.collection.internal.nodes.GraphNode;
import dev.guedes.datastructures.util.collection.internal.validation.Validator;
import dev.guedes.datastructures.util.collection.list.List;
import dev.guedes.datastructures.util.collection.list.SinglyLinkedList;
import dev.guedes.datastructures.util.collection.queue.LinkedQueue;
import dev.guedes.datastructures.util.collection.queue.PriorityQueue;
import dev.guedes.datastructures.util.collection.queue.Queue;
import dev.guedes.datastructures.util.collection.set.ArraySet;
import dev.guedes.datastructures.util.collection.set.HashSet;
import dev.guedes.datastructures.util.collection.set.Set;
import dev.guedes.datastructures.util.collection.stack.LinkedStack;
import dev.guedes.datastructures.util.collection.stack.Stack;

import java.util.Arrays;

/**
 * An implementation of a graph using an adjacency matrix representation.
 * This class supports both directed and undirected graphs with weighted edges.
 *
 * @param <E> the type of elements (vertices) maintained by this graph
 *
 * @author João Guedes
 */
public class AdjacencyMatrix<E> implements Graph<E> {
    private static final int DEFAULT_INITIAL_CAPACITY = 5;
    private static final double SHRINK_THRESHOLD_FACTOR = 1.25;

    private int[][] adjacencyMatrix;
    private final int initialCapacity;
    private final boolean isDirected;
    private final List<E> vertices;

    public AdjacencyMatrix(int initialCapacity, boolean isDirected) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Initial capacity must be positive. Provided value: " + initialCapacity + ".");
        }

        this.initialCapacity = initialCapacity;
        this.isDirected = isDirected;
        this.adjacencyMatrix = new int[initialCapacity][initialCapacity];
        this.vertices = new ArraySet<>();
    }

    public AdjacencyMatrix(boolean isDirected) { this(DEFAULT_INITIAL_CAPACITY, isDirected); }

    @Override
    public int size() { return vertices.size(); }

    @Override
    public boolean isEmpty() { return vertices.isEmpty(); }

    @Override
    public boolean addVertex(E vertex) {
        Validator.validateVertexNotNull(vertex);

        if (!vertices.add(vertex)) return false;

        ensureMatrixCapacity();
        return true;
    }

    @Override
    public boolean removeVertex(E vertex) {
        Validator.validateVertexNotNull(vertex);

        int vertexIndex = getVertexIndex(vertex);
        if (vertexIndex < 0) return false;

        removeVertexFromMatrix(vertexIndex);
        vertices.remove(vertexIndex);
        shrinkMatrixCapacity();

        return true;
    }

    @Override
    public boolean containsVertex(E vertex) {
        Validator.validateVertexNotNull(vertex);
        return vertices.contains(vertex);
    }

    @Override
    public Set<E> vertices() {
        Set<E> vertexSet = new HashSet<>();

        vertices.forEach(vertexSet::add);

        return vertexSet;
    }

    @Override
    public boolean addEdge(E source, E destination, int weight) {
        Validator.validateVerticesNotNull(source, destination);
        Validator.validatePositiveWeight(weight);

        if (source.equals(destination)) return false;

        int sourceIndex = getVertexIndex(source);
        int destinationIndex = getVertexIndex(destination);

        if (!areValidIndices(sourceIndex, destinationIndex)) return false;

        setEdgeWeight(sourceIndex, destinationIndex, weight);
        return true;
    }

    @Override
    public boolean addEdge(E source, E destination) { return addEdge(source, destination, 1); }

    @Override
    public boolean removeEdge(E source, E destination) {
        Validator.validateVerticesNotNull(source, destination);

        int sourceIndex = getVertexIndex(source);
        int destinationIndex = getVertexIndex(destination);

        if (!hasEdge(sourceIndex, destinationIndex)) return false;

        setEdgeWeight(sourceIndex, destinationIndex, 0);
        return true;
    }

    @Override
    public boolean containsEdge(E source, E destination) {
        Validator.validateVerticesNotNull(source, destination);

        int sourceIndex = getVertexIndex(source);
        int destinationIndex = getVertexIndex(destination);

        return hasEdge(sourceIndex, destinationIndex);
    }

    @Override
    public int degree(E vertex) {
        Validator.validateVertexNotNull(vertex);

        int vertexIndex = getVertexIndex(vertex);
        if (vertexIndex < 0) return -1;

        int degree = calculateOutDegree(vertexIndex);
        if (isDirected) degree += calculateInDegree(vertexIndex);

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
        List<E> path = findPathDijkstra(source, destination);
        return !path.isEmpty();
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
        Validator.validateVerticesNotNull(source, destination);

        int sourceIndex = getVertexIndex(source);
        int destinationIndex = getVertexIndex(destination);

        if (!areValidIndices(sourceIndex, destinationIndex)) return new SinglyLinkedList<>();

        return findShortestPathDijkstra(sourceIndex, destinationIndex);
    }

    @Override
    public int calculatePathDistance(List<E> path) {
        Objects.requireNonNull(path, "Path cannot be null.");

        if (path.isEmpty()) return -1;

        int totalDistance = 0;

        int currentIndex = getVertexIndex(path.get(0));
        if (currentIndex < 0) return -1;

        for (int i = 1; i < path.size(); i++) {
            int nextIndex = getVertexIndex(path.get(i));
            if (!hasEdge(currentIndex, nextIndex)) return -1;

            totalDistance += getEdgeWeight(currentIndex, nextIndex);
            currentIndex = nextIndex;
        }

        return totalDistance;
    }

    @Override
    public void clear() {
        vertices.clear();
        adjacencyMatrix = new int[initialCapacity][initialCapacity];
    }

    @Override
    public String toString() {
        if (isEmpty()) return "Empty Graph";

        int cellWidth = calculateMaxCellWidth() + 2;
        String horizontalBorder = createHorizontalBorder(cellWidth);

        StringBuilder sb = new StringBuilder();

        sb.append(horizontalBorder).append("\n");
        sb.append(buildHeaderRow(cellWidth)).append("\n");
        sb.append(horizontalBorder).append("\n");

        for (int row = 0; row < vertices.size(); row++) {
            sb.append(buildMatrixRow(row, cellWidth)).append("\n");
            sb.append(horizontalBorder).append("\n");
        }

        sb.setLength(sb.length() - 1);

        return sb.toString();
    }

    /**
     * Ensures the adjacency matrix has sufficient capacity for new vertices.
     * Expands the matrix if necessary.
     */
    private void ensureMatrixCapacity() {
        if (vertices.size() > adjacencyMatrix.length) resizeMatrix(adjacencyMatrix.length * 2);
    }

    /**
     * Shrinks the adjacency matrix if it's significantly underutilized.
     * Maintains the matrix size between initialCapacity and current needs.
     */
    private void shrinkMatrixCapacity() {
        if (adjacencyMatrix.length <= initialCapacity) return;

        int newCapacity = (int) Math.ceil(size() * SHRINK_THRESHOLD_FACTOR);
        if (adjacencyMatrix.length <= newCapacity) return;

        resizeMatrix(newCapacity);
    }

    /**
     * Resizes the adjacency matrix to the specified new capacity.
     *
     * @param newCapacity the new size for the matrix
     */
    private void resizeMatrix(int newCapacity) {
        int[][] newMatrix = new int[newCapacity][newCapacity];
        int length = Math.min(adjacencyMatrix.length, newCapacity);

        for (int i = 0; i < length; i++) {
            System.arraycopy(adjacencyMatrix[i], 0, newMatrix[i], 0, length);
        }

        adjacencyMatrix = newMatrix;
    }

    /**
     * Removes a vertex from the adjacency matrix by shifting rows/columns.
     *
     * @param vertexIndex the index of the vertex to remove
     */
    private void removeVertexFromMatrix(int vertexIndex) {
        for (int i = vertexIndex; i < vertices.size() - 1; i++) {
            adjacencyMatrix[i] = adjacencyMatrix[i + 1];
            for (int j = vertexIndex; j < adjacencyMatrix[i].length - 1; j++) {
                adjacencyMatrix[i][j] = adjacencyMatrix[i][j + 1];
            }
            adjacencyMatrix[i][adjacencyMatrix[i].length - 1] = 0;
        }
        Arrays.fill(adjacencyMatrix[vertices.size() - 1], 0);
    }

    /**
     * Gets the index of a vertex in the vertices list.
     *
     * @param vertex the vertex to find
     * @return the index of the vertex, or -1 if not found
     */
    private int getVertexIndex(E vertex) { return vertices.indexOf(vertex); }

    /**
     * Checks if two vertex indices are valid (non-negative).
     *
     * @param sourceIndex the source vertex index
     * @param destinationIndex the destination vertex index
     * @return true if both indices are valid, false otherwise
     */
    private boolean areValidIndices(int sourceIndex, int destinationIndex) {
        return sourceIndex >= 0 && destinationIndex >= 0;
    }

    /**
     * Checks if an edge exists between two vertices (by index).
     *
     * @param sourceIndex the source vertex index
     * @param destinationIndex the destination vertex index
     * @return true if an edge exists (weight != 0), false otherwise
     */
    private boolean hasEdge(int sourceIndex, int destinationIndex) {
        return areValidIndices(sourceIndex, destinationIndex)
            && adjacencyMatrix[sourceIndex][destinationIndex] != 0;
    }

    /**
     * Sets the weight of an edge between two vertices.
     * For undirected graphs, sets both directions.
     *
     * @param sourceIndex the source vertex index
     * @param destinationIndex the destination vertex index
     * @param weight the edge weight to set
     */
    private void setEdgeWeight(int sourceIndex, int destinationIndex, int weight) {
        adjacencyMatrix[sourceIndex][destinationIndex] = weight;
        if (!isDirected) {
            adjacencyMatrix[destinationIndex][sourceIndex] = weight;
        }
    }

    /**
     * Calculates the out-degree of a vertex (number of outgoing edges).
     *
     * @param vertexIndex the vertex index
     * @return the out-degree count
     */
    private int calculateOutDegree(int vertexIndex) {
        int degree = 0;

        for (int weight : adjacencyMatrix[vertexIndex]) {
            if (weight != 0) degree++;
        }

        return degree;
    }

    /**
     * Calculates the in-degree of a vertex (number of incoming edges).
     *
     * @param vertexIndex the vertex index
     * @return the in-degree count
     */
    private int calculateInDegree(int vertexIndex) {
        int degree = 0;

        for (int i = 0; i < vertices.size(); i++) {
            if (hasEdge(i, vertexIndex)) degree++;
        }

        return degree;
    }

    /**
     * Generic path existence check using either DFS or BFS based on collection type.
     *
     * @param source the starting vertex
     * @param destination the target vertex
     * @param collection the collection to use (Stack for DFS, Queue for BFS)
     * @return true if a path exists, false otherwise
     */
    private boolean hasPath(E source, E destination, Collection<E> collection) {
        Validator.validateVerticesNotNull(source, destination);

        if (!containsVertex(source) || !containsVertex(destination)) return false;

        boolean[] visited = new boolean[vertices.size()];

        collection.add(source);
        visited[getVertexIndex(source)] = true;

        while (!collection.isEmpty()) {
            E currentVertex = removeNextElement(collection);

            if (destination.equals(currentVertex)) return true;

            int currentIndex = getVertexIndex(currentVertex);
            exploreNeighbors(currentIndex, visited, collection);
        }

        return false;
    }

    /**
     * Explores all neighbors of a vertex for path finding.
     *
     * @param currentIndex the index of the current vertex
     * @param visited array tracking visited vertices
     * @param collection the collection to add neighbors to
     */
    private void exploreNeighbors(int currentIndex, boolean[] visited, Collection<E> collection) {
        for (int neighborIndex = 0; neighborIndex < vertices.size(); neighborIndex++) {
            if (hasEdge(currentIndex, neighborIndex) && !visited[neighborIndex]) {
                collection.add(vertices.get(neighborIndex));
                visited[neighborIndex] = true;
            }
        }
    }

    /**
     * Generic path finding using either DFS or BFS based on collection type.
     *
     * @param source the starting vertex
     * @param destination the target vertex
     * @param collection the collection to use (Stack for DFS, Queue for BFS)
     * @return the path as a list, or empty list if no path exists
     */
    private List<E> findPath(E source, E destination, Collection<E> collection) {
        Validator.validateVerticesNotNull(source, destination);

        if (!containsVertex(source) || !containsVertex(destination)) return new SinglyLinkedList<>();

        boolean[] visited = new boolean[vertices.size()];
        E[] predecessors = (E[]) new Object[vertices.size()];

        collection.add(source);
        visited[getVertexIndex(source)] = true;

        while (!collection.isEmpty()) {
            E currentVertex = removeNextElement(collection);

            if (currentVertex.equals(destination)) break;

            int currentIndex = getVertexIndex(currentVertex);
            recordPredecessors(currentIndex, currentVertex, visited, predecessors, collection);
        }

        return reconstructPath(source, destination, predecessors);
    }

    /**
     * Records predecessor relationships during path finding.
     *
     * @param currentIndex the index of the current vertex
     * @param currentVertex the current vertex object
     * @param visited array tracking visited vertices
     * @param predecessors array tracking path predecessors
     * @param collection the collection to add neighbors to
     */
    private void recordPredecessors(int currentIndex, E currentVertex, boolean[] visited, E[] predecessors, Collection<E> collection) {
        for (int neighborIndex = 0; neighborIndex < vertices.size(); neighborIndex++) {
            if (hasEdge(currentIndex, neighborIndex) && !visited[neighborIndex]) {
                predecessors[neighborIndex] = currentVertex;
                collection.add(vertices.get(neighborIndex));
                visited[neighborIndex] = true;
            }
        }
    }

    /**
     * Removes the next element from a collection based on its type.
     *
     * @param collection the collection to remove from (Stack or Queue)
     * @return the removed element
     * @throws IllegalArgumentException if collection type is unsupported
     */
    private E removeNextElement(Collection<E> collection) {
        if (collection instanceof Stack<E> stack) return stack.pop();

        if (collection instanceof Queue<E> queue) return queue.poll();

        throw new IllegalArgumentException("Unsupported collection type: " + collection.getClass());
    }

    /**
     * Finds the shortest path using Dijkstra's algorithm.
     *
     * @param sourceIndex the starting vertex index
     * @param destinationIndex the target vertex index
     * @return the shortest path as a list, or empty list if no path exists
     */
    private List<E> findShortestPathDijkstra(int sourceIndex, int destinationIndex) {
        boolean[] visited = new boolean[vertices.size()];
        E[] predecessors = (E[]) new Object[vertices.size()];
        int[] distances = new int[vertices.size()];

        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[sourceIndex] = 0;

        PriorityQueue<GraphNode<E>> queue = new PriorityQueue<>();
        queue.add(new GraphNode<>(sourceIndex, 0));

        while (!queue.isEmpty()) {
            int currentIndex = queue.poll().index();

            if (visited[currentIndex]) continue;

            visited[currentIndex] = true;

            if (currentIndex == destinationIndex) {
                return reconstructPath(vertices.get(sourceIndex), vertices.get(destinationIndex), predecessors);
            }

            updateNeighborDistances(currentIndex, distances, predecessors, queue);
        }

        return new SinglyLinkedList<>();
    }

    /**
     * Updates neighbor distances during Dijkstra's algorithm execution.
     *
     * @param currentIndex the index of the current vertex
     * @param distances array tracking shortest distances
     * @param predecessors array tracking path predecessors
     * @param queue the priority queue used in the algorithm
     */
    private void updateNeighborDistances(int currentIndex, int[] distances, E[] predecessors, PriorityQueue<GraphNode<E>> queue) {
        for (int neighborIndex = 0; neighborIndex < vertices.size(); neighborIndex++) {
            if (hasEdge(currentIndex, neighborIndex)) {
                int edgeWeight = getEdgeWeight(currentIndex, neighborIndex);
                int newDistance = distances[currentIndex] + edgeWeight;

                if (newDistance < distances[neighborIndex]) {
                    distances[neighborIndex] = newDistance;
                    predecessors[neighborIndex] = vertices.get(currentIndex);
                    queue.add(new GraphNode<>(neighborIndex, newDistance));
                }
            }
        }
    }

    /**
     * Gets the weight of an edge between two vertices.
     *
     * @param sourceIndex the source vertex index
     * @param destinationIndex the destination vertex index
     * @return the edge weight
     */
    private int getEdgeWeight(int sourceIndex, int destinationIndex) {
        return adjacencyMatrix[sourceIndex][destinationIndex];
    }

    /**
     * Reconstructs a path from predecessors array.
     *
     * @param source the starting vertex
     * @param destination the target vertex
     * @param predecessors array tracking path predecessors
     * @return the reconstructed path as a list
     */
    private List<E> reconstructPath(E source, E destination, E[] predecessors) {
        List<E> path = new SinglyLinkedList<>();
        E current = destination;

        while (current != null && !current.equals(source)) {
            path.add(0, current);
            current = predecessors[getVertexIndex(current)];
        }

        if (current == null) return new SinglyLinkedList<>();

        path.add(0, source);
        return path;
    }

    /**
     * Calculates the maximum cell width needed for matrix display.
     *
     * @return the maximum width needed to display any vertex or weight
     */
    private int calculateMaxCellWidth() {
        return Arrays.stream(vertices.toArray())
                .mapToInt(v -> v.toString().length())
                .max()
                .orElse(0);
    }

    /**
     * Creates a horizontal border string for matrix display.
     *
     * @param cellWidth the width of each cell in characters
     * @return the border string
     */
    private String createHorizontalBorder(int cellWidth) {
        int totalWidth = (cellWidth + 3) * (vertices.size() + 1) + 1;
        return "-".repeat(totalWidth);
    }

    /**
     * Builds the header row for matrix display.
     *
     * @param cellWidth the width of each cell in characters
     * @return the formatted header row string
     */
    private String buildHeaderRow(int cellWidth) {
        StringBuilder header = new StringBuilder();
        header.append(String.format("| %" + cellWidth + "s |", ""));

        for (int col = 0; col < vertices.size(); col++) {
            header.append(String.format(" %" + cellWidth + "s |", vertices.get(col)));
        }

        return header.toString();
    }

    /**
     * Builds a matrix row for display.
     *
     * @param rowIndex the index of the row to build
     * @param cellWidth the width of each cell in characters
     * @return the formatted matrix row string
     */
    private String buildMatrixRow(int rowIndex, int cellWidth) {
        StringBuilder row = new StringBuilder();
        row.append(String.format("| %" + cellWidth + "s |", vertices.get(rowIndex)));

        for (int col = 0; col < vertices.size(); col++) {
            row.append(String.format(" %" + cellWidth + "d |", adjacencyMatrix[rowIndex][col]));
        }

        return row.toString();
    }
}
