package com.debug.middleware.model.mapper;


import com.debug.middleware.model.entity.RedDetail;

/**
 * <p>RedDetailMapper interface.</p>
 *
 * @author lmmarise.j
 * @version $Id: $Id
 */
public interface RedDetailMapper {
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
     * @param record a {@link com.debug.middleware.model.entity.RedDetail} object.
     * @return a int.
     */
    int insert(RedDetail record);

    /**
     * <p>insertSelective.</p>
     *
     * @param record a {@link com.debug.middleware.model.entity.RedDetail} object.
     * @return a int.
     */
    int insertSelective(RedDetail record);

    /**
     * <p>selectByPrimaryKey.</p>
     *
     * @param id a {@link java.lang.Integer} object.
     * @return a {@link com.debug.middleware.model.entity.RedDetail} object.
     */
    RedDetail selectByPrimaryKey(Integer id);

    /**
     * <p>updateByPrimaryKeySelective.</p>
     *
     * @param record a {@link com.debug.middleware.model.entity.RedDetail} object.
     * @return a int.
     */
    int updateByPrimaryKeySelective(RedDetail record);

    /**
     * <p>updateByPrimaryKey.</p>
     *
     * @param record a {@link com.debug.middleware.model.entity.RedDetail} object.
     * @return a int.
     */
    int updateByPrimaryKey(RedDetail record);
}
