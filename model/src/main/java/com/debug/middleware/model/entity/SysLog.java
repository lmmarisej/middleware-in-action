package com.debug.middleware.model.entity;

import java.util.Date;

/**
 * <p>SysLog class.</p>
 *
 * @author lmmarise.j
 * @version $Id: $Id
 */
public class SysLog {
    private Integer id;

    private Integer userId;

    private String module;

    private String data;

    private String memo;

    private Date createTime;

    /**
     * <p>Getter for the field <code>id</code>.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getId() {
        return id;
    }

    /**
     * <p>Setter for the field <code>id</code>.</p>
     *
     * @param id a {@link java.lang.Integer} object.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * <p>Getter for the field <code>userId</code>.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * <p>Setter for the field <code>userId</code>.</p>
     *
     * @param userId a {@link java.lang.Integer} object.
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * <p>Getter for the field <code>module</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getModule() {
        return module;
    }

    /**
     * <p>Setter for the field <code>module</code>.</p>
     *
     * @param module a {@link java.lang.String} object.
     */
    public void setModule(String module) {
        this.module = module == null ? null : module.trim();
    }

    /**
     * <p>Getter for the field <code>data</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getData() {
        return data;
    }

    /**
     * <p>Setter for the field <code>data</code>.</p>
     *
     * @param data a {@link java.lang.String} object.
     */
    public void setData(String data) {
        this.data = data == null ? null : data.trim();
    }

    /**
     * <p>Getter for the field <code>memo</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getMemo() {
        return memo;
    }

    /**
     * <p>Setter for the field <code>memo</code>.</p>
     *
     * @param memo a {@link java.lang.String} object.
     */
    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    /**
     * <p>Getter for the field <code>createTime</code>.</p>
     *
     * @return a {@link java.util.Date} object.
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * <p>Setter for the field <code>createTime</code>.</p>
     *
     * @param createTime a {@link java.util.Date} object.
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
