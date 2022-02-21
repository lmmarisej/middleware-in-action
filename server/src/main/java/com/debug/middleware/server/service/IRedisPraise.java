package com.debug.middleware.server.service;

import com.debug.middleware.model.dto.PraiseRankDto;

import java.util.List;

/**
 * 博客点赞Redis处理服务
 *
 * @author: zhonglinsen
 * @date: 2019/1/15
 * @author lmmarise.j
 * @version $Id: $Id
 */
public interface IRedisPraise {
    //缓存当前用户点赞博客的记录-包括正常点赞、取消点赞
    /**
     * <p>cachePraiseBlog.</p>
     *
     * @param blogId a {@link java.lang.Integer} object.
     * @param uId a {@link java.lang.Integer} object.
     * @param status a {@link java.lang.Integer} object.
     * @throws java.lang.Exception if any.
     */
    void cachePraiseBlog(Integer blogId, Integer uId, Integer status) throws Exception;

    //获取当前博客总的点赞数
    /**
     * <p>getCacheTotalBlog.</p>
     *
     * @param blogId a {@link java.lang.Integer} object.
     * @return a {@link java.lang.Long} object.
     * @throws java.lang.Exception if any.
     */
    Long getCacheTotalBlog(Integer blogId) throws Exception;

    //触发博客点赞总数排行榜
    /**
     * <p>rankBlogPraise.</p>
     *
     * @throws java.lang.Exception if any.
     */
    void rankBlogPraise() throws Exception;

    //获得博客点赞排行榜
    /**
     * <p>getBlogPraiseRank.</p>
     *
     * @return a {@link java.util.List} object.
     * @throws java.lang.Exception if any.
     */
    List<PraiseRankDto> getBlogPraiseRank() throws Exception;
}




















