package priv.eric.domain.flow;


import priv.eric.domain.dag.Dag;
import priv.eric.domain.dag.Node;
import priv.eric.domain.task.Task;

import java.util.Set;

/**
 * Description: work pipeline
 *
 * @author EricTowns
 * @date 2023/4/28 16:49
 */
public class Pipeline {

    private Dag dag;

    private Node first;

    private Set<Node> next;

    private State state;

    private Mode mode;

    public Pipeline(Dag dag, Node first) {
        this.dag = dag;
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
        dag.dfs(first, (node) -> {
            Task task = node.getTask();
            if (task != null) {
                task.setContext(context);
                task.setNode(node);
                task.run();
            }
        });
        setState(State.COMPLETE);
    }

    public void debug() {

    }

    private void checkState() {
        if (state != State.RUNNABLE) {
            throw new IllegalStateException("pipeline now is not runnable, current state is " + state.name());
        }
    }

    public enum State {
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

    public enum Mode {
        RUN,
        DEBUG
    }
}
