package priv.eric.application.tasks.repo;

import priv.eric.domain.flow.Context;
import priv.eric.domain.task.BaseTask;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Description: todo
 *
 * @author EricTowns
 * @date 2023/5/17 14:53
 */
public class InputTask extends BaseTask {

    @Override
    protected Type type() {
        return Type.INPUT;
    }

    @Override
    protected void components() {

    }

    @Override
    public void process() {
        Properties properties = getNodeProp();
        Map<String, Object> inputMap = new HashMap<>(properties.size());
        for (String key : properties.stringPropertyNames()) {
            String propValue = properties.getProperty(key);
            Object value = getValueByExpress(propValue);
            storeToNode(key, value);
            inputMap.put(key, value);
        }
        storeToGlobal(Context.INPUT, inputMap);
    }

    @Override
    public void record() {

    }
}
