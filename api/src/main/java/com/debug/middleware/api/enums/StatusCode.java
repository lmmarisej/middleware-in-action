package com.debug.middleware.api.enums;

/**
 * 通用状态码类
 *
 * @author lmmarise.j
 * @version $Id: $Id
 */
public enum StatusCode {
    //以下是暂时设定的几种状态码类
    Success(0, "成功"),
    Fail(-1, "失败"),
    InvalidParams(201, "非法的参数!"),
    InvalidGrantType(202, "非法的授权类型");

    //状态码
    private Integer code;
    //描述信息
    private String msg;

    //重载的构造方法
    StatusCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * <p>Getter for the field <code>code</code>.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getCode() {
        return code;
    }

    /**
     * <p>Setter for the field <code>code</code>.</p>
     *
     * @param code a {@link java.lang.Integer} object.
     */
    public void setCode(Integer code) {
        this.code = code;
    }

    /**
     * <p>Getter for the field <code>msg</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getMsg() {
        return msg;
    }

    /**
     * <p>Setter for the field <code>msg</code>.</p>
     *
     * @param msg a {@link java.lang.String} object.
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
