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
 * @date 2023/5/17 15:47
 */
public class OutputTask extends BaseTask {

    @Override
    protected Type type() {
        return Type.OUTPUT;
    }

    @Override
    protected void process() {
        Properties properties = getNodeProp();
        Map<String, Object> outputMap = new HashMap<>(properties.size());
        for (String key : properties.stringPropertyNames()) {
            String propValue = properties.getProperty(key);
            Object value = getValueByExpress(propValue);
            outputMap.put(key, value);
        }
        storeToGlobal(Context.OUTPUT, outputMap);
    }

    @Override
    protected void record() {

    }
}
