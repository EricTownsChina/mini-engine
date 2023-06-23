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

    private String rootId;

    private Set<Node> next;

    private State state;

    public Pipeline(Dag dag, String rootId) {
        this.dag = dag;
        this.rootId = rootId;
        this.state = State.BUILD;
    }

    public Dag getDag() {
        return dag;
    }

    public void setDag(Dag dag) {
        this.dag = dag;
    }

    public Node getRoot() {
        return dag.getNodeById(rootId);
    }

    public void setRootId(String rootId) {
        this.rootId = rootId;
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
        dag.dfs(rootId, node -> {
            Task task = node.getTask();
            if (task != null) {
                task.setContext(context);
                task.setNode(node);
                task.run();
            }
        });
        setState(State.COMPLETE);
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
}
