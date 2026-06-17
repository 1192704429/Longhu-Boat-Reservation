package com.lake.booking.module.boat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lake.booking.module.boat.entity.Boat;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wuming
 * @description: 游船信息Mapper接口
 */
@Mapper
public interface BoatMapper extends BaseMapper<Boat> {

    /**
     * 分页查询
     */
    List<Boat> pageQuery(@Param("entity") Boat entity,
                             @Param("offset") Long offset,
                             @Param("limit") Long limit);

    /**
     * 查询总数
     */
    Long countQuery(@Param("entity") Boat entity);

    /**
     * 列表查询（不分页）
     */
    List<Boat> listQuery(@Param("entity") Boat entity);

    /**
     * 根据ID查询
     */
    Boat getById(@Param("id") Long id);

}
