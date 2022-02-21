package com.debug.middleware.model.mapper;

import com.debug.middleware.model.entity.SysLog;

/**
 * <p>SysLogMapper interface.</p>
 *
 * @author lmmarise.j
 * @version $Id: $Id
 */
public interface SysLogMapper {
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
     * @param record a {@link com.debug.middleware.model.entity.SysLog} object.
     * @return a int.
     */
    int insert(SysLog record);

    /**
     * <p>insertSelective.</p>
     *
     * @param record a {@link com.debug.middleware.model.entity.SysLog} object.
     * @return a int.
     */
    int insertSelective(SysLog record);

    /**
     * <p>selectByPrimaryKey.</p>
     *
     * @param id a {@link java.lang.Integer} object.
     * @return a {@link com.debug.middleware.model.entity.SysLog} object.
     */
    SysLog selectByPrimaryKey(Integer id);

    /**
     * <p>updateByPrimaryKeySelective.</p>
     *
     * @param record a {@link com.debug.middleware.model.entity.SysLog} object.
     * @return a int.
     */
    int updateByPrimaryKeySelective(SysLog record);

    /**
     * <p>updateByPrimaryKey.</p>
     *
     * @param record a {@link com.debug.middleware.model.entity.SysLog} object.
     * @return a int.
     */
    int updateByPrimaryKey(SysLog record);
}
