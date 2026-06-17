package com.lake.booking.module.boat_order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lake.booking.module.boat_order.entity.BoatOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.lake.booking.vo.SelectVO;

import java.util.List;

/**
 * @author wuming
 * @description: 游船预约订单Mapper接口
 */
@Mapper
public interface BoatOrderMapper extends BaseMapper<BoatOrder> {

    /**
     * 分页查询
     */
    List<BoatOrder> pageQuery(@Param("entity") BoatOrder entity,
                             @Param("offset") Long offset,
                             @Param("limit") Long limit);

    /**
     * 查询总数
     */
    Long countQuery(@Param("entity") BoatOrder entity);

    /**
     * 列表查询（不分页）
     */
    List<BoatOrder> listQuery(@Param("entity") BoatOrder entity);

    /**
     * 根据ID查询
     */
    BoatOrder getById(@Param("id") Long id);

    /**
     * 下拉选项查询
     */
    /**
     * 下拉数据：user
     */
    @Select("select username as label, id as value from user")
    List<SelectVO> listUser();

    /**
     * 下拉数据：boat_time_config
     */
    @Select("select appoint_time as label, id as value from boat_time_config")
    List<SelectVO> listBoatTimeConfig();

    /**
     * 下拉数据：boat
     */
    @Select("select boat_name as label, id as value from boat")
    List<SelectVO> listBoat();

}
