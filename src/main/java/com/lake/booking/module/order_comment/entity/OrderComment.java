package com.lake.booking.module.order_comment.entity;

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
 * @description: 订单评价实体类
 */
@Data
@TableName("order_comment")
public class OrderComment implements Serializable {

    private static final long serialVersionUID=1L;

    /** 主键ID */
    @TableId(type = IdType.AUTO)
    private Long id;
    /** 关联订单 */
    private Long orderId;
    /** 评价用户 */
    private Long userId;
    /** 关联游船 */
    private Long boatId;
    /** 评价分数 */
    private Integer score;
    /** 评价内容 */
    private String content;
    /** 评价图片 */
    private String commentImages;
    /** 审核状态 */
    private Integer auditStatus;
    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    /** 订单编号(gen:OR) */
    @TableField(exist = false)
    private String orderNo;
    /** 用户名 */
    @TableField(exist = false)
    private String username;
    /** 用户头像 */
    @TableField(exist = false)
    private String userAvatar;
    /** 游船名称 */
    @TableField(exist = false)
    private String boatName;


}
