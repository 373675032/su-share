package com.share.vo;

import com.share.entity.Comment;
import com.share.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ClassName: CommentVo
 * @Description: TODO
 * @author: 莫提
 * @date 2020/11/8 20:07
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentVo {
    private Comment comment;
    private User user;
    private List<CommentVo> replyComments;
}
