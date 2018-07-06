package com.zhichao.admin.controller.platformConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.zhichao.admin.controller.presite.PresiteController;
import com.zhichao.admin.controller.siteRegistration.BsCorpController;
import com.zhichao.admin.controller.siteRegistration.FixedsiteController;
import com.zhichao.admin.controller.siteRegistration.UnloadingController;
import com.zhichao.beans.guns.Dept;
import com.zhichao.beans.guns.Fixedsite;
import com.zhichao.dal.mapper.DeptMapper;
import com.zhichao.dal.siteRegistration.FixedsiteDao;
import com.zhichao.service.core.shiro.ShiroKit;

/**
 * 树形菜单controller
 * @author fengshuonan
 * @Date 2018年1月12日 上午10:48:22
 */
@Controller
@RequestMapping("/tree")
public class TreeController {
	
	private String PREFIX = "/platformConfig/tree/";
	
	
	@Autowired 
	PresiteController presiteController;
	
	@Autowired
	AreaController areaController;
	
	@Autowired
	FixedsiteController fixedsiteController;

    @Autowired
    private FixedsiteDao fixedsiteDao;

	@Autowired
	BsCorpController bsCorpController;
	
	@Autowired
	UnloadingController unloadingController;

    @Resource
    DeptMapper deptMapper;


	@RequestMapping("")
    public String index() {
        return PREFIX + "tree.html";
    }
	
	/**
     * 获取区域信息列表列表 getAreaNodesList
     */
    @RequestMapping(value = "/getAreaNodesList")
    @ResponseBody
    public Object getAreaNodesList() {
    	return areaController.tree();
    }
	
	/**
     * 获取不停车站点列表 getPresiteList
     */
    @RequestMapping(value = "/getPresiteList")
    @ResponseBody
    public Object getPresiteList() {
    	return presiteController.list("", "");
    }
    
    /**
     * 获取固定治超站点列表 getPresiteList
     */
    @RequestMapping(value = "/getFixedsiteList")
    @ResponseBody
    public Object getFixedsiteList() {
    	return fixedsiteController.list("", "", "", "", "");
    }
    
    /**
     * 获取不停车站点列表 getPresiteList
     */
    @RequestMapping(value = "/getCorpList")
    @ResponseBody
    public Object getCorpList() {
    	return bsCorpController.list("", "");
    }

    /**
     * 获取部门列表
     */
    @RequestMapping(value = "/getDeptList")
    @ResponseBody
    public Object getDeptList() {
        EntityWrapper<Dept> ew = new EntityWrapper();
        String userDeptid = String.valueOf(ShiroKit.getUser().getDeptId());
        ew.where("pids LIKE CONCAT('%[', "+userDeptid+", ']%')");
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("deptid",userDeptid);
        //resultMap.put("data",deptMapper.selectList(null));

        List<Dept> depts = deptMapper.selectList(null);
        List<Fixedsite> fixedsiteList = fixedsiteDao.selectList(null);

//        List<Map<String, Object>> fixedlist = fixedsiteController.list("", "", "", "", "");
        for(Fixedsite tempFixed : fixedsiteList) {
            Integer deptid = tempFixed.getDeptid();
            Integer deptpid = tempFixed.getDeptpid();
            for(int i = depts.size() - 1; i >= 0; i--) {
                Dept temp = depts.get(i);
                if(temp.getId() == deptid && temp.getPid() == deptpid) {
                    System.out.println("-------------------------------"+temp+"--------------------------------------");
                    depts.remove(temp);
                    break;
                }
            }
        }

        resultMap.put("data",depts);
        return resultMap;
    }

//    /**
//     * 获取卸货场列表 getUnloadingList
//     */
//    @RequestMapping(value = "/getUnloadingList")
//    @ResponseBody
//    public Object getUnloadingList() {
//    	return unloadingController.list("", "", "", "", "");
//    }
}
