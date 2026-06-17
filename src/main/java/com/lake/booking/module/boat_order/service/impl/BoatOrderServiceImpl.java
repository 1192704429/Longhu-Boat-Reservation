package com.lake.booking.module.boat_order.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lake.booking.module.boat_order.entity.BoatOrder;
import com.lake.booking.module.boat_order.mapper.BoatOrderMapper;
import com.lake.booking.module.boat_order.service.BoatOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lake.booking.vo.SelectVO;

import java.util.List;

/**
 * @author wuming
 * @description: 游船预约订单Service实现类
 */
@Service
public class BoatOrderServiceImpl extends ServiceImpl<BoatOrderMapper, BoatOrder> implements BoatOrderService {

    @Autowired
    private BoatOrderMapper boatOrderMapper;

    @Override
    public IPage<BoatOrder> pageQuery(Integer current, Integer size, BoatOrder entity) {
        Long offset = (long) (current - 1) * size;
        Long limit = (long) size;
        List<BoatOrder> records = boatOrderMapper.pageQuery(entity, offset, limit);
        Long total = boatOrderMapper.countQuery(entity);

        Page<BoatOrder> page = new Page<>(current, size);
        page.setRecords(records);
        page.setTotal(total);
        return page;
    }

    @Override
    public List<BoatOrder> listQuery(BoatOrder entity) {
        return boatOrderMapper.listQuery(entity);
    }

    @Override
    public BoatOrder getById(Long id) {
        return boatOrderMapper.getById(id);
    }

    /**
     * 下拉数据：user
     */
    @Override
    public List<SelectVO> listUser() {
        return boatOrderMapper.listUser();
    }

    /**
     * 下拉数据：boat_time_config
     */
    @Override
    public List<SelectVO> listBoatTimeConfig() {
        return boatOrderMapper.listBoatTimeConfig();
    }

    /**
     * 下拉数据：boat
     */
    @Override
    public List<SelectVO> listBoat() {
        return boatOrderMapper.listBoat();
    }

}
