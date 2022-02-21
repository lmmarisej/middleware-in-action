package com.debug.middleware.model.mapper;

import com.debug.middleware.model.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * <p>UserMapper interface.</p>
 *
 * @author lmmarise.j
 * @version $Id: $Id
 */
public interface UserMapper {
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
     * @param record a {@link com.debug.middleware.model.entity.User} object.
     * @return a int.
     */
    int insert(User record);

    /**
     * <p>insertSelective.</p>
     *
     * @param record a {@link com.debug.middleware.model.entity.User} object.
     * @return a int.
     */
    int insertSelective(User record);

    /**
     * <p>selectByPrimaryKey.</p>
     *
     * @param id a {@link java.lang.Integer} object.
     * @return a {@link com.debug.middleware.model.entity.User} object.
     */
    User selectByPrimaryKey(Integer id);

    /**
     * <p>updateByPrimaryKeySelective.</p>
     *
     * @param record a {@link com.debug.middleware.model.entity.User} object.
     * @return a int.
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * <p>updateByPrimaryKey.</p>
     *
     * @param record a {@link com.debug.middleware.model.entity.User} object.
     * @return a int.
     */
    int updateByPrimaryKey(User record);

    /**
     * <p>selectByUserNamePassword.</p>
     *
     * @param userName a {@link java.lang.String} object.
     * @param password a {@link java.lang.String} object.
     * @return a {@link com.debug.middleware.model.entity.User} object.
     */
    User selectByUserNamePassword(@Param("userName") String userName, @Param("password") String password);
}
