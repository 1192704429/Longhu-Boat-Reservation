package com.lake.booking.module.admin.entity;

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
 * @description: 管理员实体类
 */
@Data
@TableName("admin")
public class Admin implements Serializable {

    private static final long serialVersionUID=1L;

    /** 主键ID */
    @TableId(type = IdType.AUTO)
    private Long id;
    /** 管理员账号 */
    private String username;
    /** 密码 */
    private String password;
    /** 管理员昵称 */
    private String nickname;
    /** 联系电话 */
    private String phone;
    /** 邮箱 */
    private String email;
    /** 角色 */
    private String role;
    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;


}
