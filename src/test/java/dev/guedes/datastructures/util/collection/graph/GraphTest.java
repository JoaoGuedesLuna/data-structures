package dev.guedes.datastructures.util.collection.graph;

import dev.guedes.datastructures.util.collection.list.SinglyLinkedList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Contract class for {@link Graph} test classes.
 *
 * @author João Guedes
 */
abstract class GraphTest {
    protected abstract Graph<Integer> createGraphDirected();
    protected abstract Graph<Integer> createGraphUndirected();

    @Test
    void size_ShouldIncrease_WhenVertexIsAdded() {
        Graph<Integer> graph = createGraphDirected();

        assertEquals(0, graph.size());

        graph.addVertex(1);

        assertEquals(1, graph.size());

        graph.addVertex(2);

        assertEquals(2, graph.size());
    }

    @Test
    void size_ShouldDecrease_WhenVertexIsRemoved() {
        Graph<Integer> graph = createGraphDirected();

        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);

        assertEquals(3, graph.size());

        graph.removeVertex(2);

        assertEquals(2, graph.size());

        graph.removeVertex(1);

        assertEquals(1, graph.size());

        graph.removeVertex(3);

        assertEquals(0, graph.size());
    }

    @Test
    void size_ShouldBeZero_WhenGraphIsCleared() {
        Graph<Integer> graph = createGraphDirected();

        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);

        assertEquals(3, graph.size());

        graph.clear();

        assertEquals(0, graph.size());
        assertTrue(graph.isEmpty());
    }

    @Test
    void isEmpty_ShouldReturnTrue_WhenGraphIsCreated() {
        Graph<Integer> graph = createGraphDirected();

        assertTrue(graph.isEmpty());
    }

    @Test
    void isEmpty_ShouldReturnFalse_WhenVertexIsAdded() {
        Graph<Integer> graph = createGraphDirected();

        graph.addVertex(1);

        assertFalse(graph.isEmpty());
    }

    @Test
    void isEmpty_ShouldReturnTrue_WhenAllVerticesAreRemoved() {
        Graph<Integer> graph = createGraphDirected();

        graph.addVertex(1);
        graph.addVertex(2);

        assertFalse(graph.isEmpty());

        graph.removeVertex(1);
        graph.removeVertex(2);

        assertTrue(graph.isEmpty());
    }

    @Test
    void isEmpty_ShouldReturnTrue_WhenGraphIsCleared() {
        Graph<Integer> graph = createGraphDirected();

        graph.addVertex(1);
        graph.addVertex(2);

        assertFalse(graph.isEmpty());

        graph.clear();

        assertTrue(graph.isEmpty());
    }

    @Test
    void addVertex_ShouldThrowException_WhenVertexIsNull() {
        Graph<Integer> graph = createGraphDirected();

        assertThrows(NullPointerException.class, () -> graph.addVertex(null));
    }

    @Test
    void addVertex_ShouldAddVertex_WhenNotExists() {
        Graph<Integer> graph = createGraphDirected();

        assertTrue(graph.addVertex(1));
        assertTrue(graph.containsVertex(1));
        assertEquals(1, graph.size());
    }

    @Test
    void addVertex_ShouldNotAddVertex_WhenVertexAlreadyExists() {
        Graph<Integer> graph = createGraphDirected();

        graph.addVertex(1);

        assertFalse(graph.addVertex(1));
        assertEquals(1, graph.size());
    }

    @Test
    void removeVertex_ShouldThrowException_WhenVertexIsNull() {
        Graph<Integer> graph = createGraphDirected();

        assertThrows(NullPointerException.class, () -> graph.removeVertex(null));
    }

    @Test
    void removeVertex_ShouldNotRemoveVertex_WhenVertexDoesNotExists() {
        Graph<Integer> graph = createGraphDirected();

        graph.addVertex(1);

        assertFalse(graph.removeVertex(2));
        assertEquals(1, graph.size());
    }

    @Test
    void removeVertex_ShouldRemoveVertex_WhenVertexExists() {
        Graph<Integer> graph = createGraphDirected();

        graph.addVertex(1);
        graph.addVertex(2);

        assertTrue(graph.removeVertex(1));
        assertFalse(graph.containsVertex(1));
        assertEquals(1, graph.size());
    }

    @Test
    void containsVertex_ShouldThrowException_WhenVertexIsNull() {
        Graph<Integer> graph = createGraphDirected();

        assertThrows(NullPointerException.class, () -> graph.containsVertex(null));
    }

    @Test
    void containsVertex_ShouldReturnTrue_WhenVertexExists() {
        Graph<Integer> graph = createGraphDirected();

        graph.addVertex(1);

        assertTrue(graph.containsVertex(1));
    }

    @Test
    void containsVertex_ShouldReturnFalse_WhenVertexDoesNotExists() {
        Graph<Integer> graph = createGraphDirected();

        assertFalse(graph.containsVertex(1));
    }

    @Test
    void vertices_ShouldReturnEmptySet_WhenGraphIsEmpty() {
        Graph<Integer> graph = createGraphDirected();

        var vertices = graph.vertices();

        assertTrue(vertices.isEmpty());
        assertEquals(0, vertices.size());
    }

    @Test
    void vertices_ShouldReturnAllVertices_WhenGraphContainsVertices() {
        Graph<Integer> graph = createGraphDirected();

        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);

        var vertices = graph.vertices();

        assertEquals(3, vertices.size());
        assertTrue(vertices.contains(1));
        assertTrue(vertices.contains(2));
        assertTrue(vertices.contains(3));
    }

    @Test
    void vertices_ShouldNotContainRemovedVertex_WhenVertexIsRemovedFromGraph() {
        Graph<Integer> graph = createGraphDirected();

        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);

        graph.removeVertex(2);

        var vertices = graph.vertices();

        assertEquals(2, vertices.size());
        assertTrue(vertices.contains(1));
        assertFalse(vertices.contains(2));
        assertTrue(vertices.contains(3));
    }

    @Test
    void vertices_ShouldReturnIndependentSet_WhenReturnedSetIsModified() {
        Graph<Integer> graph = createGraphDirected();

        graph.addVertex(1);
        graph.addVertex(2);

        var vertices = graph.vertices();

        vertices.remove(1);

        assertTrue(graph.containsVertex(1));
        assertEquals(2, graph.size());
    }

    @Test
    void addEdge_ShouldThrowException_WhenVerticesAreNull() {
        Graph<Integer> graph = createGraphDirected();

        assertThrows(NullPointerException.class, () -> graph.addEdge(null, 1));
        assertThrows(NullPointerException.class, () -> graph.addEdge(1, null));
    }

    @Test
    void addEdge_ShouldThrowException_WhenWeightIsInvalid() {
        Graph<Integer> graph = createGraphDirected();

        graph.addVertex(1);
        graph.addVertex(2);

        assertThrows(IllegalArgumentException.class, () -> graph.addEdge(1, 2, -1));
        assertThrows(IllegalArgumentException.class, () -> graph.addEdge(1, 2, 0));
    }

    @Test
    void addEdge_ShouldNotAddEdge_WhenSameVertex() {
        Graph<Integer> graph = createGraphDirected();

        graph.addVertex(1);

        assertFalse(graph.addEdge(1, 1));
    }

    @Test
    void addEdge_ShouldNotAddEdge_WhenVerticesDoNotExist() {
        Graph<Integer> graph = createGraphDirected();

        graph.addVertex(1);
        graph.addVertex(2);

        assertFalse(graph.addEdge(0, 2));
        assertFalse(graph.addEdge(1, 0));
    }

    @Test
    void addEdge_ShouldAddEdge_WhenVerticesExist() {
        Graph<Integer> graph = createGraphDirected();

        graph.addVertex(1);
        graph.addVertex(2);

        assertTrue(graph.addEdge(1, 2));
        assertTrue(graph.containsEdge(1, 2));
    }

    @Test
    void removeEdge_ShouldThrowException_WhenVerticesAreNull() {
        Graph<Integer> graph = createGraphDirected();

        assertThrows(NullPointerException.class, () -> graph.removeEdge(null, 1));
        assertThrows(NullPointerException.class, () -> graph.removeEdge(1, null));
    }

    @Test
    void removeEdge_ShouldNotRemoveEdge_WhenVerticesDoNotExist() {
        Graph<Integer> graph = createGraphDirected();

        graph.addVertex(1);
        graph.addVertex(2);

        assertFalse(graph.removeEdge(0, 2));
        assertFalse(graph.removeEdge(1, 0));
    }

    @Test
    void removeEdge_ShouldReturnFalse_WhenEdgeDoesNotExist() {
        Graph<Integer> graph = createGraphDirected();

        graph.addVertex(1);
        graph.addVertex(2);

        assertFalse(graph.removeEdge(1, 2));
    }

    @Test
    void removeEdge_ShouldRemoveEdge_WhenExists() {
        Graph<Integer> graph = createGraphDirected();

        graph.addVertex(1);
        graph.addVertex(2);

        graph.addEdge(1, 2);

        assertTrue(graph.removeEdge(1, 2));
        assertFalse(graph.containsEdge(1, 2));
    }

    @Test
    void containsEdge_ShouldThrowException_WhenVerticesAreNull() {
        Graph<Integer> graph = createGraphDirected();

        assertThrows(NullPointerException.class, () -> graph.containsEdge(null, 1));
        assertThrows(NullPointerException.class, () -> graph.containsEdge(1, null));
    }

    @Test
    void containsEdge_ShouldReturnFalse_WhenVerticesDoNotExist() {
        Graph<Integer> graph = createGraphDirected();

        graph.addVertex(1);
        graph.addVertex(2);

        graph.addEdge(1, 2);

        assertFalse(graph.containsEdge(0, 1));
        assertFalse(graph.containsEdge(1, 0));
    }

    @Test
    void containsEdge_ShouldReturnFalse_WhenEdgeDoesNotExists() {
        Graph<Integer> graph = createGraphDirected();

        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);

        graph.addEdge(1, 2);

        assertFalse(graph.containsEdge(1, 3));
        assertFalse(graph.containsEdge(2, 3));
    }

    @Test
    void containsEdge_ShouldReturnTrue_WhenEdgeExists() {
        Graph<Integer> graph = createGraphDirected();

        graph.addVertex(1);
        graph.addVertex(2);

        graph.addEdge(1, 2);

        assertTrue(graph.containsEdge(1, 2));
    }

    @Test
    void degree_ShouldThrowException_WhenVertexIsNull() {
        Graph<Integer> graph = createGraphDirected();

        assertThrows(NullPointerException.class, () -> graph.degree(null));
    }

    @Test
    void degree_ShouldReturnMinusOne_WhenVertexDoesNotExist() {
        Graph<Integer> graph = createGraphDirected();

        assertEquals(-1, graph.degree(1));
    }

    @Test
    void degree_ShouldReturnCorrectDegree_WhenIsDirected() {
        Graph<Integer> graph = createGraphDirected();

        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);

        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 1);

        assertEquals(3, graph.degree(1));
    }

    @Test
    void degree_ShouldReturnCorrectDegree_WhenIsUndirected() {
        Graph<Integer> graph = createGraphUndirected();

        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);

        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 1);

        assertEquals(2, graph.degree(1));
    }

    @Test
    void hasPathDepthFirst_ShouldThrowException_WhenVerticesAreNull() {
        Graph<Integer> graph = createGraphDirected();

        assertThrows(NullPointerException.class, () -> graph.hasPathDepthFirst(null, 1));
        assertThrows(NullPointerException.class, () -> graph.hasPathDepthFirst(1, null));
    }

    @Test
    void hasPathDepthFirst_ShouldReturnFalse_WhenVerticesDoNotExist() {
        Graph<Integer> graph = createGraphDirected();

        graph.addVertex(1);
        graph.addVertex(2);

        assertFalse(graph.hasPathDepthFirst(0, 2));
        assertFalse(graph.hasPathDepthFirst(1, 0));
    }

    @Test
    void hasPathDepthFirst_ShouldReturnFalse_WhenNoPathExists() {
        Graph<Integer> graph = createGraphDirected();

        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);

        assertFalse(graph.hasPathDepthFirst(1, 3));
    }

    @Test
    void hasPathDepthFirst_ShouldReturnTrue_WhenPathExists() {
        Graph<Integer> graph = createGraphDirected();

        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);

        graph.addEdge(1, 2);
        graph.addEdge(2, 3);

        assertTrue(graph.hasPathDepthFirst(1, 3));
    }

    @Test
    void hasPathDepthFirst_ShouldIgnoreNeighbor_WhenNeighborWasAlreadyVisited() {
        Graph<Integer> graph = createGraphDirected();

        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);

        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);

        assertTrue(graph.hasPathDepthFirst(1, 3));
    }

    @Test
    void hasPathBreadthFirst_ShouldThrowException_WhenVerticesAreNull() {
        Graph<Integer> graph = createGraphDirected();

        assertThrows(NullPointerException.class, () -> graph.hasPathBreadthFirst(null, 1));
        assertThrows(NullPointerException.class, () -> graph.hasPathBreadthFirst(1, null));
    }

    @Test
    void hasPathBreadthFirst_ShouldReturnFalse_WhenVerticesDoNotExist() {
        Graph<Integer> graph = createGraphDirected();

        graph.addVertex(1);
        graph.addVertex(2);

        assertFalse(graph.hasPathBreadthFirst(0, 2));
        assertFalse(graph.hasPathBreadthFirst(1, 0));
    }

    @Test
    void hasPathBreadthFirst_ShouldReturnFalse_WhenNoPathExists() {
        Graph<Integer> graph = createGraphDirected();

        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);

        assertFalse(graph.hasPathBreadthFirst(1, 3));
    }

    @Test
    void hasPathBreadthFirst_ShouldReturnTrue_WhenPathExists() {
        Graph<Integer> graph = createGraphDirected();

        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);

        graph.addEdge(1, 2);
        graph.addEdge(2, 3);

        assertTrue(graph.hasPathBreadthFirst(1, 3));
    }

    @Test
    void hasPathBreadthFirst_ShouldIgnoreNeighbor_WhenNeighborWasAlreadyVisited() {
        Graph<Integer> graph = createGraphDirected();

        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);

        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);

        assertTrue(graph.hasPathBreadthFirst(1, 3));
    }

    @Test
    void hasPathDijkstra_ShouldThrowException_WhenVerticesAreNull() {
        Graph<Integer> graph = createGraphDirected();

        assertThrows(NullPointerException.class, () -> graph.hasPathDijkstra(null, 1));
        assertThrows(NullPointerException.class, () -> graph.hasPathDijkstra(1, null));
    }

    @Test
    void hasPathDijkstra_ShouldReturnFalse_WhenVerticesDoNotExist() {
        Graph<Integer> graph = createGraphDirected();

        graph.addVertex(1);
        graph.addVertex(2);

        assertFalse(graph.hasPathDijkstra(0, 2));
        assertFalse(graph.hasPathDijkstra(1, 0));
    }

    @Test
    void hasPathDijkstra_ShouldReturnFalse_WhenNoPathExists() {
        Graph<Integer> graph = createGraphDirected();

        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);

        assertFalse(graph.hasPathDijkstra(1, 3));
    }

    @Test
    void hasPathDijkstra_ShouldReturnTrue_WhenPathExists() {
        Graph<Integer> graph = createGraphDirected();

        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);

        graph.addEdge(1, 2);
        graph.addEdge(2, 3);

        assertTrue(graph.hasPathDijkstra(1, 3));
    }

    @Test
    void hasPathDijkstra_ShouldIgnoreNeighbor_WhenNeighborWasAlreadyVisited() {
        Graph<Integer> graph = createGraphDirected();

        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);

        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);

        assertTrue(graph.hasPathDijkstra(1, 3));
    }

    @Test
    void findPathDepthFirst_ShouldReturnEmptyPath_WhenSourceDoesNotExist() {
        Graph<Integer> graph = createGraphDirected();

        graph.addVertex(1);
        graph.addVertex(2);

        var path = graph.findPathDepthFirst(0, 2);

        assertTrue(path.isEmpty());
    }

    @Test
    void findPathDepthFirst_ShouldReturnEmptyPath_WhenDestinationDoesNotExist() {
        Graph<Integer> graph = createGraphDirected();

        graph.addVertex(1);
        graph.addVertex(2);

        var path = graph.findPathDepthFirst(1, 0);

        assertTrue(path.isEmpty());
    }

    @Test
    void findPathDepthFirst_ShouldReturnEmptyPath_WhenNoPathExists() {
        Graph<Integer> graph = createGraphDirected();

        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);

        graph.addEdge(1, 2);

        var path = graph.findPathDepthFirst(1, 3);

        assertTrue(path.isEmpty());
    }

    @Test
    void findPathDepthFirst_ShouldReturnSingleVertexPath_WhenSourceEqualsDestination() {
        Graph<Integer> graph = createGraphDirected();

        graph.addVertex(1);

        var path = graph.findPathDepthFirst(1, 1);

        assertEquals(1, path.size());
        assertEquals(1, path.get(0));
    }

    @Test
    void findPathDepthFirst_ShouldReturnPath_WhenPathIsValid() {
        Graph<Integer> graph = createGraphDirected();

        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);
        graph.addVertex(6);
        graph.addVertex(7);
        graph.addVertex(8);

        graph.addEdge(1, 2, 3);
        graph.addEdge(1, 4, 1);
        graph.addEdge(1, 5, 1);
        graph.addEdge(2, 8, 4);
        graph.addEdge(3, 8, 3);
        graph.addEdge(4, 6, 1);
        graph.addEdge(5, 1, 1);
        graph.addEdge(5, 3, 2);
        graph.addEdge(6, 7, 1);
        graph.addEdge(7, 8, 1);

        var path = graph.findPathDepthFirst(1, 8);

        assertEquals(4, path.size());
        assertEquals(1, path.get(0));
        assertEquals(8, path.get(path.size() - 1));
        assertEquals("[1, 5, 3, 8]", path.toString());
    }

    @Test
    void findPathBreadthFirst_ShouldReturnEmptyPath_WhenSourceDoesNotExist() {
        Graph<Integer> graph = createGraphDirected();

        graph.addVertex(1);
        graph.addVertex(2);

        var path = graph.findPathBreadthFirst(0, 2);

        assertTrue(path.isEmpty());
    }

    @Test
    void findPathBreadthFirst_ShouldReturnEmptyPath_WhenDestinationDoesNotExist() {
        Graph<Integer> graph = createGraphDirected();

        graph.addVertex(1);
        graph.addVertex(2);

        var path = graph.findPathBreadthFirst(1, 0);

        assertTrue(path.isEmpty());
    }

    @Test
    void findPathBreadthFirst_ShouldReturnEmptyPath_WhenNoPathExists() {
        Graph<Integer> graph = createGraphDirected();

        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);

        graph.addEdge(1, 2);

        var path = graph.findPathBreadthFirst(1, 3);

        assertTrue(path.isEmpty());
    }

    @Test
    void findPathBreadthFirst_ShouldReturnSingleVertexPath_WhenSourceEqualsDestination() {
        Graph<Integer> graph = createGraphDirected();

        graph.addVertex(1);

        var path = graph.findPathBreadthFirst(1, 1);

        assertEquals(1, path.size());
        assertEquals(1, path.get(0));
    }

    @Test
    void findPathBreadthFirst_ShouldReturnPath_WhenPathIsValid() {
        Graph<Integer> graph = createGraphDirected();

        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);
        graph.addVertex(6);
        graph.addVertex(7);
        graph.addVertex(8);

        graph.addEdge(1, 2, 3);
        graph.addEdge(1, 4, 1);
        graph.addEdge(1, 5, 1);
        graph.addEdge(2, 8, 4);
        graph.addEdge(3, 8, 3);
        graph.addEdge(4, 6, 1);
        graph.addEdge(5, 1, 1);
        graph.addEdge(5, 3, 2);
        graph.addEdge(6, 7, 1);
        graph.addEdge(7, 8, 1);

        var path = graph.findPathBreadthFirst(1, 8);

        assertEquals(3, path.size());
        assertEquals(1, path.get(0));
        assertEquals(8, path.get(path.size() - 1));
        assertEquals("[1, 2, 8]", path.toString());
    }

    @Test
    void findPathDijkstra_ShouldReturnEmptyPath_WhenSourceDoesNotExist() {
        Graph<Integer> graph = createGraphDirected();

        graph.addVertex(1);
        graph.addVertex(2);

        var path = graph.findPathDijkstra(0, 2);

        assertTrue(path.isEmpty());
    }

    @Test
    void findPathDijkstra_ShouldReturnEmptyPath_WhenDestinationDoesNotExist() {
        Graph<Integer> graph = createGraphDirected();

        graph.addVertex(1);
        graph.addVertex(2);

        var path = graph.findPathDijkstra(1, 0);

        assertTrue(path.isEmpty());
    }

    @Test
    void findPathDijkstra_ShouldReturnEmptyPath_WhenNoPathExists() {
        Graph<Integer> graph = createGraphDirected();

        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);

        graph.addEdge(1, 2);

        var path = graph.findPathDijkstra(1, 3);

        assertTrue(path.isEmpty());
    }

    @Test
    void findPathDijkstra_ShouldReturnSingleVertexPath_WhenSourceEqualsDestination() {
        Graph<Integer> graph = createGraphDirected();

        graph.addVertex(1);

        var path = graph.findPathDijkstra(1, 1);

        assertEquals(1, path.size());
        assertEquals(1, path.get(0));
    }

    @Test
    void findPathDijkstra_ShouldReturnPath_WhenPathIsValid() {
        Graph<Integer> graph = createGraphDirected();

        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);
        graph.addVertex(6);
        graph.addVertex(7);
        graph.addVertex(8);

        graph.addEdge(1, 2, 3);
        graph.addEdge(1, 4, 1);
        graph.addEdge(1, 5, 1);
        graph.addEdge(2, 6, 4);
        graph.addEdge(3, 8, 3);
        graph.addEdge(4, 6, 1);
        graph.addEdge(5, 1, 1);
        graph.addEdge(6, 3, 2);
        graph.addEdge(6, 7, 1);
        graph.addEdge(7, 8, 1);

        var path = graph.findPathDijkstra(1, 8);

        assertEquals(5, path.size());
        assertEquals(1, path.get(0));
        assertEquals(8, path.get(path.size() - 1));
        assertEquals("[1, 4, 6, 7, 8]", path.toString());
    }

    @Test
    void calculatePathDistance_ShouldThrowException_WhenPathIsNull() {
        Graph<Integer> graph = createGraphDirected();

        assertThrows(NullPointerException.class, () -> graph.calculatePathDistance(null));
    }

    @Test
    void calculatePathDistance_ShouldReturnMinusOne_WhenPathIsEmpty() {
        Graph<Integer> graph = createGraphDirected();

        assertEquals(-1, graph.calculatePathDistance(new SinglyLinkedList<>()));
    }

    @Test
    void calculatePathDistance_ShouldReturnMinusOne_WhenPathFirstVertexDoesNotExists() {
        Graph<Integer> graph = createGraphDirected();

        var path = new SinglyLinkedList<Integer>();
        path.add(1);

        assertEquals(-1, graph.calculatePathDistance(path));
    }

    @Test
    void calculatePathDistance_ShouldReturnMinusOne_WhenPathSecondVertexDoesNotExists() {
        Graph<Integer> graph = createGraphDirected();

        graph.addVertex(1);

        var path = new SinglyLinkedList<Integer>();
        path.add(1);
        path.add(2);

        assertEquals(-1, graph.calculatePathDistance(path));
    }

    @Test
    void calculatePathDistance_ShouldReturnMinusOne_WhenPathDoesNotHaveEdge() {
        Graph<Integer> graph = createGraphDirected();

        graph.addVertex(1);
        graph.addVertex(2);

        var path = new SinglyLinkedList<Integer>();
        path.add(1);
        path.add(2);

        assertEquals(-1, graph.calculatePathDistance(path));
    }

    @Test
    void calculatePathDistance_ShouldReturnCorrectDistance_WhenPathIsValid() {
        Graph<Integer> graph = createGraphDirected();

        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);
        graph.addVertex(6);
        graph.addVertex(7);
        graph.addVertex(8);

        graph.addEdge(1, 2, 3);
        graph.addEdge(1, 4, 1);
        graph.addEdge(1, 5, 1);
        graph.addEdge(2, 8, 4);
        graph.addEdge(3, 8, 3);
        graph.addEdge(4, 6, 1);
        graph.addEdge(5, 1, 1);
        graph.addEdge(5, 3, 2);
        graph.addEdge(6, 7, 1);
        graph.addEdge(7, 8, 1);

        var pathDepthFirst   = graph.findPathDepthFirst(1, 8);
        var pathBreadthFirst = graph.findPathBreadthFirst(1, 8);
        var pathDijkstra     = graph.findPathDijkstra(1, 8);

        assertEquals(6, graph.calculatePathDistance(pathDepthFirst));
        assertEquals(4, pathDepthFirst.size());

        assertEquals(7, graph.calculatePathDistance(pathBreadthFirst));
        assertEquals(3, pathBreadthFirst.size());

        assertEquals(4, graph.calculatePathDistance(pathDijkstra));
        assertEquals(5, pathDijkstra.size());
    }

    @Test
    void clear_ShouldRemoveAllVertices_WhenGraphContainsVertices() {
        Graph<Integer> graph = createGraphDirected();

        graph.addVertex(1);
        graph.addVertex(2);

        graph.clear();

        assertFalse(graph.containsVertex(1));
        assertFalse(graph.containsVertex(2));
        assertEquals(0, graph.size());
        assertTrue(graph.isEmpty());
    }

    @Test
    void clear_ShouldRemoveAllEdges_WhenGraphContainsEdges() {
        Graph<Integer> graph = createGraphDirected();

        graph.addVertex(1);
        graph.addVertex(2);
        graph.addEdge(1, 2);

        graph.clear();

        assertFalse(graph.containsEdge(1, 2));
    }
}
