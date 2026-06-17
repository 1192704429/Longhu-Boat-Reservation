package com.lake.booking.module.boat_time_config.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lake.booking.module.boat_time_config.entity.BoatTimeConfig;
import com.lake.booking.vo.SelectVO;

import java.util.List;

/**
 * @author wuming
 * @description: 游船预约时间配置Service接口
 */
public interface BoatTimeConfigService extends IService<BoatTimeConfig> {

    /**
     * 分页查询
     */
    IPage<BoatTimeConfig> pageQuery(Integer current, Integer size, BoatTimeConfig entity);

    /**
     * 列表查询（不分页）
     */
    List<BoatTimeConfig> listQuery(BoatTimeConfig entity);

    /**
     * 根据ID查询
     */
    BoatTimeConfig getById(Long id);

    /**
     * 下拉数据：boat
     */
    List<SelectVO> listBoat();

}
