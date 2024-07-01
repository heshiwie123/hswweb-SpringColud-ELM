package com.he.elm.controller;


import com.he.elm.common.dtos.ResponseResult;
import com.he.elm.config.MinIoProperties;
import com.he.elm.service.FileStorageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

@RestController
@RequestMapping("/file")
@Tag(name = "文件上传测试接口")
public class FileStorageController {
    @Resource
    private FileStorageService storageService;

    @PutMapping("/uploadFile")
    @Operation(summary = "上传图片")
    public ResponseResult uploadFile(MultipartFile file){
        String url = storageService.uploadFile(file);
        HashMap<String, String> resultMap = new HashMap<>();
        resultMap.put("URL",url);
        return new ResponseResult<>(200,"上传成功！将返回图片路径",resultMap);
    }
}
