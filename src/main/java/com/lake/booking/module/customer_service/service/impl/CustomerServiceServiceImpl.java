package com.lake.booking.module.customer_service.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lake.booking.module.customer_service.entity.CustomerService;
import com.lake.booking.module.customer_service.mapper.CustomerServiceMapper;
import com.lake.booking.module.customer_service.service.CustomerServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wuming
 * @description: 客服信息Service实现类
 */
@Service
public class CustomerServiceServiceImpl extends ServiceImpl<CustomerServiceMapper, CustomerService> implements CustomerServiceService {

    @Autowired
    private CustomerServiceMapper customerServiceMapper;

    @Override
    public IPage<CustomerService> pageQuery(Integer current, Integer size, CustomerService entity) {
        Long offset = (long) (current - 1) * size;
        Long limit = (long) size;
        List<CustomerService> records = customerServiceMapper.pageQuery(entity, offset, limit);
        Long total = customerServiceMapper.countQuery(entity);

        Page<CustomerService> page = new Page<>(current, size);
        page.setRecords(records);
        page.setTotal(total);
        return page;
    }

    @Override
    public List<CustomerService> listQuery(CustomerService entity) {
        return customerServiceMapper.listQuery(entity);
    }

    @Override
    public CustomerService getById(Long id) {
        return customerServiceMapper.getById(id);
    }

}
