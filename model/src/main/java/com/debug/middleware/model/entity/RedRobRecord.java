package com.debug.middleware.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Getter
/**
 * <p>RedRobRecord class.</p>
 *
 * @author lmmarise.j
 * @version $Id: $Id
 */
@Setter
@ToString
public class RedRobRecord {
    private Integer id;
    private Integer userId;
    private String redPacket;
    private BigDecimal amount;
    private Date robTime;
    private Byte isActive;

    /**
     * <p>Setter for the field <code>redPacket</code>.</p>
     *
     * @param redPacket a {@link java.lang.String} object.
     */
    public void setRedPacket(String redPacket) {
        this.redPacket = redPacket == null ? null : redPacket.trim();
    }
}
