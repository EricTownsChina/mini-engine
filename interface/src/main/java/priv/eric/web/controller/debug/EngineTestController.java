package priv.eric.web.controller.debug;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import priv.eric.application.tasks.BaseTask;
import priv.eric.application.tasks.TasksManager;
import priv.eric.application.tasks.repo.InputTask;
import priv.eric.domain.dag.Dag;
import priv.eric.domain.dag.Line;
import priv.eric.domain.dag.Node;
import priv.eric.domain.flow.Context;
import priv.eric.domain.flow.Pipeline;
import priv.eric.infrastructure.common.kit.Storage;
import priv.eric.infrastructure.graph.Edge;
import priv.eric.web.entity.Resp;

import java.util.HashSet;
import java.util.Set;

/**
 * Description: todo
 *
 * @author EricTowns
 * @date 2023/5/18 21:21
 */
@RequestMapping("debug")
@RestController
public class EngineTestController {

    @GetMapping("run")
    public Resp run() {
        test();
        return Resp.success();
    }

    private void test() {
        BaseTask inputTask = TasksManager.getTask("INPUT");
        BaseTask printTask = TasksManager.getTask("PRINT");
        BaseTask outputTask = TasksManager.getTask("OUTPUT");
        BaseTask httpRequestTask = TasksManager.getTask("HTTP_REQUEST");
        Node inputNode = new Node("004", inputTask);
        Node printNode = new Node("000", printTask);
        Node printNode1 = new Node("001", printTask);
        Node printNode2 = new Node("002", printTask);
        Node printNode3 = new Node("003", printTask);
        Node httpRequestNode = new Node("005", httpRequestTask);
        Node outputNode = new Node("-1", outputTask);
        Set<Node> nodes = new HashSet<>(4);
        nodes.add(inputNode);
        nodes.add(printNode);
        nodes.add(printNode1);
        nodes.add(printNode2);
        nodes.add(printNode3);
        nodes.add(httpRequestNode);
        nodes.add(outputNode);

        Edge<Node> edge40 = new Line(inputNode, printNode);
        Edge<Node> edge01 = new Line(printNode, printNode1);
        Edge<Node> edge12 = new Line(printNode1, printNode2);
        Edge<Node> edge13 = new Line(printNode1, printNode3);
        Edge<Node> edge23 = new Line(printNode2, printNode3);
        Edge<Node> edge35 = new Line(printNode3, httpRequestNode);
        Edge<Node> edge3Out = new Line(printNode3, outputNode);
        Set<Edge<Node>> edges = new HashSet<>(4);
        edges.add(edge40);
        edges.add(edge01);
        edges.add(edge12);
        edges.add(edge13);
//        edges.add(edge23);
        edges.add(edge35);
//        edges.add(edge3Out);

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
