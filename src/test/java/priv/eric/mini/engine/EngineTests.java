package priv.eric.mini.engine;

import org.junit.jupiter.api.Test;
import priv.eric.mini.engine.entity.dag.Dag;
import priv.eric.mini.engine.entity.dag.Line;
import priv.eric.mini.engine.entity.dag.Node;
import priv.eric.mini.engine.entity.flow.Context;
import priv.eric.mini.engine.entity.flow.Pipeline;
import priv.eric.mini.engine.entity.graph.Edge;
import priv.eric.mini.engine.entity.nodes.PrintNode;

import java.util.HashSet;
import java.util.Set;

/**
 * Description: tests
 *
 * @author EricTowns
 * @date 2023/5/5 14:22
 */
public class EngineTests {

    @Test
    public void printTest() {
        PrintNode printNode = new PrintNode("000", "PrintNode", "print node's name and desc.");
        PrintNode printNode1 = new PrintNode("001", "PrintNode1", "print node's name and desc.");
        PrintNode printNode2 = new PrintNode("002", "PrintNode2", "print node's name and desc.");
        PrintNode printNode3 = new PrintNode("003", "PrintNode3", "print node's name and desc.");
        Set<Node> nodes = new HashSet<>(4);
        nodes.add(printNode);
        nodes.add(printNode1);
        nodes.add(printNode2);
        nodes.add(printNode3);

        Edge<Node> edge01 = new Line(printNode, printNode1);
        Edge<Node> edge12 = new Line(printNode1, printNode2);
        Edge<Node> edge13 = new Line(printNode1, printNode3);
        Edge<Node> edge23 = new Line(printNode2, printNode3);
        Set<Edge<Node>> edges = new HashSet<>(4);
        edges.add(edge01);
        edges.add(edge12);
        edges.add(edge13);
        edges.add(edge23);

        Dag dag = new Dag(nodes, edges);

        Pipeline pipeline = new Pipeline(dag, printNode);
        Context context = new Context(pipeline);
        pipeline.setContext(context);

        pipeline.run();

        System.out.println("pipeline is end.");
    }

}
