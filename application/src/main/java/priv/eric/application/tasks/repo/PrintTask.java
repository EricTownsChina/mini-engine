package priv.eric.application.tasks.repo;

import priv.eric.application.tasks.BaseTask;

import java.util.Properties;

/**
 * Description: todo
 *
 * @author EricTowns
 * @date 2023/5/17 15:48
 */
public class PrintTask extends BaseTask {

    @Override
    protected Type type() {
        return Type.PRINT;
    }

    @Override
    protected void process() {
        Properties properties = getNodeProp();
        System.out.println("-------" + getNodeId() + "-------");
        for (String propKey : properties.stringPropertyNames()) {
            Object valueByExpress = getValueByExpress(properties.getProperty(propKey));
            System.out.println(propKey + " : " + valueByExpress);
        }
    }

    @Override
    protected void record() {

    }
}
