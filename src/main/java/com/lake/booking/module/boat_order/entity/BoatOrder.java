package com.lake.booking.module.boat_order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author wuming
 * @description: 游船预约订单实体类
 */
@Data
@TableName("boat_order")
public class BoatOrder implements Serializable {

    private static final long serialVersionUID=1L;

    /** 主键ID */
    @TableId(type = IdType.AUTO)
    private Long id;
    /** 订单编号(gen:OR) */
    private String orderNo;
    /** 预约用户 */
    private Long userId;
    /** 关联游船 */
    private Long boatId;
    /** 关联预约时段 */
    private Long timeConfigId;
    /** 预约日期 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime appointDate;
    /** 预约时段 */
    private String appointTime;
    /** 预约人数 */
    private Integer appointCount;
    /** 订单状态 */
    private Integer status;
    /** 预约备注 */
    private String remark;
    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    /** 用户名 */
    @TableField(exist = false)
    private String username;
    /** 游船名称 */
    @TableField(exist = false)
    private String boatName;
    /** 预约时段 */
    @TableField(exist = false)
    private String appointTime2;

    // 范围查询字段
    /** 预约日期 开始 */
    @TableField(exist = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime appointDateStart;
    /** 预约日期 结束 */
    @TableField(exist = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime appointDateEnd;

}
