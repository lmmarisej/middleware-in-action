package com.debug.middleware.model.entity;

import java.util.Date;

//用户下单实体
/**
 * <p>UserOrder class.</p>
 *
 * @author lmmarise.j
 * @version $Id: $Id
 */
public class UserOrder {
    private Integer id;

    private String orderNo;

    private Integer userId;

    private Integer status;

    private Integer isActive;

    private Date createTime;

    private Date updateTime;

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
     * <p>Getter for the field <code>orderNo</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * <p>Setter for the field <code>orderNo</code>.</p>
     *
     * @param orderNo a {@link java.lang.String} object.
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
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
     * <p>Getter for the field <code>status</code>.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * <p>Setter for the field <code>status</code>.</p>
     *
     * @param status a {@link java.lang.Integer} object.
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * <p>Getter for the field <code>isActive</code>.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getIsActive() {
        return isActive;
    }

    /**
     * <p>Setter for the field <code>isActive</code>.</p>
     *
     * @param isActive a {@link java.lang.Integer} object.
     */
    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
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

    /**
     * <p>Getter for the field <code>updateTime</code>.</p>
     *
     * @return a {@link java.util.Date} object.
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * <p>Setter for the field <code>updateTime</code>.</p>
     *
     * @param updateTime a {@link java.util.Date} object.
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "UserOrder{" +
                "id=" + id +
                ", orderNo='" + orderNo + '\'' +
                ", userId=" + userId +
                ", status=" + status +
                ", isActive=" + isActive +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
