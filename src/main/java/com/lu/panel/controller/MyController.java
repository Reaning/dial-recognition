package com.lu.panel.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Api(tags = "主页控制")
@Controller
public class MyController {
    @ApiOperation("主页控制接口")
    @GetMapping("/")
    public String index(){
        return "index";
    }
}
