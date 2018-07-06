package com.zhichao.admin.controller.system;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhichao.service.core.log.LogObjectHolder;
import com.zhichao.service.core.shiro.ShiroKit;
import com.zhichao.service.system.IDeptAreasiteService;
import com.zhichao.beans.guns.DeptAreasite;
import com.zhichao.core.base.controller.BaseController;
import com.zhichao.dal.mapper.DeptAreasiteMapper;

/**
 * 区域站点权限控制器
 *
 * @author fengshuonan
 * @Date 2018-01-25 16:27:50
 */
@Controller
@RequestMapping("/deptAreasite")
public class DeptAreasiteController extends BaseController {

    private String PREFIX = "/system/deptAreasite/";

    @Autowired
    private IDeptAreasiteService deptAreasiteService;

    @Autowired
    private DeptAreasiteMapper dm;
    /**
     * 跳转到区域站点权限首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "deptAreasite.html";
    }

    /**
     * 跳转到添加区域站点权限
     */
    @RequestMapping("/deptAreasite_add")
    public String deptAreasiteAdd() {
        return PREFIX + "deptAreasite_add.html";
    }

    /**
     * 跳转到修改区域站点权限
     */
    @RequestMapping("/deptAreasite_update/{deptAreasiteId}")
    public String deptAreasiteUpdate(@PathVariable Integer deptAreasiteId, Model model) {
    	List<Map<String, Object>> fli= dm.getDeptAreaById(deptAreasiteId);
    	if(fli.size()==1){
    		 model.addAttribute("item",fli.get(0));
    	}else {
        DeptAreasite deptAreasite = deptAreasiteService.selectById(deptAreasiteId);
        model.addAttribute("item",deptAreasite);
        LogObjectHolder.me().set(deptAreasite);
     
    	}
    	   return PREFIX + "deptAreasite_edit.html";
        
    }

    /**
     * 获取区域站点权限列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String areasitecode,Integer deptid) {
      //  return deptAreasiteService.selectList(null);
    	return dm.list(areasitecode, deptid);
    }

    /**
     * 新增区域站点权限
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(DeptAreasite deptAreasite) {
    	deptAreasite.setCrtuserid(ShiroKit.getUser().getId());
        deptAreasiteService.insert(deptAreasite);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除区域站点权限
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer deptAreasiteId) {
        deptAreasiteService.deleteById(deptAreasiteId);
        return SUCCESS_TIP;
    }

    /**
     * 修改区域站点权限
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(DeptAreasite deptAreasite) {
        deptAreasiteService.updateById(deptAreasite);
        return super.SUCCESS_TIP;
    }

    /**
     * 区域站点权限详情
     */
    @RequestMapping(value = "/detail/{deptAreasiteId}")
    @ResponseBody
    public Object detail(@PathVariable("deptAreasiteId") Integer deptAreasiteId) {
        return deptAreasiteService.selectById(deptAreasiteId);
    }
}
