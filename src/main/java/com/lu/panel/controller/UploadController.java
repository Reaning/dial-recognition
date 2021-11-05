package com.lu.panel.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Api(tags = "上传文件控制类")
@Controller
public class UploadController {

    @Value("${upload.path}")
    private String filePath;
    @Value("${upload.static.path}")
    private String staticPath;

    @ApiOperation("上传文件接口")
    @PostMapping("/fileUpload")
    public String fileUpload(@ApiParam(value = "上传的文件") @RequestParam(value = "file") MultipartFile file,
                             Model model,
                             HttpServletRequest request) {
        if (file.isEmpty()) {
            System.out.println("文件为空空");
            return "index";
        }
        String fileName = file.getOriginalFilename();  // 文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
        fileName = UUID.randomUUID() + suffixName; // 新文件名
        System.out.println(filePath + fileName);
        File dest = new File(filePath + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String filename = staticPath + fileName;
        model.addAttribute("filename", filename);
        model.addAttribute("imgfile", filePath + fileName);
        return "index";
    }
}

