package com.share.controller;

import com.github.pagehelper.PageHelper;
import com.share.constant.CheckedStatusType;
import com.share.constant.ReadStatusType;
import com.share.constant.RoleType;
import com.share.constant.TrashStatusType;
import com.share.dto.ResponseResult;
import com.share.entity.Kind;
import com.share.entity.Material;
import com.share.entity.Message;
import com.share.entity.User;
import com.share.service.*;
import com.share.utils.LogUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @ClassName: BaseController
 * @Description: 控制器的基类
 * @author: 莫提
 * @date 2020/11/2 20:37
 * @Version: 1.0
 */
public class BaseController {

    @Autowired
    protected UserService userService;
    @Autowired
    protected KindService kindService;
    @Autowired
    protected FavoriteService favoriteService;
    @Autowired
    protected CommentService commentService;
    @Autowired
    protected MaterialService materialService;
    @Autowired
    protected NoticeService noticeService;
    @Autowired
    protected LinkService linkService;
    @Autowired
    protected MessageService messageService;

    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;
    protected User loginUser;
    protected List<Kind> kinds;

    protected ResponseResult result;

    /**
     * 在每个子类方法调用之前先调用，设置request,response,session这三个对象
     */
    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.session = request.getSession(true);
        loginUser = (User) session.getAttribute("loginUser");
        result = new ResponseResult();
        // 获取全部的分类
        kinds = kindService.listKinds();
        session.setAttribute("kinds",kinds);
        session.setAttribute("links",linkService.listLinks());
        if (loginUser == null){
            // 获取当前用户量
            session.setAttribute("userCount",userService.countUser());
        }else {
            // 获取我的收藏
            PageHelper.startPage(0,5);
            List<Material> favoriteMaterials = favoriteService.listFavoriteMaterial(loginUser.getId());
            session.setAttribute("favoriteMaterials",favoriteMaterials);
            if (loginUser.getRole() == RoleType.ADMIN){
                Material material = Material.builder().isChecked(CheckedStatusType.WAIT).status(TrashStatusType.NOT_IN).build();
                session.setAttribute("waitAudit",materialService.listMaterials(material).size());
            }
            Message build = Message.builder().receiveUserId(loginUser.getId()).isRead(ReadStatusType.UN_READ).build();
            int size = messageService.listMessages(build).size();
            if (size > 0) {
                session.setAttribute("newMessage",size);
            }
        }

    }
}
