package com.share.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.share.constant.ReadStatusType;
import com.share.entity.Message;
import com.share.entity.User;
import com.share.utils.ImageUtils;
import com.share.utils.LogUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

/**
 * @ClassName: UserController
 * @Description: 普通用户控制器
 * @author: 莫提
 * @date 2020/11/4 20:34
 * @Version: 1.0
 */
@Controller
public class UserController extends BaseController {
    
    /**
     * 上传头像
     */
    @PostMapping("/uploadAvatar")
    @ResponseBody
    public String uploadAvatar(@RequestParam("avatar") MultipartFile file) throws IOException {
        String url = ImageUtils.upload(file, loginUser.getId());
        loginUser.setImg(url);
        if (userService.update(loginUser)) {
            result.setCode(200);
            result.setData(url);
        } else {
            result.setCode(500);
        }
        return JSONObject.toJSONString(result);
    }
    
    /**
     * 修改资料
     */
    @PostMapping("/updateProfile")
    @ResponseBody
    public String updateProfile(@RequestParam("name") String name,@RequestParam("info") String info,@RequestParam("sex") Integer sex){
        User user = User.builder().name(name).build();
        List<User> users = userService.listUsers(user);
        // 用户名被占用
        if (users.size() > 0 && users.get(0).getId() != loginUser.getId().intValue()){
            result.setCode(201);
        }else {
            // 用户名正常
            loginUser.setName(name);
            loginUser.setInfo(info);
            loginUser.setSex(sex);
            if (userService.update(loginUser)) {
                result.setCode(200);
            }else {
                result.setCode(500);
            }
        }
        return JSONObject.toJSONString(result);
    }

    /**
     * 修改密码
     */
    @PostMapping("/changePassword")
    @ResponseBody
    public String changePassword(@RequestParam("old") String old,@RequestParam("password") String password){
        if (loginUser.getPassword().equals(old)){
            loginUser.setPassword(password);
            if (userService.update(loginUser)) {
                session.setAttribute("loginUser",loginUser);
                result.setCode(200);
            }else {
                result.setCode(500);
            }
        }else {
            result.setCode(201);
        }
        return JSONObject.toJSONString(result);
    }

}
