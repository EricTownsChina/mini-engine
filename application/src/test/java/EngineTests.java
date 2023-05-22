
import org.junit.jupiter.api.BeforeEach;
import priv.eric.application.tasks.TasksManager;
import priv.eric.domain.task.BaseTask;
import priv.eric.infrastructure.common.kit.Storage;
import priv.eric.domain.dag.Dag;
import priv.eric.domain.dag.Line;
import priv.eric.domain.dag.Node;
import priv.eric.domain.flow.Context;
import priv.eric.domain.flow.Pipeline;
import priv.eric.infrastructure.graph.Edge;
import org.junit.jupiter.api.Test;


import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Description: tests
 *
 * @author EricTowns
 * @date 2023/5/5 14:22
 */
public class EngineTests {

    private Map<String, BaseTask> taskMap;

    @BeforeEach
    public void prepareTasks() {
        taskMap = TasksManager.getTaskMap();
    }

    @Test
    public void printTest() {
        Node inputNode = new Node("004");
        inputNode.setTask(taskMap.get("INPUT"));
        Node printNode = new Node("000");
        printNode.setTask(taskMap.get("PRINT"));
        Node printNode1 = new Node("001");
        printNode1.setTask(taskMap.get("PRINT"));
        Node printNode2 = new Node("002");
        printNode2.setTask(taskMap.get("PRINT"));
        Node printNode3 = new Node("003");
        printNode3.setTask(taskMap.get("PRINT"));
        Node outputNode = new Node("-1");
        outputNode.setTask(taskMap.get("OUTPUT"));
        Set<Node> nodes = new HashSet<>(4);
        nodes.add(inputNode);
        nodes.add(printNode);
        nodes.add(printNode1);
        nodes.add(printNode2);
        nodes.add(printNode3);
        nodes.add(outputNode);

        Edge<Node> edge40 = new Line(inputNode, printNode);
        Edge<Node> edge01 = new Line(printNode, printNode1);
        Edge<Node> edge12 = new Line(printNode1, printNode2);
        Edge<Node> edge13 = new Line(printNode1, printNode3);
        Edge<Node> edge23 = new Line(printNode2, printNode3);
        Edge<Node> edge3Out = new Line(printNode3, outputNode);
        Set<Edge<Node>> edges = new HashSet<>(4);
        edges.add(edge40);
        edges.add(edge01);
        edges.add(edge12);
        edges.add(edge13);
        edges.add(edge23);
        edges.add(edge3Out);

        Dag dag = new Dag(nodes, edges);

        Pipeline pipeline = new Pipeline(dag, inputNode);
        Context context = new Context(pipeline);
        context.storeValueToNode(inputNode, "scene", "wap");
        context.storeValueToNode(inputNode, "userId", "622594518");

        context.putPropToNode(printNode1, "scene", "$004.scene");

        context.putPropToNode(outputNode, "scene", "$004.scene");
        context.putPropToNode(outputNode, "userId", "$004.userId");

        pipeline.run(context);

        Object result = context.getValueFromGlobal(Context.OUTPUT);
        System.out.println("out : " + Storage.defaultGson().toJson(result));

        System.out.println("---------------");
    }

}
