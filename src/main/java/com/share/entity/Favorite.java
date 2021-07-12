package com.share.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (Favorite)实体类
 *
 * @author 莫提
 * @since 2020-11-01 18:34:40
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Favorite implements Serializable {

    private static final long serialVersionUID = 629359600426615833L;

    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 素材ID
     */
    private Integer materialId;

}