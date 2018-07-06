package com.zhichao.admin.controller.compreAnalysis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhichao.service.compreAnalysis.ICarAreaCountService;
import com.zhichao.core.base.controller.BaseController;

/**
 *
 * @author yice
 * @Date 2018-01-02 08:48:59
 */
@Controller
@RequestMapping("/areaCaseCount")
public class areaCaseCountController extends BaseController {

    private String PREFIX = "/compreAnalysis/areaCaseCount/";

    @Autowired
    private ICarAreaCountService carAreaCountService;

    /**
     *
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "areaCaseCount.html";
    }

    @RequestMapping("/areaOverRun")
    @ResponseBody
    public Object areaOverRun(@RequestParam(value = "fctime", required = false) String fctime,
                              @RequestParam(value = "level", required = false) String level,
                              @RequestParam(value = "stationids[]", required = false) String[] stationids,
                              @RequestParam(value = "init", required = false) String init) {
        return carAreaCountService.selOverRunSite(level, fctime, stationids, init);
    }

}
