package com.lake.booking.basic.controller;

import com.lake.booking.basic.service.EchartsService;
import com.lake.booking.vo.EchartsDataVO;
import com.lake.booking.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 图表可视化接口
 */
@RestController
@RequestMapping("/echarts")
public class EchartsController {

    @Autowired
    private EchartsService echartsService;

    /**
     * 游船类型分布统计
     */
    @PostMapping("/getBoatTypeStatistics")
    public Result getBoatTypeStatistics(@RequestBody Map<String, Object> params) {
        // 从 RequestBody 中提取参数
        Integer status = Optional.ofNullable(params.get("status"))
                .map(Object::toString)
                .filter(s -> !s.isBlank())
                .map(Integer::valueOf)
                .orElse(null);
        // 前端使用 -1 表示“全部”，后端按 null 处理为不加状态过滤
        if (status != null && status == -1) {
            status = null;
        }
        List<EchartsDataVO> data = echartsService.getBoatTypeStatistics(status);
        return Result.success(data);
    }

    /**
     * 订单状态分布统计
     */
    @PostMapping("/getBoatOrderStatusStatistics")
    public Result getBoatOrderStatusStatistics(@RequestBody Map<String, Object> params) {
        // 从 RequestBody 中提取参数
        String startTime = Optional.ofNullable(params.get("startTime"))
                .map(Object::toString)
                .filter(s -> !s.isBlank())
                .orElse(null);
        String endTime = Optional.ofNullable(params.get("endTime"))
                .map(Object::toString)
                .filter(s -> !s.isBlank())
                .orElse(null);
        List<EchartsDataVO> data = echartsService.getBoatOrderStatusStatistics(startTime, endTime);
        return Result.success(data);
    }

    /**
     * 评价星级分布统计
     */
    @PostMapping("/getBoatCommentScoreStatistics")
    public Result getBoatCommentScoreStatistics(@RequestBody Map<String, Object> params) {
        // 从 RequestBody 中提取参数
        Integer auditStatus = Optional.ofNullable(params.get("auditStatus"))
                .map(Object::toString)
                .filter(s -> !s.isBlank())
                .map(Integer::valueOf)
                .orElse(null);
        // 前端使用 -1 表示“全部”
        if (auditStatus != null && auditStatus == -1) {
            auditStatus = null;
        }
        List<EchartsDataVO> data = echartsService.getBoatCommentScoreStatistics(auditStatus);
        return Result.success(data);
    }

    /**
     * 每日订单数量趋势统计
     */
    @PostMapping("/getBoatDailyOrderTrend")
    public Result getBoatDailyOrderTrend(@RequestBody Map<String, Object> params) {
        // 从 RequestBody 中提取参数
        String startDate = Optional.ofNullable(params.get("startDate"))
                .map(Object::toString)
                .filter(s -> !s.isBlank())
                .orElse(null);
        String endDate = Optional.ofNullable(params.get("endDate"))
                .map(Object::toString)
                .filter(s -> !s.isBlank())
                .orElse(null);
        List<EchartsDataVO> data = echartsService.getBoatDailyOrderTrend(startDate, endDate);
        return Result.success(data);
    }

    /**
     * 游船座位数区间分布统计
     */
    @PostMapping("/getBoatSeatCountDistribution")
    public Result getBoatSeatCountDistribution(@RequestBody Map<String, Object> params) {
        // 从 RequestBody 中提取参数
        Integer boatType = Optional.ofNullable(params.get("boatType"))
                .map(Object::toString)
                .filter(s -> !s.isBlank())
                .map(Integer::valueOf)
                .orElse(null);
        // 前端使用 -1 表示“全部”
        if (boatType != null && boatType == -1) {
            boatType = null;
        }
        List<EchartsDataVO> data = echartsService.getBoatSeatCountDistribution(boatType);
        return Result.success(data);
    }

    /**
     * 用户订单数量排行统计
     */
    @PostMapping("/getBoatOrderUserCount")
    public Result getBoatOrderUserCount(@RequestBody Map<String, Object> params) {
        // 从 RequestBody 中提取参数
        String startTime = Optional.ofNullable(params.get("startTime"))
                .map(Object::toString)
                .filter(s -> !s.isBlank())
                .orElse(null);
        String endTime = Optional.ofNullable(params.get("endTime"))
                .map(Object::toString)
                .filter(s -> !s.isBlank())
                .orElse(null);
        List<EchartsDataVO> data = echartsService.getBoatOrderUserCount(startTime, endTime);
        return Result.success(data);
    }

}
