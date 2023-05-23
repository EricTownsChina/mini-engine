package priv.eric.domain.task;

import priv.eric.domain.dag.Node;
import priv.eric.domain.flow.Context;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Description: todo
 *
 * @author EricTowns
 * @date 2023/5/17 14:01
 */
public abstract class BaseTask implements Task {

    private final Type type;
    private final Map<String, Object> components = new HashMap<>(0);
    private Context context;
    private Node node;

    public BaseTask() {
        this.type = type();
    }

    protected Context getContext() {
        return this.context;
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }

    public Type getType() {
        return type;
    }

    public Map<String, Object> getComponents() {
        return components;
    }

    public <T> T getComponent(String componentKey) {
        Object bean = components.get(componentKey);
        if (bean == null) {
            throw new NoComponentException(componentKey);
        } else {
            return (T) bean;
        }
    }

    public void putComponent(String componentKey, Object componentBean) {
        components.put(componentKey, componentBean);
    }

    protected Node getNode() {
        return node;
    }

    @Override
    public void setNode(Node node) {
        this.node = node;
    }

    @Override
    public void run() {
        process();
        record();
    }

    protected abstract Type type();

    public void components() {
    }

    protected abstract void process();

    protected abstract void record();

    protected String getNodeId() {
        return this.node.getId();
    }

    protected Properties getNodeProp() {
        return this.context.getNodeProps().getOrDefault(this.node, new Properties());
    }

    protected String getFromNodeProp(String key) {
        return getNodeProp().getProperty(key);
    }

    protected Properties getGlobalProp() {
        return this.context.getGlobalProp();
    }

    protected String getFromGlobalProp(String key) {
        return getGlobalProp().getProperty(key);
    }

    protected void storeToNode(String key, Object value) {
        context.storeValueToNode(this.node, key, value);
    }

    protected void storeToGlobal(String key, Object value) {
        context.storeValueToGlobal(key, value);
    }

    protected <T> T getValueByExpress(String express) {
        return this.context.getValueByExpress(express);
    }

    public enum Type {
        BLANK,
        INPUT,
        OUTPUT,
        PRINT,
        HTTP_REQUEST,
        SWITCH,
        CACHE,
        FILTER,
        MAP,
    }

}
