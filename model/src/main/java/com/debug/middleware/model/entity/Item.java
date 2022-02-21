package com.debug.middleware.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
/**
 * <p>Item class.</p>
 *
 * @author lmmarise.j
 * @version $Id: $Id
 */
@Setter
@ToString
public class Item {
    private Integer id;
    private String code;
    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * <p>Setter for the field <code>code</code>.</p>
     *
     * @param code a {@link java.lang.String} object.
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * <p>Setter for the field <code>name</code>.</p>
     *
     * @param name a {@link java.lang.String} object.
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}
