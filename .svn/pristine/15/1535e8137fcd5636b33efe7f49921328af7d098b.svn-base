package com.zhichao.admin.controller.platformConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zhichao.admin.warpper.platformConfig.LawEnforceManWapper;
import com.zhichao.beans.guns.LawEnforceMan;
import com.zhichao.core.base.controller.BaseController;
import com.zhichao.dal.platformConfig.LawEnforceManDao;
import com.zhichao.service.common.IBsImageEntityService;
import com.zhichao.service.core.log.LogObjectHolder;
import com.zhichao.service.core.util.DictUtil;
import com.zhichao.service.platformConfig.ILawEnforceManService;

/**
 * 执法人员信息控制器
 *
 * @author fengshuonan
 * @Date 2018-01-03 10:50:20
 */
@Controller
@RequestMapping("/lawEnforceMan")
public class LawEnforceManController extends BaseController {

    private String PREFIX = "/platformConfig/lawEnforceMan/";

    @Autowired
    private ILawEnforceManService lawEnforceManService;
    
    @Autowired
    private LawEnforceManDao lawEnforceManDao;
    
    @Autowired
	private IBsImageEntityService imageEntityService;

    
    @RequestMapping("uploadXls")
    @ResponseBody()
    public Object uploadXls(@RequestPart("file") MultipartFile file, 
    		@RequestPart("fileName") String fileName) {
    	return lawEnforceManService.saveInfo(file, fileName);
    }
    
    /**
     * 生成返回的对象
     */
    public Map<String, Object> msgMap(Integer code, Object message, Object data) {
		Map<String, Object> msg = new HashMap<String, Object>();
		msg.put("code", code);
		msg.put("msg", message);
		msg.put("data", data);
		return msg;
	}
    
    /**
     * 跳转到执法人员信息首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "lawEnforceMan.html";
    }

    /**
     * 跳转到添加执法人员信息
     */
    @RequestMapping("/lawEnforceMan_add")
    public String lawEnforceManAdd() {
        return PREFIX + "lawEnforceMan_add.html";
    }

    /**
     * 跳转到修改执法人员信息
     */
    @RequestMapping("/lawEnforceMan_update/{lawEnforceManId}")
    public String lawEnforceManUpdate(@PathVariable Integer lawEnforceManId, Model model) {
        LawEnforceMan lawEnforceMan = lawEnforceManService.selectById(lawEnforceManId);
        model.addAttribute("item",lawEnforceMan);
        LogObjectHolder.me().set(lawEnforceMan);
        return PREFIX + "lawEnforceMan_edit.html";
    }

    /**
     * 获取执法人员信息列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(value = "areacode", required = false) String areacode, 
    		@RequestParam(value = "lemName", required = false) String lemName, 
    		@RequestParam(value = "lawEnforcementAgencies", required = false) String lawEnforcementAgencies) {
    		
    	List<Map<String, Object>> list = lawEnforceManDao.selectList(areacode, lemName, lawEnforcementAgencies);
    	
    	return new LawEnforceManWapper(list).warp();
    	
    }

    /**
     * 新增执法人员信息
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(LawEnforceMan lawEnforceMan,
    		@RequestParam(value = "img_id", required = false) Integer imgId,
    		@RequestParam(value = "imagetype", required = true) String imageType) {

      //标记基本信息是否存储成功
    	boolean falg = lawEnforceManService.insert(lawEnforceMan);
    	if (falg) {//成功则将图和站点信息相关联
    		imageEntityService.insertImageEntity(imgId, lawEnforceMan.getId(), imageType);
    		return super.SUCCESS_TIP;
    	} else {
 			return super.ERROR;
    	}
    }

    /**
     * 删除执法人员信息
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer lawEnforceManId) {
        lawEnforceManService.deleteById(lawEnforceManId);
        return SUCCESS_TIP;
    }

    /**
     * 修改执法人员信息
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(LawEnforceMan lawEnforceMan,
    		@RequestParam(value = "img_id", required = false) Integer imgId,
    		@RequestParam(value = "imagetype", required = true) String imageType) {
    	
    	if(lawEnforceMan != null) {
    		boolean flag = lawEnforceManService.updateById(lawEnforceMan);
    		if (flag) {
				imageEntityService.updateEntityImage(imgId, lawEnforceMan.getId(), imageType);
				return super.SUCCESS_TIP;
			} else {
				return super.ERROR;
			}
    	} else {
    		return super.ERROR;
		}
    	
    }

    /**
     * 执法人员信息详情
     */
    @RequestMapping(value = "/detail/{lawEnforceManId}")
    @ResponseBody
    public Object detail(@PathVariable("lawEnforceManId") Integer lawEnforceManId) {
        return lawEnforceManService.selectById(lawEnforceManId);
    }
    
    /**
     * 获取性别列表
     */
    @RequestMapping(value = "/getSexList")
    @ResponseBody
    public Object getSexList() {
    	return DictUtil.selectListByColname("sex");
    }
    
    /**
     * 获取学历列表
     */
    @RequestMapping(value = "/getEduBgList")
    @ResponseBody
    public Object getEduBgList() {
    	return DictUtil.selectListByColname("eduBg");
    }
    
    /**
     * 获取政治面貌列表
     */
    @RequestMapping(value = "/getPolCodeList")
    @ResponseBody
    public Object getPolCodeList() {
    	return DictUtil.selectListByColname("PolCode");
    }
    
    /**
     * 获取执法人员等级列表lemGrade
     */
    @RequestMapping(value = "/getLemGradeList")
    @ResponseBody
    public Object getLemGradeList() {
    	return DictUtil.selectListByColname("lemGrade");
    }
    
    /**
     * 获取执法人员职务列表 lemDuty
     */
    @RequestMapping(value = "/getLemDutyList")
    @ResponseBody
    public Object getLemDutyList() {
    	return DictUtil.selectListByColname("lemDuty");
    }
    
    /**
     * 判断是否存在这个身份证号码
     */
    @RequestMapping(value = "/hasPerson")
    @ResponseBody
    public Object hasPerson(@RequestParam(value = "lemIdCardNum", required = false) String lemIdCardNum,
    		@RequestParam(value = "lemNum", required = false) String lemNum,
    		@RequestParam(value = "lemId", required = false) String lemId) {

    	Integer temp = 1;
    	Map<String,Boolean> tempMap = new HashMap();
    	//tempMap.put("valid", false);
    	temp = lawEnforceManDao.check(lemIdCardNum, lemNum, lemId);
    	
    	if(temp == 0) {
    		tempMap.put("valid", true);
    	} else {
    		tempMap.put("valid", false);
    	}
    	
//    	if(lemId == 0) {
//    		if(temp.size() > 0) {
//    			tempMap.put("valid", false);
//    		} else {
//    			tempMap.put("valid", true);
//    		}
//    	} else {
//    		if(temp.size() > 0) {
//    			Map<String,Object> tempp = temp.get(0);
//        		Integer id =  (Integer) tempp.get("id");
//        		if(temp.size() < 2 && lemId == id) {
//        			tempMap.put("valid", true);
//        		} else {
//        			tempMap.put("valid", false);
//        		}
//    		} else {
//    			tempMap.put("valid", true);
//    		}
//
//    	}
    	return tempMap;
    }
}
