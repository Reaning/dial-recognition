package com.lu.panel.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Api(tags = "脚本控制类")
@Controller
public class ScriptController {

    @Value("${python.source}")
    private String pythonSource;

    @Value("${python.pointer}")
    private String pythonPointer;

    @Value("${python.orctest}")
    private String orcTest;

    @ApiOperation("脚本控制接口")
    @PostMapping("/getHandle")
    public String getPython(@ApiParam("图片地址") String img,
                            @ApiParam("识别类型") Integer option,
                            Model model) throws IOException, InterruptedException {
        model.addAttribute("filename",img);
        String scriptFile;
        String result;
        switch (option){
            case 1:
            {
                scriptFile = orcTest;
                result = getpy(scriptFile,img);
                break;
            }
            case 2:{
                scriptFile = pythonPointer;
	System.out.println(img);
                result = getpy(scriptFile,img);
                break;
            }
            default:
                result = "请选择识别类型！";
        }
        model.addAttribute("msg",result);
        return "index";
    }

    public String getpy(String scriptFile,String img) throws IOException, InterruptedException {
        StringBuilder str = new StringBuilder();
        StringBuilder sbError = new StringBuilder();
        String[] args = new String[]{pythonSource,scriptFile,img};
        Process proc = Runtime.getRuntime().exec(args);
        proc.waitFor();
        BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream(),"GBK"));
        BufferedReader isError = new BufferedReader(new InputStreamReader(proc.getErrorStream(),"gbk"));
        String line = null;
        String lineError = null;
        while ((line = in.readLine()) != null) {
            str.append(line);
            str.append("\n");
        }
        while ((lineError= isError.readLine()) != null) {
            sbError.append(lineError);
            sbError.append("\n");
        }
        System.out.println(sbError.toString());
        System.out.println(str.toString());
        in.close();
        return str.toString();
    }
}