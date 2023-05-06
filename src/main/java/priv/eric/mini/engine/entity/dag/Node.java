package priv.eric.mini.engine.entity.dag;

import priv.eric.mini.engine.entity.flow.Context;
import priv.eric.mini.engine.entity.graph.Vertex;

import java.util.Properties;

/**
 * Description: Dag node
 *
 * @author EricTowns
 * @date 2023/4/28 16:02
 */
public abstract class Node extends Vertex {

    private String name;

    private String desc;

    private Type type;

    private State state;

    public Node(String id, String name, String desc) {
        super.setId(id);
        this.name = name;
        this.desc = desc;
        this.type = type();
        this.state = State.WAIT;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Type getType() {
        return type;
    }

    public Node setType(Type type) {
        this.type = type;
        return this;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String nextId() {
        return null;
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }

    /**
     * declare type of node.
     *
     * @return {@link Type}
     */
    public abstract Type type();

    /**
     * before node build, add some special component, like spring component,
     * this function will execute once when node building.
     */
    public abstract void addComponents();

    /**
     * when pipeline is running, run to the node to execute the function.
     *
     * @param context {@link Context}
     */
    public abstract void process(Context context);

    /**
     * record node info when pipeline run to the node.
     *
     * @param context {@link Context}
     */
    public abstract void record(Context context);

    public Properties getGlobalProp(Context context) {
        return context.getGlobalProp();
    }

    public void valueToGlobalStorage(Context context, String key, Object value) {
        context.valueToGlobal(key, value);
    }

    public Properties getSelfProp(Context context) {
        return context.getNodeProp().getOrDefault(this, new Properties());
    }

    public void valueToSelfStorage(Context context, String key, Object value) {
        context.valueToNode(this, key, value);
    }

    public enum Type {
        BLANK,
        START,
        PRINT,
        SWITCH,
        CACHE,
        FILTER,
        MAP,
    }

    public enum State {
        WAIT,
        RUNNING,
        COMPLETE,
        PAUSE
    }
}
