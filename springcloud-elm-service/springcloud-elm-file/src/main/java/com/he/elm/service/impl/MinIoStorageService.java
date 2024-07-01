package com.he.elm.service.impl;


import com.he.elm.common.constant.Constant;
import com.he.elm.config.MinIoProperties;
import com.he.elm.service.FileStorageService;
import io.minio.*;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@Slf4j
public class MinIoStorageService implements FileStorageService {
    @Resource
    private MinioClient minioClient;
    @Resource
    private MinIoProperties minIoProperties;


    @Override
    public String uploadFile(MultipartFile file) {
        log.info("MinIoStorageService====>uploadFile"+file.getOriginalFilename());
        try{
            //先判断存储桶是否存在
            Boolean bucketExitsBool = minioClient.bucketExists(BucketExistsArgs.builder().bucket(minIoProperties.getBucketName()).build());

            //不存在
            if(!bucketExitsBool){
                //创建桶
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(minIoProperties.getBucketName()).build());
            }
            //文件上传
            //记录上传时间
            String uploadTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

//            String uuid = UUID.randomUUID().toString();
            //组合文件名字
            //加一个/表示创建一个文件夹
            //这里是以日期进行二级区分
//            String fileName = uploadTime+"/"+file.getOriginalFilename();
            //我要以文件类型进行区分
            String fileName = file.getContentType()+"/"+file.getOriginalFilename();
            //上传到MinIo
            minioClient.putObject(PutObjectArgs
                    .builder().bucket(minIoProperties.getBucketName())
                    .object(fileName)
                    .stream(file.getInputStream(), file.getSize(), -1) //流式上传，-1,自动分片
                    .contentType(file.getContentType()) //文件上传的类型，如果不指定，那么每次访问时都要先下载文件
                    .build());

            //返回文件路径
            String url= Constant.CLOUD_FLARE_MINIO_SHOW_URL +"/"+ minIoProperties.getBucketName()+"/"+fileName;
            log.info("本次文件上传后获取路径=====>{}",url);
            return url;
        }catch (Exception e){
            e.printStackTrace();
            log.error("文件上传时出现错误！！！！");
            throw new RuntimeException("文件上传失败");
        }
    }
    //文件的下载
    @Override
    public String getObject(HttpServletRequest request, HttpServletResponse response, String url) {
        //        将传递的url 进行切割，获取到相对于桶的文件名
        String objectUrl = url.split(minIoProperties.getBucketName()+"/")[1];
        //        文件名
        String fileName = objectUrl.split("/")[1];
        // 设置响应的内容类型为文件类型
        response.setContentType("application/octet-stream");
        // 设置响应头，指定文件名
        response.setHeader("Content-Disposition", "attachment; filename=\"filename.extension\"");
        // 缓冲区大小,先将数据流暂存
        byte[] buffer = new byte[1024];
        int bytesRead;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try{
            inputStream = minioClient.getObject(GetObjectArgs.builder()
                    .bucket(minIoProperties.getBucketName())
                    .object(objectUrl)
                    .build());
         /*   // 将输入流的内容复制到一个文件中
            String filePath = "D://"+fileName;
            Files.copy(inputStream, Path.of(filePath),
                    StandardCopyOption.REPLACE_EXISTING);
            return "文件下载成功，地址为"+filePath;*/
            // 获取输出流
            outputStream = response.getOutputStream();
            // 将输入流中的数据写入输出流
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            inputStream.close();
            outputStream.close();

        }catch (Exception e){
            log.error("文件下载异常");
            e.printStackTrace();
        }
        return "文件下载失败";
    }

    @Override
    public void deleteFile(String url) {
        //        将传递的url 进行切割，获取到相对于桶的文件名
        String objectUrl = url.split(minIoProperties.getBucketName()+"/")[1];
        try {
            minioClient.removeObject(RemoveObjectArgs.builder()
                    .bucket(minIoProperties.getBucketName())
                    .object(objectUrl)
                    .build());

        } catch (Exception e) {
            e.printStackTrace();
            log.error("文件删除异常");
            throw new RuntimeException(url+"文件删除失败");
        }
    }
}
