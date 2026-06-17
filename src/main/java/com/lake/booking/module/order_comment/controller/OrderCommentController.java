package com.lake.booking.module.order_comment.controller;

import com.lake.booking.common.Result;
import com.lake.booking.module.order_comment.entity.OrderComment;
import com.lake.booking.module.order_comment.service.OrderCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import com.lake.booking.vo.SelectVO;

/**
 * @author wuming
 * @description: 订单评价Controller
 */
@RestController
@RequestMapping("/order_comment")
public class OrderCommentController {

    @Autowired
    private OrderCommentService orderCommentService;

    /**
     * 分页查询订单评价列表
     */
    @PostMapping("/page")
    public Result page(@RequestParam(defaultValue = "1") Integer current,
                       @RequestParam(defaultValue = "10") Integer size,
                       @RequestBody(required = false) OrderComment entity) {
        if (entity == null) {
            entity = new OrderComment();
        }
        return Result.success(orderCommentService.pageQuery(current, size, entity));
    }

    /**
     * 查询所有订单评价列表
     */
    @PostMapping("/list")
    public Result list(@RequestBody(required = false) OrderComment entity) {
        if (entity == null) {
            entity = new OrderComment();
        }
        List<OrderComment> list = orderCommentService.listQuery(entity);
        return Result.success(list);
    }

    /**
     * 根据ID查询订单评价
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Long id) {
        OrderComment orderComment = orderCommentService.getById(id);
        if (orderComment == null) {
            return Result.error("订单评价不存在");
        }
        return Result.success(orderComment);
    }

    /**
     * 新增订单评价
     */
    @PostMapping
    public Result save(@RequestBody OrderComment orderComment) {
        orderComment.setCreateTime(LocalDateTime.now());
        boolean success = orderCommentService.save(orderComment);
        if (success) {
            return Result.success("新增成功", orderComment);
        }
        return Result.error("新增失败");
    }

    /**
     * 更新订单评价
     */
    @PutMapping
    public Result update(@RequestBody OrderComment orderComment) {
        boolean success = orderCommentService.updateById(orderComment);
        if (success) {
            return Result.success("更新成功", orderComment);
        }
        return Result.error("更新失败");
    }

    /**
     * 根据ID删除订单评价
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        boolean success = orderCommentService.removeById(id);
        if (success) {
            return Result.success("删除成功");
        }
        return Result.error("删除失败");
    }

    /**
     * 批量删除订单评价
     */
    @DeleteMapping("/batch")
    public Result deleteBatch(@RequestBody List<Long> ids) {
        boolean success = orderCommentService.removeByIds(ids);
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
        List<SelectVO> list = orderCommentService.listUser();
        return Result.success(list);
    }

    /**
     * 下拉数据接口：boat
     */
    @GetMapping("/select/listBoat")
    public Result listBoat() {
        List<SelectVO> list = orderCommentService.listBoat();
        return Result.success(list);
    }

    /**
     * 下拉数据接口：boat_order
     */
    @GetMapping("/select/listBoatOrder")
    public Result listBoatOrder() {
        List<SelectVO> list = orderCommentService.listBoatOrder();
        return Result.success(list);
    }

}
