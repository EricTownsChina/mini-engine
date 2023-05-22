package priv.eric.domain.dag;

import priv.eric.infrastructure.common.exception.DagException;
import priv.eric.infrastructure.common.exception.ExceptionType;
import priv.eric.infrastructure.common.exception.InstantiationException;
import priv.eric.infrastructure.graph.AbstractGraph;
import priv.eric.infrastructure.graph.Edge;
import priv.eric.infrastructure.graph.Graphs;
import priv.eric.infrastructure.graph.Vertex;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Description: dag
 *
 * @author EricTowns
 * @date 2023/4/28 16:02
 */
public class Dag extends AbstractGraph<Node> {

    private final Map<String, Node> nodeMap;

    private final LinkedHashMap<Node, Integer> runningRoute;

    public Dag(Builder builder) {
        super(builder.nodes, builder.edges);
        check();
        this.nodeMap = builder.nodes.stream().collect(Collectors.toMap(Node::getId, n -> n));
        this.runningRoute = new LinkedHashMap<>(nodeMap.size());
    }

    public Dag(Set<Node> nodes, Set<Edge<Node>> edges) {
        super(nodes, edges);
        check();
        this.nodeMap = nodes.stream().collect(Collectors.toMap(Node::getId, n -> n));
        this.runningRoute = new LinkedHashMap<>(nodeMap.size());
    }

    public static Builder n() {
        return new Builder();
    }

    @Override
    public void dfs(Node start, Consumer<Node> consumer) {
        Map<String, Set<Node>> adjacencyMap = super.getAdjacencyMap();
        String startId = start.getId();
        Node startNode = nodeMap.get(startId);
        if (startNode == null) {
            throw new DagException(ExceptionType.DAG_TRAVERSE_NO_ROOT);
        }
        if (startNode.checkState() && checkRunning(startNode)) {
            consumer.accept(startNode);
            runningRoute.put(startNode, 1);
            Set<Node> post = adjacencyMap.getOrDefault(startNode.getId(), new HashSet<>(0));
            for (Node node : post) {
                dfsTraverse(node, adjacencyMap, consumer);
            }
        }
    }

    private void dfsTraverse(Node node, Map<String, Set<Node>> adjacencyMap, Consumer<Node> consumer) {
        if (node.checkState() && checkRunning(node)) {
            node.setState(Node.State.RUNNING);
            consumer.accept(node);
            runningRoute.put(node, 1);
            node.setState(Node.State.COMPLETE);
            Set<Node> post = adjacencyMap.getOrDefault(node.getId(), new HashSet<>(0));
            for (Node postNode : post) {
                dfsTraverse(postNode, adjacencyMap, consumer);
            }
        } else {
            runningRoute.put(node, 1);
        }
    }

    public LinkedHashMap<Node, Integer> getRunningRoute() {
        return runningRoute;
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
        Map<String, Set<Node>> adjacencyMap = super.getAdjacencyMap();
        Set<String> preNodeIds = adjacencyMap.keySet()
                .stream()
                .filter(key -> {
                    Set<Node> nodes = adjacencyMap.getOrDefault(key, new HashSet<>(0));
                    return nodes.stream().map(Vertex::getId).collect(Collectors.toSet()).contains(node.getId());
                })
                .collect(Collectors.toSet());
        return preNodeIds.stream().map(nodeMap::get).collect(Collectors.toSet());
    }

    @Override
    public Set<Node> post(Node node) {
        return super.getAdjacencyMap().getOrDefault(node.getId(), new HashSet<>(0));
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

    public boolean checkRunning(Node node) {
        if (runningRoute.containsKey(node)) {
            return false;
        }
        Set<Node> preNodes = pre(node);
        if (preNodes != null && !preNodes.isEmpty()) {
            for (Node preNode : preNodes) {
                if (!runningRoute.containsKey(preNode)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static class Builder {
        private final Set<Node> nodes = new HashSet<>();
        private final Set<Edge<Node>> edges = new HashSet<>();

        public Builder addNode(Node node) {
            this.nodes.add(node);
            return this;
        }

        public Builder addEdge(Edge<Node> edge) {
            this.edges.add(edge);
            return this;
        }

        public Builder addAllNodes(Set<Node> nodes) {
            this.nodes.addAll(nodes);
            return this;
        }

        public Builder setEdges(Set<Edge<Node>> edges) {
            this.edges.addAll(edges);
            return this;
        }

        public Dag build() {
            return new Dag(this);
        }
    }

}
