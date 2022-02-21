package com.debug.middleware.model.mapper;

import com.debug.middleware.model.entity.UserAccountRecord;

/**
 * <p>UserAccountRecordMapper interface.</p>
 *
 * @author lmmarise.j
 * @version $Id: $Id
 */
public interface UserAccountRecordMapper {
    //插入记录
    /**
     * <p>insert.</p>
     *
     * @param record a {@link com.debug.middleware.model.entity.UserAccountRecord} object.
     * @return a int.
     */
    int insert(UserAccountRecord record);

    //根据主键id查询
    /**
     * <p>selectByPrimaryKey.</p>
     *
     * @param id a {@link java.lang.Integer} object.
     * @return a {@link com.debug.middleware.model.entity.UserAccountRecord} object.
     */
    UserAccountRecord selectByPrimaryKey(Integer id);
}
