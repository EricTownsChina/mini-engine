package priv.eric.nodes.input;


import dag.Node;
import flow.Context;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Description: input node
 *
 * @author EricTowns
 * @date 2023/5/6 00:34
 */
public class InputNode extends Node {

    public InputNode(String id, String name, String desc) {
        super(id, name, desc);
    }

    @Override
    public Type type() {
        return Type.INPUT;
    }

    @Override
    public void addComponents() {
    }

    @Override
    public void process(Context context) {
        Properties properties = selfProp(context);
        Map<String, Object> inputMap = new HashMap<>(properties.size());
        for (String key : properties.stringPropertyNames()) {
            String propValue = properties.getProperty(key);
            Object value = context.getValueByExpress(propValue);
            valueToSelfStorage(context, key, value);
            inputMap.put(key, value);
        }
        valueToGlobalStorage(context, Context.INPUT, inputMap);
    }

    @Override
    public void record(Context context) {
    }

}
