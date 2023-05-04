package priv.eric.mini.engine.entity.flow;

import priv.eric.mini.engine.entity.dag.Dag;
import priv.eric.mini.engine.entity.dag.Node;
import priv.eric.mini.engine.entity.graph.Vertex;
import priv.eric.mini.engine.entity.dag.Node;

import java.util.Set;

/**
 * Description: work pipeline
 *
 * @author EricTowns
 * @date 2023/4/28 16:49
 */
public class Pipeline {

    private Dag dag;

    private Context context;

    private Node first;

    private Set<Node> next;

    private State state;

    private Mode mode;

    public Pipeline(Dag dag, Context context, Node first) {
        this.dag = dag;
        this.context = context;
        this.first = first;
        this.state = State.BUILD;
    }

    public Dag getDag() {
        return dag;
    }

    public void setDag(Dag dag) {
        this.dag = dag;
    }

    public Node getFirst() {
        return first;
    }

    public void setFirst(Node first) {
        this.first = first;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Set<Node> getNext() {
        return next;
    }

    public void setNext(Set<Node> next) {
        this.next = next;
    }

    public void run(Context context) {
        dag.dfs(first, (node) -> node.execute(this));
    }

    public void debug(Context context) {

    }

    private void checkState() {
        if (state != State.RUNNABLE) {
            throw new IllegalStateException("pipeline now is not runnable, current state is " + state.name());
        }
    }

    public static enum State {
        /**
         * pipeline is build.
         */
        BUILD,
        /**
         * pipeline's dag can not run.
         */
        UNAVAILABLE,
        /**
         * pipeline's dag checked has no exception.
         */
        RUNNABLE,
        /**
         * pipeline is running.
         */
        RUNNING,
        /**
         * pipeline is complete.
         */
        COMPLETE,
        /**
         * pipeline has exception and stop.
         */
        PAUSE
    }

    public static enum Mode {
        RUN,
        DEBUG
    }
}
