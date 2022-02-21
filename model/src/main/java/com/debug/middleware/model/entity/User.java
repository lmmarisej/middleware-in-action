package com.debug.middleware.model.entity;

import java.util.Date;

/**
 * <p>User class.</p>
 *
 * @author lmmarise.j
 * @version $Id: $Id
 */
public class User {
    private Integer id;

    private String userName;

    private String password;

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
     * <p>Getter for the field <code>userName</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * <p>Setter for the field <code>userName</code>.</p>
     *
     * @param userName a {@link java.lang.String} object.
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * <p>Getter for the field <code>password</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getPassword() {
        return password;
    }

    /**
     * <p>Setter for the field <code>password</code>.</p>
     *
     * @param password a {@link java.lang.String} object.
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
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
