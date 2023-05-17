package priv.eric.infrastructure.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

/**
 * Description: extends abstract graph to build a graph entity
 *
 * @author EricTowns
 * @date 2023/4/28 13:58
 */
public abstract class AbstractGraph<V extends Vertex> implements Graph<V> {

    public static final String CONNECT = "->";

    private final Set<V> vertexes;

    private final Set<Edge<V>> edges;

    private final Map<V, Set<V>> adjacencyMap;

    private final Map<String, Edge<V>> edgeMap;

    public AbstractGraph(Set<V> vertexes, Set<Edge<V>> edges) {
        this.vertexes = vertexes;
        this.edges = edges;
        this.adjacencyMap = new HashMap<>(vertexes.size());
        this.edgeMap = new HashMap<>(edges.size());
        for (Edge<V> edge : edges) {
            V from = edge.getFrom();
            V to = edge.getTo();
            edgeMap.put(from.getId() + CONNECT + to.getId(), edge);
            Set<V> postVertexes = adjacencyMap.get(from);
            if (postVertexes == null) {
                postVertexes = new HashSet<>(1);
                postVertexes.add(to);
                adjacencyMap.put(from, postVertexes);
            } else {
                postVertexes.add(to);
            }
        }
    }

    public Set<V> getVertexes() {
        return vertexes;
    }

    public Set<Edge<V>> getEdges() {
        return edges;
    }

    public Map<V, Set<V>> getAdjacencyMap() {
        return adjacencyMap;
    }

    public Map<String, Edge<V>> getEdgeMap() {
        return edgeMap;
    }

    public abstract void dfs(V start, Consumer<V> consumer);
}
