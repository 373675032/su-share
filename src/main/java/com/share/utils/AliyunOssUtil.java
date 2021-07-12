package com.share.utils;

import cn.hutool.core.util.IdUtil;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.*;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.UUID;

/**
 * @ClassName: AliyunOssUtil
 * @Description: 阿里云对象存储OSS工具类
 **/
@Component
public class AliyunOssUtil {

    private static String File_URL;
    private static String bucketName = AliyunOssConfigConstant.BUCKE_NAME;
    private static String endPoint = AliyunOssConfigConstant.END_POINT;
    private static String accessKeyId = AliyunOssConfigConstant.AccessKey_ID;
    private static String accessKeySecret = AliyunOssConfigConstant.AccessKey_Secret;

    /**
     * 上传文件
     */
    public static String upLoad(MultipartFile file, String path) throws IOException {
        if (file == null || path == null) {
            return null;
        }
        OSSClient ossClient = new OSSClient(endPoint, accessKeyId, accessKeySecret);
        if (!ossClient.doesBucketExist(bucketName)) {
            ossClient.createBucket(bucketName);
            CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
            createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
            ossClient.createBucket(createBucketRequest);
        }
        String extension = AliyunOssUtil.getFileExtension(file);
        //设置文件路径
        String fileUrl = path + "/" + IdUtil.simpleUUID() + extension;
        File_URL = "https://" + bucketName + "." + endPoint + "/" + fileUrl;
        PutObjectResult result = ossClient.putObject(new PutObjectRequest(bucketName, fileUrl, file.getInputStream()));
        //上传文件
        ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
        return File_URL;
    }

    /**
     * 下载文件到输出流
     */
    public static boolean download(String path,OutputStream outputStream) throws IOException {

        String objectName = path.split("https://su-share.oss-cn-beijing.aliyuncs.com/")[1];
        System.err.println(objectName);
        OSSClient ossClient = new OSSClient(endPoint, accessKeyId, accessKeySecret);
        if (!ossClient.doesBucketExist(bucketName)) {
            ossClient.createBucket(bucketName);
            CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
            createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
            ossClient.createBucket(createBucketRequest);
        }
        OSSObject ossObject = ossClient.getObject(bucketName, objectName);
        IOUtils.copy(ossObject.getObjectContent(),outputStream);
        // 关闭OSSClient。
        ossClient.shutdown();
        return true;
    }

    /**
     * 删除文件
     */
    public static boolean delete(String path) throws IOException {
        String objectName = path.split("https://su-share.oss-cn-beijing.aliyuncs.com/")[1];
        System.err.println(objectName);
        OSSClient ossClient = new OSSClient(endPoint, accessKeyId, accessKeySecret);
        if (!ossClient.doesBucketExist(bucketName)) {
            ossClient.createBucket(bucketName);
            CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
            createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
            ossClient.createBucket(createBucketRequest);
        }
        ossClient.deleteObject(bucketName, objectName);
        // 关闭OSSClient。
        ossClient.shutdown();
        return true;
    }

    /**
     * 修改文件名
     */
    public static boolean reName(String path) throws IOException {
        String objectName = path.split("https://su-share.oss-cn-beijing.aliyuncs.com/")[1];
        System.err.println(objectName);
        OSSClient ossClient = new OSSClient(endPoint, accessKeyId, accessKeySecret);
        if (!ossClient.doesBucketExist(bucketName)) {
            ossClient.createBucket(bucketName);
            CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
            createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
            ossClient.createBucket(createBucketRequest);
        }
        // 关闭OSSClient。
        ossClient.shutdown();
        return true;
    }

    /**
     * 获取文件的扩展名
     */
    public static String getFileExtension(MultipartFile file){
        String filename = file.getOriginalFilename();
        String extension = filename.substring(filename.lastIndexOf("."));
        return extension;
    }
    
    /**
     * 获取文件的大小描述
     */
    public static String getFileSize(long size) {
        StringBuffer bytes = new StringBuffer();
        DecimalFormat format = new DecimalFormat("###.0");
        if (size >= 1024 * 1024 * 1024) {
            double i = (size / (1024.0 * 1024.0 * 1024.0));
            bytes.append(format.format(i)).append("GB");
        }
        else if (size >= 1024 * 1024) {
            double i = (size / (1024.0 * 1024.0));
            bytes.append(format.format(i)).append("MB");
        }
        else if (size >= 1024) {
            double i = (size / (1024.0));
            bytes.append(format.format(i)).append("KB");
        }
        else if (size < 1024) {
            if (size <= 0) {
                bytes.append("0B");
            }
            else {
                bytes.append((int) size).append("B");
            }
        }
        return bytes.toString();
    }
    

}
