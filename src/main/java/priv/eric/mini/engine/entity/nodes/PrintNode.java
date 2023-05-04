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

    public PrintNode(String name, String desc) {
        super(name, desc, Type.PRINT);
    }

    @Override
    public void execute(Pipeline pipeline) {
        Context context = pipeline.getContext();
    }

    @Override
    public void record(Pipeline pipeline) {

    }
}