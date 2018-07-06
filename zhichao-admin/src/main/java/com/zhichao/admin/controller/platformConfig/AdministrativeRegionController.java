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
import com.zhichao.service.core.log.LogObjectHolder;
import com.zhichao.service.platformConfig.IAreaService;

/**
 * 行政区域管理
 *
 * @author fengshuonan
 * @Date 2018-01-02 10:51:53
 */
@Controller
@RequestMapping("/arc")
public class AdministrativeRegionController extends BaseController {

    private String PREFIX = "/platformConfig/administrativeregion/";

    @Autowired
    private IAreaService areaService;

    /**
     * 跳转到行政区域管理
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "area.html";
    }

    /**
     * 跳转到添加行政区域管理
     */
    @RequestMapping("/area_add")
    public String bsCorpAdd() {
        return PREFIX + "area_add.html";
    }

    /**
     * 跳转到修改行政区域管理
     */
    @RequestMapping("/area_update/{areaId}")
    public String bsCorpUpdate(@PathVariable Integer areaId, Model model) {
        Area area = areaService.selById(areaId);
        model.addAttribute("item",area);
        LogObjectHolder.me().set(area);
        return PREFIX + "area_edit.html";
    }

    /**
     * 获取行政区域管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(
    		@RequestParam(value = "address", required = false) String address,
    		@RequestParam(value = "areatype", required = false) String areatype,
    		@RequestParam(value = "areaname", required = false) String areaname,
    		@RequestParam(value = "areacode", required = false) String areacode,
    		@RequestParam(value = "pid", required = false) String pid) {
    	
    	List<Map<String, Object>> area = areaService.queryAreaByCondition(address,areatype,areaname,areacode,pid);
        return new MunicipalWarpper(area).warp();
    }

    /**
     * 新增行政区域管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Area area) {
    	areaService.insert(area);
        return SUCCESS_TIP;
    }

    /**
     * 删除行政区域管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer areaId) {
    	areaService.deleteById(areaId);
        return SUCCESS_TIP;
    }

    /**
     * 修改行政区域管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Area area) {
    	areaService.updateById(area);
        return SUCCESS_TIP;
    }

    /**
     * 行政区域详情
     */
    @RequestMapping(value = "/detail/{areaId}")
    @ResponseBody
    public Object detail(@PathVariable("areaId") Integer areaId) {
        return areaService.selectById(areaId);
    }
    
    /**
     * 根据pid查询区域名称
     */
    @RequestMapping(value = "/selByPid")
    @ResponseBody
    public Object selByPid(@RequestParam(value="pid", required = false, defaultValue = "2") Integer pid) {
    	return areaService.selByPid(pid);
    }
}
