package priv.eric.mini.engine.entity.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Description: 顶点
 *
 * @author EricTowns
 * @date 2023/4/21 23:49
 */
public abstract class Vertex {

    private String id;


    public Vertex() {
    }

    public Vertex(String id, String name, String desc) {
        if (null == id || id.isEmpty()) {
            id = UUID.randomUUID().toString();
        }
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * 顶点是否有效
     *
     * @return boolean
     */
    public abstract boolean isValid();

}
