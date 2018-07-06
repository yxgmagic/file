package com.zhichao.admin.controller.lawEnforcement;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhichao.common.util.YUtil;
import com.zhichao.service.core.log.LogObjectHolder;
import com.zhichao.service.core.util.DictUtil;
import com.zhichao.service.core.util.PubUtil;
import com.zhichao.service.lawEnforcement.ICargoHandlingService;
import com.zhichao.beans.guns.CargoHandling;
import com.zhichao.core.base.controller.BaseController;

/**
 * 超载货物处理控制器
 *
 * @author fengshuonan
 * @Date 2018-01-25 09:44:25
 */
@Controller
@RequestMapping("/cargoHandling")
public class CargoHandlingController extends BaseController {

    private String PREFIX = "/lawEnforcement/cargoHandling/";

    @Autowired
    private ICargoHandlingService cargoHandlingService;

    /**
     * 跳转到超载货物处理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "cargoHandling.html";
    }

    /**
     * 跳转到添加超载货物处理
     */
    @RequestMapping("/cargoHandling_add")
    public String cargoHandlingAdd(Model model) {
    	
    	PubUtil pub= new PubUtil();
    	Map<String,Object> sequenceMap = pub.getSequence("withholdno" , "" , 0 , "withholdno" , "b_cargo_handling");
    	model.addAttribute("sequenceMap", sequenceMap);
    	//String withholdno;
        return PREFIX + "cargoHandling_add.html";
    }

    /**
     * 跳转到修改超载货物处理
     */
    @RequestMapping("/cargoHandling_update/{cargoHandlingId}")
    public String cargoHandlingUpdate(@PathVariable Integer cargoHandlingId, Model model) {
    
        Map<String, Object> cargoHandlingMap = cargoHandlingService.selListById(cargoHandlingId);
        model.addAttribute("item",cargoHandlingMap);
        LogObjectHolder.me().set(cargoHandlingMap);
        return PREFIX + "cargoHandling_edit.html";
    }

    /**
     * 获取超载货物处理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(value = "withholdno", required = false) String withholdno,
    		@RequestParam(value = "stationid", required = false) String stationid,
    		@RequestParam(value = "vehicleid", required = false) String vehicleid,
    		@RequestParam(value = "unloadtime", required = false) String unloadtime) {
        if (!YUtil.isNullOrEmpty("vehicleid",true)){
            vehicleid = YUtil.URLDecoderString(vehicleid);
        }
        return cargoHandlingService.selList(withholdno, stationid, vehicleid, unloadtime);
    }

    /**
     * 新增超载货物处理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(CargoHandling cargoHandling, 
    		@RequestParam(value = "unloadtimeString", required = false) String unloadtimeString) {
        cargoHandlingService.insert(cargoHandling, unloadtimeString);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除超载货物处理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer cargoHandlingId) {
        cargoHandlingService.deleteById(cargoHandlingId);
        return SUCCESS_TIP;
    }

    /**
     * 修改超载货物处理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(CargoHandling cargoHandling, 
    		@RequestParam(value = "unloadtimeString", required = false) String unloadtimeString) {
        cargoHandlingService.updateById(cargoHandling, unloadtimeString);
        return super.SUCCESS_TIP;
    }

    /**
     * 超载货物处理详情
     */
    @RequestMapping(value = "/detail/{cargoHandlingId}")
    @ResponseBody
    public Object detail(@PathVariable("cargoHandlingId") Integer cargoHandlingId) {
        return cargoHandlingService.selectById(cargoHandlingId);
    }
    
    /**
     * 跳转到打印预览
     */
    @RequestMapping("/preview/{cargoHandlingId}")
    public 	Object preview(@PathVariable Integer cargoHandlingId, Model model) {
        Map<String, Object> cargoHandling = cargoHandlingService.getPrintData(cargoHandlingId);
        model.addAttribute("item",cargoHandling);
        LogObjectHolder.me().set(cargoHandling);
    	return PREFIX + "print_preview.html";
    }
    
    /**
     * 打开卸货场弹窗
     */
    @RequestMapping("/unloading_popups")
    public 	Object unloadingPopups() {
    	return PREFIX + "unloading_popups.html";
    }
    
    /**
     * 超载货物处理详情
     */
    @RequestMapping(value = "/getCargoHandlingByWithholdno/{withholdno}")
    @ResponseBody
    public Object getCargoHandlingByWithholdno(@PathVariable("withholdno") String withholdno) {

        return cargoHandlingService.selListByWithholdno(withholdno);
    }
    
    /**
     * 获取货物类型列表
     * 前端有字典,没拿这里的数据了
     */
    @RequestMapping(value = "/getCargoType")
    @ResponseBody
    public Object getCargoType() {
    	//在字典表中获取货物类型的列表
        return DictUtil.selectListByColname("itemName");
    }
    
    /**
     * 打开选车牌的弹窗
     */
    @RequestMapping("/liscinfo_popups/{stationid}")
    public 	Object liscinfoPopups(@PathVariable(value = "stationid", required = false) String stationid, Model model) {
    	model.addAttribute("stationid",stationid);
    	return PREFIX + "lscinfo_popups.html";
    }
    
    /**
     * 根据站点数据id获取站点相关信息
     */
    @RequestMapping(value = "/getInfoByStationid")
    @ResponseBody
    public Object getInfoByStationid(@RequestParam(value = "id", required = false) String id) {
    	//cargoHandlingService.getInfoByStationid(stationid, vehicleid);
        return cargoHandlingService.getInfoByStationid(id);
    }
}
