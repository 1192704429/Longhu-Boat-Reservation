package com.lake.booking.module.boat.controller;

import com.lake.booking.common.Result;
import com.lake.booking.module.boat.entity.Boat;
import com.lake.booking.module.boat.service.BoatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author wuming
 * @description: 游船信息Controller
 */
@RestController
@RequestMapping("/boat")
public class BoatController {

    @Autowired
    private BoatService boatService;

    /**
     * 分页查询游船信息列表
     */
    @PostMapping("/page")
    public Result page(@RequestParam(defaultValue = "1") Integer current,
                       @RequestParam(defaultValue = "10") Integer size,
                       @RequestBody(required = false) Boat entity) {
        if (entity == null) {
            entity = new Boat();
        }
        return Result.success(boatService.pageQuery(current, size, entity));
    }

    /**
     * 查询所有游船信息列表
     */
    @PostMapping("/list")
    public Result list(@RequestBody(required = false) Boat entity) {
        if (entity == null) {
            entity = new Boat();
        }
        List<Boat> list = boatService.listQuery(entity);
        return Result.success(list);
    }

    /**
     * 根据ID查询游船信息
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Long id) {
        Boat boat = boatService.getById(id);
        if (boat == null) {
            return Result.error("游船信息不存在");
        }
        return Result.success(boat);
    }

    /**
     * 新增游船信息
     */
    @PostMapping
    public Result save(@RequestBody Boat boat) {
        boat.setCreateTime(LocalDateTime.now());
        boolean success = boatService.save(boat);
        if (success) {
            return Result.success("新增成功", boat);
        }
        return Result.error("新增失败");
    }

    /**
     * 更新游船信息
     */
    @PutMapping
    public Result update(@RequestBody Boat boat) {
        boolean success = boatService.updateById(boat);
        if (success) {
            return Result.success("更新成功", boat);
        }
        return Result.error("更新失败");
    }

    /**
     * 根据ID删除游船信息
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        boolean success = boatService.removeById(id);
        if (success) {
            return Result.success("删除成功");
        }
        return Result.error("删除失败");
    }

    /**
     * 批量删除游船信息
     */
    @DeleteMapping("/batch")
    public Result deleteBatch(@RequestBody List<Long> ids) {
        boolean success = boatService.removeByIds(ids);
        if (success) {
            return Result.success("批量删除成功");
        }
        return Result.error("批量删除失败");
    }

}
