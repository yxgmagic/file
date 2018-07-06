package com.zhichao.admin.controller.siteRegistration;

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
import com.zhichao.service.common.constant.factory.ConstantFactory;
import com.zhichao.service.core.log.LogObjectHolder;
import com.zhichao.service.common.IBsImageEntityService;
import com.zhichao.service.siteRegistration.IHswayService;
import com.zhichao.beans.guns.Hsway;
import com.zhichao.core.base.controller.BaseController;
import com.zhichao.dal.mapper.HswayMapper;

/**
 * 高速出入口信息控制器
 *
 * @author fengshuonan
 * @Date 2018-01-02 13:47:59
 */
@Controller
@RequestMapping("/hsway")
public class HswayController extends BaseController {

    private String PREFIX = "/siteRegistration/hsway/";

    @Autowired
    private IHswayService hswayService;

    @Resource
    private HswayMapper hswayDao;
    
    @Autowired
	private IBsImageEntityService imageEntityService;
    
    /**
     * 跳转到高速出入口信息首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "hsway.html";
    }

    /**
     * 跳转到添加高速出入口信息
     */
    @RequestMapping("/hsway_add")
    public String hswayAdd() {
        return PREFIX + "hsway_add.html";
    }

    /**
     * 跳转到修改高速出入口信息
     */
    @RequestMapping("/hsway_update/{hswayId}")
    public String hswayUpdate(@PathVariable Integer hswayId, Model model) {
        Hsway hsway = hswayService.selectById(hswayId);
        model.addAttribute("item",hsway);
		model.addAttribute("areaName",ConstantFactory.me().getAreaName(hsway.getAreacode()));
		model.addAttribute("roadName",ConstantFactory.me().getRoadName(hsway.getRoadcode()));
        LogObjectHolder.me().set(hsway);
        return PREFIX + "hsway_edit.html";
    }

    /**
     * 获取高速出入口信息列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String hsRoadName,@RequestParam(required = false) String hsWayName) {
    	List<Map<String, Object>> list = hswayDao.findList(hsRoadName, hsWayName);
    	return list;
    }

    /**
     * 新增高速出入口信息
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Hsway hsway,@RequestParam(value = "img_id", required = false) Integer imgId,
			@RequestParam(value = "imagetype", required = true) String imageType) {
		if(hsway !=null){
			EntityWrapper<Hsway> ep=new EntityWrapper<>();
			Wrapper<Hsway> wrapper = ep.eq("hswayname", hsway.getHswayname());

			int count =hswayService.selectCount(wrapper);

			if(count>0){
				return "{\"flag\":false,\"msg\":\"出入口名称已添加,请重新输入!\"}";
			}else{
				boolean flag = hswayService.insert(hsway);
				//存储成功
				if (flag) {
					imageEntityService.insertImageEntity(imgId, hsway.getId(), imageType);
				}
			}
		}

		return "{\"flag\":true,\"msg\":\"添加成功!\"}";
    }

    /**
     * 删除高速出入口信息
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer hswayId) {
        hswayService.deleteById(hswayId);
        return SUCCESS_TIP;
    }

    /**
     * 修改高速出入口信息
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Hsway hsway,@RequestParam(value = "img_id", required = false) Integer imgId,
			@RequestParam(value = "imagetype", required = true) String imageType) {
		if(hsway !=null){
			EntityWrapper<Hsway> ep=new EntityWrapper<>();
			Wrapper<Hsway> wrapper = ep.eq("hswayname", hsway.getHswayname());

			int count =hswayService.selectCount(wrapper);
			
			if(count>0){
				Hsway hsway_old = hswayService.selectById(hsway.getId());
				hsway.setHswayname(hsway_old.getHswayname());
			}
			
			boolean flag = hswayService.updateById(hsway);
			//存储成功
			if (flag) {
				imageEntityService.insertImageEntity(imgId, hsway.getId(), imageType);
			}
		}
        return super.SUCCESS_TIP;
    }

    /**
     * 高速出入口信息详情
     */
    @RequestMapping(value = "/detail/{hswayId}")
    @ResponseBody
    public Object detail(@PathVariable("hswayId") Integer hswayId) {
        return hswayService.selectById(hswayId);
    }
}
