package com.lake.booking.module.boat_time_config.controller;

import com.lake.booking.common.Result;
import com.lake.booking.module.boat_time_config.entity.BoatTimeConfig;
import com.lake.booking.module.boat_time_config.service.BoatTimeConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import com.lake.booking.vo.SelectVO;

/**
 * @author wuming
 * @description: 游船预约时间配置Controller
 */
@RestController
@RequestMapping("/boat_time_config")
public class BoatTimeConfigController {

    @Autowired
    private BoatTimeConfigService boatTimeConfigService;

    /**
     * 分页查询游船预约时间配置列表
     */
    @PostMapping("/page")
    public Result page(@RequestParam(defaultValue = "1") Integer current,
                       @RequestParam(defaultValue = "10") Integer size,
                       @RequestBody(required = false) BoatTimeConfig entity) {
        if (entity == null) {
            entity = new BoatTimeConfig();
        }
        return Result.success(boatTimeConfigService.pageQuery(current, size, entity));
    }

    /**
     * 查询所有游船预约时间配置列表
     */
    @PostMapping("/list")
    public Result list(@RequestBody(required = false) BoatTimeConfig entity) {
        if (entity == null) {
            entity = new BoatTimeConfig();
        }
        List<BoatTimeConfig> list = boatTimeConfigService.listQuery(entity);
        return Result.success(list);
    }

    /**
     * 根据ID查询游船预约时间配置
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Long id) {
        BoatTimeConfig boatTimeConfig = boatTimeConfigService.getById(id);
        if (boatTimeConfig == null) {
            return Result.error("游船预约时间配置不存在");
        }
        return Result.success(boatTimeConfig);
    }

    /**
     * 新增游船预约时间配置
     */
    @PostMapping
    public Result save(@RequestBody BoatTimeConfig boatTimeConfig) {
        boatTimeConfig.setCreateTime(LocalDateTime.now());
        boolean success = boatTimeConfigService.save(boatTimeConfig);
        if (success) {
            return Result.success("新增成功", boatTimeConfig);
        }
        return Result.error("新增失败");
    }

    /**
     * 更新游船预约时间配置
     */
    @PutMapping
    public Result update(@RequestBody BoatTimeConfig boatTimeConfig) {
        boolean success = boatTimeConfigService.updateById(boatTimeConfig);
        if (success) {
            return Result.success("更新成功", boatTimeConfig);
        }
        return Result.error("更新失败");
    }

    /**
     * 根据ID删除游船预约时间配置
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        boolean success = boatTimeConfigService.removeById(id);
        if (success) {
            return Result.success("删除成功");
        }
        return Result.error("删除失败");
    }

    /**
     * 批量删除游船预约时间配置
     */
    @DeleteMapping("/batch")
    public Result deleteBatch(@RequestBody List<Long> ids) {
        boolean success = boatTimeConfigService.removeByIds(ids);
        if (success) {
            return Result.success("批量删除成功");
        }
        return Result.error("批量删除失败");
    }

    /**
     * 下拉数据接口：boat
     */
    @GetMapping("/select/listBoat")
    public Result listBoat() {
        List<SelectVO> list = boatTimeConfigService.listBoat();
        return Result.success(list);
    }

}
