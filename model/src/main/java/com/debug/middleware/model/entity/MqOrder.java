package com.debug.middleware.model.entity;

import java.util.Date;

//RabbitMQ死信队列更新失效订单的状态实体
/**
 * <p>MqOrder class.</p>
 *
 * @author lmmarise.j
 * @version $Id: $Id
 */
public class MqOrder {
    private Integer id; //主键id
    private Integer orderId; //下单记录id
    private Date businessTime; //失效下单记录状态的业务时间
    private String memo; //备注信息

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
     * <p>Getter for the field <code>orderId</code>.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * <p>Setter for the field <code>orderId</code>.</p>
     *
     * @param orderId a {@link java.lang.Integer} object.
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * <p>Getter for the field <code>businessTime</code>.</p>
     *
     * @return a {@link java.util.Date} object.
     */
    public Date getBusinessTime() {
        return businessTime;
    }

    /**
     * <p>Setter for the field <code>businessTime</code>.</p>
     *
     * @param businessTime a {@link java.util.Date} object.
     */
    public void setBusinessTime(Date businessTime) {
        this.businessTime = businessTime;
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
}
