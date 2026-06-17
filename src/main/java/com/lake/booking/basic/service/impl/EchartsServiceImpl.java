package com.lake.booking.basic.service.impl;

import com.lake.booking.basic.mapper.EchartsMapper;
import com.lake.booking.basic.service.EchartsService;
import com.lake.booking.vo.EchartsDataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EchartsServiceImpl implements EchartsService {

    @Autowired
    private EchartsMapper echartsMapper;

    /**
     * 游船类型分布统计
     */
    @Override
    public List<EchartsDataVO> getBoatTypeStatistics(Integer status) {
        return echartsMapper.getBoatTypeStatistics(status);
    }

    /**
     * 订单状态分布统计
     */
    @Override
    public List<EchartsDataVO> getBoatOrderStatusStatistics(String startTime, String endTime) {
        return echartsMapper.getBoatOrderStatusStatistics(startTime, endTime);
    }

    /**
     * 评价星级分布统计
     */
    @Override
    public List<EchartsDataVO> getBoatCommentScoreStatistics(Integer auditStatus) {
        return echartsMapper.getBoatCommentScoreStatistics(auditStatus);
    }

    /**
     * 每日订单数量趋势统计
     */
    @Override
    public List<EchartsDataVO> getBoatDailyOrderTrend(String startDate, String endDate) {
        return echartsMapper.getBoatDailyOrderTrend(startDate, endDate);
    }

    /**
     * 游船座位数区间分布统计
     */
    @Override
    public List<EchartsDataVO> getBoatSeatCountDistribution(Integer boatType) {
        return echartsMapper.getBoatSeatCountDistribution(boatType);
    }

    /**
     * 用户订单数量排行统计
     */
    @Override
    public List<EchartsDataVO> getBoatOrderUserCount(String startTime, String endTime) {
        return echartsMapper.getBoatOrderUserCount(startTime, endTime);
    }

}
