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

import com.zhichao.admin.warpper.platformConfig.PunishmentRulesWarpper;
import com.zhichao.beans.guns.PunishmentRules;
import com.zhichao.core.base.controller.BaseController;
import com.zhichao.dal.platformConfig.PunishmentRulesDao;
import com.zhichao.service.common.IBsImageEntityService;
import com.zhichao.service.core.log.LogObjectHolder;
import com.zhichao.service.core.util.DictUtil;
import com.zhichao.service.core.util.PubUtil;
import com.zhichao.service.platformConfig.IPunishmentRulesService;

/**
 * 处罚规则控制器
 *
 * @author fengshuonan
 * @Date 2018-01-11 11:06:21
 */
@Controller
@RequestMapping("/punishmentRules")
public class PunishmentRulesController extends BaseController {

    private String PREFIX = "/platformConfig/punishmentRules/";

    @Autowired
    private IPunishmentRulesService punishmentRulesService;
    
    @Autowired
	private IBsImageEntityService imageEntityService;
    
    @Autowired
    PunishmentRulesDao punishmentRulesDao;

    /**
     * 跳转到处罚规则首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "punishmentRules.html";
    }

    /**
     * 跳转到添加处罚规则
     */
    @RequestMapping("/punishmentRules_add")
    public String punishmentRulesAdd(Model model) {

        PubUtil pub= new PubUtil();
        Map<String,Object> sequenceMap = pub.getSequence("ruleName" , "" , 0 , "rule_name" , "bs_punishment_rules");
        model.addAttribute("sequenceMap", sequenceMap);
        return PREFIX + "punishmentRules_add.html";
    }

    /**
     * 跳转到修改处罚规则
     */
    @RequestMapping("/punishmentRules_update/{punishmentRulesId}")
    public String punishmentRulesUpdate(@PathVariable Integer punishmentRulesId, Model model) {
        PunishmentRules punishmentRules = punishmentRulesService.selectById(punishmentRulesId);
        model.addAttribute("item",punishmentRules);
        LogObjectHolder.me().set(punishmentRules);
        return PREFIX + "punishmentRules_edit.html";
    }

    /**
     * 获取处罚规则列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    
    public Object list(@RequestParam(value = "trucksType", required = false) String trucksType, 
    		@RequestParam(value = "trucksAxles", required = false) String trucksAxles) {
    	List<Map<String, Object>> list = punishmentRulesDao.selectPunishmentRulesList(trucksType, trucksAxles);
        return new PunishmentRulesWarpper(list).warp();
    }

    /**
     * 新增处罚规则
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(PunishmentRules punishmentRules,
    		@RequestParam(value = "img_id", required = false) Integer imgId,
    		@RequestParam(value = "imagetype", required = true) String imageType) {
    	
    	boolean falg = punishmentRulesService.insert(punishmentRules);
    	if (falg) {//成功则将图和站点信息相关联
    		imageEntityService.insertImageEntity(imgId, punishmentRules.getId(), imageType);
    		return super.SUCCESS_TIP;
    	} else {
 			return super.ERROR;
    	}
    	
    }

    /**
     * 删除处罚规则
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer punishmentRulesId) {
        punishmentRulesService.deleteById(punishmentRulesId);
        return SUCCESS_TIP;
    }

    /**
     * 修改处罚规则
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(PunishmentRules punishmentRules,
    		@RequestParam(value = "img_id", required = false) Integer imgId,
    		@RequestParam(value = "imagetype", required = true) String imageType) {
    	
    	if(punishmentRules != null) {
    		boolean flag = punishmentRulesService.updateById(punishmentRules);
    		if (flag) {
				imageEntityService.updateEntityImage(imgId, punishmentRules.getId(), imageType);
				return super.SUCCESS_TIP;
			} else {
				return super.ERROR;
			}
    	} else {
    		return super.ERROR;
		}

    }

    /**
     * 处罚规则详情
     */
    @RequestMapping(value = "/detail/{punishmentRulesId}")
    @ResponseBody
    public Object detail(@PathVariable("punishmentRulesId") Integer punishmentRulesId) {
        return punishmentRulesService.selectById(punishmentRulesId);
    }
    
    /**
     * 获取车型字典列表
     */
    @RequestMapping(value = "/getTrucksTypeList")
    @ResponseBody
    public Object getTrucksTypeList() {
    	return DictUtil.selectListByColname("trucksType");
    }
    
    /**
     * 获取车轴数字典列表 getTrucksAxlesList
     */
    @RequestMapping(value = "/getTrucksAxlesList")
    @ResponseBody
    public Object getTrucksAxlesList() {
    	return DictUtil.selectListByColname("trucksAxles");
    }
}
