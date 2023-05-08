package priv.eric.mini.engine.nodes.print;

import priv.eric.mini.engine.entity.dag.Node;
import priv.eric.mini.engine.entity.flow.Context;
import priv.eric.mini.engine.entity.flow.Pipeline;

import java.util.Properties;

/**
 * Description: print node
 *
 * @author EricTowns
 * @date 2023/5/5 00:16
 */
public class PrintNode extends Node {

    public PrintNode(String id, String name, String desc) {
        super(id, name, desc);
    }

    @Override
    public Type type() {
        return Type.PRINT;
    }

    @Override
    public void addComponents() {

    }

    @Override
    public void process(Context context) {
        Properties properties = getSelfProp(context);
        System.out.println("-------" + this.getId() + " : " + this.getName() + "-------");
        for (String propKey : properties.stringPropertyNames()) {
            Object valueByExpress = context.getValueByExpress(properties.getProperty(propKey));
            System.out.println(propKey + " : " + valueByExpress);
        }
    }

    @Override
    public void record(Context context) {

    }

}
