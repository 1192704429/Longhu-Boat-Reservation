package com.lake.booking.module.order_comment.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lake.booking.module.order_comment.entity.OrderComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.lake.booking.vo.SelectVO;

import java.util.List;

/**
 * @author wuming
 * @description: 订单评价Mapper接口
 */
@Mapper
public interface OrderCommentMapper extends BaseMapper<OrderComment> {

    /**
     * 分页查询
     */
    List<OrderComment> pageQuery(@Param("entity") OrderComment entity,
                             @Param("offset") Long offset,
                             @Param("limit") Long limit);

    /**
     * 查询总数
     */
    Long countQuery(@Param("entity") OrderComment entity);

    /**
     * 列表查询（不分页）
     */
    List<OrderComment> listQuery(@Param("entity") OrderComment entity);

    /**
     * 根据ID查询
     */
    OrderComment getById(@Param("id") Long id);

    /**
     * 下拉选项查询
     */
    /**
     * 下拉数据：user
     */
    @Select("select username as label, id as value from user")
    List<SelectVO> listUser();

    /**
     * 下拉数据：boat
     */
    @Select("select boat_name as label, id as value from boat")
    List<SelectVO> listBoat();

    /**
     * 下拉数据：boat_order
     */
    @Select("select order_no as label, id as value from boat_order")
    List<SelectVO> listBoatOrder();

}
