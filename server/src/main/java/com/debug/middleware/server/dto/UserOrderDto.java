package com.debug.middleware.server.dto;

import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 用户下单实体信息
 *
 * @author lmmarise.j
 * @version $Id: $Id
 */
@Data
@ToString
public class UserOrderDto implements Serializable {
    @NotBlank
    private String orderNo;//订单编号-必填
    @NotNull
    private Integer userId;//用户id-必填
}
