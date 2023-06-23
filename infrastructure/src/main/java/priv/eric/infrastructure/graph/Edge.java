package priv.eric.infrastructure.graph;

import java.util.Objects;

/**
 * Description: edge
 *
 * @author EricTowns
 * @date 2023/4/22 00:32
 */
public abstract class Edge {

    private String from;

    private String to;

    private Number weight;

    public Edge() {
    }

    public Edge(String from, String to) {
        if (null == from || null == to || from.isBlank() || to.isBlank()) {
            throw new IllegalArgumentException("'from' or 'to' should not be blank.");
        }
        this.from = from;
        this.to = to;
    }

    public Edge(String from, String to, Number weight) {
        if (null == from || null == to || from.isBlank() || to.isBlank()) {
            throw new IllegalArgumentException("'from' or 'to' should not be blank.");
        }
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public String getFrom() {
        return from;
    }

    public Edge setFrom(String from) {
        this.from = from;
        return this;
    }

    public String getTo() {
        return to;
    }

    public Edge setTo(String to) {
        this.to = to;
        return this;
    }

    public Number getWeight() {
        return weight;
    }

    public Edge setWeight(Number weight) {
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
        Edge edge = (Edge) o;
        return Objects.equals(from, edge.from) && Objects.equals(to, edge.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }
}
