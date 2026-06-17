package com.lake.booking.module.customer_service.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lake.booking.module.customer_service.entity.CustomerService;

import java.util.List;

/**
 * @author wuming
 * @description: 客服信息Service接口
 */
public interface CustomerServiceService extends IService<CustomerService> {

    /**
     * 分页查询
     */
    IPage<CustomerService> pageQuery(Integer current, Integer size, CustomerService entity);

    /**
     * 列表查询（不分页）
     */
    List<CustomerService> listQuery(CustomerService entity);

    /**
     * 根据ID查询
     */
    CustomerService getById(Long id);

}
