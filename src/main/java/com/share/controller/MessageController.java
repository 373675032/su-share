package com.share.controller;

import com.alibaba.fastjson.JSONObject;
import com.share.constant.ReadStatusType;
import com.share.entity.Message;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: MessageController
 * @Description: TODO
 * @author: 莫提
 * @date 2020/11/14 17:59
 * @Version: 1.0
 */
@RestController
public class MessageController extends BaseController {

    /**
     * 将某个消息设为已读
     */
    @PostMapping("/readMessage")
    public String readMessage(@RequestParam("id")Integer id){
        Message build = Message.builder().id(id).isRead(ReadStatusType.IS_READ).build();
        if (messageService.update(build)) {
            result.setCode(200);
            session.setAttribute("newMessage",0);
        }else {
            result.setCode(500);
        }
        return JSONObject.toJSONString(result);
    }

    /**
     * 将某个消息删除
     */
    @PostMapping("/deleteMessage")
    public String deleteMessage(@RequestParam("id")Integer id){
        if (messageService.deleteById(id)) {
            result.setCode(200);
        }else {
            result.setCode(500);
        }
        return JSONObject.toJSONString(result);
    }

    /**
     * 将用户全部消息设为已读
     */
    @PostMapping("/readAll")
    public String readAll(){
        if (messageService.updateAllRead(loginUser.getId())) {
            result.setCode(200);
            session.setAttribute("newMessage",0);
        }else {
            result.setCode(500);
        }
        return JSONObject.toJSONString(result);
    }

    /**
     * 将用户全部消息删除
     */
    @PostMapping("/deleteAll")
    public String deleteAll(){
        if (messageService.deleteByUserId(loginUser.getId()) > 0) {
            result.setCode(200);
        }else {
            result.setCode(500);
        }
        return JSONObject.toJSONString(result);
    }
}
