package com.lake.booking.module.order_comment.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lake.booking.module.order_comment.entity.OrderComment;
import com.lake.booking.vo.SelectVO;

import java.util.List;

/**
 * @author wuming
 * @description: 订单评价Service接口
 */
public interface OrderCommentService extends IService<OrderComment> {

    /**
     * 分页查询
     */
    IPage<OrderComment> pageQuery(Integer current, Integer size, OrderComment entity);

    /**
     * 列表查询（不分页）
     */
    List<OrderComment> listQuery(OrderComment entity);

    /**
     * 根据ID查询
     */
    OrderComment getById(Long id);

    /**
     * 下拉数据：user
     */
    List<SelectVO> listUser();

    /**
     * 下拉数据：boat
     */
    List<SelectVO> listBoat();

    /**
     * 下拉数据：boat_order
     */
    List<SelectVO> listBoatOrder();

}
