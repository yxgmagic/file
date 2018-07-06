package com.zhichao.admin.controller.perforeValue;

import com.zhichao.core.base.controller.BaseController;
import com.zhichao.service.perforevalue.IPeSiteIndicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 站点排行统计控制层
 */
@Controller
@RequestMapping("/siterank")
public class SiteRankController extends BaseController {

    private String PREFIX = "/perforevalue/siterank/";

    @Autowired
    private IPeSiteIndicService peSiteIndicService;

    /**
     * 跳转到站点排行统计首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "SiteRank.html";
    }

    /**
     * list查询
     * TODO 未完成
     * @param date 日期
     * @return
     */
    @PostMapping(value = "list")
    public @ResponseBody Object list(@RequestParam(value = "date") String date){

        return null;
    }
}
