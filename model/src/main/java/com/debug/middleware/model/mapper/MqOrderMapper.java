package com.debug.middleware.model.mapper;

import com.debug.middleware.model.entity.MqOrder;

/**
 * <p>MqOrderMapper interface.</p>
 *
 * @author lmmarise.j
 * @version $Id: $Id
 */
public interface MqOrderMapper {
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
     * @param record a {@link com.debug.middleware.model.entity.MqOrder} object.
     * @return a int.
     */
    int insert(MqOrder record);

    //插入记录
    /**
     * <p>insertSelective.</p>
     *
     * @param record a {@link com.debug.middleware.model.entity.MqOrder} object.
     * @return a int.
     */
    int insertSelective(MqOrder record);

    //根据主键id查询记录
    /**
     * <p>selectByPrimaryKey.</p>
     *
     * @param id a {@link java.lang.Integer} object.
     * @return a {@link com.debug.middleware.model.entity.MqOrder} object.
     */
    MqOrder selectByPrimaryKey(Integer id);

    //更新记录
    /**
     * <p>updateByPrimaryKeySelective.</p>
     *
     * @param record a {@link com.debug.middleware.model.entity.MqOrder} object.
     * @return a int.
     */
    int updateByPrimaryKeySelective(MqOrder record);

    //更新记录
    /**
     * <p>updateByPrimaryKey.</p>
     *
     * @param record a {@link com.debug.middleware.model.entity.MqOrder} object.
     * @return a int.
     */
    int updateByPrimaryKey(MqOrder record);
}
