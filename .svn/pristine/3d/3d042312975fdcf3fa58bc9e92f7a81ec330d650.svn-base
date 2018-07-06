package com.zhichao.admin.controller.compreAnalysis;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhichao.service.compreAnalysis.ISiteYoyAnalysisService;
import com.zhichao.core.base.controller.BaseController;

@Controller
@RequestMapping("/siteYoyAnalysis")
public class siteYoyAnalysisController extends BaseController {
    private String PREFIX = "/compreAnalysis/siteYoyAnalysis/";

    @Autowired
    ISiteYoyAnalysisService siteYoyAnalysisService;

    @RequestMapping("")
    public String index() {
        return PREFIX + "siteYoyAnalysis.html";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Object list(@RequestParam(value = "casetime", required = false) String casetime,
                       @RequestParam(value = "violationlevel", required = false) String violationlevel){
        List<Object> list = siteYoyAnalysisService.selList(casetime, violationlevel);
        return list;
    }

    @RequestMapping("listTable")
    @ResponseBody
    public Object listTable(@RequestParam(value = "casetime", required = false) String casetime,
                            @RequestParam(value = "violationlevel", required = false) String violationlevel){
        List<Map<String, Object>> lists = siteYoyAnalysisService.selListTable(casetime, violationlevel);
        return lists;
    }

    @RequestMapping("/carnumber")
    @ResponseBody
    public Object ListNumberSite(@RequestParam(value = "period", required = false) String period){
        Object list = siteYoyAnalysisService.selectNumberSite(period);
        return list;
    }

    @RequestMapping("/selectoverrun")
    @ResponseBody
    public Object ListOverRun(@RequestParam(value = "fctime", required = false) String fctime){
        Object list = siteYoyAnalysisService.selectOverRun(fctime);
        return list;
    }

    @RequestMapping("/selectcargotype")
    @ResponseBody
    public List<Map<String, Object>> ListCarGoType(@RequestParam(value = "fctime", required = false) String fctime){
        List<Map<String, Object>> list = siteYoyAnalysisService.selectCarGoType(fctime);
        return list;
    }

}
