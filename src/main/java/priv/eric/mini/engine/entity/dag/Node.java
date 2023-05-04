package priv.eric.mini.engine.entity.dag;

import priv.eric.mini.engine.entity.flow.Pipeline;
import priv.eric.mini.engine.entity.graph.Vertex;

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

    public Node(String name, String desc, Type type) {
        this.name = name;
        this.desc = desc;
        this.type = type;
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
     * when pipeline is running, run to the node to execute the function.
     *
     * @param pipeline {@link Pipeline}
     */
    public abstract void execute(Pipeline pipeline);

    /**
     * record node info when pipeline run to the node.
     *
     * @param pipeline {@link Pipeline}
     */
    public abstract void record(Pipeline pipeline);

    public enum Type {
        BLANK,
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
