package priv.eric.domain.flow;

import jdk.internal.event.Event;
import priv.eric.domain.dag.Node;

/**
 * desc: state machine of node
 *
 * @author EricTowns
 * @date 2023-05-27 22:31
 */
public class NodeStateMachine {

    private Node.State currentState;

    public NodeStateMachine(Node.State state) {
        this.currentState = state;
    }

    public void transfer(Event event) {

    }


}
