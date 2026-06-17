package com.lake.booking.module.boat_time_config.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lake.booking.module.boat_time_config.entity.BoatTimeConfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.lake.booking.vo.SelectVO;

import java.util.List;

/**
 * @author wuming
 * @description: 游船预约时间配置Mapper接口
 */
@Mapper
public interface BoatTimeConfigMapper extends BaseMapper<BoatTimeConfig> {

    /**
     * 分页查询
     */
    List<BoatTimeConfig> pageQuery(@Param("entity") BoatTimeConfig entity,
                             @Param("offset") Long offset,
                             @Param("limit") Long limit);

    /**
     * 查询总数
     */
    Long countQuery(@Param("entity") BoatTimeConfig entity);

    /**
     * 列表查询（不分页）
     */
    List<BoatTimeConfig> listQuery(@Param("entity") BoatTimeConfig entity);

    /**
     * 根据ID查询
     */
    BoatTimeConfig getById(@Param("id") Long id);

    /**
     * 下拉选项查询
     */
    /**
     * 下拉数据：boat
     */
    @Select("select boat_name as label, id as value from boat")
    List<SelectVO> listBoat();

}
