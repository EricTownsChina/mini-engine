package priv.eric.nodes.zwitch;

/**
 * Description: Switch Node's comparison
 *
 * @author EricTowns
 * @date 2023/5/8 15:59
 */
public class Comparison {

    private String var0Express;

    private ComparisonOperator comparisonOperator;

    private String[] var1Express;

    public String getVar0Express() {
        return var0Express;
    }

    public void setVar0Express(String var0Express) {
        this.var0Express = var0Express;
    }

    public ComparisonOperator getComparisonOperator() {
        return comparisonOperator;
    }

    public void setComparisonOperator(ComparisonOperator comparisonOperator) {
        this.comparisonOperator = comparisonOperator;
    }

    public String[] getVar1Express() {
        return var1Express;
    }

    public void setVar1Express(String[] var1Express) {
        this.var1Express = var1Express;
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
