package com.share.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * (Comment)实体类
 *
 * @author 莫提
 * @since 2020-11-01 18:34:40
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment implements Serializable {

    private static final long serialVersionUID = 892751159351319724L;

    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 评论者ID
     */
    private Integer userId;

    /**
     * 素材ID
     */
    private Integer materialId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评星数【0-5】
     */
    private Integer stars;

    /**
     * 被回复的评论ID，一级评论id为0
     */
    private Integer replyId;

    /**
     * 评论时间
     */
    private Date commentTime;

    /**
     * 是否置顶【0：否】【1：置顶】
     */
    private Integer isFirst;

}