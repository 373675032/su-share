package com.share.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;

/**
 * @ClassName: LogoUtils
 * @Description: 水印工具类
 * @author: 莫提
 * @date 2020/11/29 20:13
 * @Version: 1.0
 */
public class LogoUtils {

    public static File addWaterMark(String mark,String tarImgPath) {
        // 水印颜色
        Color color = new Color(255, 52, 52, 255);
        //水印字体
        Font font = new Font("微软雅黑", Font.BOLD, 45);
        //水印内容
        String waterMarkContent="SU-SHARE/素材分享网";
        try {
            //存储目标路径
            File file = new File(tarImgPath);
            BufferedImage buImage = ImageIO.read(file);
            //图片宽
            int width = buImage.getWidth();
            //图片高
            int height = buImage.getHeight();
            //添加水印
            BufferedImage bufferedImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_BGR);
            Graphics2D g = bufferedImage.createGraphics();
            g.drawImage(buImage, 0, 0, width, height, null);
            //水印颜色
            g.setColor(color);
            //水印字体
            g.setFont(font);
            //这是一个计算水印位置的函数，可以根据需求添加
            int x = width - 2 * getWatermarkLength(mark, g);
            int y = height - getWatermarkLength(mark, g);
            //水印位置
            g.drawString(mark,x,y);
            //释放资源
            g.dispose();
            FileOutputStream outImgStream = new FileOutputStream(tarImgPath);
            ImageIO.write(bufferedImage, "jpg", outImgStream);
            outImgStream.flush();
            outImgStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            return new File(tarImgPath);
        }
    }

    private static int getWatermarkLength(String waterMarkContent, Graphics2D g) {
        return g.getFontMetrics(g.getFont()).charsWidth(waterMarkContent.toCharArray(), 0, waterMarkContent.length());
    }
}
