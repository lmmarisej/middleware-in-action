package com.debug.middleware.server.controller.lock.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 用户账户提现申请dto
 *
 * @author lmmarise.j
 * @version $Id: $Id
 */
@Data
@ToString
public class UserAccountDto implements Serializable {
    private Integer userId; //用户账户Id
    private Double amount;  //提现金额
}
