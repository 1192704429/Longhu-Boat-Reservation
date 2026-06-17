package com.lake.booking.basic.service;

import com.lake.booking.vo.EchartsDataVO;

import java.util.List;

public interface EchartsService {

    /**
     * 游船类型分布统计
     */
    List<EchartsDataVO> getBoatTypeStatistics(Integer status);

    /**
     * 订单状态分布统计
     */
    List<EchartsDataVO> getBoatOrderStatusStatistics(String startTime, String endTime);

    /**
     * 评价星级分布统计
     */
    List<EchartsDataVO> getBoatCommentScoreStatistics(Integer auditStatus);

    /**
     * 每日订单数量趋势统计
     */
    List<EchartsDataVO> getBoatDailyOrderTrend(String startDate, String endDate);

    /**
     * 游船座位数区间分布统计
     */
    List<EchartsDataVO> getBoatSeatCountDistribution(Integer boatType);

    /**
     * 用户订单数量排行统计
     */
    List<EchartsDataVO> getBoatOrderUserCount(String startTime, String endTime);

}
