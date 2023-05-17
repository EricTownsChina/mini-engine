package priv.eric.infrastructure.common.exception;

/**
 * Description: exception type enum
 *
 * @author EricTowns
 * @date 2023/5/4 10:33
 */
public enum ExceptionType {

    FAILED_CHECK(0, 1000, "failed checked"),
    PARAM_IS_EMPTY(0, 1001, "param can not be empty."),
    EXPRESS_NOT_START_WITH_SYMBOL(1, 1002, "fetch context value should use express start with $"),

    GRAPH_NOT_DAG(1, 2001, "graph is not a dag."),

    DAG_TRAVERSE_NO_ROOT(1, 3001, "there is no root node when traverse dag.");


    /**
     * 0: system exception
     * 1: business exception
     */
    private final Integer type;
    /**
     * exception code
     */
    private final Integer code;
    /**
     * exception description
     */
    private final String desc;

    ExceptionType(Integer type, Integer code, String desc) {
        this.type = type;
        this.code = code;
        this.desc = desc;
    }

    public Integer getType() {
        return type;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
