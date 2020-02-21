package com.baby.oss.service.impl;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.CannedAccessControlList;
import com.baby.oss.service.FileService;
import com.baby.oss.util.ConstantPropertiesUtil;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @Author: Adorez
 * @Date: 2020/2/10 16:50
 * @Description:
 */
@Service
public class FileServiceImpl implements FileService {

    @Override
    public String upload(MultipartFile file) {
        //获取阿里云存储相关常量
        String endPoint = ConstantPropertiesUtil.END_POINT;
        String fileHost = ConstantPropertiesUtil.FILE_HOST;
        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;
        String accessKeySecret= ConstantPropertiesUtil.ACCESS_KEY_SECRET;
        String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;

        String uploadUrl=null;
        try {
            //判断oss实例是否存在，如果不存在则创建，如果存在则获取
            OSSClient client=new OSSClient(endPoint,accessKeyId,accessKeySecret);
            if (client.doesBucketExist(bucketName)){
                //创建bucket
                client.createBucket(bucketName);
                //设置oss实例的访问权限：公共读
                client.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
            }
            //获取上传文件流
            InputStream inputStream = file.getInputStream();
            //构建日期路径:avatar/../文件名
            String filePath = new DateTime().toString("yyyy-MM-dd");
            //文件名:uuid.扩展名
            String original= file.getOriginalFilename();
            String fileName = UUID.randomUUID().toString();
            String fileType = original.substring(original.lastIndexOf("."));
            String newName = fileName + fileType;
            String fileUrl = fileHost + "/" + filePath + "/" + newName;
            //文件上传至阿里云
            client.putObject(bucketName,fileUrl,inputStream);
            //关闭OSSClient
            client.shutdown();
            //获取url地址
            uploadUrl="http://"+bucketName+"."+endPoint+"/"+fileUrl;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return uploadUrl;
    }
}
