package com.lake.booking.module.boat.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lake.booking.module.boat.entity.Boat;
import com.lake.booking.module.boat.mapper.BoatMapper;
import com.lake.booking.module.boat.service.BoatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wuming
 * @description: 游船信息Service实现类
 */
@Service
public class BoatServiceImpl extends ServiceImpl<BoatMapper, Boat> implements BoatService {

    @Autowired
    private BoatMapper boatMapper;

    @Override
    public IPage<Boat> pageQuery(Integer current, Integer size, Boat entity) {
        Long offset = (long) (current - 1) * size;
        Long limit = (long) size;
        List<Boat> records = boatMapper.pageQuery(entity, offset, limit);
        Long total = boatMapper.countQuery(entity);

        Page<Boat> page = new Page<>(current, size);
        page.setRecords(records);
        page.setTotal(total);
        return page;
    }

    @Override
    public List<Boat> listQuery(Boat entity) {
        return boatMapper.listQuery(entity);
    }

    @Override
    public Boat getById(Long id) {
        return boatMapper.getById(id);
    }

}
