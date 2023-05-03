package priv.eric.mini.engine.entity.dag;

import priv.eric.mini.engine.entity.graph.AbstractGraph;
import priv.eric.mini.engine.entity.graph.Edge;

import java.util.Set;

/**
 * Description: todo
 *
 * @author EricTowns
 * @date 2023/4/28 16:02
 */
public class Dag extends AbstractGraph<Node> {

    private Set<Node> nodes;

    private Set<Edge<Node>> edges;

    public Dag(Set<Node> nodes, Set<Edge<Node>> edges) {
        this.nodes = nodes;
        this.edges = edges;
    }

    public void check() {

    }

    @Override
    public Set<Node> vertexes() {
        return null;
    }

    @Override
    public Set<Edge<Node>> edges() {
        return null;
    }

    @Override
    public boolean directed() {
        return true;
    }

    @Override
    public boolean allowsSelfLoops() {
        return false;
    }

    @Override
    public Set<Node> adjacencyVertexes(Node vertex) {
        return null;
    }

    @Override
    public Set<Node> pre(Node vertex) {
        return null;
    }

    @Override
    public Set<Node> post(Node vertex) {
        return null;
    }

    @Override
    public Set<Edge<Node>> incidentEdges(Node vertex) {
        return null;
    }

    @Override
    public boolean connecting(Node from, Node to) {
        return false;
    }

    @Override
    public int degree(Node vertex) {
        return 0;
    }

    @Override
    public int inDegree(Node vertex) {
        return 0;
    }

    @Override
    public int outDegree(Node vertex) {
        return 0;
    }
}
