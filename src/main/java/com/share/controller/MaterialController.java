package com.share.controller;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONObject;
import com.share.constant.*;
import com.share.entity.Kind;
import com.share.entity.Material;
import com.share.entity.Message;
import com.share.utils.AliyunOssUtil;
import com.share.utils.ImageUtils;
import com.share.utils.LogoUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: MaterialController
 * @Description: 素材控制器
 * @author: 莫提
 * @date 2020/11/2 22:20
 * @Version: 1.0
 */
@Controller
public class MaterialController extends BaseController {

    /**
     * 上传素材
     */
    @PostMapping("/publish")
    public String publish(Integer kindId,String name,String info,
                          @RequestParam("preview") MultipartFile[] preview,
                          @RequestParam("face") MultipartFile face,
                          @RequestParam("source") MultipartFile source) throws IOException {
        Integer userId = loginUser.getId();
        if (PermissionsType.NORMAL != loginUser.getStatus()){
            return "error/401";
        }
        // 添加水印
        File faceFile = setLogo(face);
        // 上传封面图片获得文件的地址
        String faceUrl = ImageUtils.upload(faceFile,userId);
        if (!StringUtils.isEmpty(faceUrl)){
            faceFile.delete();
        }
        // 保存预览图片的地址
        List<String> previewUrlList = new ArrayList<>();
        // 上传预览图
        for (MultipartFile file : preview) {
            if (file.getSize() != 0 && !"".equals(file.getOriginalFilename())){
                // 设置水印
                File tempFile = setLogo(file);
                String fileUrl = ImageUtils.upload(tempFile,userId);
                if (!StringUtils.isEmpty(fileUrl)){
                    tempFile.delete();
                }
                previewUrlList.add(fileUrl);
            }
        }
        // 获取预览图的字符数组
        String previewUrl = JSONObject.toJSONString(previewUrlList);
        // 上传源文件获得文件的地址
        String sourceUrl = AliyunOssUtil.upLoad(source, String.valueOf(userId));
        // 获取文件的大小
        String sizeInfo = AliyunOssUtil.getFileSize(source.getSize());
        Material material = Material.builder()
                .userId(userId).name(name).info(info).kindId(kindId).publishTime(new Date())
                .img(faceUrl).filePath(sourceUrl).imgs(previewUrl)
                .extension(AliyunOssUtil.getFileExtension(source)).size(sizeInfo)
                .status(TrashStatusType.NOT_IN).isChecked(CheckedStatusType.WAIT)
                .build();
        // 添加到数据库
        materialService.insert(material);
        Kind kind = kindService.getById(kindId);
        kind.setCount(kind.getCount() + 1);
        kindService.update(kind);
        return "redirect:/audit";
    }

    /**
     * 下载素材
     */
    @GetMapping("/downloadMaterial")
    @ResponseBody
    public String download(@RequestParam("id") Integer id) {
        if (loginUser.getStatus() == PermissionsType.BAN_UPLOAD_AND_DOWNLOAD){
            result.setCode(402);
        }else {
            // 获取素材信息
            Material material = materialService.getById(id);
            if (material != null){
                result.setCode(200);
                result.setUrl(material.getFilePath());
                // 更新下载量
                material.setDownloadCount(material.getDownloadCount()+1);
                materialService.update(material);
            }else {
                result.setCode(404);
                result.setMessage("资源不存在！");
            }
        }
        return JSONObject.toJSONString(result);
    }

    /**
     * 移入回收站
     */
    @PostMapping("/intoTrash")
    @ResponseBody
    public String intoTrash(@RequestParam("mId")Integer mId){
        Material material = materialService.getById(mId);
        material.setStatus(TrashStatusType.IS_IN);
        if (materialService.update(material)) {
            // 更新分类
            Kind kind = kindService.getById(material.getKindId());
            if (kind != null){
                kind.setCount(kind.getCount() -1);
                kindService.update(kind);
            }
            result.setCode(200);
        }else {
            result.setCode(500);
        }
        return JSONObject.toJSONString(result);
    }

    /**
     * 恢复素材，移出回收站
     */
    @PostMapping("/restoreMaterial")
    @ResponseBody
    public String restoreMaterial(@RequestParam("mId")Integer mId){
        Material material = materialService.getById(mId);
        material.setStatus(TrashStatusType.NOT_IN);
        if (materialService.update(material)) {
            // 更新分类
            Kind kind = kindService.getById(material.getKindId());
            if (kind != null){
                kind.setCount(kind.getCount() +1);
                kindService.update(kind);
            }
            result.setCode(200);
        }else {
            result.setCode(500);
        }
        return JSONObject.toJSONString(result);
    }

    /**
     * 删除素材
     */
    @PostMapping("/deleteMaterial")
    @ResponseBody
    public String deleteMaterial(@RequestParam("mId")Integer mId){
        // 获取需要删除的素材
        Material material = materialService.getById(mId);
        if (material.getUserId() != loginUser.getId().intValue()){
            // 他人删除
            if (loginUser.getRole() == RoleType.USER){
                result.setCode(401);
            }else {
                // 管理员删除
                if (materialService.deleteById(mId)) {
                    // 更新分类
                    Kind kind = kindService.getById(material.getKindId());
                    if (kind != null){
                        kind.setCount(kind.getCount() -1);
                        kindService.update(kind);
                    }
                    // 删除所有评论
                    commentService.deleteByMaterialId(mId);
                    String msg = "你的素材《"+material.getName()+"》已被管理员删除！";
                    // 创建消息
                    Message message = Message.builder()
                            .content(msg).isRead(ReadStatusType.UN_READ).messageTime(new Date())
                            .sentUserId(loginUser.getId()).sentUserImg(loginUser.getImg()).receiveUserId(material.getUserId())
                            .build();
                    messageService.insert(message);
                    result.setCode(200);
                }else {
                    result.setCode(500);
                }
            }
        }else {
            // 作者自己删除
            if (materialService.deleteById(mId)) {
                // 删除所有评论
                commentService.deleteByMaterialId(mId);
                result.setCode(200);
            }else {
                result.setCode(500);
            }
        }
        return JSONObject.toJSONString(result);

    }

    /**
     * 编辑素材
     */
    @PostMapping("editMaterial")
    @ResponseBody
    public String editMaterial(Material material){
        material.setIsChecked(CheckedStatusType.WAIT);
        if (materialService.update(material)) {
            result.setCode(200);
        }else {
            result.setCode(500);
        }
        return JSONObject.toJSONString(result);
    }

    /**
     * 为文件添加水印并替换
     */
    private File setLogo(MultipartFile img){
        String fileName = img.getOriginalFilename();
        String path = request.getSession().getServletContext().getRealPath("/");
        String realPath = path + IdUtil.simpleUUID() + fileName;
        File file = new File(realPath);
        try {
            // 保存图片
            img.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 设置水印
        File mark = LogoUtils.addWaterMark("SuShare|素材分享网", realPath);
        return mark;
    }
}
