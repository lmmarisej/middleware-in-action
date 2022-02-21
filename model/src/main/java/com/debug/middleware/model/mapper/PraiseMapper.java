package com.debug.middleware.model.mapper;

import com.debug.middleware.model.dto.PraiseRankDto;
import com.debug.middleware.model.entity.Praise;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 点赞实体操作接口Mapper
 *
 * @author lmmarise.j
 * @version $Id: $Id
 */
public interface PraiseMapper {
    //插入点赞信息
    /**
     * <p>insertSelective.</p>
     *
     * @param record a {@link com.debug.middleware.model.entity.Praise} object.
     * @return a int.
     */
    int insertSelective(Praise record);

    //根据博客id跟用户id查询点赞记录
    /**
     * <p>selectByBlogUserId.</p>
     *
     * @param blogId a {@link java.lang.Integer} object.
     * @param uId a {@link java.lang.Integer} object.
     * @return a {@link com.debug.middleware.model.entity.Praise} object.
     */
    Praise selectByBlogUserId(@Param("blogId") Integer blogId, @Param("uId") Integer uId);

    //根据博客id查询总的点赞数
    /**
     * <p>countByBlogId.</p>
     *
     * @param blogId a {@link java.lang.Integer} object.
     * @return a int.
     */
    int countByBlogId(@Param("blogId") Integer blogId);

    //取消点赞博客
    /**
     * <p>cancelPraiseBlog.</p>
     *
     * @param blogId a {@link java.lang.Integer} object.
     * @param uId a {@link java.lang.Integer} object.
     * @return a int.
     */
    int cancelPraiseBlog(@Param("blogId") Integer blogId, @Param("uId") Integer uId);

    //获取博客点赞总数排行榜
    /**
     * <p>getPraiseRank.</p>
     *
     * @return a {@link java.util.List} object.
     */
    List<PraiseRankDto> getPraiseRank();
}
