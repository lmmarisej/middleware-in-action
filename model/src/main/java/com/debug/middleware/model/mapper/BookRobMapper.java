package com.debug.middleware.model.mapper;

import com.debug.middleware.model.entity.BookRob;
import org.apache.ibatis.annotations.Param;

//书籍抢购成功的记录实体Mapper操作接口
/**
 * <p>BookRobMapper interface.</p>
 *
 * @author lmmarise.j
 * @version $Id: $Id
 */
public interface BookRobMapper {
    //插入抢购成功的记录信息
    /**
     * <p>insertSelective.</p>
     *
     * @param record a {@link com.debug.middleware.model.entity.BookRob} object.
     * @return a int.
     */
    int insertSelective(BookRob record);

    //统计每个用户每本书的抢购数量
    //用于判断用户是否抢购过该书籍
    /**
     * <p>countByBookNoUserId.</p>
     *
     * @param userId a {@link java.lang.Integer} object.
     * @param bookNo a {@link java.lang.String} object.
     * @return a int.
     */
    int countByBookNoUserId(@Param("userId") Integer userId, @Param("bookNo") String bookNo);
}
