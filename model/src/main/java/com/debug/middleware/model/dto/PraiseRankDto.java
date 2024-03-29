package com.debug.middleware.model.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 博客点赞总数排行
 *
 * @Author:debug (SteadyJack)
 * @Date: 2019/5/4 14:29
 * @author lmmarise.j
 * @version $Id: $Id
 */
@Data
@ToString
public class PraiseRankDto implements Serializable {
    private Integer blogId; //博客id
    private Long total;     //点赞总数

    //空的构造器
    /**
     * <p>Constructor for PraiseRankDto.</p>
     */
    public PraiseRankDto() {
    }

    //包含所有字段的构造器
    /**
     * <p>Constructor for PraiseRankDto.</p>
     *
     * @param blogId a {@link java.lang.Integer} object.
     * @param total a {@link java.lang.Long} object.
     */
    public PraiseRankDto(Integer blogId, Long total) {
        this.blogId = blogId;
        this.total = total;
    }
}
















