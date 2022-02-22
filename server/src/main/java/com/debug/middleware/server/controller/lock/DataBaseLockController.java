package com.debug.middleware.server.controller.lock;

import com.debug.middleware.api.enums.StatusCode;
import com.debug.middleware.api.response.BaseResponse;
import com.debug.middleware.server.controller.lock.dto.UserAccountDto;
import com.debug.middleware.server.service.lock.DataBaseLockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 基于数据库的乐观悲观锁
 *
 * @author lmmarise.j
 * @version $Id: $Id
 */
@RestController
public class DataBaseLockController {
    //定义日志
    private static final Logger log = LoggerFactory.getLogger(DataBaseLockController.class);
    //定义请求前缀
    private static final String prefix = "db";
    //定义核心逻辑处理服务类
    @Autowired
    private DataBaseLockService dataBaseLockService;

    /**
     * 用户账户余额提现申请
     */
    @RequestMapping(value = prefix + "/money/take", method = RequestMethod.GET)
    public BaseResponse takeMoney(UserAccountDto dto) {
        if (dto.getAmount() == null || dto.getUserId() == null) {
            return new BaseResponse(StatusCode.InvalidParams);
        }
        BaseResponse response = new BaseResponse(StatusCode.Success);
        try {
            //不加锁的情况
//            dataBaseLockService.takeMoney(dto);

            //加乐观锁的情况
            dataBaseLockService.takeMoneyWithOptimisticLock(dto);

            //加悲观锁的情况
            // dataBaseLockService.takeMoneyWithLockNegative(dto);
        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
        }
        return response;
    }
}