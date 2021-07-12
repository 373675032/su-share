package com.share.entity;

import java.util.Date;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * (Material)实体类
 *
 * @author 莫提
 * @since 2020-11-01 18:34:40
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Material implements Serializable {

    private static final long serialVersionUID = -96814918832956559L;

    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 上传者ID
     */
    private Integer userId;

    /**
     * 名称
     */
    private String name;

    /**
     * 分类ID【默认为0，代表未分类】
     */
    private Integer kindId;

    /**
     * 描述
     */
    private String info;

    /**
     * 封面图片地址
     */
    private String img;

    /**
     * 素材的阅览图
     */
    private String imgs;

    /**
     * 阅读量
     */
    private Integer readCount;

    /**
     * 下载量
     */
    private Integer downloadCount;

    /**
     * 发布时间
     */
    private Date publishTime;

    /**
     * 文件路径
     */
    private String filePath;

    /**
     * 源文件扩展名
     */
    private String extension;

    /**
     * 文件大小，MB为单位，保留一位小数
     */
    private String size;

    /**
     * 素材状态【0：回收站】【1：已发布】
     */
    private Integer status;

    /**
     * 审核状态【0：等待审核】【1：审核通过】【2：审核未通过】
     */
    private Integer isChecked;

    /**
     * 审核信息
     */
    private String checkedInfo;

}