package priv.eric.nodes.zwitch;

/**
 * Description: Switch Node's comparison
 *
 * @author EricTowns
 * @date 2023/5/8 15:59
 */
public class Comparsion {

    private String express0;

    private ComparisonOperator comparisonOperator;

    private String express1;

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
