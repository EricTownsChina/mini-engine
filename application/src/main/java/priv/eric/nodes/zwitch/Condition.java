package priv.eric.nodes.zwitch;

import java.util.List;

/**
 * Description: Switch Node's Condition
 *
 * @author EricTowns
 * @date 2023/5/8 16:15
 */
public class Condition {

    private Relationship relationship;

    private List<Comparison> comparisonList;

    private String nextNodeId;

    public Relationship getRelationship() {
        return relationship;
    }

    public void setRelationship(Relationship relationship) {
        this.relationship = relationship;
    }

    public List<Comparison> getComparisonList() {
        return comparisonList;
    }

    public void setComparisonList(List<Comparison> comparisonList) {
        this.comparisonList = comparisonList;
    }

    public String getNextNodeId() {
        return nextNodeId;
    }

    public void setNextNodeId(String nextNodeId) {
        this.nextNodeId = nextNodeId;
    }

    private enum Relationship {
        AND,
        OR
    }

}
