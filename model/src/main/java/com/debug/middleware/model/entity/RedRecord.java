package com.debug.middleware.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@ToString
public class RedRecord {
    private Integer id;
    private Integer userId;
    private String redPacket;
    private Integer total;
    private BigDecimal amount;
    private Byte isActive;
    private Date createTime;

    public void setRedPacket(String redPacket) {
        this.redPacket = redPacket == null ? null : redPacket.trim();
    }
}