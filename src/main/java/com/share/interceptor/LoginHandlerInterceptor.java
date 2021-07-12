package com.share.interceptor;

import com.share.constant.RoleType;
import com.share.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.management.relation.RoleStatus;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName: LoginHandlerInterceptor
 * @Description: 登录拦截器:用于登录检查,权限控制
 * @Version: 1.0
 **/
public class LoginHandlerInterceptor implements HandlerInterceptor {

    /**
     * 在目标方式执行之前执行
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getServletPath();
        String[] array = new String[]{
                "/manage-user","/addKind","/updateKind","/deleteKind","/addLink","/deleteLink",
                "/dealMaterial","/updateNotice","/setting","/getAllUsers","/editUser"
        };
        List<String> adminPath = new ArrayList<>(Arrays.asList(array));
        User user = (User) request.getSession().getAttribute("loginUser");
        if (user == null){
            //未登录,返回登录页面
            response.sendRedirect("/su-share");
            return false;
        }else {
            // 如果是普通用户
            if (user.getRole() == RoleType.USER){
                // 访问管理员路径
                if (adminPath.contains(path)) {
                    response.sendRedirect("/su-share/error401Page");
                    return false;
                }
            }
            //已登录,放行
            return true;
        }
    }
}
