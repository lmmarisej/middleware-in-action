package com.debug.middleware.server.service;


import com.debug.middleware.server.dto.RedPacketDto;

import java.math.BigDecimal;
import java.util.List;

/**
 * 红包记录服务
 *
 * @author lmmarise.j
 * @version $Id: $Id
 */
public interface IRedService {

    /**
     * <p>recordRedPacket.</p>
     *
     * @param dto a {@link com.debug.middleware.server.dto.RedPacketDto} object.
     * @param redId a {@link java.lang.String} object.
     * @param list a {@link java.util.List} object.
     * @throws java.lang.Exception if any.
     */
    void recordRedPacket(RedPacketDto dto, String redId, List<Integer> list) throws Exception;

    /**
     * <p>recordRobRedPacket.</p>
     *
     * @param userId a {@link java.lang.Integer} object.
     * @param redId a {@link java.lang.String} object.
     * @param amount a {@link java.math.BigDecimal} object.
     * @throws java.lang.Exception if any.
     */
    void recordRobRedPacket(Integer userId, String redId, BigDecimal amount) throws Exception;

}
