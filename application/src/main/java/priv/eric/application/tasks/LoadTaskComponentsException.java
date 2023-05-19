package priv.eric.application.tasks;

/**
 * Description: todo
 *
 * @author EricTowns
 * @date 2023/5/19 15:26
 */
public class LoadTaskComponentsException extends RuntimeException {

    public LoadTaskComponentsException(String task, Throwable throwable) {
        super("Task : " + task + " components load fail.", throwable);
    }

}
