package priv.eric.domain.flow;

import priv.eric.domain.dag.Dag;
import priv.eric.infrastructure.common.kit.Constants;
import priv.eric.infrastructure.common.kit.Extractor;
import priv.eric.infrastructure.common.kit.Storage;
import priv.eric.domain.dag.Node;

import java.util.*;

/**
 * Description: context of {@link Pipeline}
 *
 * @author EricTowns
 * @date 2023/4/28 16:49
 */
public class Context {

    /**
     * Context Keyword
     */
    public static final String INPUT = "INPUT";
    public static final String OUTPUT = "OUTPUT";

    private final String id;

    private final Pipeline pipeline;

    private final Properties globalProp;

    private final Map<Node, Properties> nodeProp;

    private final Map<String, Object> globalStorage;

    private final Map<Node, Map<String, Object>> nodeStorage;

    public Context(Pipeline pipeline) {
        this.id = UUID.randomUUID().toString().replace(Constants.MINUS, Constants.EMPTY_STR);
        this.pipeline = pipeline;
        Dag dag = pipeline.getDag();
        Set<Node> vertexes = dag.vertexes();
        globalProp = new Properties();
        nodeProp = new HashMap<>(vertexes.size());
        globalStorage = new HashMap<>(1);
        nodeStorage = new HashMap<>(vertexes.size());
    }

    public String getId() {
        return this.id;
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

    public <T> T jsonPropFromNode(Node node, String key, Class<T> clazz) {
        String value = propFromNode(node, key);
        return Storage.defaultGson().fromJson(value, clazz);
    }

    public String propFromGlobal(String key) {
        return globalProp.getProperty(key);
    }

    public <T> T jsonPropFromGlobal(String key, Class<T> clazz) {
        String value = propFromGlobal(key);
        return Storage.defaultGson().fromJson(value, clazz);
    }

    public void propToGlobal(String key, String value) {
        globalProp.setProperty(key, value);
    }

    public void jsonPropToGlobal(String key, Object value) {
        String valueJsonString = Storage.defaultGson().toJson(value);
        propToGlobal(key, valueJsonString);
    }

    public void propToNode(Node node, String key, String value) {
        Properties properties = nodeProp.getOrDefault(node, new Properties());
        properties.setProperty(key, value);
        nodeProp.put(node, properties);
    }

    public void jsonPropToNode(Node node, String key, Object value) {
        String valueJsonString = Storage.defaultGson().toJson(value);
        propToNode(node, key, valueJsonString);
    }

    public Properties getGlobalProp() {
        return globalProp;
    }

    public Map<String, Object> getGlobalStorage() {
        return globalStorage;
    }

    public Map<Node, Properties> getNodeProps() {
        return nodeProp;
    }

    public Map<Node, Map<String, Object>> getNodeStorage() {
        return nodeStorage;
    }

    public <T> T getValueByExpress(String express) {
        if (express == null || express.isEmpty()) {
            return null;
        }
        if (!express.startsWith(Constants.DOLLAR)) {
            return (T) express;
        }
        int index = express.contains(Constants.DOT) ? express.indexOf(Constants.DOT) : express.length() - 1;
        String originExpress = express.substring(0, index).replace(Constants.DOLLAR, Constants.EMPTY_STR);
        Object originValue;
        if (originExpress.isEmpty()) {
            originValue = globalStorage;
        } else {
            Node node = pipeline.getDag().getNodeById(originExpress);
            originValue = nodeStorage.get(node);
        }
        String postExpress = express.substring(index + 1);
        return (T) Extractor.of(originValue, postExpress).getValue();
    }
}
