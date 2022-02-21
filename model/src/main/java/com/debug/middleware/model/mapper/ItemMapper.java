package com.debug.middleware.model.mapper;

import com.debug.middleware.model.entity.Item;
import org.apache.ibatis.annotations.Param;

/**
 * <p>ItemMapper interface.</p>
 *
 * @author lmmarise.j
 * @version $Id: $Id
 */
public interface ItemMapper {
    /**
     * <p>deleteByPrimaryKey.</p>
     *
     * @param id a {@link java.lang.Integer} object.
     * @return a int.
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * <p>deleteByCode.</p>
     *
     * @param code a {@link java.lang.String} object.
     * @return a int.
     */
    int deleteByCode(String code);

    /**
     * <p>insert.</p>
     *
     * @param record a {@link com.debug.middleware.model.entity.Item} object.
     * @return a int.
     */
    int insert(Item record);

    /**
     * <p>insertSelective.</p>
     *
     * @param record a {@link com.debug.middleware.model.entity.Item} object.
     * @return a int.
     */
    int insertSelective(Item record);

    /**
     * <p>selectByPrimaryKey.</p>
     *
     * @param id a {@link java.lang.Integer} object.
     * @return a {@link com.debug.middleware.model.entity.Item} object.
     */
    Item selectByPrimaryKey(Integer id);

    /**
     * <p>updateByPrimaryKeySelective.</p>
     *
     * @param record a {@link com.debug.middleware.model.entity.Item} object.
     * @return a int.
     */
    int updateByPrimaryKeySelective(Item record);

    /**
     * <p>updateByPrimaryKey.</p>
     *
     * @param record a {@link com.debug.middleware.model.entity.Item} object.
     * @return a int.
     */
    int updateByPrimaryKey(Item record);

    /**
     * <p>selectByCode.</p>
     *
     * @param code a {@link java.lang.String} object.
     * @return a {@link com.debug.middleware.model.entity.Item} object.
     */
    Item selectByCode(@Param("code") String code);
}
