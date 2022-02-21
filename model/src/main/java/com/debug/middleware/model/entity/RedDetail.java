package com.debug.middleware.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Getter
/**
 * <p>RedDetail class.</p>
 *
 * @author lmmarise.j
 * @version $Id: $Id
 */
@Setter
@ToString
public class RedDetail {
    private Integer id;
    private Integer recordId;
    private BigDecimal amount;
    private Byte isActive;
    private Date createTime;
}
