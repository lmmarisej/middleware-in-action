package com.debug.middleware.server.service;


import com.debug.middleware.server.dto.RedPacketDto;

import java.math.BigDecimal;

/**
 * <p>IRedPacketService interface.</p>
 *
 * @author: zhonglinsen
 * @date: 2019/3/15
 * @author lmmarise.j
 * @version $Id: $Id
 */
public interface IRedPacketService {

    /**
     * <p>handOut.</p>
     *
     * @param dto a {@link com.debug.middleware.server.dto.RedPacketDto} object.
     * @return a {@link java.lang.String} object.
     * @throws java.lang.Exception if any.
     */
    String handOut(RedPacketDto dto) throws Exception;

    /**
     * <p>rob.</p>
     *
     * @param userId a {@link java.lang.Integer} object.
     * @param redId a {@link java.lang.String} object.
     * @return a {@link java.math.BigDecimal} object.
     * @throws java.lang.Exception if any.
     */
    BigDecimal rob(Integer userId, String redId) throws Exception;
}
