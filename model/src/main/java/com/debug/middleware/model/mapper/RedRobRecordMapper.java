package com.debug.middleware.model.mapper;


import com.debug.middleware.model.entity.RedRobRecord;

/**
 * <p>RedRobRecordMapper interface.</p>
 *
 * @author lmmarise.j
 * @version $Id: $Id
 */
public interface RedRobRecordMapper {
    /**
     * <p>deleteByPrimaryKey.</p>
     *
     * @param id a {@link java.lang.Integer} object.
     * @return a int.
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * <p>insert.</p>
     *
     * @param record a {@link com.debug.middleware.model.entity.RedRobRecord} object.
     * @return a int.
     */
    int insert(RedRobRecord record);

    /**
     * <p>insertSelective.</p>
     *
     * @param record a {@link com.debug.middleware.model.entity.RedRobRecord} object.
     * @return a int.
     */
    int insertSelective(RedRobRecord record);

    /**
     * <p>selectByPrimaryKey.</p>
     *
     * @param id a {@link java.lang.Integer} object.
     * @return a {@link com.debug.middleware.model.entity.RedRobRecord} object.
     */
    RedRobRecord selectByPrimaryKey(Integer id);

    /**
     * <p>updateByPrimaryKeySelective.</p>
     *
     * @param record a {@link com.debug.middleware.model.entity.RedRobRecord} object.
     * @return a int.
     */
    int updateByPrimaryKeySelective(RedRobRecord record);

    /**
     * <p>updateByPrimaryKey.</p>
     *
     * @param record a {@link com.debug.middleware.model.entity.RedRobRecord} object.
     * @return a int.
     */
    int updateByPrimaryKey(RedRobRecord record);
}
