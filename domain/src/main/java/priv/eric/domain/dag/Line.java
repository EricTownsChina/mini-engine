package priv.eric.domain.dag;

import priv.eric.infrastructure.graph.Edge;

/**
 * Description: dag edge
 *
 * @author EricTowns
 * @date 2023/5/5 14:50
 */
public class Line extends Edge {

    public Line(String from, String to) {
        super(from, to);
    }

    public static Line build(String from, String to) {
        return new Line(from, to);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
