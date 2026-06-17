package com.lake.booking.module.boat_order.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lake.booking.module.boat_order.entity.BoatOrder;
import com.lake.booking.vo.SelectVO;

import java.util.List;

/**
 * @author wuming
 * @description: 游船预约订单Service接口
 */
public interface BoatOrderService extends IService<BoatOrder> {

    /**
     * 分页查询
     */
    IPage<BoatOrder> pageQuery(Integer current, Integer size, BoatOrder entity);

    /**
     * 列表查询（不分页）
     */
    List<BoatOrder> listQuery(BoatOrder entity);

    /**
     * 根据ID查询
     */
    BoatOrder getById(Long id);

    /**
     * 下拉数据：user
     */
    List<SelectVO> listUser();

    /**
     * 下拉数据：boat_time_config
     */
    List<SelectVO> listBoatTimeConfig();

    /**
     * 下拉数据：boat
     */
    List<SelectVO> listBoat();

}
