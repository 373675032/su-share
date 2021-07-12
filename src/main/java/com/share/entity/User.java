package com.share.entity;

import java.util.Date;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * (User)实体类
 *
 * @author 莫提
 * @since 2020-11-01 18:34:40
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements Serializable {

    private static final long serialVersionUID = 728157421469726075L;

    /**
     * 主键ID
     */
    private Integer id;
    /**
     * 用户名
     */
    private String name;
    /**
     * 密码
     */
    private String password;

    /**
     * 性别【0：女】【1：男】
     */
    private Integer sex;

    /**
     * 头像地址
     */
    private String img;

    /**
     * 个人说明
     */
    private String info;

    /**
     * 封禁状态【1：正常】【2：不可上传】【3：不可上传和下载】
     */
    private Integer status;

    /**
     * 用户角色【0：普通用户】【1：管理员】
     */
    private Integer role;

    /**
     * 注册时间
     */
    private Date registerTime;

}