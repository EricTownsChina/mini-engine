package priv.eric.mini.engine.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 节点
 *
 * @author EricTowns
 * @date 2023/4/21 23:49
 */
public abstract class Vertex {

    private String id;

    private String name;

    private String desc;

    private Integer state;

    private List<Vertex> pre = new ArrayList<>(0);

    private List<Vertex> post = new ArrayList<>(0);

    public Vertex() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public List<Vertex> getPre() {
        return this.pre;
    }

    public List<Vertex> getPost() {
        return this.post;
    }

    public void addPre(Vertex vertex) {
        this.pre.add(vertex);
    }

    public void addPre(List<Vertex> vertexList) {
        this.pre.addAll(vertexList);
    }

    public void addPost(Vertex vertex) {
        this.post.add(vertex);
    }

    public void addPost(List<Vertex> vertexList) {
        this.post.addAll(vertexList);
    }

    public void setPre(List<Vertex> pre) {
        this.pre = pre;
    }

    public void setPost(List<Vertex> post) {
        this.post = post;
    }

    /**
     * 该节点已准备
     * @return boolean
     */
    public abstract boolean isReady();

    /**
     * 该节点是有效的
     * @return boolean
     */
    public abstract boolean isValid();

    /**
     * 该节点已经结束
     * @return boolean
     */
    public abstract boolean isOver();

}
