package priv.eric.nodes.zwitch;

import dag.Node;
import flow.Context;

import java.util.Properties;

/**
 * Description: TODO
 *
 * @author EricTowns
 * @date 2023/5/6 00:42
 */
public class SwitchNode extends Node {

    public SwitchNode(String id, String name, String desc) {
        super(id, name, desc);
    }

    @Override
    public Type type() {
        return Type.SWITCH;
    }

    @Override
    public void addComponents() {

    }

    @Override
    public void process(Context context) {
        Properties properties = getSelfProp(context);
    }

    @Override
    public Node nextNode(Context context) {
        return null;
    }

    @Override
    public void record(Context context) {

    }

}
