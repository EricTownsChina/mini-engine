package priv.eric.web.controller.debug;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import priv.eric.domain.task.BaseTask;
import priv.eric.application.tasks.TasksManager;
import priv.eric.domain.dag.Dag;
import priv.eric.domain.dag.Line;
import priv.eric.domain.dag.Node;
import priv.eric.domain.flow.Context;
import priv.eric.domain.flow.Pipeline;
import priv.eric.domain.task.Task;
import priv.eric.infrastructure.common.kit.Storage;
import priv.eric.web.entity.Resp;

import java.util.stream.Collectors;

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

        Dag dag = Dag.n()
                .addNode(inputNode)
                .addNode(printNode)
                .addNode(printNode1)
                .addNode(printNode2)
                .addNode(printNode3)
                .addNode(httpRequestNode)
                .addNode(outputNode)

                .addEdge(Line.build("input", "print0"))
                .addEdge(Line.build("print0", "print1"))
                .addEdge(Line.build("print1", "print2"))
                .addEdge(Line.build("print1", "print3"))
                .addEdge(Line.build("print2", "print3"))
                .addEdge(Line.build("print3", "httpRequest0"))
                .addEdge(Line.build("httpRequest0", "output"))

                .build();

        Pipeline pipeline = new Pipeline(dag, "input");
        Context context = new Context(pipeline);
        context.storeValueToNode(inputNode, "scene", "wap");
        context.storeValueToNode(inputNode, "userId", "622594518");

        context.putPropToNode(printNode1, "scene", "$input.scene");

        context.putPropToNode(outputNode, "scene", "$input.scene");
        context.putPropToNode(outputNode, "userId", "$input.userId");

        pipeline.run(context);

        Object result = context.getValueFromGlobal(Context.OUTPUT);
        System.out.println();
        System.out.println("out : " + Storage.defaultGson().toJson(result));

        System.out.println("---------------");
    }


}
