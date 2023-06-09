package priv.eric.application.tasks.repo;

import priv.eric.application.components.HttpService;
import priv.eric.application.components.SpringBeanLoader;
import priv.eric.domain.task.BaseTask;

/**
 * Description: todo
 *
 * @author EricTowns
 * @date 2023/5/17 20:41
 */
public class HttpRequestTask extends BaseTask {

    @Override
    protected Type type() {
        return Type.HTTP_REQUEST;
    }

    @Override
    public void components() {
        putComponent("httpService", SpringBeanLoader.getBean(HttpService.class));
    }

    @Override
    protected void process() {
        HttpService httpService = getComponent("httpService");
        httpService.get();
    }

    @Override
    protected void record() {

    }
}
