package com.debug.middleware.model.mapper;

import com.debug.middleware.model.entity.UserAccount;
import org.apache.ibatis.annotations.Param;

/**
 * <p>UserAccountMapper interface.</p>
 *
 * @author lmmarise.j
 * @version $Id: $Id
 */
public interface UserAccountMapper {
    //根据主键id查询
    /**
     * <p>selectByPrimaryKey.</p>
     *
     * @param id a {@link java.lang.Integer} object.
     * @return a {@link com.debug.middleware.model.entity.UserAccount} object.
     */
    UserAccount selectByPrimaryKey(Integer id);

    //根据用户账户Id查询
    /**
     * <p>selectByUserId.</p>
     *
     * @param userId a {@link java.lang.Integer} object.
     * @return a {@link com.debug.middleware.model.entity.UserAccount} object.
     */
    UserAccount selectByUserId(@Param("userId") Integer userId);

    //更新账户金额
    /**
     * <p>updateAmount.</p>
     *
     * @param money a {@link java.lang.Double} object.
     * @param id a {@link java.lang.Integer} object.
     * @return a int.
     */
    int updateAmount(@Param("money") Double money, @Param("id") Integer id);

    //根据主键id跟version进行更新
    /**
     * <p>updateByPKVersion.</p>
     *
     * @param money a {@link java.lang.Double} object.
     * @param id a {@link java.lang.Integer} object.
     * @param version a {@link java.lang.Integer} object.
     * @return a int.
     */
    int updateByPKVersion(@Param("money") Double money, @Param("id") Integer id, @Param("version") Integer version);

    //根据用户id查询记录-for update方式
    /**
     * <p>selectByUserIdLock.</p>
     *
     * @param userId a {@link java.lang.Integer} object.
     * @return a {@link com.debug.middleware.model.entity.UserAccount} object.
     */
    UserAccount selectByUserIdLock(@Param("userId") Integer userId);

    //更新账户金额-悲观锁的方式
    /**
     * <p>updateAmountLock.</p>
     *
     * @param money a {@link java.lang.Double} object.
     * @param id a {@link java.lang.Integer} object.
     * @return a int.
     */
    int updateAmountLock(@Param("money") Double money, @Param("id") Integer id);
}
