package com.debug.middleware.server.rabbitmq.entity;/**
 * Created by Administrator on 2019/4/9.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * <p>DeadInfo class.</p>
 *
 * @author lmmarise.j
 * @version $Id: $Id
 */
@Data
@ToString
public class DeadInfo implements Serializable {

    private Integer id;
    private String msg;

    /**
     * <p>Constructor for DeadInfo.</p>
     */
    public DeadInfo() {
    }

    /**
     * <p>Constructor for DeadInfo.</p>
     *
     * @param id a {@link java.lang.Integer} object.
     * @param msg a {@link java.lang.String} object.
     */
    public DeadInfo(Integer id, String msg) {
        this.id = id;
        this.msg = msg;
    }
}
