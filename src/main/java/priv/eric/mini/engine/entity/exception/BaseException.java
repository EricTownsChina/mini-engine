package priv.eric.mini.engine.entity.exception;

/**
 * Description: base exception in this engine.
 *
 * @author EricTowns
 * @date 2023/5/4 10:32
 */
public class BaseException extends RuntimeException {

    private final Integer code;

    public BaseException(ExceptionType exceptionType) {
        super(exceptionType.getDesc());
        this.code = exceptionType.getCode();
    }

    public BaseException(ExceptionType exceptionType, String message) {
        super(message);
        this.code = exceptionType.getCode();
    }

    public Integer getCode() {
        return code;
    }

}
