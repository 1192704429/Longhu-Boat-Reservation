package com.lake.booking.module.boat.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author wuming
 * @description: 游船信息实体类
 */
@Data
@TableName("boat")
public class Boat implements Serializable {

    private static final long serialVersionUID=1L;

    /** 主键ID */
    @TableId(type = IdType.AUTO)
    private Long id;
    /** 游船名称 */
    private String boatName;
    /** 游船封面 */
    private String boatCover;
    /** 游船轮播图 */
    private String boatImages;
    /** 游船类型 */
    private Integer boatType;
    /** 座位数 */
    private Integer seatCount;
    /** 运营开始时间 */
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime operateStartTime;
    /** 运营结束时间 */
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime operateEndTime;
    /** 游船介绍 */
    private String boatDesc;
    /** 状态 */
    private Integer status;
    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;


}
