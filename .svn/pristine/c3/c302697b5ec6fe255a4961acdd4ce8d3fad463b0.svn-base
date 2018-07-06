package com.zhichao.admin.controller.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhichao.service.core.log.LogObjectHolder;
import com.zhichao.service.report.IReportsendService;
import com.zhichao.beans.guns.Reportsend;
import com.zhichao.core.base.controller.BaseController;

/**
 * 抄告信息反馈控制器
 *
 * @author fengshuonan
 * @Date 2018-03-22 09:52:48
 */
@Controller
@RequestMapping("/reportsend")
public class ReportsendController extends BaseController {

    private String PREFIX = "/report/reportsend/";

    @Autowired
    private IReportsendService reportsendService;

    /**
     * 跳转到抄告信息反馈首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "reportsend.html";
    }

    /**
     * 跳转到添加抄告信息反馈
     */
    @RequestMapping("/reportsend_add")
    public String reportsendAdd() {
        return PREFIX + "reportsend_add.html";
    }

    /**
     * 跳转到修改抄告信息反馈
     */
    @RequestMapping("/reportsend_update/{reportsendId}")
    public String reportsendUpdate(@PathVariable Integer reportsendId, Model model) {
        Reportsend reportsend = reportsendService.selectById(reportsendId);
        model.addAttribute("item",reportsend);
        LogObjectHolder.me().set(reportsend);
        return PREFIX + "reportsend_edit.html";
    }

    /**
     * 获取抄告信息反馈列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return reportsendService.selectList(null);
    }

    /**
     * 新增抄告信息反馈
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Reportsend reportsend) {
        reportsendService.insert(reportsend);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除抄告信息反馈
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer reportsendId) {
        reportsendService.deleteById(reportsendId);
        return SUCCESS_TIP;
    }

    /**
     * 修改抄告信息反馈
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Reportsend reportsend) {
        reportsendService.updateById(reportsend);
        return super.SUCCESS_TIP;
    }

    /**
     * 抄告信息反馈详情
     */
    @RequestMapping(value = "/detail/{reportsendId}")
    @ResponseBody
    public Object detail(@PathVariable("reportsendId") Integer reportsendId) {
        return reportsendService.selectById(reportsendId);
    }
}
