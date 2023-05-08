package priv.eric.mini.engine.entity.exception;

/**
 * Description: todo
 *
 * @author EricTowns
 * @date 2023/5/8 11:15
 */
public class CheckException extends BaseException {

    public CheckException(ExceptionType exceptionType) {
        super(exceptionType);
    }

    public CheckException(ExceptionType exceptionType, String message) {
        super(exceptionType, message);
    }

}
