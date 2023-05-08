package dag;

import common.exception.DagException;
import common.exception.ExceptionType;
import common.exception.InstantiationException;
import graph.AbstractGraph;
import graph.Edge;
import graph.Graphs;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Description: dag
 *
 * @author EricTowns
 * @date 2023/4/28 16:02
 */
public class Dag extends AbstractGraph<Node> {

    private final Map<String, Node> nodeMap;

    public Dag(Set<Node> nodes, Set<Edge<Node>> edges) {
        super(nodes, edges);
        check();
        this.nodeMap = nodes.stream().collect(Collectors.toMap(Node::getId, n -> n));
    }

    @Override
    public void dfs(Node start, Consumer<Node> consumer, Function<Node, Node> function) {
        Map<Node, Set<Node>> adjacencyMap = super.getAdjacencyMap();
        String startId = start.getId();
        Node startNode = nodeMap.get(startId);
        if (startNode == null) {
            throw new DagException(ExceptionType.DAG_TRAVERSE_NO_ROOT);
        }
        consumer.accept(startNode);
        Set<Node> post = adjacencyMap.getOrDefault(startNode, new HashSet<>(0));
        for (Node node : post) {
            dfsTraverse(node, adjacencyMap, consumer, function);
        }
    }

    private void dfsTraverse(Node node, Map<Node, Set<Node>> adjacencyMap, Consumer<Node> consumer, Function<Node, Node> function) {
        if (Node.State.WAIT == node.getState()) {
            node.setState(Node.State.RUNNING);
            consumer.accept(node);
            Node nextNode = function.apply(node);
            node.setState(Node.State.COMPLETE);
            // if node define next node, use it
            if (nextNode != null) {
                dfsTraverse(nextNode, adjacencyMap, consumer, function);
            } else {
                Set<Node> post = adjacencyMap.getOrDefault(node, new HashSet<>(0));
                for (Node postNode : post) {
                    dfsTraverse(postNode, adjacencyMap, consumer, function);
                }
            }
        }
    }

    public void check() {
        if (Graphs.hasCycle(this)) {
            throw new InstantiationException(ExceptionType.GRAPH_NOT_DAG, "graph is not a dag, it has cycle.");
        }
    }

    public Node getNodeById(String id) {
        return nodeMap.get(id);
    }

    @Override
    public Set<Node> vertexes() {
        return super.getVertexes();
    }

    @Override
    public Set<Edge<Node>> edges() {
        return super.getEdges();
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
    public Set<Node> adjacencyVertexes(Node node) {
        Set<Node> pre = pre(node);
        Set<Node> post = post(node);
        pre.addAll(post);
        return pre;
    }

    @Override
    public Set<Node> pre(Node node) {
        Map<Node, Set<Node>> adjacencyMap = super.getAdjacencyMap();
        return adjacencyMap.keySet().stream().filter(adjacencyMap::containsKey).collect(Collectors.toSet());
    }

    @Override
    public Set<Node> post(Node node) {
        return super.getAdjacencyMap().getOrDefault(node, new HashSet<>(0));
    }

    @Override
    public Set<Edge<Node>> incidentEdges(Node node) {
        Map<String, Edge<Node>> edgeMap = super.getEdgeMap();
        Set<Edge<Node>> incidentEdges = new HashSet<>(1);
        for (String edgeKey : edgeMap.keySet()) {
            if (edgeKey.contains(node.getId())) {
                incidentEdges.add(edgeMap.get(edgeKey));
            }
        }
        return incidentEdges;
    }

    @Override
    public boolean connecting(Node from, Node to) {
        String edgeKey = from.getId() + AbstractGraph.CONNECT + to.getId();
        return null != super.getEdgeMap().get(edgeKey);
    }

    @Override
    public int degree(Node node) {
        Set<Node> pre = pre(node);
        Set<Node> post = post(node);
        return pre.size() + post.size();
    }

    @Override
    public int inDegree(Node node) {
        return pre(node).size();
    }

    @Override
    public int outDegree(Node node) {
        return post(node).size();
    }

}