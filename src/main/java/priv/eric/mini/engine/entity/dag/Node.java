package priv.eric.mini.engine.entity.dag;

import priv.eric.mini.engine.entity.graph.Vertex;

/**
 * Description: Dag node
 *
 * @author EricTowns
 * @date 2023/4/28 16:02
 */
public abstract class Node extends Vertex {

    private String name;

    private String desc;

    private Type type;

    public Node(String name, String desc, Type type) {
        this.name = name;
        this.desc = desc;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Type getType() {
        return type;
    }

    public Node setType(Type type) {
        this.type = type;
        return this;
    }

    @Override
    public boolean isValid() {
        return true;
    }

    public enum Type {
        BLANK,
        PRINT,
        SWITCH,
        CACHE,
        FILTER,
        MAP,
    }
}
