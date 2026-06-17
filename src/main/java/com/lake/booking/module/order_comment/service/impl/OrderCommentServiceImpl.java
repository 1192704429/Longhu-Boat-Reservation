package com.lake.booking.module.order_comment.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lake.booking.module.order_comment.entity.OrderComment;
import com.lake.booking.module.order_comment.mapper.OrderCommentMapper;
import com.lake.booking.module.order_comment.service.OrderCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lake.booking.vo.SelectVO;

import java.util.List;

/**
 * @author wuming
 * @description: 订单评价Service实现类
 */
@Service
public class OrderCommentServiceImpl extends ServiceImpl<OrderCommentMapper, OrderComment> implements OrderCommentService {

    @Autowired
    private OrderCommentMapper orderCommentMapper;

    @Override
    public IPage<OrderComment> pageQuery(Integer current, Integer size, OrderComment entity) {
        Long offset = (long) (current - 1) * size;
        Long limit = (long) size;
        List<OrderComment> records = orderCommentMapper.pageQuery(entity, offset, limit);
        Long total = orderCommentMapper.countQuery(entity);

        Page<OrderComment> page = new Page<>(current, size);
        page.setRecords(records);
        page.setTotal(total);
        return page;
    }

    @Override
    public List<OrderComment> listQuery(OrderComment entity) {
        return orderCommentMapper.listQuery(entity);
    }

    @Override
    public OrderComment getById(Long id) {
        return orderCommentMapper.getById(id);
    }

    /**
     * 下拉数据：user
     */
    @Override
    public List<SelectVO> listUser() {
        return orderCommentMapper.listUser();
    }

    /**
     * 下拉数据：boat
     */
    @Override
    public List<SelectVO> listBoat() {
        return orderCommentMapper.listBoat();
    }

    /**
     * 下拉数据：boat_order
     */
    @Override
    public List<SelectVO> listBoatOrder() {
        return orderCommentMapper.listBoatOrder();
    }

}
