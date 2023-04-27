package priv.eric.mini.engine.entity.graph;

import java.util.Set;

/**
 * Description: interface for graph
 *
 * @author EricTowns
 * @date 2023/4/27 23:42
 */
public interface Graph<V extends Vertex> {

    /**
     * all vertex in this graph
     *
     * @return vertex set
     */
    Set<V> vertexes();

    /**
     * all edge in this graph
     *
     * @return edge set
     */
    Set<Edge<V>> edges();

    /**
     * adjacency vertex of parameter
     * this is equal union of {@link #preVertexes(Vertex)} and {@link #postVertexes(Vertex)}
     *
     * @param vertex vertex
     * @return vertex set
     */
    Set<V> adjacencyVertexes(V vertex);

    /**
     * pre-vertex set of parameter
     *
     * @param vertex vertex
     * @return vertex set
     */
    Set<V> preVertexes(V vertex);

    /**
     * post-vertex set of parameter
     *
     * @param vertex vertex
     * @return vertex set
     */
    Set<V> postVertexes(V vertex);

}
