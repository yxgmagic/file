package com.zhichao.admin.controller.platformConfig;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhichao.service.core.shiro.ShiroKit;
import com.zhichao.dal.platformConfig.AreaDao;
import com.zhichao.service.platformConfig.IAreaService;
import com.zhichao.beans.guns.Area;
import com.zhichao.beans.node.ZTreeNodeArea;
import com.zhichao.core.base.controller.BaseController;
import com.zhichao.dal.mapper.AreaMapper;

/**
 * 省级信息管理控制器
 *
 * @author fengshuonan
 * @Date 2018-01-02 10:51:53
 */
@Controller
@RequestMapping("/area")
public class AreaController extends BaseController {

    private String PREFIX = "/platformConfig/area/";

    @Autowired
    private IAreaService areaService;
    @Autowired
    private AreaMapper am  ;
    
    @Resource
    private AreaDao areaDao;
    
    
    /**
     * 跳转到省级信息管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "area.html";
    }
    
    /**
     * 获取省级信息
     */
    @RequestMapping(value = "areaS")
    @ResponseBody
    public Object selectAreaS() {
    	return areaDao.selectAreaS();
    }
    
    /**
     * 行政区域下拉框列表
     */
    @RequestMapping(value = "/arealist")
    @ResponseBody
    public Object getArealist(String pid,String areatype){
    	
    	
    	return am.listSelect( pid,areatype);
    }

    
    /**
     * 获取区域的tree列表
     */
    @RequestMapping(value = "/tree")
    @ResponseBody
    public List<ZTreeNodeArea> tree() {
        List<ZTreeNodeArea> tree = areaDao.tree(ShiroKit.getUser().getId());
        return tree;
    }
    
    
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Area area) {
    	areaService.updateById(area);
        return super.SUCCESS_TIP;
    }
}
