package com.share.utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

/**
 * @ClassName: LogUtils
 * @Description: 打印日志工具类
 * @author: 莫提
 * @date 2020/9/21 20:13
 * @Version: 1.0
 **/
public class LogUtils {

    private static Logger logger;

    public static Logger getInstance(Class c){
        return logger =  LoggerFactory.getLogger(c);
    }

    private LogUtils(){}

    /**
     * 日志打印
     */
    public static void log(Logger log,String tip,Object obj){
        // 打印控制台
        System.err.println("===============================");
        System.err.println("时间戳：" + System.currentTimeMillis());
        System.err.println(tip);
        System.err.println(obj);
        System.err.println("===============================");
        // 打印日志
        log.info("===============================");
        System.err.println(tip);
        System.err.println(obj);
        log.info("===============================");
    }

    /**
     * 日志打印
     */
    public static Long log(String tip,Object obj){
        long current = System.currentTimeMillis();
        // 打印控制台
        System.err.println("===============================");
        System.err.println("时间戳：" + current);
        System.err.println(tip);
        System.err.println(obj);
        System.err.println("===============================");
        return current;
    }

    /**
     * 日志打印
     */
    public static Long log(String tip,Object obj,Long last){
        long current = System.currentTimeMillis();
        BigDecimal time = BigDecimal.valueOf(current - last).divide(BigDecimal.valueOf(1000), 3, BigDecimal.ROUND_UP);
        // 打印控制台
        System.err.println("===============================");
        System.err.println("时间戳：" + current);
        System.err.println("用时：" + time + "s");
        System.err.println(tip);
        System.err.println(obj);
        System.err.println("===============================");
        return current;
    }
}
