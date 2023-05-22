package priv.eric.application.tasks;

import priv.eric.domain.task.BaseTask;
import priv.eric.domain.task.NoComponentException;
import priv.eric.domain.task.Task;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * Description: todo
 *
 * @author EricTowns
 * @date 2023/5/17 16:12
 */
public class TasksManager<T> {

    private static final Map<String, BaseTask> TASK_MAP = new HashMap<>();

    private static final Map<String, Object> COMPONENTS = new HashMap<>();

    static {
        ServiceLoader<BaseTask> serviceLoader = ServiceLoader.load(BaseTask.class);
        for (BaseTask task : serviceLoader) {
            try {
                task.components();
                for (Map.Entry<String, Object> entry : task.getComponents().entrySet()) {
                    String taskComponentKey = task.getType().name() + "." + entry.getKey();
                    COMPONENTS.put(taskComponentKey, entry.getValue());
                }
            } catch (RuntimeException re) {
                throw new LoadTaskComponentsException(task.getType().name(), re);
            }
            TASK_MAP.put(task.getType().name(), task);
        }
    }

    public static BaseTask getTask(String type) {
        return TASK_MAP.get(type);
    }

    public static Task getTask(BaseTask.Type type) {
        return TASK_MAP.get(type.name());
    }

    public static Map<String, BaseTask> getTaskMap() {
        return new HashMap<>(TASK_MAP);
    }

    public static <T> T getComponent(String taskType, String componentKey) {
        String taskComponentKey = taskType + "." + componentKey;
        if (COMPONENTS.containsKey(taskComponentKey)) {
            return (T) COMPONENTS.get(taskComponentKey);
        } else {
            throw new NoComponentException(componentKey);
        }
    }

}
