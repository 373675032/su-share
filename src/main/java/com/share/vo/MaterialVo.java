package com.share.vo;

import com.share.entity.Kind;
import com.share.entity.Material;
import com.share.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ClassName: MaterialVo
 * @Description: TODO
 * @author: 莫提
 * @date 2020/11/4 8:33
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MaterialVo {
    private Material material;
    private User author;
    private Kind kind;
    private List<String> imgs;
    // 评论数
    private Integer comments;
    private boolean favorite;
    // 综合评分星星数
    private Integer starAvg;
}
