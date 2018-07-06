package com.zhichao.admin.controller.report;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.zhichao.service.core.log.LogObjectHolder;
import com.zhichao.service.report.IReportserviceService;
import com.zhichao.beans.guns.Reportservice;
import com.zhichao.core.base.controller.BaseController;
import com.zhichao.dal.mapper.ReportserviceMapper;

/**
 * reportservice控制器
 *
 * @author fengshuonan
 * @Date 2018-03-16 15:11:05
 */
@Controller
@RequestMapping("/reportservice")
public class ReportserviceController extends BaseController {

    private String PREFIX = "/report/reportservice/";

    @Autowired
    private IReportserviceService reportserviceService;

    @Resource
    private ReportserviceMapper reportServiceDao;
    /**
     * 跳转到reportservice首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "reportservice.html";
    }

    /**
     * 跳转到添加reportservice
     */
    @RequestMapping("/reportservice_add")
    public String reportserviceAdd() {
        return PREFIX + "reportservice_add.html";
    }

    /**
     * 跳转到修改reportservice
     */
    @RequestMapping("/reportservice_update/{reportserviceId}")
    public String reportserviceUpdate(@PathVariable Integer reportserviceId, Model model) {
        Reportservice reportservice = reportserviceService.selectById(reportserviceId);
        model.addAttribute("item",reportservice);
        LogObjectHolder.me().set(reportservice);
        return PREFIX + "reportservice_edit.html";
    }

    /**
     * 获取reportservice列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String reportName) {
    	List<Map<String, Object>> list = reportServiceDao.findList(reportName);
    	
        return list;
    }

    /**
     * 新增reportservice
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Reportservice reportservice) {
        //reportserviceService.insert(reportservice);
        //return super.SUCCESS_TIP;
        
        if(reportservice!=null){
        	EntityWrapper<Reportservice> ep=new EntityWrapper<>();
			Wrapper<Reportservice> wrapper = ep.eq("reportName", reportservice.getReportName());
			
			int count=reportserviceService.selectCount(wrapper);
			
			if(count>0){
				return "{\"flag\":false,\"msg\":\"对象名称已添加,请重新输入!\"}";
			}else{
				boolean flag = reportserviceService.insert(reportservice);
				if(flag)
					return "{\"flag\":true,\"msg\":\"添加成功!\"}";
			}
        }
        
        return "{\"flag\":false,\"msg\":\"添加数据错误!\"}";
    }

    /**
     * 删除reportservice
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer reportserviceId) {
        reportserviceService.deleteById(reportserviceId);
        return SUCCESS_TIP;
    }

    /**
     * 修改reportservice
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Reportservice reportservice) {
        if(reportservice!=null){
        	EntityWrapper<Reportservice> ep=new EntityWrapper<>();
			Wrapper<Reportservice> wrapper = ep.eq("reportName", reportservice.getReportName());
			
			int count=reportserviceService.selectCount(wrapper);
			
			if(count>0){
				Reportservice reportservice_old = reportserviceService.selectById(reportservice.getId());
				reportservice.setReportName(reportservice_old.getReportName());
			}

			boolean flag = reportserviceService.updateById(reportservice);
        }
        
        return super.SUCCESS_TIP;
    }

    /**
     * reportservice详情
     */
    @RequestMapping(value = "/detail/{reportserviceId}")
    @ResponseBody
    public Object detail(@PathVariable("reportserviceId") Integer reportserviceId) {
        return reportserviceService.selectById(reportserviceId);
    }
}
