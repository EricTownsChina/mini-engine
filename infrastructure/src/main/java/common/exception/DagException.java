package common.exception;

/**
 * Description: exception about dag operation
 *
 * @author EricTowns
 * @date 2023/5/4 21:11
 */
public class DagException extends BaseException {

    public DagException(ExceptionType exceptionType) {
        super(exceptionType);
    }

    public DagException(ExceptionType exceptionType, String message) {
        super(exceptionType, message);
    }

}
