package com.zhichao.admin.controller.detectManage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;
import com.zhichao.service.common.constant.factory.PageFactory;
import com.zhichao.service.core.log.LogObjectHolder;
import com.zhichao.service.detecManage.IHspinfoService;
import com.zhichao.beans.guns.Hspinfo;
import com.zhichao.core.base.controller.BaseController;
import com.zhichao.common.util.ToolUtil;

/**
 * 不停车预检数据管理控制器
 *
 * @author fengshuonan
 * @Date 2018-01-15 11:32:38
 */
@Controller
@RequestMapping("/hspinfo")
public class HspinfoController extends BaseController {

	private String PREFIX = "/detecManage/hspinfo/";

	@Autowired
	private IHspinfoService hspinfoService;

	/**
	 * 跳转到不停车预检数据管理首页
	 */
	@RequestMapping("")
	public String index(@RequestParam(value = "stationid", required = false) String stationid, Model model) {
		model.addAttribute("stationid",stationid);
		return PREFIX + "hspinfo.html";
	}


	/**
	 * 跳转到修改不停车预检数据管理
	 */
	@RequestMapping("/hspinfo_update/{id}")
	public String hspinfoUpdate(@PathVariable(value = "id") Integer id, Model model) {
		
		Hspinfo hspinfo = hspinfoService.selHspInfo(id);
		
		model.addAttribute("item",hspinfo);
		LogObjectHolder.me().set(hspinfo);
		return PREFIX + "hspinfo_edit.html";
	}

	/**
	 * 跳转到修改不停车预检数据管理后查询图片信息
	 * @param id 业务id
	 * @return
	 */
    @RequestMapping(value = "/getVehicleImages/{id}")
    @ResponseBody
    public Object getVehicleImages (@PathVariable(value = "id") Integer id) {
    	
    	return hspinfoService.getVehicleImages(id);
    }

	/**
	 * 获取不停车预检数据管理列表
	 * @param vehicleid 车牌号
	 * @param hsptime 检测时间
	 * @param depts 部门号
	 * @param stationid 站点id
	 * @return
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public Object list(
			@RequestParam(value = "vehicleid", required = false) String vehicleid, 
    		@RequestParam(value = "hsptime", required = false) String hsptime, 
    		@RequestParam(value = "depts", required = false) String depts,
    		@RequestParam(value = "stationid", required = false) String stationid) {


		if(ToolUtil.isEmpty(depts) && ToolUtil.isEmpty(stationid)) {
    		return null;
    	}
    	
    	Page<Hspinfo> page = new PageFactory<Hspinfo>().defaultPage();
    	List<Hspinfo> result = hspinfoService.selList(page, depts, stationid, vehicleid, hsptime);
    	page.setRecords(result);
    	return super.packForBT(page);
	}


	@RequestMapping(value = "/getStatistics")
    @ResponseBody
    public Object getStatistics(@RequestParam(value = "depts", required = false) String depts,
    		@RequestParam(value = "stationid", required = false) String stationid) {
    	
    	return hspinfoService.statistics(depts, stationid);
    }
}
