package priv.eric.mini.engine.entity.exception;

/**
 * Description: Thrown when an application tries to create an instance of a class,
 * but the specified class object cannot be instantiated.
 *
 * @author EricTowns
 * @date 2023/5/4 10:24
 */
public class InstantiationException extends BaseException {

    public InstantiationException(ExceptionType exceptionType) {
        super(exceptionType);
    }

    public InstantiationException(ExceptionType exceptionType, String message) {
        super(exceptionType, message);
    }
}
