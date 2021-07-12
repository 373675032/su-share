package com.share.config;

import org.springframework.boot.web.server.ErrorPage;
import com.share.interceptor.LoginHandlerInterceptor;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName: MvcConfig
 * @Description: 配置MVC相关信息
 * @author: 莫提
 * @Date 18:58 2020/8/30
 * @Version: 1.0
 **/
@Configuration
public class MvcConfig implements WebMvcConfigurer, ErrorPageRegistrar {

    /**
     * 注册视图控制器
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 注册错误页面
        registry.addViewController("/password.html").setViewName("change-password");
        registry.addViewController("/400").setViewName("error/400");
        registry.addViewController("/401").setViewName("error/401");
        registry.addViewController("/404").setViewName("error/404");
        registry.addViewController("/500").setViewName("error/500");
    }

    /**
     * 配置错误页面
     */
    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        ErrorPage error400Page = new ErrorPage(HttpStatus.BAD_REQUEST, "/400");
        ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/401");
        ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404");
        ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500");
        registry.addErrorPages(error400Page,error401Page,error404Page,error500Page);
    }

    /**
     * 注册登录拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor())
                // 拦截的请求
                .addPathPatterns("/**")
                // 不拦截的请求（放行）
                .excludePathPatterns(
                        "/","/index","/login.html","/join.html","/home","/material","/materials","/admin/**",
                        "/login","/register",
                        "/error400Page","/error401Page","/error404Page","/error500Page",
                        "/**/front/**", "/asserts/**","/**/*.css", "/**/*.js", "/**/*.png ",
                        "/**/*.jpg", "/**/*.jpeg","/**/*.gif", "/**/fonts/*", "/**/*.svg");
    }
}
