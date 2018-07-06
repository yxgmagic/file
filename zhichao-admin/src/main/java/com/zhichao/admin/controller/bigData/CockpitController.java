package com.zhichao.admin.controller.bigData;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/cockpit")
public class CockpitController {

    private String PREFIX = "/bigdata/cockpit/";


    @RequestMapping("")
    public String index(){
        return PREFIX + "cockpit.html";
    }

    @RequestMapping("/fly")
    public String fly(){
        return PREFIX + "cockpit-fly.html";
    }

    @RequestMapping("/json")
    public String json(@RequestParam(value = "mapCode", required = false) String mapCode, Model model){
        return PREFIX + "map/china-main-city/" + mapCode + ".json";
    }

    @RequestMapping("/JSON")
    public String JSON(@RequestParam(value = "code", required = false) String code, Model model){
        return PREFIX + "map/main-city/" + code + ".json";
    }

}
