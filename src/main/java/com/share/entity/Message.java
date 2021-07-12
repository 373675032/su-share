package com.share.entity;

import java.util.Date;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * (Message)实体类
 *
 * @author 莫提
 * @since 2020-11-01 18:34:40
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message implements Serializable {

    private static final long serialVersionUID = -90095753076248080L;

    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 接收消息的用户ID
     */
    private Integer receiveUserId;

    /**
     * 发送消息的用户ID
     */
    private Integer sentUserId;

    /**
     * 发送消息的用户头像地址
     */
    private String sentUserImg;

    /**
     * 消息的内容
     */
    private String content;

    /**
     * 素材ID
     */
    private Integer materialId;

    /**
     * 是否已读：未读（0），已读（1）
     */
    private Integer isRead;

    /**
     * 时间
     */
    private Date messageTime;

}