package com.debug.middleware.model.entity;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

//书籍抢购记录实体
@Data
/**
 * <p>BookRob class.</p>
 *
 * @author lmmarise.j
 * @version $Id: $Id
 */
@ToString
public class BookRob {
    private Integer id;    //主键id
    private Integer userId;//用户id
    private String bookNo; //书籍编号
    private Date robTime;  //抢购时间
}
