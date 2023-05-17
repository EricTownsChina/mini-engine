package priv.eric.domain.task;

import priv.eric.domain.dag.Node;
import priv.eric.domain.flow.Context;

/**
 * Description: task in node
 *
 * @author EricTowns
 * @date 2023/5/17 13:41
 */
public interface Task {

    void setContext(Context context);

    void setNode(Node node);

    void run();

}
