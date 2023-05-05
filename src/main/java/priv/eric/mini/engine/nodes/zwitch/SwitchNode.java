package priv.eric.mini.engine.nodes.zwitch;

import priv.eric.mini.engine.entity.dag.Node;
import priv.eric.mini.engine.entity.flow.Context;

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

    }

    @Override
    public void record(Context context) {

    }

}
