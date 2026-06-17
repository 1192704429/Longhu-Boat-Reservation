package com.lake.booking.basic.mapper;

import com.lake.booking.vo.EchartsDataVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EchartsMapper {

    /**
     * 游船类型分布统计
     */
    List<EchartsDataVO> getBoatTypeStatistics(@Param("status") Integer status);

    /**
     * 订单状态分布统计
     */
    List<EchartsDataVO> getBoatOrderStatusStatistics(@Param("startTime") String startTime, @Param("endTime") String endTime);

    /**
     * 评价星级分布统计
     */
    List<EchartsDataVO> getBoatCommentScoreStatistics(@Param("auditStatus") Integer auditStatus);

    /**
     * 每日订单数量趋势统计
     */
    List<EchartsDataVO> getBoatDailyOrderTrend(@Param("startDate") String startDate, @Param("endDate") String endDate);

    /**
     * 游船座位数区间分布统计
     */
    List<EchartsDataVO> getBoatSeatCountDistribution(@Param("boatType") Integer boatType);

    /**
     * 用户订单数量排行统计
     */
    List<EchartsDataVO> getBoatOrderUserCount(@Param("startTime") String startTime, @Param("endTime") String endTime);

}
