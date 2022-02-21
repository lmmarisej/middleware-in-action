package com.debug.middleware.model.mapper;


import com.debug.middleware.model.entity.RedRecord;

/**
 * <p>RedRecordMapper interface.</p>
 *
 * @author lmmarise.j
 * @version $Id: $Id
 */
public interface RedRecordMapper {
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
     * @param record a {@link com.debug.middleware.model.entity.RedRecord} object.
     * @return a int.
     */
    int insert(RedRecord record);

    /**
     * <p>insertSelective.</p>
     *
     * @param record a {@link com.debug.middleware.model.entity.RedRecord} object.
     * @return a int.
     */
    int insertSelective(RedRecord record);

    /**
     * <p>selectByPrimaryKey.</p>
     *
     * @param id a {@link java.lang.Integer} object.
     * @return a {@link com.debug.middleware.model.entity.RedRecord} object.
     */
    RedRecord selectByPrimaryKey(Integer id);

    /**
     * <p>updateByPrimaryKeySelective.</p>
     *
     * @param record a {@link com.debug.middleware.model.entity.RedRecord} object.
     * @return a int.
     */
    int updateByPrimaryKeySelective(RedRecord record);

    /**
     * <p>updateByPrimaryKey.</p>
     *
     * @param record a {@link com.debug.middleware.model.entity.RedRecord} object.
     * @return a int.
     */
    int updateByPrimaryKey(RedRecord record);
}
