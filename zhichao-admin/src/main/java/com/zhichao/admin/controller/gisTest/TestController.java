package com.zhichao.admin.controller.gisTest;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/gistest")
public class TestController {

    private String PREFIX = "/gistest/gistest/";


    @RequestMapping("")
    public String index() {
        return PREFIX + "gistest.html";
    }
}
