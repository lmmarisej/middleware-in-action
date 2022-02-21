package com.debug.middleware.server.rabbitmq.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * <p>Person class.</p>
 *
 * @Author:debug (SteadyJack)
 * @Date: 2019/3/31 15:09
 * @author lmmarise.j
 * @version $Id: $Id
 */
@Data
@ToString
public class Person implements Serializable {

    private Integer id;
    private String name;
    private String userName;

    /**
     * <p>Constructor for Person.</p>
     */
    public Person() {
    }

    /**
     * <p>Constructor for Person.</p>
     *
     * @param id a {@link java.lang.Integer} object.
     * @param name a {@link java.lang.String} object.
     * @param userName a {@link java.lang.String} object.
     */
    public Person(Integer id, String name, String userName) {
        this.id = id;
        this.name = name;
        this.userName = userName;
    }
}


























