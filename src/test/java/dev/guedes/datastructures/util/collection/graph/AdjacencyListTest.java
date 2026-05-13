package dev.guedes.datastructures.util.collection.graph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test class for {@link AdjacencyList}.
 *
 * @author João Guedes
 */
class AdjacencyListTest extends GraphTest {
    @Override
    protected Graph<Integer> createGraphDirected() { return new AdjacencyList<>(1, true); }

    @Override
    protected Graph<Integer> createGraphUndirected() { return new AdjacencyList<>(1, false); }

    @Test
    void constructor_ShouldThrowException_WhenInitialCapacityIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> new AdjacencyList<>(-1, false));
    }

    @Test
    void constructor_ShouldThrowException_WhenInitialCapacityIsZero() {
        assertThrows(IllegalArgumentException.class, () -> new AdjacencyList<>(0, false));
    }

    @Test
    void constructor_ShouldCreateInstance_WhenInitialCapacityIsPositive() {
        assertDoesNotThrow(() -> new AdjacencyList<>(1, false));
    }

    @Test
    void constructor_ShouldCreateGraphWithDefaultCapacity_WhenNoArgsConstructorIsUsed() {
        assertDoesNotThrow(() -> new AdjacencyList<>(false));
    }

    @Test
    void toString_ShouldReturnEmptyGraph_WhenGraphIsEmpty() {
        Graph<Integer> graph = createGraphDirected();

        assertEquals("Empty Graph", graph.toString());
    }

    @Test
    void toString_ShouldReturnAdjacencyList_WhenGraphHasVerticesAndEdges() {
        Graph<Integer> graph = createGraphDirected();

        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);

        graph.addEdge(1, 2);
        graph.addEdge(1, 3);

        String expected = """
            [1] -> [2] -> [3]
            [2]
            [3]
            """.trim();

        assertEquals(expected, graph.toString());
    }
}
