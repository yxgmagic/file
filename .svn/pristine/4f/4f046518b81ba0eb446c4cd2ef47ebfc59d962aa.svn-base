package com.zhichao.admin.controller.platformConfig;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhichao.admin.warpper.platformConfig.MunicipalWarpper;
import com.zhichao.beans.guns.Area;
import com.zhichao.core.base.controller.BaseController;
import com.zhichao.dal.platformConfig.AreaDao;
import com.zhichao.service.core.log.LogObjectHolder;
import com.zhichao.service.core.util.DictUtil;
import com.zhichao.service.platformConfig.IAreaService;

/**
 * 市级信息管理Controller
 * @author fengshuonan
 * @Date 2018年1月2日 上午10:56:52
 */
@Controller
@RequestMapping("/municipal")
public class MunicipalController extends BaseController {
	
	private String PREFIX = "/platformConfig/municipal/";
	
	@Autowired
	AreaDao areaDao;
	
	@Autowired
	IAreaService areaService;
	
	/**
     * 跳转到市级信息管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "municipal.html";
    }
    
    /**
     * 获取市级信息列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(value = "areaname", required = false) String areaname, 
    		@RequestParam(value = "areacode", required = false) String areacode, 
    		@RequestParam(value = "areatype", required = false) String areatype) {
    	List<Map<String, Object>> list = areaDao.selectMunicipalList(areaname,areacode,areatype);
        return new MunicipalWarpper(list).warp();
    }
    
    /**
     * 获取区域类型列表
     * @return
     */
    @RequestMapping(value = "/getAreaTypeList")
    @ResponseBody
    public Object getAreaTypeList() {
    	return DictUtil.selectListByColname("areatype");
    }
    
    /**
     * 跳转到市级信息添加页面
     * @return
     */
    @RequestMapping(value = "/municipal_add")
    public String municipalAdd() {
    	return PREFIX + "municipal_add.html";
    }
    
    
    /**
     * 跳转到市级信息修改页面
     */
    @RequestMapping("/municipal_update/{areaId}")
    public String municipalUpdate(@PathVariable Integer areaId, Model model) {
        Area area = areaService.selectById(areaId);
        model.addAttribute("item",area);
        LogObjectHolder.me().set(area);
        return PREFIX + "municipal_edit.html";
    }
    
    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Area area) {
    	areaService.insert(area);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer areaId) {
    	areaService.deleteById(areaId);
        return SUCCESS_TIP;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Area area) {
    	areaService.updateById(area);
        return super.SUCCESS_TIP;
    }
    
    
    
	
}
