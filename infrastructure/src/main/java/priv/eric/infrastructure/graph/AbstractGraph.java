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
public abstract class AbstractGraph<V extends Vertex, E extends Edge> implements Graph<V, E> {

    public static final String CONNECT = "->";

    private final Set<V> vertexes;

    private final Set<E> edges;

    private final Map<String, Set<String>> adjacencyMap;

    private final Map<String, E> edgeMap;

    public AbstractGraph(Set<V> vertexes, Set<E> edges) {
        this.vertexes = vertexes;
        this.edges = edges;
        this.adjacencyMap = new HashMap<>(vertexes.size());
        this.edgeMap = new HashMap<>(edges.size());
        for (E edge : edges) {
            String from = edge.getFrom();
            String to = edge.getTo();
            edgeMap.put(from + CONNECT + to, edge);
            Set<String> postVertexes = adjacencyMap.get(from);
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

    public Set<E> getEdges() {
        return edges;
    }

    public Map<String, Set<String>> getAdjacencyMap() {
        return adjacencyMap;
    }

    public Map<String, E> getEdgeMap() {
        return edgeMap;
    }

    public abstract void dfs(String rootId, Consumer<V> consumer);
}
