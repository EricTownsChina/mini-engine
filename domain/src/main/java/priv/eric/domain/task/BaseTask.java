package priv.eric.domain.task;

import priv.eric.domain.dag.Node;
import priv.eric.domain.flow.Context;

import java.util.Properties;

/**
 * Description: todo
 *
 * @author EricTowns
 * @date 2023/5/17 14:01
 */
public abstract class BaseTask implements Task {

    private Context context;

    private Node node;

    private final Type type;

    protected Context getContext() {
        return this.context;
    }

    public Type getType() {
        return type;
    }

    public BaseTask() {
        this.type = type();
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
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

    protected abstract void components();

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
        context.valueToNode(this.node, key, value);
    }

    protected void storeToGlobal(String key, Object value) {
        context.valueToGlobal(key, value);
    }

    protected <T> T getValueByExpress(String express) {
        return this.context.getValueByExpress(express);
    }

    public enum Type {
        BLANK,
        INPUT,
        OUTPUT,
        PRINT,
        SWITCH,
        CACHE,
        FILTER,
        MAP,
    }

}
