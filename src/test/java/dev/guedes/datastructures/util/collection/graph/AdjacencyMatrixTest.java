package dev.guedes.datastructures.util.collection.graph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test class for {@link AdjacencyMatrix}.
 *
 * @author João Guedes
 */
class AdjacencyMatrixTest extends GraphTest {
    @Override
    protected Graph<Integer> createGraphDirected() { return new AdjacencyMatrix<>(1, true); }

    @Override
    protected Graph<Integer> createGraphUndirected() { return new AdjacencyMatrix<>(1, false); }

    @Test
    void constructor_ShouldThrowException_WhenInitialCapacityIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> new AdjacencyMatrix<>(-1, false));
    }

    @Test
    void constructor_ShouldThrowException_WhenInitialCapacityIsZero() {
        assertThrows(IllegalArgumentException.class, () -> new AdjacencyMatrix<>(0, false));
    }

    @Test
    void constructor_ShouldCreateInstance_WhenInitialCapacityIsPositive() {
        assertDoesNotThrow(() -> new AdjacencyMatrix<>(1, false));
    }

    @Test
    void constructor_ShouldCreateGraphWithDefaultCapacity_WhenNoArgsConstructorIsUsed() {
        assertDoesNotThrow(() -> new AdjacencyMatrix<>(false));
    }

    @Test
    void removeVertex_ShouldNotShrinkMatrix_WhenMatrixLengthIsLessOrEqualInitialCapacity() {
        Graph<Integer> graph = new AdjacencyMatrix<>(2, true);

        graph.addVertex(1);
        graph.addVertex(2);

        assertTrue(graph.removeVertex(1));

        assertEquals(1, graph.size());
        assertTrue(graph.containsVertex(2));
    }

    @Test
    void toString_ShouldReturnEmptyGraph_WhenGraphIsEmpty() {
        Graph<Integer> graph = createGraphDirected();

        assertEquals("Empty Graph", graph.toString());
    }

    @Test
    void toString_ShouldReturnAdjacencyMatrix_WhenGraphHasVerticesAndEdges() {
        Graph<Integer> graph = createGraphDirected();

        graph.addVertex(1);
        graph.addVertex(2);

        graph.addEdge(1, 2);

        String expected = """
            -------------------
            |     |   1 |   2 |
            -------------------
            |   1 |   0 |   1 |
            -------------------
            |   2 |   0 |   0 |
            -------------------
            """.trim();

        assertEquals(expected, graph.toString());
    }
}
