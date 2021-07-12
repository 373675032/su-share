package com.share.controller;

import com.alibaba.fastjson.JSONObject;
import com.share.constant.PermissionsType;
import com.share.constant.RoleType;
import com.share.constant.SexType;
import com.share.entity.User;
import com.share.utils.ImageUtils;
import com.share.utils.LogUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: LoginController
 * @Description: 登录注册控制器
 * @author: 莫提
 * @date 2020/11/5 18:19
 * @Version: 1.0
 */
@Controller
public class LoginController extends BaseController {

    /**
     * 登录后门，上线部署请删除此方法
     */
    @GetMapping("/admin")
    public String admin(@RequestParam("id") Integer id){
        // 获取用户放入Session
        session.setAttribute("loginUser",userService.getById(id));
        return "redirect:/index";
    }

    /**
     * 前往登录页面
     */
    @GetMapping("/login.html")
    public String login(){
        return "login";
    }

    /**
     * 前往登录页面
     */
    @GetMapping("/join.html")
    public String join() {
        LogUtils.log("请求注册",111);
        return "join";
    }

    /**
     * 登录
     */
    @PostMapping("/login")
    @ResponseBody
    public String login(@RequestParam("name")String name,@RequestParam("password")String password){
        User user = User.builder().name(name).password(password).build();
        LogUtils.log("请求登录用户",user);
        List<User> users = userService.listUsers(user);
        if (users.size() > 0){
            Integer id = users.get(0).getId();
            // 获取用户放入Session
            session.setAttribute("loginUser",userService.getById(id));
            result.setCode(200);
        }else {
            user.setPassword(null);
            List<User> list = userService.listUsers(user);
            if (list.size() > 0){
                // 密码错误
                result.setCode(201);
            }else {
                // 尚未注册
                result.setCode(202);
            }
        }
        return JSONObject.toJSONString(result);
    }

    /**
     * 注册
     */
    @PostMapping("/register")
    @ResponseBody
    public String register(@RequestParam("name")String name,@RequestParam("password")String password){
        LogUtils.log("请求注册用户",name + "-" +password);
        User user = User.builder().name(name).build();
        if (userService.listUsers(user).size() == 0) {
            // 正常的用户名，继续注册
            user.setPassword(password);
            user.setSex(SexType.MAN);
            user.setInfo("-");
            user.setImg(ImageUtils.getRandomImg());
            user.setRole(RoleType.USER);
            user.setStatus(PermissionsType.NORMAL);
            user.setRegisterTime(new Date());
            if (userService.insert(user)) {
                Integer id = user.getId();
                // 获取用户放入Session
                session.setAttribute("loginUser",userService.getById(id));
                result.setCode(200);
            }else {
                // 用户名已被占用
                result.setCode(500);
            }
        }else {
            result.setCode(201);
        }
        return JSONObject.toJSONString(result);
    }

    /**
     * 退出登录
     */
    @GetMapping("/logout")
    public String logout(){
        session.invalidate();
        return "redirect:/";
    }

}
