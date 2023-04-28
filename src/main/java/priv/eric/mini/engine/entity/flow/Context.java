package priv.eric.mini.engine.entity.flow;

import priv.eric.mini.engine.entity.dag.Node;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: context of {@link Pipeline}
 *
 * @author EricTowns
 * @date 2023/4/28 16:49
 */
public class Context {

    private Pipeline pipeline;

    private final Map<Node, Map<String, Object>> nodeStorage = new HashMap<>(1);

    private final Map<String, Object> globalStorage = new HashMap<>(1);

    public Context(Pipeline pipeline) {
        this.pipeline = pipeline;
    }

    public <T> T getValueFromNode(Node node, String key) {
        Map<String, Object> storage = nodeStorage.getOrDefault(node, new HashMap<>(0));
        return (T) storage.get(key);
    }

    public <T> T getValueFromGlobal(String key) {
        return (T) globalStorage.get(key);
    }

    public void addInNode(Node node, String key, Object value) {
        Map<String, Object> storage = nodeStorage.getOrDefault(node, new HashMap<>(1));
        storage.put(key, value);
        nodeStorage.put(node, storage);
    }

    public void addInGlobal(String key, Object value) {
        globalStorage.put(key, value);
    }

}
