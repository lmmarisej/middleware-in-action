package com.debug.middleware.server.rabbitmq.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 对象实体信息
 *
 * @Author:debug (SteadyJack)
 * @Date: 2019/3/31 21:50
 * @author lmmarise.j
 * @version $Id: $Id
 */
@Data
@ToString
public class EventInfo implements Serializable {

    private Integer id;
    private String module;
    private String name;
    private String desc;

    /**
     * <p>Constructor for EventInfo.</p>
     */
    public EventInfo() {
    }

    /**
     * <p>Constructor for EventInfo.</p>
     *
     * @param id a {@link java.lang.Integer} object.
     * @param module a {@link java.lang.String} object.
     * @param name a {@link java.lang.String} object.
     * @param desc a {@link java.lang.String} object.
     */
    public EventInfo(Integer id, String module, String name, String desc) {
        this.id = id;
        this.module = module;
        this.name = name;
        this.desc = desc;
    }
}























