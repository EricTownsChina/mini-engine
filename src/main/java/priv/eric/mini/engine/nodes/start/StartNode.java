package priv.eric.mini.engine.nodes.start;

import priv.eric.mini.engine.entity.dag.Node;
import priv.eric.mini.engine.entity.flow.Context;

import java.util.Enumeration;
import java.util.Properties;
import java.util.Set;

/**
 * Description: TODO
 *
 * @author EricTowns
 * @date 2023/5/6 00:34
 */
public class StartNode extends Node {

    public StartNode(String id, String name, String desc) {
        super(id, name, desc);
    }

    @Override
    public Type type() {
        return Type.START;
    }



    @Override
    public void addComponents() {

    }

    @Override
    public void process(Context context) {
        System.out.println(getId() + " : " + getName() + " : " + getDesc());
        Properties properties = getSelfProp(context);
        Set<String> propKeys = properties.stringPropertyNames();
        for (String propKey : propKeys) {
            String propValue = properties.getProperty(propKey);
            valueToSelfStorage(context, propKey, propValue);
        }
    }

    @Override
    public void record(Context context) {

    }
}
