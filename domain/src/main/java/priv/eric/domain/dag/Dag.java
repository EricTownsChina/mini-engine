package priv.eric.domain.dag;

import priv.eric.infrastructure.common.exception.DagException;
import priv.eric.infrastructure.common.exception.ExceptionType;
import priv.eric.infrastructure.common.exception.InstantiationException;
import priv.eric.infrastructure.graph.AbstractGraph;
import priv.eric.infrastructure.graph.Edge;
import priv.eric.infrastructure.graph.Graphs;
import priv.eric.infrastructure.graph.Vertex;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Description: dag
 *
 * @author EricTowns
 * @date 2023/4/28 16:02
 */
public class Dag extends AbstractGraph<Node, Line> {

    private final Map<String, Node> nodeMap;

    public Dag(Builder builder) {
        super(builder.nodes, builder.edges);
        check();
        this.vertexes().forEach(n -> n.setState(Node.State.RUNNABLE));
        this.nodeMap = builder.nodes.stream().collect(Collectors.toMap(Node::getId, n -> n));
    }

    public Dag(Set<Node> nodes, Set<Line> edges) {
        super(nodes, edges);
        check();
        this.vertexes().forEach(n -> n.setState(Node.State.RUNNABLE));
        this.nodeMap = nodes.stream().collect(Collectors.toMap(Node::getId, n -> n));
    }

    public static Builder n() {
        return new Builder();
    }

    @Override
    public void dfs(String rootId, Consumer<Node> consumer) {
        Map<String, Set<String>> adjacencyMap = super.getAdjacencyMap();
        dfsTraverse(rootId, consumer, adjacencyMap);
    }

    private void dfsTraverse(String nodeId, Consumer<Node> consumer, Map<String, Set<String>> adjacencyMap) {
        Node node = nodeMap.get(nodeId);
        if (null == node) {
            throw new DagException(ExceptionType.DAG_TRAVERSE_ILLEGAL_NODE);
        }
        Node.State state = checkState(node);
        if (Node.State.RUNNABLE == state) {
            return;
        }
        node.setState(state);
        consumer.accept(node);
        node.setState(Node.State.SKIP == state ? Node.State.SKIP : Node.State.COMPLETE);
        Set<String> post = adjacencyMap.getOrDefault(node.getId(), new HashSet<>(0));
        for (String postNode : post) {
            dfsTraverse(postNode, consumer, adjacencyMap);
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
    public Set<Line> edges() {
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
        Map<String, Set<String>> adjacencyMap = super.getAdjacencyMap();
        Set<String> preNodeIds = adjacencyMap.keySet()
                .stream()
                .filter(key -> {
                    Set<String> nodes = adjacencyMap.getOrDefault(key, new HashSet<>(0));
                    return nodes.contains(node.getId());
                })
                .collect(Collectors.toSet());
        return preNodeIds.stream().map(nodeMap::get).collect(Collectors.toSet());
    }

    @Override
    public Set<Node> post(Node node) {
        Set<String> postNodeIds = super.getAdjacencyMap().getOrDefault(node.getId(), new HashSet<>(0));
        return postNodeIds.stream().map(nodeMap::get).collect(Collectors.toSet());
    }

    @Override
    public Set<Line> incidentEdges(Node node) {
        Map<String, Line> edgeMap = super.getEdgeMap();
        Set<Line> incidentEdges = new HashSet<>(1);
        for (String edgeKey : edgeMap.keySet()) {
            if (edgeKey.contains(node.getId())) {
                incidentEdges.add(edgeMap.get(edgeKey));
            }
        }
        return incidentEdges;
    }

    @Override
    public boolean connecting(String from, String to) {
        String edgeKey = from + AbstractGraph.CONNECT + to;
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

    public Node.State checkState(Node node) {
        Set<Node> preNodes = pre(node);
        if (preNodes.isEmpty()) {
            return Node.State.RUNNING;
        } else {
            int stateCode = 0;
            int preSize = preNodes.size();
            for (Node preNode : preNodes) {
                if (preNode.getState() == Node.State.RUNNABLE) {
                    return Node.State.RUNNABLE;
                }
                stateCode += preNode.getState().getCode();
            }
            if (stateCode == -preSize) {
                return Node.State.SKIP;
            } else {
                return Node.State.RUNNING;
            }
        }
    }

    public static class Builder {
        private final Set<Node> nodes = new HashSet<>();
        private final Set<Line> edges = new HashSet<>();

        public Builder addNode(Node node) {
            this.nodes.add(node);
            return this;
        }

        public Builder addEdge(Line line) {
            this.edges.add(line);
            return this;
        }

        public Builder addAllNodes(Set<Node> nodes) {
            this.nodes.addAll(nodes);
            return this;
        }

        public Builder setEdges(Set<Line> lines) {
            this.edges.addAll(lines);
            return this;
        }

        public Dag build() {
            return new Dag(this);
        }
    }

}
