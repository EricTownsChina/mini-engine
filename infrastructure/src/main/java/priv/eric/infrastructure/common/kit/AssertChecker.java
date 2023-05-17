package priv.eric.infrastructure.common.kit;

import priv.eric.infrastructure.common.exception.CheckException;
import priv.eric.infrastructure.common.exception.ExceptionType;

/**
 * Description: assert util throws {@link CheckException}
 *
 * @author EricTowns
 * @date 2023/5/8 11:11
 */
public final class AssertChecker {

    public static void notEmpty(String msg) {
        if (msg == null || msg.isEmpty()) {
            throw new CheckException(ExceptionType.PARAM_IS_EMPTY);
        }
    }

    public static void check(boolean condition, ExceptionType exceptionType) {
        if (!condition) {
            throw new CheckException(exceptionType);
        }
    }

    public static void check(boolean condition, ExceptionType exceptionType, String errorMessage) {
        if (!condition) {
            throw new CheckException(exceptionType, errorMessage);
        }
    }

}
