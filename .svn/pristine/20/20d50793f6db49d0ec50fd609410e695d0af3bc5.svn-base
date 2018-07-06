package com.zhichao.admin.controller.report;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhichao.service.core.log.LogObjectHolder;
import com.zhichao.service.report.IReportinfoService;
import com.zhichao.beans.guns.Reportinfo;
import com.zhichao.core.base.controller.BaseController;
import com.zhichao.dal.mapper.ReportinfoMapper;

/**
 * 抄告内容信息控制器
 *
 * @author fengshuonan
 * @Date 2018-03-22 09:50:50
 */
@Controller
@RequestMapping("/reportinfo")
public class ReportinfoController extends BaseController {

    private String PREFIX = "/report/reportinfo/";

    @Autowired
    private IReportinfoService reportinfoService;
    
    @Resource
    private ReportinfoMapper reportinfoDao;

    /**
     * 跳转到抄告内容信息首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "reportinfo.html";
    }

    /**
     * 跳转到添加抄告内容信息
     */
    @RequestMapping("/reportinfo_add")
    public String reportinfoAdd() {
        return PREFIX + "reportinfo_add.html";
    }

    /**
     * 跳转到修改抄告内容信息
     */
    @RequestMapping("/reportinfo_update/{reportinfoId}")
    public String reportinfoUpdate(@PathVariable Integer reportinfoId, Model model) {
        Reportinfo reportinfo = reportinfoService.selectById(reportinfoId);
        model.addAttribute("item",reportinfo);
        LogObjectHolder.me().set(reportinfo);
        return PREFIX + "reportinfo_edit.html";
    }

    /**
     * 获取抄告内容信息列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
    	return reportinfoDao.ListReport(condition, "");
        //return reportinfoService.selectList(null);
    }

    /**
     * 新增抄告内容信息
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Reportinfo reportinfo) {
        reportinfoService.insert(reportinfo);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除抄告内容信息
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer reportinfoId) {
        reportinfoService.deleteById(reportinfoId);
        return SUCCESS_TIP;
    }

    /**
     * 修改抄告内容信息
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Reportinfo reportinfo) {
        reportinfoService.updateById(reportinfo);
        return super.SUCCESS_TIP;
    }

    /**
     * 抄告内容信息详情
     */
    @RequestMapping(value = "/detail/{reportinfoId}")
    @ResponseBody
    public Object detail(@PathVariable("reportinfoId") Integer reportinfoId) {
        return reportinfoService.selectById(reportinfoId);
    }
}
