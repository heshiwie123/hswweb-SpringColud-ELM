package com.he.elm.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public interface FileStorageService {
    /**
     * 上传文件
     * @param file 文件
     * @return 文件路径
     */
    public String uploadFile(MultipartFile file);

    /**
     * 下载文件
     * @param request
     * @param response
     * @param url
     * @return
     */
    public String getObject(HttpServletRequest request, HttpServletResponse response, String url);

    /**
     * 删除文件
     * @param url
     */
    public void deleteFile(String url) ;
}
