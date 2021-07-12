package com.share.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * (Link)实体类
 *
 * @author 莫提
 * @since 2020-11-01 18:34:40
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Link implements Serializable {

    private static final long serialVersionUID = 989722474076737737L;

    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 链接地址
     */
    private String targetUrl;

    /**
     * 类型：顶部菜单（1），友情链接（2）
     */
    private Integer type;

}