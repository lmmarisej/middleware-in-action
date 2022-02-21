package com.debug.middleware.server.service;

import com.debug.middleware.server.dto.PraiseDto;
import com.debug.middleware.model.dto.PraiseRankDto;

import java.util.Collection;

/**
 * 点赞业务处理接口
 *
 * @author: zhonglinsen
 * @author lmmarise.j
 * @version $Id: $Id
 */
public interface IPraiseService {
    //点赞博客-无锁
    /**
     * <p>addPraise.</p>
     *
     * @param dto a {@link com.debug.middleware.server.dto.PraiseDto} object.
     * @throws java.lang.Exception if any.
     */
    void addPraise(PraiseDto dto) throws Exception;

    //点赞博客-加分布式锁
    /**
     * <p>addPraiseLock.</p>
     *
     * @param dto a {@link com.debug.middleware.server.dto.PraiseDto} object.
     * @throws java.lang.Exception if any.
     */
    void addPraiseLock(PraiseDto dto) throws Exception;

    //取消点赞博客
    /**
     * <p>cancelPraise.</p>
     *
     * @param dto a {@link com.debug.middleware.server.dto.PraiseDto} object.
     * @throws java.lang.Exception if any.
     */
    void cancelPraise(PraiseDto dto) throws Exception;

    //获取博客的点赞数
    /**
     * <p>getBlogPraiseTotal.</p>
     *
     * @param blogId a {@link java.lang.Integer} object.
     * @return a {@link java.lang.Long} object.
     * @throws java.lang.Exception if any.
     */
    Long getBlogPraiseTotal(Integer blogId) throws Exception;

    //获取博客点赞总数排行榜-不采用缓存
    /**
     * <p>getRankNoRedisson.</p>
     *
     * @return a {@link java.util.Collection} object.
     * @throws java.lang.Exception if any.
     */
    Collection<PraiseRankDto> getRankNoRedisson() throws Exception;

    //获取博客点赞总数排行榜-采用缓存
    /**
     * <p>getRankWithRedisson.</p>
     *
     * @return a {@link java.util.Collection} object.
     * @throws java.lang.Exception if any.
     */
    Collection<PraiseRankDto> getRankWithRedisson() throws Exception;
}
