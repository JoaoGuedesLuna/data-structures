package dev.guedes.datastructures.util.collection.graph;

import dev.guedes.datastructures.util.collection.list.List;
import dev.guedes.datastructures.util.collection.set.Set;

/**
 * Represents a generic graph data structure, where vertices are of type {@code E}.
 * Supports basic operations for both directed and undirected graphs, including
 * vertex/edge management and pathfinding algorithms such as DFS, BFS, and Dijkstra.
 *
 * @param <E> the type of elements (vertices) stored in the graph
 *
 * @author João Guedes
 */
public interface Graph<E> {
    /**
     * Returns the number of vertices in the graph.
     *
     * @return the total number of vertices
     */
    int size();

    /**
     * Checks if the graph has no vertices.
     *
     * @return {@code true} if the graph is empty, {@code false} otherwise
     */
    boolean isEmpty();

    /**
     * Adds a new vertex to the graph.
     *
     * @param vertex the vertex to add
     * @return {@code true} if the vertex was added, {@code false} if it already exists
     * @throws NullPointerException if {@code vertex} is {@code null}
     */
    boolean addVertex(E vertex) throws NullPointerException;

    /**
     * Removes a vertex and all its associated edges from the graph.
     *
     * @param vertex the vertex to remove
     * @return {@code true} if the vertex was removed, {@code false} if it did not exist
     * @throws NullPointerException if {@code vertex} is {@code null}
     */
    boolean removeVertex(E vertex)  throws NullPointerException;

    /**
     * Checks whether a given vertex exists in the graph.
     *
     * @param vertex the vertex to check
     * @return {@code true} if the vertex exists, {@code false} otherwise
     * @throws NullPointerException if {@code vertex} is {@code null}
     */
    boolean containsVertex(E vertex)  throws NullPointerException;

    /**
     * Returns a set of all vertices in the graph.
     *
     * @return a {@link Set} containing all vertices
     */
    Set<E> vertices();

    /**
     * Adds a directed edge from the {@code source} vertex to the {@code destination} vertex
     * with a specified {@code weight}. The method performs validation to ensure that:
     * <ul>
     *   <li>Both vertices are non-null.</li>
     *   <li>The weight is positive.</li>
     *   <li>The vertices are distinct (no self-loops).</li>
     * </ul>
     * If all checks pass, the edge is added and the method returns {@code true}.
     *
     * @param source the source vertex
     * @param destination the destination vertex
     * @param weight the weight of the edge; must be positive
     * @return {@code true} if the edge was successfully added; {@code false} otherwise
     * @throws NullPointerException if {@code source} or {@code destination} is {@code null}
     */
    boolean addEdge(E source, E destination, int weight)  throws NullPointerException;

    /**
     * Adds a directed edge from the {@code source} vertex to the {@code destination} vertex
     * with a default weight of 1.
     * <p>
     * This is a shorthand for calling {@code addEdge(source, destination, 1)}.
     *
     * @param source the source vertex
     * @param destination the destination vertex
     * @return {@code true} if the edge was successfully added; {@code false} otherwise
     * @throws NullPointerException if {@code source} or {@code destination} is {@code null}
     */
    boolean addEdge(E source, E destination)  throws NullPointerException;

    /**
     * Removes an edge between two vertices.
     *
     * @param source the source vertex
     * @param destination the destination vertex
     * @return {@code true} if the edge was removed, {@code false} if it did not exist
     * @throws NullPointerException if either {@code source} or {@code destination} is {@code null}
     */
    boolean removeEdge(E source, E destination)  throws NullPointerException;

    /**
     * Checks whether an edge exists between two vertices.
     *
     * @param source the source vertex
     * @param destination the destination vertex
     * @return {@code true} if the edge exists, {@code false} otherwise
     * @throws NullPointerException if either {@code source} or {@code destination} is {@code null}
     */
    boolean containsEdge(E source, E destination)  throws NullPointerException;

    /**
     * Returns the degree (number of edges) of a given vertex.
     *
     * @param vertex the vertex whose degree is to be calculated
     * @return the degree of the vertex
     */
    int degree(E vertex);

    /**
     * Checks whether there is a path between two vertices using depth-first search (DFS).
     *
     * @param source the source vertex
     * @param destination the destination vertex
     * @return {@code true} if a path exists, {@code false} otherwise
     * @throws NullPointerException if either {@code source} or {@code destination} is {@code null}
     */
    boolean hasPathDepthFirst(E source, E destination)  throws NullPointerException;

    /**
     * Checks whether there is a path between two vertices using breadth-first search (BFS).
     *
     * @param source the source vertex
     * @param destination the destination vertex
     * @return {@code true} if a path exists, {@code false} otherwise
     * @throws NullPointerException if either {@code source} or {@code destination} is {@code null}
     */
    boolean hasPathBreadthFirst(E source, E destination)  throws NullPointerException;

    /**
     * Checks whether there is a path between two vertices using Dijkstra's algorithm.
     *
     * @param source the source vertex
     * @param destination the destination vertex
     * @return {@code true} if a path exists, {@code false} otherwise
     * @throws NullPointerException if either {@code source} or {@code destination} is {@code null}
     */
    boolean hasPathDijkstra(E source, E destination)  throws NullPointerException;

    /**
     * Finds a path between two vertices using depth-first search (DFS).
     *
     * @param source the source vertex
     * @param destination the destination vertex
     * @return a list of vertices representing the path, or an empty list if no path exists
     * @throws NullPointerException if either {@code source} or {@code destination} is {@code null}
     */
    List<E> findPathDepthFirst(E source, E destination)  throws NullPointerException;

    /**
     * Finds a path between two vertices using breadth-first search (BFS).
     *
     * @param source the source vertex
     * @param destination the destination vertex
     * @return a list of vertices representing the path, or an empty list if no path exists
     * @throws NullPointerException if either {@code source} or {@code destination} is {@code null}
     */
    List<E> findPathBreadthFirst(E source, E destination) throws NullPointerException;

    /**
     * Finds the shortest path between two vertices using Dijkstra's algorithm.
     *
     * @param source the source vertex
     * @param destination the destination vertex
     * @return a list of vertices representing the shortest path, or an empty list if no path exists
     * @throws NullPointerException if either {@code source} or {@code destination} is {@code null}
     */
    List<E> findPathDijkstra(E source, E destination) throws NullPointerException;

    /**
     * Calculates the total weight (distance) of a given path.
     *
     * @param path a list of vertices representing the path
     * @return the total distance of the path, or -1 if the path is invalid
     * @throws NullPointerException if {@code path} is {@code null}
     */
    int calculatePathDistance(List<E> path) throws NullPointerException;

    /**
     * Removes all vertices and edges from the graph, resetting it to an empty state.
     */
    void clear();
}
