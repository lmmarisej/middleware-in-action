package com.debug.middleware.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@ToString
public class RedRobRecord {
    private Integer id;
    private Integer userId;
    private String redPacket;
    private BigDecimal amount;
    private Date robTime;
    private Byte isActive;

    public void setRedPacket(String redPacket) {
        this.redPacket = redPacket == null ? null : redPacket.trim();
    }
}