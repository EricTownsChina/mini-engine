package priv.eric.web.controller.debug;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import priv.eric.application.tasks.BaseTask;
import priv.eric.application.tasks.TasksManager;
import priv.eric.application.tasks.repo.InputTask;
import priv.eric.application.tasks.repo.PrintTask;
import priv.eric.domain.dag.Dag;
import priv.eric.domain.dag.Line;
import priv.eric.domain.dag.Node;
import priv.eric.domain.flow.Context;
import priv.eric.domain.flow.Pipeline;
import priv.eric.domain.task.Task;
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
        Task inputTask = TasksManager.getTask(BaseTask.Type.INPUT);
        Task printTask = TasksManager.getTask(BaseTask.Type.PRINT);
        Task outputTask = TasksManager.getTask(BaseTask.Type.OUTPUT);
        Task httpRequestTask = TasksManager.getTask(BaseTask.Type.HTTP_REQUEST);

        Node inputNode = Node.build("input").setTask(inputTask);
        Node printNode = Node.build("print0").setTask(printTask);
        Node printNode1 = Node.build("print1").setTask(printTask);
        Node printNode2 = Node.build("print2").setTask(printTask);
        Node printNode3 = Node.build("print3").setTask(printTask);
        Node httpRequestNode = Node.build("httpRequest0").setTask(httpRequestTask);
        Node outputNode = Node.build("output").setTask(outputTask);

        Dag dag = new Dag()
                .addNode(Node.build("input").setTask(inputTask))
                .addNode(Node.build("print0").setTask(printTask))
                .addNode(Node.build("print1").setTask(printTask))
                .addNode(Node.build("print2").setTask(printTask))
                .addNode(Node.build("print3").setTask(printTask))
                .addNode(Node.build("httpRequest0").setTask(httpRequestTask))
                .addNode(Node.build("output").setTask(outputTask))

                .addEdge(Line.build(inputNode, printNode))
                .addEdge(Line.build(printNode, printNode1))
                .addEdge(Line.build(printNode1, printNode2))
                .addEdge(Line.build(printNode1, printNode3))
                .addEdge(Line.build(printNode2, printNode3))
                .addEdge(Line.build(printNode3, httpRequestNode))
                .addEdge(Line.build(printNode3, outputNode));

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
