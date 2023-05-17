package priv.eric.domain.dag;

import priv.eric.infrastructure.graph.Edge;

/**
 * Description: dag edge
 *
 * @author EricTowns
 * @date 2023/5/5 14:50
 */
public class Line extends Edge<Node> {

    public Line(Node from, Node to) {
        super(from, to);
    }

}