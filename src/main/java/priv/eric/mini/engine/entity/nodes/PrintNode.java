package priv.eric.mini.engine.entity.nodes;

import priv.eric.mini.engine.entity.dag.Node;
import priv.eric.mini.engine.entity.flow.Context;
import priv.eric.mini.engine.entity.flow.Pipeline;

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
        System.out.println(this.getId() + " : " + this.getName() + " : " + this.getDesc());
    }

    @Override
    public void record(Context context) {

    }

}
