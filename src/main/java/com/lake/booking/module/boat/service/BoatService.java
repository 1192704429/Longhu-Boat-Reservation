package com.lake.booking.module.boat.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lake.booking.module.boat.entity.Boat;

import java.util.List;

/**
 * @author wuming
 * @description: 游船信息Service接口
 */
public interface BoatService extends IService<Boat> {

    /**
     * 分页查询
     */
    IPage<Boat> pageQuery(Integer current, Integer size, Boat entity);

    /**
     * 列表查询（不分页）
     */
    List<Boat> listQuery(Boat entity);

    /**
     * 根据ID查询
     */
    Boat getById(Long id);

}
