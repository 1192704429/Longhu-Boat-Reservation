package com.lake.booking.module.boat_time_config.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lake.booking.module.boat_time_config.entity.BoatTimeConfig;
import com.lake.booking.module.boat_time_config.mapper.BoatTimeConfigMapper;
import com.lake.booking.module.boat_time_config.service.BoatTimeConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lake.booking.vo.SelectVO;

import java.util.List;

/**
 * @author wuming
 * @description: 游船预约时间配置Service实现类
 */
@Service
public class BoatTimeConfigServiceImpl extends ServiceImpl<BoatTimeConfigMapper, BoatTimeConfig> implements BoatTimeConfigService {

    @Autowired
    private BoatTimeConfigMapper boatTimeConfigMapper;

    @Override
    public IPage<BoatTimeConfig> pageQuery(Integer current, Integer size, BoatTimeConfig entity) {
        Long offset = (long) (current - 1) * size;
        Long limit = (long) size;
        List<BoatTimeConfig> records = boatTimeConfigMapper.pageQuery(entity, offset, limit);
        Long total = boatTimeConfigMapper.countQuery(entity);

        Page<BoatTimeConfig> page = new Page<>(current, size);
        page.setRecords(records);
        page.setTotal(total);
        return page;
    }

    @Override
    public List<BoatTimeConfig> listQuery(BoatTimeConfig entity) {
        return boatTimeConfigMapper.listQuery(entity);
    }

    @Override
    public BoatTimeConfig getById(Long id) {
        return boatTimeConfigMapper.getById(id);
    }

    /**
     * 下拉数据：boat
     */
    @Override
    public List<SelectVO> listBoat() {
        return boatTimeConfigMapper.listBoat();
    }

}
