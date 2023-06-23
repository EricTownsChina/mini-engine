package priv.eric.infrastructure.graph;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Description: kit of graph
 *
 * @author EricTowns
 * @date 2023/4/27 00:18
 */
public final class Graphs {

    private Graphs() {
    }

    public static <V extends Vertex, E extends Edge> boolean hasCycle(Graph<V, E> graph) {
        Set<V> vertexes = graph.vertexes();
        Set<E> edges = graph.edges();
        if (vertexes.size() == 1) {
            return false;
        } else if (!graph.directed() && edges.size() >= vertexes.size()) {
            return true;
        } else {
            Map<String, VertexTravelState> travelVertexMap = new HashMap<>(vertexes.size());
            for (V vertex : vertexes) {
                if (subHasCycle(graph, travelVertexMap, vertex, null)) {
                    return true;
                }
            }
            return false;
        }
    }

    private static <V extends Vertex, E extends Edge> boolean subHasCycle(Graph<V, E> graph, Map<String, VertexTravelState> travelVertexMap, V vertex, V preVertex) {
        String vertexId = vertex.getId();
        VertexTravelState vertexTravelState = travelVertexMap.get(vertexId);
        if (vertexTravelState == VertexTravelState.COMPLETE) {
            return false;
        }
        if (vertexTravelState == VertexTravelState.PENDING) {
            return true;
        }

        Set<V> post = graph.post(vertex);
        for (V postVertex : post) {
            if (canTraverseWithoutReusingEdge(graph, preVertex, postVertex) &&
                    subHasCycle(graph, travelVertexMap, postVertex, vertex)) {
                return true;
            }
        }
        travelVertexMap.put(vertexId, VertexTravelState.COMPLETE);
        return false;
    }

    /**
     * can reach not use reusing edge, like A->B->A
     *
     * @param graph      graph
     * @param preVertex  pre
     * @param postVertex post
     * @param <V>        Vertex
     * @return boolean
     */
    private static <V extends Vertex, E extends Edge> boolean canTraverseWithoutReusingEdge(Graph<V, E> graph, V preVertex, V postVertex) {
        return graph.directed() || !Objects.equals(preVertex, postVertex);
    }

    private enum VertexTravelState {
        PENDING,
        COMPLETE
    }

}
