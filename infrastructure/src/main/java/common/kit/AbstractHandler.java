package common.kit;

/**
 * Description: base handler
 *
 * @author EricTowns
 * @date 2023/5/12 13:46
 */
public abstract class AbstractHandler<T> {

    protected abstract boolean validate(T data);

    protected abstract void pre();

    protected abstract T process(T data);

    protected abstract void post();

    public T handle(T data) {
        if (validate(data)) {
            pre();
            T result = process(data);
            post();
            return result;
        }
        return data;
    }

}
