package graph;

import java.util.Set;

/**
 * Description: interface for graph
 *
 * @author EricTowns
 * @date 2023/4/27 23:42
 */
public interface Graph<V extends Vertex> {

    /**
     * all vertexes in this graph
     *
     * @return vertex set
     */
    Set<V> vertexes();

    /**
     * all edges in this graph
     *
     * @return edge set
     */
    Set<Edge<V>> edges();

    /**
     * Returns true if the edges in this graph are directed
     *
     * @return boolean
     */
    boolean directed();

    /**
     * Returns true if this graph allows self-loops (edges that connect a node to itself)
     *
     * @return boolean
     */
    boolean allowsSelfLoops();

    /**
     * all vertexes in this graph adjacency to vertex
     * this is equal union of {@link #pre(Vertex)} and {@link #post(Vertex)}
     *
     * @param vertex vertex
     * @return vertex set
     */
    Set<V> adjacencyVertexes(V vertex);

    /**
     * vertex's pre-vertexes
     *
     * @param vertex vertex
     * @return vertex set
     */
    Set<V> pre(V vertex);

    /**
     * vertex's post-vertexes
     *
     * @param vertex vertex
     * @return vertex set
     */
    Set<V> post(V vertex);

    /**
     * the edges in this graph whose endpoints include vertex.
     * this is equal union of incoming and outgoing edges.
     *
     * @return edge set
     */
    Set<Edge<V>> incidentEdges(V vertex);

    /**
     * Returns true if there is an edge that directly connects {@param from} to {@param to}.
     *
     * @param from from which vertex
     * @param to   to which vertex
     * @return boolean
     */
    boolean connecting(V from, V to);

    /**
     * the count of vertex's incident edges
     * this is equal union of {@link #inDegree(Vertex)} and {@link #outDegree(Vertex)}
     *
     * @param vertex vertex
     * @return count
     */
    int degree(V vertex);

    /**
     * the count of vertex's incoming edges;
     *
     * @param vertex vertex
     * @return count
     */
    int inDegree(V vertex);

    /**
     * the count of vertex's outgoing edges;
     *
     * @param vertex vertex
     * @return count
     */
    int outDegree(V vertex);


}
