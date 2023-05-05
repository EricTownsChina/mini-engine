package priv.eric.mini.engine.nodes.start;

import priv.eric.mini.engine.entity.dag.Node;
import priv.eric.mini.engine.entity.flow.Context;

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

    }

    @Override
    public void record(Context context) {

    }
}
