package com.debug.middleware.api.response;


import com.debug.middleware.api.enums.StatusCode;

/**
 * <p>BaseResponse class.</p>
 *
 * @author lmmarise.j
 * @version $Id: $Id
 */
public class BaseResponse<T> {
    //状态码
    private Integer code;
    //描述信息
    private String msg;
    //响应数据-采用泛型表示可以接受通用的数据类型
    private T data;

    //重载的构造方法一
    /**
     * <p>Constructor for BaseResponse.</p>
     *
     * @param code a {@link java.lang.Integer} object.
     * @param msg a {@link java.lang.String} object.
     */
    public BaseResponse(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    //重载的构造方法二
    /**
     * <p>Constructor for BaseResponse.</p>
     *
     * @param statusCode a {@link com.debug.middleware.api.enums.StatusCode} object.
     */
    public BaseResponse(StatusCode statusCode) {
        this.code = statusCode.getCode();
        this.msg = statusCode.getMsg();
    }

    //重载的构造方法三
    /**
     * <p>Constructor for BaseResponse.</p>
     *
     * @param code a {@link java.lang.Integer} object.
     * @param msg a {@link java.lang.String} object.
     * @param data a T object.
     */
    public BaseResponse(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
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

    /**
     * <p>Getter for the field <code>data</code>.</p>
     *
     * @return a T object.
     */
    public T getData() {
        return data;
    }

    /**
     * <p>Setter for the field <code>data</code>.</p>
     *
     * @param data a T object.
     */
    public void setData(T data) {
        this.data = data;
    }
}
