package priv.eric.mini.engine.entity.flow;

import priv.eric.mini.engine.entity.dag.Dag;
import priv.eric.mini.engine.entity.dag.Node;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Description: context of {@link Pipeline}
 *
 * @author EricTowns
 * @date 2023/4/28 16:49
 */
public class Context {

    private final Pipeline pipeline;

    private final Map<Node, Map<String, Object>> nodeStorage;

    private final Map<String, Object> globalStorage;

    public Context(Pipeline pipeline) {
        this.pipeline = pipeline;
        Dag dag = pipeline.getDag();
        Set<Node> vertexes = dag.vertexes();
        nodeStorage = new HashMap<>(vertexes.size());
        globalStorage = new HashMap<>(1);
    }

    public Pipeline getPipeline() {
        return pipeline;
    }

    public <T> T getFromNode(Node node, String key) {
        Map<String, Object> storage = nodeStorage.getOrDefault(node, new HashMap<>(0));
        return (T) storage.get(key);
    }

    public <T> T getFromGlobal(String key) {
        return (T) globalStorage.get(key);
    }

    public void addToNode(Node node, String key, Object value) {
        Map<String, Object> storage = nodeStorage.getOrDefault(node, new HashMap<>(1));
        storage.put(key, value);
        nodeStorage.put(node, storage);
    }

    public void addToGlobal(String key, Object value) {
        globalStorage.put(key, value);
    }

}
