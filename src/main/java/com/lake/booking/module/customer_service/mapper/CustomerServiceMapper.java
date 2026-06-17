package com.lake.booking.module.customer_service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lake.booking.module.customer_service.entity.CustomerService;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wuming
 * @description: 客服信息Mapper接口
 */
@Mapper
public interface CustomerServiceMapper extends BaseMapper<CustomerService> {

    /**
     * 分页查询
     */
    List<CustomerService> pageQuery(@Param("entity") CustomerService entity,
                             @Param("offset") Long offset,
                             @Param("limit") Long limit);

    /**
     * 查询总数
     */
    Long countQuery(@Param("entity") CustomerService entity);

    /**
     * 列表查询（不分页）
     */
    List<CustomerService> listQuery(@Param("entity") CustomerService entity);

    /**
     * 根据ID查询
     */
    CustomerService getById(@Param("id") Long id);

}
