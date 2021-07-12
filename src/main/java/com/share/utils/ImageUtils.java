package com.share.utils;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.IdUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.share.config.GiteeImgBedConfig;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: ImageUtils
 * @Description: 图片上传工具类
 * @author: 莫提
 * @date 2020/9/14 10:19
 * @Version: 1.0
 **/
public class ImageUtils {
    
    /**
     * 上传图片到Gitee，根据Flag类型的不同，上传到码云不同的文件夹
     *
     * flag：图片所属类型，取值如下：
     *      article：包含文章封面/文章内容中的图片
     *      project：包含公益项目封面/公益项目内容中的图片
     *      activity：包含活动封面/活动内容中的图片
     *      user：包含用户头像/上传认证
     *      other：包含其他上面没有涉及的图片
     *
     *  返回值：返回图片的网络地址
     */
    public static String upload(MultipartFile file, Integer userId)throws IOException {
        String trueFileName = file.getOriginalFilename();
        System.out.println("trueFileName = " + trueFileName);
        assert trueFileName != null;
        String suffix = trueFileName.substring(trueFileName.lastIndexOf("."));

        String paramImgFile = Base64.encode(file.getBytes());
        //转存到gitee
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("access_token", GiteeImgBedConfig.ACCESS_TOKEN);
        paramMap.put("message", GiteeImgBedConfig.CREATE_REPOS_MESSAGE);
        paramMap.put("content", paramImgFile);

        String targetDir = "";

        targetDir = userId + "/" + IdUtil.simpleUUID() + suffix;

        String requestUrl = String.format(GiteeImgBedConfig.CREATE_REPOS_URL, GiteeImgBedConfig.OWNER,
                GiteeImgBedConfig.REPO_NAME, targetDir);

        String resultJson = HttpUtil.post(requestUrl, paramMap);

        if (JSONUtil.parseObj(resultJson).getObj("commit") != null) {
            return GiteeImgBedConfig.GITPAGE_REQUEST_URL + targetDir;
        }
        return null;
    }

    /**
     * 上传图片到码云
     */
    public static String upload(File file, Integer userId)throws IOException {
        String trueFileName = file.getName();
        System.out.println("trueFileName = " + trueFileName);
        assert trueFileName != null;
        String suffix = trueFileName.substring(trueFileName.lastIndexOf("."));

        String paramImgFile = Base64.encode(file);
        //转存到gitee
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("access_token", GiteeImgBedConfig.ACCESS_TOKEN);
        paramMap.put("message", GiteeImgBedConfig.CREATE_REPOS_MESSAGE);
        paramMap.put("content", paramImgFile);

        String targetDir = "";

        targetDir = userId + "/" + IdUtil.simpleUUID() + suffix;

        String requestUrl = String.format(GiteeImgBedConfig.CREATE_REPOS_URL, GiteeImgBedConfig.OWNER,
                GiteeImgBedConfig.REPO_NAME, targetDir);

        String resultJson = HttpUtil.post(requestUrl, paramMap);

        if (JSONUtil.parseObj(resultJson).getObj("commit") != null) {
            return GiteeImgBedConfig.GITPAGE_REQUEST_URL + targetDir;
        }
        return null;
    }

    /**
     * 获得随机的头像
     */
    public static String getRandomImg(){
        String[] imgs = {
                "https://gitee.com/cn_moti/moti-img/raw/master/other/f64e8aef0fad4026b6f24ecf40b55bd8.jpg",
                "https://gitee.com/cn_moti/moti-img/raw/master/other/b040ec7facf44a10a9b34c401956d219.jpg",
                "https://gitee.com/cn_moti/moti-img/raw/master/other/81f3db577a1842e4aef298dec5339472.jpg",
                "https://gitee.com/cn_moti/moti-img/raw/master/other/771ba241fe164ea0a1a1a17068c69ab4.jpg",
                "https://gitee.com/cn_moti/moti-img/raw/master/other/fa962e4d45e04dda9c90afcb8e4ff46e.jpg"};
        return imgs[(int) (Math.random() * 5)];
    }

    /**
     * 获得随机的文章封面
     */
    public static String getRandomFace(){
        String[] imgs = {
                "https://gitee.com/cn_moti/moti-img/raw/master/other/9c5ae1c46f5d4bc2b62423ce20fb060d.png",
                "https://gitee.com/cn_moti/moti-img/raw/master/other/8b11eb7e6311402098beb55845d2ce6a.jpg",
                "https://gitee.com/cn_moti/moti-img/raw/master/other/213bb80e420d42048c38716568213b29.png",
                "https://gitee.com/cn_moti/moti-img/raw/master/other/8d56dd882b32466d80922c63c100cf3a.png",
                "https://gitee.com/cn_moti/moti-img/raw/master/other/d4eef3844c3643d08e4d4bc1cb41e508.jpg",
                "https://gitee.com/cn_moti/moti-img/raw/master/other/00b2ddd16bc94db2a4d54df17fc146a1.jpg",
                "https://gitee.com/cn_moti/moti-img/raw/master/other/732916df51924cb7bb0566afd42d9965.jpg",
                "https://gitee.com/cn_moti/moti-img/raw/master/other/43ff1d5db66e49a48e4b75ad18b3f888.jpg"

        };
        return imgs[(int) (Math.random() * 8)];
    }


    public static boolean checkFileSize(Long len, int size, String unit) {
        double fileSize = 0;
        if ("B".equals(unit.toUpperCase())) {
            fileSize = (double) len;
        } else if ("K".equals(unit.toUpperCase())) {
            fileSize = (double) len / 1024;
        } else if ("M".equals(unit.toUpperCase())) {
            fileSize = (double) len / 1048576;
        } else if ("G".equals(unit.toUpperCase())) {
            fileSize = (double) len / 1073741824;
        }
        if (fileSize > size) {
            return false;
        }
        return true;
    }
}
