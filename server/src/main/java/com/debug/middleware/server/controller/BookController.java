package com.debug.middleware.server.controller;
/**
 * Created by Administrator on 2019/3/3.
 */

import com.debug.middleware.server.entity.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>BookController class.</p>
 *
 * @Author:debug (SteadyJack)
 * @Date: 2019/3/3 17:34
 * @author lmmarise.j
 * @version $Id: $Id
 */
@RestController
@RequestMapping("/book")
public class BookController {

    /**
     * 获取书籍对象信息
     *
     * @param bookNo a {@link java.lang.Integer} object.
     * @param bookName a {@link java.lang.String} object.
     * @return a {@link com.debug.middleware.server.entity.Book} object.
     */
    @RequestMapping(value = "info", method = RequestMethod.GET)
    public Book info(Integer bookNo, String bookName) {
        Book book = new Book();
        book.setBookNo(bookNo);
        book.setName(bookName);
        return book;
    }

}






















