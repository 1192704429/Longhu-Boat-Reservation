package com.lake.booking.module.customer_service.controller;

import com.lake.booking.common.Result;
import com.lake.booking.module.customer_service.entity.CustomerService;
import com.lake.booking.module.customer_service.service.CustomerServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author wuming
 * @description: 客服信息Controller
 */
@RestController
@RequestMapping("/customer_service")
public class CustomerServiceController {

    @Autowired
    private CustomerServiceService customerServiceService;

    /**
     * 分页查询客服信息列表
     */
    @PostMapping("/page")
    public Result page(@RequestParam(defaultValue = "1") Integer current,
                       @RequestParam(defaultValue = "10") Integer size,
                       @RequestBody(required = false) CustomerService entity) {
        if (entity == null) {
            entity = new CustomerService();
        }
        return Result.success(customerServiceService.pageQuery(current, size, entity));
    }

    /**
     * 查询所有客服信息列表
     */
    @PostMapping("/list")
    public Result list(@RequestBody(required = false) CustomerService entity) {
        if (entity == null) {
            entity = new CustomerService();
        }
        List<CustomerService> list = customerServiceService.listQuery(entity);
        return Result.success(list);
    }

    /**
     * 根据ID查询客服信息
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Long id) {
        CustomerService customerService = customerServiceService.getById(id);
        if (customerService == null) {
            return Result.error("客服信息不存在");
        }
        return Result.success(customerService);
    }

    /**
     * 新增客服信息
     */
    @PostMapping
    public Result save(@RequestBody CustomerService customerService) {
        customerService.setCreateTime(LocalDateTime.now());
        boolean success = customerServiceService.save(customerService);
        if (success) {
            return Result.success("新增成功", customerService);
        }
        return Result.error("新增失败");
    }

    /**
     * 更新客服信息
     */
    @PutMapping
    public Result update(@RequestBody CustomerService customerService) {
        boolean success = customerServiceService.updateById(customerService);
        if (success) {
            return Result.success("更新成功", customerService);
        }
        return Result.error("更新失败");
    }

    /**
     * 根据ID删除客服信息
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        boolean success = customerServiceService.removeById(id);
        if (success) {
            return Result.success("删除成功");
        }
        return Result.error("删除失败");
    }

    /**
     * 批量删除客服信息
     */
    @DeleteMapping("/batch")
    public Result deleteBatch(@RequestBody List<Long> ids) {
        boolean success = customerServiceService.removeByIds(ids);
        if (success) {
            return Result.success("批量删除成功");
        }
        return Result.error("批量删除失败");
    }

}
