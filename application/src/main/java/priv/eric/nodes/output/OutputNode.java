package priv.eric.nodes.output;

import dag.Node;
import flow.Context;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Description: output node
 *
 * @author EricTowns
 * @date 2023/5/12 14:48
 */
public class OutputNode extends Node {

    public OutputNode(String id, String name, String desc) {
        super(id, name, desc);
    }

    @Override
    public Type type() {
        return Type.OUTPUT;
    }

    @Override
    public void addComponents() {
    }

    @Override
    public void process(Context context) {
        Properties properties = selfProp(context);
        Map<String, Object> outputMap = new HashMap<>(properties.size());
        for (String key : properties.stringPropertyNames()) {
            String propValue = properties.getProperty(key);
            Object value = context.getValueByExpress(propValue);
            outputMap.put(key, value);
        }
        valueToGlobalStorage(context, Context.OUTPUT, outputMap);
    }

    @Override
    public void record(Context context) {
    }
}
