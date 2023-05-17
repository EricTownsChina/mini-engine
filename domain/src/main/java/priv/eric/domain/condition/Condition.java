package priv.eric.domain.condition;

import java.util.List;

/**
 * Description: Switch Node's Condition
 *
 * @author EricTowns
 * @date 2023/5/8 16:15
 */
public class Condition {

    private Relationship subRelationship;

    private List<Condition> subConditions;

    private String var0Express;

    private Comparison comparison;

    private String[] var1Express;


    public String getVar0Express() {
        return var0Express;
    }

    public void setVar0Express(String var0Express) {
        this.var0Express = var0Express;
    }

    public String[] getVar1Express() {
        return var1Express;
    }

    public void setVar1Express(String[] var1Express) {
        this.var1Express = var1Express;
    }

    public Relationship getSubRelationship() {
        return subRelationship;
    }

    public Condition setSubRelationship(Relationship subRelationship) {
        this.subRelationship = subRelationship;
        return this;
    }

    public List<Condition> getSubConditions() {
        return subConditions;
    }

    public Condition setSubConditions(List<Condition> subConditions) {
        this.subConditions = subConditions;
        return this;
    }

    public Comparison getComparison() {
        return comparison;
    }

    public Condition setComparison(Comparison comparison) {
        this.comparison = comparison;
        return this;
    }

    private enum Comparison {
        EQUALS,
        GT,
        GTE,
        LT,
        LTE,
        IS_TRUE,
        IS_FALSE,
        IN,
        CONTAINS,
        EMPTY,
        NOT_EMPTY
    }

    private enum Relationship {
        AND,
        OR
    }

}
