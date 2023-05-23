package priv.eric.domain.dag;


import priv.eric.domain.flow.Context;
import priv.eric.infrastructure.graph.Vertex;
import priv.eric.domain.task.Task;

import java.util.Properties;

/**
 * Description: Dag node
 *
 * @author EricTowns
 * @date 2023/4/28 16:02
 */
public class Node extends Vertex {

    private Task task;

    private State state;

    public Node() {}

    public Node(String id) {
        super(id);
        this.state = State.NEW;
    }

    public static Node build(String id) {
        return new Node(id);
    }

    public Task getTask() {
        return task;
    }

    public Node setTask(Task task) {
        this.task = task;
        return this;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }

    public Properties getGlobalProp(Context context) {
        return context.getGlobalProp();
    }

    public void valueToGlobalStorage(Context context, String key, Object value) {
        context.storeValueToGlobal(key, value);
    }

    public Properties selfProp(Context context) {
        return context.getNodeProps().getOrDefault(this, new Properties());
    }

    public <T> T selfPropJsonValue(Context context, String key, Class<T> clazz) {
        return context.jsonPropFromNode(this, key, clazz);
    }

    public <T> T globalPropJsonValue(Context context, String key, Class<T> clazz) {
        return context.getJsonPropFromGlobal(key, clazz);
    }

    public void valueToSelfStorage(Context context, String key, Object value) {
        context.storeValueToNode(this, key, value);
    }

    public enum State {
        NEW(0),
        RUNNABLE(1),
        RUNNING(2),
        SKIP(-1),
        COMPLETE(3),
        ;

        private final Integer code;

        State(int code) {
            this.code = code;
        }

        public int getCode() {
            return this.code;
        }
    }
}
