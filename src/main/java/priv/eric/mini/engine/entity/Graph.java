package priv.eric.mini.engine.entity;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Description: 图
 *
 * @author EricTowns
 * @date 2023/4/25 23:20
 */
public abstract class Graph<T extends Vertex> {

    private final Map<T, List<T>> adjacencyMap;
    private final Set<T> vertexes;
    private final Set<Edge<T>> edges;
    private Map<String, T> vertexMap;

    public Graph(Set<T> vertexes, Set<Edge<T>> edges) {
        Map<String, T> originVertexMap = vertexes.stream()
                .collect(Collectors.toMap(Vertex::getId, v -> v, (v1, v2) -> v2));
        this.adjacencyMap = new HashMap<>(originVertexMap.size());
        this.vertexes = new HashSet<>(vertexes.size());
        this.edges = new HashSet<>(edges.size());

        for (Edge<T> edge : edges) {
            String from = edge.getFrom();
            String to = edge.getTo();
            T fromVertex = originVertexMap.get(from);
            T toVertex = originVertexMap.get(to);
            if (null == fromVertex || null == toVertex) {
                throw new IllegalArgumentException("illegal edge " + edge + " : not found from or to vertex.");
            }
            this.vertexes.add(fromVertex);
            this.vertexes.add(toVertex);
            if (fromVertex.equals(toVertex)) {
                continue;
            }
            this.edges.add(edge);
            List<T> toVertexList = adjacencyMap.getOrDefault(fromVertex, new ArrayList<>(1));
            if (!toVertexList.contains(toVertex)) {
                toVertexList.add(toVertex);
                adjacencyMap.put(fromVertex, toVertexList);
            }
        }
    }

    public List<T> getAdjacencyVertex(T vertex) {
        return adjacencyMap.get(vertex);
    }

    public List<T> getAdjacencyVertex(String vertexId) {
        T vertex = vertexMap.get(vertexId);
        if (null == vertex) {
            return new ArrayList<>(0);
        } else {
            return adjacencyMap.get(vertex);
        }
    }

    public Map<T, List<T>> getAdjacencyMap() {
        return adjacencyMap;
    }

    public Set<T> getVertexes() {
        return vertexes;
    }

    public Set<Edge<T>> getEdges() {
        return edges;
    }

    public Map<String, T> getVertexMap() {
        return vertexMap;
    }

    /**
     * graph is directed or not
     *
     * @return boolean
     */
    abstract boolean isDirected();

    /**
     * graph has cycle
     *
     * @return boolean
     */
    abstract boolean isCycle();

}
