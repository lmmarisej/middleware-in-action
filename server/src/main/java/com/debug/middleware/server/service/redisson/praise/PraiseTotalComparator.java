package com.debug.middleware.server.service.redisson.praise;
/**
 * Created by Administrator on 2019/5/4.
 */

import com.debug.middleware.model.dto.PraiseRankDto;

import java.util.Comparator;

/**
 * 博客点赞总数比较器
 *
 * @Author:debug (SteadyJack)
 * @Date: 2019/5/4 14:36
 * @author lmmarise.j
 * @version $Id: $Id
 */
public class PraiseTotalComparator implements Comparator<PraiseRankDto> {
    /**
     * {@inheritDoc}
     *
     * 按照点赞总数进行排行
     */
    @Override
    public int compare(PraiseRankDto o1, PraiseRankDto o2) {
        //点赞总数大的排在点赞总数小的
        return o2.getTotal().compareTo(o1.getTotal());
    }
}






























