package priv.eric.nodes.zwitch;

/**
 * Description: Switch Node's comparison
 *
 * @author EricTowns
 * @date 2023/5/8 15:59
 */
public class Comparison {

    private String activeExpress;

    private ComparisonOperator comparisonOperator;

    private String passiveExpress;

    public String getActiveExpress() {
        return activeExpress;
    }

    public Comparison setActiveExpress(String activeExpress) {
        this.activeExpress = activeExpress;
        return this;
    }

    public ComparisonOperator getComparisonOperator() {
        return comparisonOperator;
    }

    public Comparison setComparisonOperator(ComparisonOperator comparisonOperator) {
        this.comparisonOperator = comparisonOperator;
        return this;
    }

    public String getPassiveExpress() {
        return passiveExpress;
    }

    public Comparison setPassiveExpress(String passiveExpress) {
        this.passiveExpress = passiveExpress;
        return this;
    }

    private enum ComparisonOperator {
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
}
