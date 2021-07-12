package com.share.entity;

import java.util.Date;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * (Notice)实体类
 *
 * @author 莫提
 * @since 2020-11-01 18:34:40
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notice implements Serializable {

    private static final long serialVersionUID = 491832705237047386L;

    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 公告内容
     */
    private String content;

    /**
     * 时间
     */
    private Date noticeTime;

}