package com.zhichao.admin.controller.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhichao.service.core.log.LogObjectHolder;
import com.zhichao.service.report.IReportcontentService;
import com.zhichao.beans.guns.Reportcontent;
import com.zhichao.core.base.controller.BaseController;

/**
 * 抄告报表关联控制器
 *
 * @author fengshuonan
 * @Date 2018-03-22 09:54:28
 */
@Controller
@RequestMapping("/reportcontent")
public class ReportcontentController extends BaseController {

    private String PREFIX = "/report/reportcontent/";

    @Autowired
    private IReportcontentService reportcontentService;

    /**
     * 跳转到抄告报表关联首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "reportcontent.html";
    }

    /**
     * 跳转到添加抄告报表关联
     */
    @RequestMapping("/reportcontent_add")
    public String reportcontentAdd() {
        return PREFIX + "reportcontent_add.html";
    }

    /**
     * 跳转到修改抄告报表关联
     */
    @RequestMapping("/reportcontent_update/{reportcontentId}")
    public String reportcontentUpdate(@PathVariable Integer reportcontentId, Model model) {
        Reportcontent reportcontent = reportcontentService.selectById(reportcontentId);
        model.addAttribute("item",reportcontent);
        LogObjectHolder.me().set(reportcontent);
        return PREFIX + "reportcontent_edit.html";
    }

    /**
     * 获取抄告报表关联列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return reportcontentService.selectList(null);
    }

    /**
     * 新增抄告报表关联
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Reportcontent reportcontent) {
        reportcontentService.insert(reportcontent);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除抄告报表关联
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer reportcontentId) {
        reportcontentService.deleteById(reportcontentId);
        return SUCCESS_TIP;
    }

    /**
     * 修改抄告报表关联
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Reportcontent reportcontent) {
        reportcontentService.updateById(reportcontent);
        return super.SUCCESS_TIP;
    }

    /**
     * 抄告报表关联详情
     */
    @RequestMapping(value = "/detail/{reportcontentId}")
    @ResponseBody
    public Object detail(@PathVariable("reportcontentId") Integer reportcontentId) {
        return reportcontentService.selectById(reportcontentId);
    }
}
