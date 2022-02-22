package com.debug.middleware.server.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 用于充当redisson死信队列中的消息
 *
 * @Author:debug (SteadyJack)
 * @Date: 2019/5/2 17:21
 * @author lmmarise.j
 * @version $Id: $Id
 */
@Data
@ToString
public class DeadDto implements Serializable {
    private Integer id;
    private String name;

    //空的构造方法
    /**
     * <p>Constructor for DeadDto.</p>
     */
    public DeadDto() {
    }

    //包含所有字段的构造方法
    /**
     * <p>Constructor for DeadDto.</p>
     *
     * @param id a {@link java.lang.Integer} object.
     * @param name a {@link java.lang.String} object.
     */
    public DeadDto(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
