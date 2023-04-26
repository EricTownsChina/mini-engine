package priv.eric.mini.engine.entity.dag;

import priv.eric.mini.engine.entity.Vertex;

import java.util.UUID;

/**
 * Description: 任务节点
 *
 * @author EricTowns
 * @date 2023/4/22 17:34
 */
public class TaskNode extends Vertex {

    public static enum TaskNodeState {
        /**
         * 创建
         */
        BUILD(10),
        /**
         * 准备
         */
        READY(20),
        /**
         * 运行中
         */
        RUNNING(30),
        /**
         * 执行完成
         */
        EXECUTE(40);

        private final Integer code;

        TaskNodeState(Integer code) {
            this.code = code;
        }

        public Integer getCode() {
            return this.code;
        }
    }

    public TaskNode(String name, String desc) {
         this.setId(UUID.randomUUID().toString());
         this.setState(TaskNodeState.BUILD.getCode());
         this.setName(name);
         this.setDesc(desc);
    }

    @Override
    public boolean isReady() {
        return TaskNodeState.READY.getCode().equals(getState());
    }

    @Override
    public boolean isValid() {
        return getId() == null || getState() == null || getState() >= TaskNodeState.EXECUTE.getCode();
    }

    @Override
    public boolean isOver() {
        Integer state = getState();
        return state != null && state >= TaskNodeState.EXECUTE.getCode();
    }
}
