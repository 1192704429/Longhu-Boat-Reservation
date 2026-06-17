package com.lake.booking.module.boat_order.controller;

import com.lake.booking.common.Result;
import com.lake.booking.module.boat_order.entity.BoatOrder;
import com.lake.booking.module.boat_order.service.BoatOrderService;
import com.lake.booking.module.boat_time_config.entity.BoatTimeConfig;
import com.lake.booking.module.boat_time_config.service.BoatTimeConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import com.lake.booking.vo.SelectVO;

/**
 * @author wuming
 * @description: 游船预约订单Controller
 */
@RestController
@RequestMapping("/boat_order")
public class BoatOrderController {

    @Autowired
    private BoatOrderService boatOrderService;
    @Autowired
    private BoatTimeConfigService boatTimeConfigService;

    /**
     * 分页查询游船预约订单列表
     */
    @PostMapping("/page")
    public Result page(@RequestParam(defaultValue = "1") Integer current,
                       @RequestParam(defaultValue = "10") Integer size,
                       @RequestBody(required = false) BoatOrder entity) {
        if (entity == null) {
            entity = new BoatOrder();
        }
        return Result.success(boatOrderService.pageQuery(current, size, entity));
    }

    /**
     * 查询所有游船预约订单列表
     */
    @PostMapping("/list")
    public Result list(@RequestBody(required = false) BoatOrder entity) {
        if (entity == null) {
            entity = new BoatOrder();
        }
        List<BoatOrder> list = boatOrderService.listQuery(entity);
        return Result.success(list);
    }

    /**
     * 根据ID查询游船预约订单
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Long id) {
        BoatOrder boatOrder = boatOrderService.getById(id);
        if (boatOrder == null) {
            return Result.error("游船预约订单不存在");
        }
        return Result.success(boatOrder);
    }

    /**
     * 新增游船预约订单
     */
    @Transactional(rollbackFor = Exception.class)
    @PostMapping
    public Result save(@RequestBody BoatOrder boatOrder) {
        if (boatOrder.getTimeConfigId() == null) {
            return Result.error("预约时段不能为空");
        }
        Integer appointCount = boatOrder.getAppointCount() == null ? 0 : boatOrder.getAppointCount();
        if (appointCount <= 0) {
            return Result.error("预约人数必须大于0");
        }
        BoatTimeConfig timeConfig = boatTimeConfigService.getById(boatOrder.getTimeConfigId());
        if (timeConfig == null) {
            return Result.error("预约时段不存在");
        }
        if (boatOrder.getStatus() == null) {
            boatOrder.setStatus(0); // 默认待确认，确认时再扣减库存
        }
        boatOrder.setCreateTime(LocalDateTime.now());
        boolean success = boatOrderService.save(boatOrder);
        if (success) {
            return Result.success("新增成功", boatOrder);
        }
        return Result.error("新增失败");
    }

    /**
     * 更新游船预约订单
     */
    @Transactional(rollbackFor = Exception.class)
    @PutMapping
    public Result update(@RequestBody BoatOrder boatOrder) {
        if (boatOrder.getId() == null) {
            return Result.error("订单ID不能为空");
        }
        BoatOrder oldOrder = boatOrderService.getById(boatOrder.getId());
        if (oldOrder == null) {
            return Result.error("订单不存在");
        }
        Integer oldStatus = oldOrder.getStatus();
        Integer newStatus = boatOrder.getStatus() == null ? oldStatus : boatOrder.getStatus();
        if (oldStatus == null) oldStatus = 0;

        // 已完成订单不允许变更为其他状态
        if (Integer.valueOf(3).equals(oldStatus) && !Integer.valueOf(3).equals(newStatus)) {
            return Result.error("已完成订单不允许修改状态");
        }

        // 状态不变，直接更新
        if (oldStatus.equals(newStatus)) {
            boolean success = boatOrderService.updateById(boatOrder);
            return success ? Result.success("更新成功", boatOrder) : Result.error("更新失败");
        }

        // 待确认 -> 已确认：扣减余量
        if (Integer.valueOf(0).equals(oldStatus) && Integer.valueOf(1).equals(newStatus)) {
            if (oldOrder.getTimeConfigId() == null) {
                return Result.error("预约时段不能为空");
            }
            BoatTimeConfig timeConfig = boatTimeConfigService.getById(oldOrder.getTimeConfigId());
            if (timeConfig == null) {
                return Result.error("预约时段不存在");
            }
            int oldCount = oldOrder.getAppointCount() == null ? 0 : oldOrder.getAppointCount();
            int currentSurplus = timeConfig.getSurplusCount() == null ? 0 : timeConfig.getSurplusCount();
            if (currentSurplus < oldCount) {
                return Result.error("确认失败：该时段余量不足");
            }
            timeConfig.setSurplusCount(currentSurplus - oldCount);
            boatTimeConfigService.updateById(timeConfig);
        } else if ((Integer.valueOf(0).equals(oldStatus) || Integer.valueOf(1).equals(oldStatus)) && Integer.valueOf(2).equals(newStatus)) {
            // 待确认/已确认 -> 已取消
            // 仅已确认订单回补余量（待确认未扣减）
            if (Integer.valueOf(1).equals(oldStatus) && oldOrder.getTimeConfigId() != null) {
                BoatTimeConfig timeConfig = boatTimeConfigService.getById(oldOrder.getTimeConfigId());
                if (timeConfig != null) {
                    int oldCount = oldOrder.getAppointCount() == null ? 0 : oldOrder.getAppointCount();
                    int currentSurplus = timeConfig.getSurplusCount() == null ? 0 : timeConfig.getSurplusCount();
                    int maxCount = timeConfig.getMaxCount() == null ? 0 : timeConfig.getMaxCount();
                    int recovered = currentSurplus + oldCount;
                    timeConfig.setSurplusCount(maxCount > 0 ? Math.min(recovered, maxCount) : recovered);
                    boatTimeConfigService.updateById(timeConfig);
                }
            }
        } else if (Integer.valueOf(1).equals(oldStatus) && Integer.valueOf(3).equals(newStatus)) {
            // 已确认 -> 已完成（允许）
        } else {
            return Result.error("非法状态流转");
        }

        boolean success = boatOrderService.updateById(boatOrder);
        if (success) {
            return Result.success("更新成功", boatOrder);
        }
        return Result.error("更新失败");
    }

    /**
     * 根据ID删除游船预约订单
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        BoatOrder oldOrder = boatOrderService.getById(id);
        if (oldOrder != null && Integer.valueOf(3).equals(oldOrder.getStatus())) {
            return Result.error("已完成订单不允许删除");
        }
        boolean success = boatOrderService.removeById(id);
        if (success) {
            return Result.success("删除成功");
        }
        return Result.error("删除失败");
    }

    /**
     * 批量删除游船预约订单
     */
    @DeleteMapping("/batch")
    public Result deleteBatch(@RequestBody List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return Result.error("请选择要删除的数据");
        }
        List<BoatOrder> orderList = boatOrderService.listByIds(ids);
        boolean hasCompleted = orderList.stream().anyMatch(item -> Integer.valueOf(3).equals(item.getStatus()));
        if (hasCompleted) {
            return Result.error("已完成订单不允许批量删除");
        }
        boolean success = boatOrderService.removeByIds(ids);
        if (success) {
            return Result.success("批量删除成功");
        }
        return Result.error("批量删除失败");
    }

    /**
     * 下拉数据接口：user
     */
    @GetMapping("/select/listUser")
    public Result listUser() {
        List<SelectVO> list = boatOrderService.listUser();
        return Result.success(list);
    }

    /**
     * 下拉数据接口：boat_time_config
     */
    @GetMapping("/select/listBoatTimeConfig")
    public Result listBoatTimeConfig() {
        List<SelectVO> list = boatOrderService.listBoatTimeConfig();
        return Result.success(list);
    }

    /**
     * 下拉数据接口：boat
     */
    @GetMapping("/select/listBoat")
    public Result listBoat() {
        List<SelectVO> list = boatOrderService.listBoat();
        return Result.success(list);
    }

}
