package priv.eric.infrastructure.graph;

import java.util.Objects;

/**
 * Description: 边
 *
 * @author EricTowns
 * @date 2023/4/22 00:32
 */
public abstract class Edge<V extends Vertex> {

    private V from;

    private V to;

    private Number weight;

    public Edge() {
    }

    public Edge(V from, V to) {
        if (null == from || null == to) {
            throw new IllegalArgumentException("missing 'from' or 'to' fields");
        }
        this.from = from;
        this.to = to;
    }

    public Edge(V from, V to, Number weight) {
        if (null == from || null == to) {
            throw new IllegalArgumentException("missing 'from' or 'to' fields");
        }
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public V getFrom() {
        return from;
    }

    public Edge<V> setFrom(V from) {
        this.from = from;
        return this;
    }

    public V getTo() {
        return to;
    }

    public Edge<V> setTo(V to) {
        this.to = to;
        return this;
    }

    public Number getWeight() {
        return weight;
    }

    public Edge<V> setWeight(Number weight) {
        this.weight = weight;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Edge<V> edge = (Edge<V>) o;
        return Objects.equals(from, edge.from) && Objects.equals(to, edge.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }
}
