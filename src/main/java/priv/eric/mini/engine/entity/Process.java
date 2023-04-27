package priv.eric.mini.engine.entity;

import priv.eric.mini.engine.entity.graph.Edge;
import priv.eric.mini.engine.entity.graph.Vertex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Description: 流程
 *
 * @author EricTowns
 * @date 2023/4/22 22:52
 */
public abstract class Process<T extends Vertex> {

    private Map<String, T> vertexMap = new HashMap<>(0);

    private List<Edge> edgeList = new ArrayList<>(0);

    private T startVertex;

    public Process(List<T> vertexList, List<Edge> edgeList) {
        this.vertexMap = vertexList.stream().collect(Collectors.toMap(Vertex::getId, v -> v, (v1, v2) -> v2));
        this.edgeList = edgeList;
    }

    public T getVertex(String vertexId) {
        return vertexMap.get(vertexId);
    }

    public void validate() {
        List<T> startVertexList = new ArrayList<>(1);
        for (String vertexId : vertexMap.keySet()) {
            T vertex = vertexMap.get(vertexId);
            if (vertex.getPre().isEmpty() && !vertex.getPost().isEmpty()) {
                startVertexList.add(vertex);
            }
        }
        if (startVertexList.size() > 1) {
            throw new IllegalArgumentException("存在多个根节点");
        } else if (startVertexList.size() < 1) {
            throw new IllegalArgumentException("没有找到根节点");
        } else {
            startVertex = startVertexList.get(0);
        }
    }

    public void build() {
        for (Edge edge : edgeList) {
            String from = edge.getFrom();
            String to = edge.getTo();
            T fromVertex = vertexMap.get(from);
            T toVertex = vertexMap.get(to);
            if (fromVertex != null && toVertex != null) {
                fromVertex.addPost(toVertex);
                toVertex.addPre(fromVertex);
            }
        }
    }

    /**
     * 下一个顶点
     *
     * @return 顶点
     */
    public abstract T nextVertex();

}
