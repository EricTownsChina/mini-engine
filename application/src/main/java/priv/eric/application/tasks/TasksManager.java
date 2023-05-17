package priv.eric.application.tasks;

import priv.eric.domain.task.BaseTask;
import priv.eric.domain.task.Task;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * Description: todo
 *
 * @author EricTowns
 * @date 2023/5/17 16:12
 */
public class TasksManager {

    private static final Map<String, BaseTask> TASK_MAP = new HashMap<>();

    static {
        ServiceLoader<BaseTask> serviceLoader = ServiceLoader.load(BaseTask.class);
        Iterator<BaseTask> iterator = serviceLoader.iterator();
        for (BaseTask task : serviceLoader) {
            TASK_MAP.put(task.getType().name(), task);
        }
    }

    public static BaseTask getTask(String type) {
        return TASK_MAP.get(type);
    }

    public static Map<String, BaseTask> getTaskMap() {
        return new HashMap<>(TASK_MAP);
    }

}
