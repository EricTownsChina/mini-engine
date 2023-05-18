package priv.eric.web.entity;

/**
 * Description: web return entity
 *
 * @author EricTowns
 * @date 2023/5/18 21:22
 */
public class Resp {

    private Integer code;
    private String msg;
    private Object value;
    private Long cost;

    public Resp() {
    }

    public Resp(Builder builder) {
        this.code = builder.code;
        this.msg = builder.msg;
        this.value = builder.value;
        this.cost = builder.cost;
    }

    public static Builder n() {
        return new Builder();
    }

    public static Resp success() {
        return n().setCode(0).setMsg("success").build();
    }

    public static Resp success(Object value) {
        return Resp.n().setCode(0).setMsg("success").setValue(value).build();
    }

    public static Resp fail() {
        return Resp.n().setCode(-1).setMsg("fail").build();
    }

    public static Resp fail(String msg) {
        return Resp.n().setMsg(msg).build();
    }

    public static Resp fail(Integer code, String msg) {
        return Resp.n().setCode(code).setMsg(msg).build();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public static class Builder {
        private Integer code;
        private String msg;
        private Object value;
        private Long cost;

        public Builder setCode(Integer code) {
            this.code = code;
            return this;
        }

        public Builder setMsg(String msg) {
            this.msg = msg;
            return this;
        }

        public Builder setValue(Object value) {
            this.value = value;
            return this;
        }

        public Builder setCost(Long cost) {
            this.cost = cost;
            return this;
        }

        public Resp build() {
            return new Resp(this);
        }
    }

}
