package com.debug.middleware.server.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 二倍均值法的代码实战
 */
public class RedPacketUtil {

    public static final int MAX_REA_COUNT = 100;

    /**
     * 发红包算法，金额参数以分为单位
     */
    public static List<Integer> divideRedPackage(Integer totalAmount, Integer totalPeopleNum) {
        paramVerify(totalAmount, totalPeopleNum);

        List<Integer> amountList = new ArrayList<>();

        if (totalAmount > 0 && totalPeopleNum > 0) {
            int restAmount = totalAmount;
            int restPeopleNum = totalPeopleNum;

            Random random = new Random();
            for (int i = 0; i < totalPeopleNum - 1; i++) {
                // 随机范围：[1，剩余人均金额的两倍)，左闭右开
                int amount = random.nextInt(restAmount / restPeopleNum * 2 - 1) + 1;
                restAmount -= amount;
                restPeopleNum--;
                amountList.add(amount);
            }
            amountList.add(restAmount);
        }

        return amountList;
    }

    private static void paramVerify(Integer totalAmount, Integer totalPeopleNum) {
        if (totalPeopleNum > MAX_REA_COUNT) {
            throw new IllegalArgumentException("单次红包个数不得超过：" + MAX_REA_COUNT);
        }
        if (totalAmount < 1) {
            throw new IllegalArgumentException("红包金额不得小于：" + "0.01");
        }
        if (totalPeopleNum < 1) {
            throw new IllegalArgumentException("参与抢红包的用户人数不能小于：" + "1");
        }
        if (totalAmount / totalPeopleNum < 1) {
            throw new IllegalArgumentException("单个用户可抢得的红包金额不得小于：" + "0.01");
        }
    }
}
