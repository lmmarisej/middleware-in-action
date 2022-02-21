package com.debug.middleware.server.rabbitmq.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 确认消费实体对象信息
 *
 * @author lmmarise.j
 * @version $Id: $Id
 */
@Data
@ToString
public class KnowledgeInfo implements Serializable {

    private Integer id; //id标识
    private String mode;//模式名称
    private String code;//对应编码
}






























