package com.debug.middleware.server.dto;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * <p>RedPacketDto class.</p>
 *
 * @author: zhonglinsen
 * @date: 2019/3/15
 * @author lmmarise.j
 * @version $Id: $Id
 */
@Data
@ToString
public class RedPacketDto {

    private Integer userId;

    //指定多少人抢
    @NotNull
    private Integer total;

    //指定总金额-单位为分
    @NotNull
    private Integer amount;
}









































