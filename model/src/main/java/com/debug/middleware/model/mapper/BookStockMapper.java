package com.debug.middleware.model.mapper;

import com.debug.middleware.model.entity.BookStock;
import org.apache.ibatis.annotations.Param;

//书籍库存实体操作接口Mapper
/**
 * <p>BookStockMapper interface.</p>
 *
 * @author lmmarise.j
 * @version $Id: $Id
 */
public interface BookStockMapper {
    //根据书籍编号查询
    /**
     * <p>selectByBookNo.</p>
     *
     * @param bookNo a {@link java.lang.String} object.
     * @return a {@link com.debug.middleware.model.entity.BookStock} object.
     */
    BookStock selectByBookNo(@Param("bookNo") String bookNo);

    //更新书籍库存-不加锁
    /**
     * <p>updateStock.</p>
     *
     * @param bookNo a {@link java.lang.String} object.
     * @return a int.
     */
    int updateStock(@Param("bookNo") String bookNo);

    //更新书籍库存-加锁
    /**
     * <p>updateStockWithLock.</p>
     *
     * @param bookNo a {@link java.lang.String} object.
     * @return a int.
     */
    int updateStockWithLock(@Param("bookNo") String bookNo);
}
