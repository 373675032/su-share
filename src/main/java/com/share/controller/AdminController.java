package com.share.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.share.constant.ReadStatusType;
import com.share.entity.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * @ClassName: AdminController
 * @Description: 管理员控制层
 * @author: 莫提
 * @date 2020/11/29 13:54
 * @Version: 1.0
 */
@Controller
public class AdminController extends BaseController {

    /**
     * 前往用户管理页面
     */
    @GetMapping("/manage-user")
    public String manageUsers(){
        return "admin/users";
    }

    /**
     * 前往 站点设置
     */
    @GetMapping("/setting")
    public String setting(Map<String,Object> map){
        // 获取最新公告
        Notice notice = noticeService.getById(1);
        // 获取所有链接
        List<Link> links = linkService.listLinks();
        // 获取全部分类
        List<Kind> kinds = kindService.listKinds();
        map.put("notice",notice);
        map.put("links",links);
        map.put("kinds",kinds);
        return "admin/setting";
    }

    /**
     * 更新公告
     */
    @PostMapping("/updateNotice")
    @ResponseBody
    public String updateNotice(@RequestParam("content") String content){
        Notice notice = Notice.builder().id(1).content(content).noticeTime(new Date()).build();
        if (noticeService.update(notice)) {
            result.setCode(200);
        }else {
            result.setCode(500);
        }
        return JSONObject.toJSONString(result);
    }

    /**
     * 添加链接或菜单
     */
    @PostMapping("/addLink")
    public String addLink(Integer type,String name,String url){
        Link link = Link.builder().name(name).targetUrl(url).type(type).build();
        if (linkService.insert(link)) {
            result.setCode(200);
        }else {
            result.setCode(500);
        }
        return JSONObject.toJSONString(result);
    }

    /**
     * 删除链接
     */
    @PostMapping("/deleteLink")
    public String deleteLink(@RequestParam("id")Integer id){
        if (linkService.deleteById(id)) {
            result.setCode(200);
        }else {
            result.setCode(500);
        }
        return JSONObject.toJSONString(result);
    }

    /**
     * 添加分类
     */
    @PostMapping("/addKind")
    @ResponseBody
    public String addKind(@RequestParam("name") String name){
        // 判断分类是否存在
        if (kindService.listKinds(Kind.builder().name(name).build()).size() > 0) {
            result.setCode(201);
        }else {
            Kind kind = Kind.builder().name(name).count(0).build();
            if (kindService.insert(kind)) {
                result.setCode(200);
            }else {
                result.setCode(500);
            }
        }
        return JSONObject.toJSONString(result);
    }

    /**
     * 更新分类
     */
    @PostMapping("/updateKind")
    @ResponseBody
    public String updateKind(@RequestParam("id") Integer id,@RequestParam("name") String name){
        // 判断分类是否存在
        if (kindService.listKinds(Kind.builder().name(name).build()).size() > 0) {
            result.setCode(201);
        }else {
            Kind kind = Kind.builder().name(name).id(id).build();
            if (kindService.update(kind)) {
                result.setCode(200);
            }else {
                result.setCode(500);
            }
        }
        return JSONObject.toJSONString(result);
    }

    /**
     * 删除分类
     */
    @PostMapping("/deleteKind")
    @ResponseBody
    public String deleteKind(@RequestParam("id") Integer id){
        if (kindService.deleteById(id)) {
            result.setCode(200);
        }else {
            result.setCode(500);
        }
        return JSONObject.toJSONString(result);
    }

    /**
     * 处理审核素材
     */
    @PostMapping("/dealMaterial")
    @ResponseBody
    public String dealMaterial(@RequestParam("id")Integer id,@RequestParam("deal")Integer deal,@RequestParam("info")String info){
        Material material = materialService.getById(id);
        material.setCheckedInfo(info);
        material.setIsChecked(deal);
        if (materialService.update(material)) {
            String msg = deal == 1 ? "你的素材《"+material.getName()+"》已经通过审核！":"你的素材《"+material.getName()+"》未通过审核："+info;
            // 创建消息
            Message message = Message.builder()
                    .content(msg).isRead(ReadStatusType.UN_READ).materialId(id).messageTime(new Date())
                    .sentUserId(loginUser.getId()).sentUserImg(loginUser.getImg()).receiveUserId(material.getUserId())
                    .build();
            messageService.insert(message);
            result.setCode(200);
        }else {
            result.setCode(500);
        }
        return JSONObject.toJSONString(result);
    }

    /**
     * 获取全部用户
     */
    @GetMapping("/getAllUsers")
    @ResponseBody
    public String getAllUsers(Integer page,Integer rows,String searchField,String searchString,String searchOper){
        Map<String,Object> map = new HashMap<>();
        PageHelper.startPage(page, rows);
        List<User> users = null;
        if (StringUtils.isEmpty(searchOper)){
            users = userService.listUsers();
        }else {
            if ("id".equals(searchField)){
                users = new ArrayList<>();
                User user = userService.getById(Integer.valueOf(searchString));
                users.add(user);
            }else if ("role".equals(searchField)){
                Integer role = 0;
                if ("管理员".equals(searchString)){
                    role = 1;
                }
                users = userService.listUsers(User.builder().role(role).build());
            }else if ("status".equals(searchField)){
                Integer status = 1;
                if ("不可上传".equals(searchString)){
                    status = 2;
                }else if ("不可上传&不可下载".equals(searchString)){
                    status = 3;
                }
                users = userService.listUsers(User.builder().status(status).build());
            }else if ("name".equals(searchField)){
                users = userService.listUsers(User.builder().name(searchString).build());
            }
        }
        // 查询所有用户
        PageInfo<User> pageInfo = new PageInfo<>(users);
        // 将查询结果放入map
        map.put("rows",users);
        // 总页数
        map.put("total",pageInfo.getPages());
        // 总条数
        map.put("records",pageInfo.getTotal());
        // 返回结果
        return JSONObject.toJSONString(map);
    }

    /**
     * 编辑用户
     */
    @PostMapping("/editUser")
    public void editUser(User user){
        if (userService.update(user)) {
            String msg = "你的个人信息已被管理员修改！";
            // 创建消息
            Message message = Message.builder()
                    .content(msg).isRead(ReadStatusType.UN_READ).messageTime(new Date())
                    .sentUserId(loginUser.getId()).sentUserImg("https://s3.ax1x.com/2020/11/25/Dd9Ic4.png").receiveUserId(user.getId())
                    .build();
            messageService.insert(message);
        }
    }
}
