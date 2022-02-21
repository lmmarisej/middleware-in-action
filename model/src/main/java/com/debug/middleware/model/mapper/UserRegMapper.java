package com.debug.middleware.model.mapper;

import com.debug.middleware.model.entity.UserReg;
import org.apache.ibatis.annotations.Param;

/**
 * 用户注册实体Mapper操作接口
 *
 * @author lmmarise.j
 * @version $Id: $Id
 */
public interface UserRegMapper {
    //插入用户注册信息
    /**
     * <p>insertSelective.</p>
     *
     * @param record a {@link com.debug.middleware.model.entity.UserReg} object.
     * @return a int.
     */
    int insertSelective(UserReg record);

    //根据用户名查询用户实体
    /**
     * <p>selectByUserName.</p>
     *
     * @param userName a {@link java.lang.String} object.
     * @return a {@link com.debug.middleware.model.entity.UserReg} object.
     */
    UserReg selectByUserName(@Param("userName") String userName);
}
