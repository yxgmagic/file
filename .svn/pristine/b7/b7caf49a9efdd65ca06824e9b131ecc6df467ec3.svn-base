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
import com.zhichao.service.detecManage.ICorpinfoService;
import com.zhichao.beans.guns.Corpinfo;
import com.zhichao.core.base.controller.BaseController;

/**
 * 源头企业检测数据管理控制器
 *
 * @author fengshuonan
 * @Date 2018-01-19 17:19:39
 */
@Controller
@RequestMapping("/corpinfo")
public class CorpinfoController extends BaseController {

    private String PREFIX = "/detecManage/corpinfo/";

    @Autowired
    private ICorpinfoService corpinfoService;

    /**
     * 跳转到源头企业检测数据管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "corpinfo.html";
    }

    /**
	 * 跳转到修改源头企业检测数据管理
	 */
	@RequestMapping("/corpinfo_update/{id}")
	public String hspinfoUpdate(@PathVariable(value = "id") Integer id, Model model) {
		
		Corpinfo corpinfo = corpinfoService.selCorpInfo(id);
		
		model.addAttribute("item",corpinfo);
		LogObjectHolder.me().set(corpinfo);
		return PREFIX + "corpinfo_edit.html";
	}

	/**
     * 跳转到修改不停车预检数据管理后查询图片信息
	 * @param id id
     * @return
     */
    @RequestMapping(value = "/getVehicleImages/{id}")
    @ResponseBody
    public Object getVehicleImages (@PathVariable(value = "id") Integer id) {
    	
    	return corpinfoService.getVehicleImages(id);
    }
    
	/**
	 * 获取源头企业检测数据管理列表
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public Object list(
			@RequestParam(value = "areacode", required = false) String areacode,
			@RequestParam(value = "vehicleid", required = false) String vehicleid,
			@RequestParam(value = "corptime", required = false) String corptime) {

		if (areacode == null){
			return null;
		}

		Page<Corpinfo> page = new PageFactory<Corpinfo>().defaultPage();

		List<Corpinfo> list = corpinfoService.selList(page, areacode, vehicleid, corptime);

		page.setRecords(list);
		return super.packForBT(page);
	}


	/**
	 * 获取大屏数据
	 */
	@RequestMapping(value = "/calculateVehicle")
	@ResponseBody
	public Object calculateVehicle (@RequestParam(value = "areacode") String areacode) {

		return corpinfoService.calculateVehicle(areacode);
	}
}
