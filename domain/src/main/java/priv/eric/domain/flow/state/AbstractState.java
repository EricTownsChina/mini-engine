package priv.eric.domain.flow.state;

import priv.eric.domain.flow.Context;

/**
 * desc:
 *
 * @author EricTowns
 * @date 2023-05-27 23:22
 */
public abstract class AbstractState {

    protected Context context;

    public AbstractState(Context context) {
        this.context = context;
    }

    /**
     * do something before node run
     */
    abstract void preRunEvent();

    /**
     * run node task
     */
    abstract void runEvent();

    /**
     * do something after node run
     */
    abstract void postRunEvent();


}
