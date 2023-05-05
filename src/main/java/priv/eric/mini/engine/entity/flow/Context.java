package priv.eric.mini.engine.entity.flow;

import priv.eric.mini.engine.entity.dag.Dag;
import priv.eric.mini.engine.entity.dag.Node;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Description: context of {@link Pipeline}
 *
 * @author EricTowns
 * @date 2023/4/28 16:49
 */
public class Context {

    private final Pipeline pipeline;

    private final Properties globalProp;

    private final Map<Node, Properties> nodeProp;

    private final Map<String, Object> globalStorage;

    private final Map<Node, Map<String, Object>> nodeStorage;

    public Context(Pipeline pipeline) {
        this.pipeline = pipeline;
        Dag dag = pipeline.getDag();
        Set<Node> vertexes = dag.vertexes();
        globalProp = new Properties();
        nodeProp = new HashMap<>(vertexes.size());
        globalStorage = new HashMap<>(1);
        nodeStorage = new HashMap<>(vertexes.size());
    }

    public Pipeline getPipeline() {
        return pipeline;
    }

    public <T> T valueFromGlobal(String key) {
        return (T) globalStorage.get(key);
    }

    public <T> T valueFromNode(Node node, String key) {
        Map<String, Object> storage = nodeStorage.getOrDefault(node, new HashMap<>(0));
        return (T) storage.get(key);
    }

    public void valueToGlobal(String key, Object value) {
        globalStorage.put(key, value);
    }

    public void valueToNode(Node node, String key, Object value) {
        Map<String, Object> storage = nodeStorage.getOrDefault(node, new HashMap<>(1));
        storage.put(key, value);
        nodeStorage.put(node, storage);
    }

    public String propFromNode(Node node, String key) {
        Properties properties = nodeProp.getOrDefault(node, new Properties());
        return properties.getProperty(key);
    }

    public String propFromGlobal(String key) {
        return globalProp.getProperty(key);
    }

    public void propToGlobal(String key, String value) {
        globalProp.setProperty(key, value);
    }

    public void propToNode(Node node, String key, String value) {
        Properties properties = nodeProp.getOrDefault(node, new Properties());
        properties.setProperty(key, value);
        nodeProp.put(node, properties);
    }

}
