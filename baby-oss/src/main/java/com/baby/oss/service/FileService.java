package com.baby.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: Adorez
 * @Date: 2020/2/10 16:49
 * @Description:
 */
public interface FileService {
    /**
     * 上传文件至阿里云
     * @param file
     * @return
     */
    String upload(MultipartFile file);
}
