package com.lake.booking.module.boat_time_config.entity;

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
 * @description: 游船预约时间配置实体类
 */
@Data
@TableName("boat_time_config")
public class BoatTimeConfig implements Serializable {

    private static final long serialVersionUID=1L;

    /** 主键ID */
    @TableId(type = IdType.AUTO)
    private Long id;
    /** 关联游船 */
    private Long boatId;
    /** 预约日期 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime appointDate;
    /** 预约时段 */
    private String appointTime;
    /** 最大预约数量 */
    private Integer maxCount;
    /** 剩余可预约数量 */
    private Integer surplusCount;
    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    /** 游船名称 */
    @TableField(exist = false)
    private String boatName;

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
