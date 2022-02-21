package com.debug.middleware.model.mapper;

import com.debug.middleware.model.entity.UserOrder;
import org.apache.ibatis.annotations.Param;

/**
 * <p>UserOrderMapper interface.</p>
 *
 * @author lmmarise.j
 * @version $Id: $Id
 */
public interface UserOrderMapper {
    //根据主键id删除记录
    /**
     * <p>deleteByPrimaryKey.</p>
     *
     * @param id a {@link java.lang.Integer} object.
     * @return a int.
     */
    int deleteByPrimaryKey(Integer id);

    //插入记录
    /**
     * <p>insert.</p>
     *
     * @param record a {@link com.debug.middleware.model.entity.UserOrder} object.
     * @return a int.
     */
    int insert(UserOrder record);

    //插入记录
    /**
     * <p>insertSelective.</p>
     *
     * @param record a {@link com.debug.middleware.model.entity.UserOrder} object.
     * @return a int.
     */
    int insertSelective(UserOrder record);

    //根据主键id查询记录
    /**
     * <p>selectByPrimaryKey.</p>
     *
     * @param id a {@link java.lang.Integer} object.
     * @return a {@link com.debug.middleware.model.entity.UserOrder} object.
     */
    UserOrder selectByPrimaryKey(Integer id);

    //更新记录
    /**
     * <p>updateByPrimaryKeySelective.</p>
     *
     * @param record a {@link com.debug.middleware.model.entity.UserOrder} object.
     * @return a int.
     */
    int updateByPrimaryKeySelective(UserOrder record);

    //更新记录
    /**
     * <p>updateByPrimaryKey.</p>
     *
     * @param record a {@link com.debug.middleware.model.entity.UserOrder} object.
     * @return a int.
     */
    int updateByPrimaryKey(UserOrder record);

    //根据下单记录Id和支付状态查询
    /**
     * <p>selectByIdAndStatus.</p>
     *
     * @param id a {@link java.lang.Integer} object.
     * @param status a {@link java.lang.Integer} object.
     * @return a {@link com.debug.middleware.model.entity.UserOrder} object.
     */
    UserOrder selectByIdAndStatus(@Param("id") Integer id, @Param("status") Integer status);
}
