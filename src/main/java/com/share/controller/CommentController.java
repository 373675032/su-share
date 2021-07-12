package com.share.controller;

import com.alibaba.fastjson.JSONObject;
import com.share.constant.ReadStatusType;
import com.share.entity.Comment;
import com.share.entity.Material;
import com.share.entity.Message;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @ClassName: CommentController
 * @Description: TODO
 * @author: 莫提
 * @date 2020/11/8 19:14
 * @Version: 1.0
 */
@Controller
public class CommentController extends BaseController {

    /**
     * 发表评论
     */
    @PostMapping("/addComment")
    @ResponseBody
    public String addComment(@RequestParam("starCount") Integer starCount,@RequestParam("comment") String comment,@RequestParam("mId")Integer mId){
        Comment build = Comment.builder()
                .materialId(mId).stars(starCount).content(comment).userId(loginUser.getId()).commentTime(new Date())
                .build();
        if (commentService.insert(build)) {
            result.setCode(200);
            Material material = materialService.getById(mId);
            if (loginUser.getId().intValue() != material.getUserId()){
                // 创建消息
                Message message = Message.builder()
                        .content("评论了你的素材《" + material.getName() + "》：" + comment).isRead(ReadStatusType.UN_READ).materialId(mId).messageTime(new Date())
                        .sentUserId(loginUser.getId()).sentUserImg(loginUser.getImg()).receiveUserId(material.getUserId())
                        .build();
                messageService.insert(message);
            }
        }else {
            result.setCode(500);
        }
        return JSONObject.toJSONString(result);
    }

    /**
     * 回复评论
     */
    @PostMapping("/replyComment")
    @ResponseBody
    public String replyComment(@RequestParam("content")String content,@RequestParam("replyId")Integer replyId,@RequestParam("mId")Integer mId){
        Comment comment = Comment.builder()
                .userId(loginUser.getId()).materialId(mId).replyId(replyId).content(content).commentTime(new Date())
                .build();
        if (commentService.insert(comment)) {
            Material material = materialService.getById(mId);
            if (loginUser.getId().intValue() != material.getUserId()){
                // 创建消息
                Message message = Message.builder()
                        .content("回复了你的评论《" + material.getName() + "》：" + content).isRead(ReadStatusType.UN_READ).materialId(mId).messageTime(new Date())
                        .sentUserId(loginUser.getId()).sentUserImg(loginUser.getImg()).receiveUserId(material.getUserId())
                        .build();
                messageService.insert(message);
            }
            result.setCode(200);
        }else {
            result.setCode(500);
        }
        return JSONObject.toJSONString(result);
    }

    /**
     * 删除评论
     */
    @PostMapping("/deleteComment")
    @ResponseBody
    public String deleteComment(@RequestParam("id") Integer id){
        // 删除一级评论
        if (commentService.deleteById(id)) {
            // 删除二级评论
            commentService.deleteByReplyId(id);
            result.setCode(200);
        }else {
            result.setCode(500);
        }
        return JSONObject.toJSONString(result);
    }
}
