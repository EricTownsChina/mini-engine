
import dag.Dag;
import dag.Line;
import dag.Node;
import flow.Context;
import flow.Pipeline;
import graph.Edge;
import priv.eric.nodes.print.PrintNode;
import priv.eric.nodes.start.StartNode;
import org.junit.jupiter.api.Test;


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
        StartNode startNode  = new StartNode("004", "StartNode", "request in.");
        PrintNode printNode = new PrintNode("000", "PrintNode", "print node's name and desc.");
        PrintNode printNode1 = new PrintNode("001", "PrintNode1", "print node's name and desc.");
        PrintNode printNode2 = new PrintNode("002", "PrintNode2", "print node's name and desc.");
        PrintNode printNode3 = new PrintNode("003", "PrintNode3", "print node's name and desc.");
        Set<Node> nodes = new HashSet<>(4);
        nodes.add(startNode);
        nodes.add(printNode);
        nodes.add(printNode1);
        nodes.add(printNode2);
        nodes.add(printNode3);

        Edge<Node> edge40 = new Line(startNode, printNode);
        Edge<Node> edge01 = new Line(printNode, printNode1);
        Edge<Node> edge12 = new Line(printNode1, printNode2);
        Edge<Node> edge13 = new Line(printNode1, printNode3);
        Edge<Node> edge23 = new Line(printNode2, printNode3);
        Set<Edge<Node>> edges = new HashSet<>(4);
        edges.add(edge40);
        edges.add(edge01);
        edges.add(edge12);
        edges.add(edge13);
        edges.add(edge23);

        Dag dag = new Dag(nodes, edges);

        Pipeline pipeline = new Pipeline(dag, startNode);
        Context context = new Context(pipeline);
        context.valueToNode(startNode, "scene", "wap");
        context.valueToNode(startNode, "userId", "622594518");

        context.propToNode(printNode1, "scene", "$004.scene");

        pipeline.run(context);

        System.out.println("pipeline is end.");
    }

}