package priv.eric.nodes.zwitch;

import java.util.List;

/**
 * Description: Switch Node's Condition
 *
 * @author EricTowns
 * @date 2023/5/8 16:15
 */
public class Condition {

    /**
     * default relationship is and.
     */
    private Relationship relationship = Relationship.AND;

    private List<Comparison> comparisonList;

    public Relationship getRelationship() {
        return relationship;
    }

    public Condition setRelationship(Relationship relationship) {
        this.relationship = relationship;
        return this;
    }

    public List<Comparison> getComparisonList() {
        return comparisonList;
    }

    public Condition setComparisonList(List<Comparison> comparisonList) {
        this.comparisonList = comparisonList;
        return this;
    }

    private enum Relationship {
        AND,
        OR
    }

}
