package priv.eric.application.tasks;

/**
 * Description: todo
 *
 * @author EricTowns
 * @date 2023/5/19 16:18
 */
public class NoComponentException extends RuntimeException {

    public NoComponentException(String msg) {
        super("there is no component : " + msg);
    }

}
