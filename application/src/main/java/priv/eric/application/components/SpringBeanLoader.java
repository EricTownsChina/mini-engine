package priv.eric.application.components;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Description: get spring bean by class or name and class.
 *
 * @author EricTowns
 * @date 2023/5/12 15:27
 */
@Component
public class SpringBeanLoader implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    private static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringBeanLoader.applicationContext == null) {
            SpringBeanLoader.applicationContext = applicationContext;
        }
    }

    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

}
