package com.zhichao.admin.controller.lawEnforcement;

import java.util.Date;
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
import com.zhichao.service.core.util.PubUtil;
import com.zhichao.service.lawEnforcement.IMakeCargoService;
import com.zhichao.beans.guns.MakeCargo;
import com.zhichao.core.base.controller.BaseController;

/**
 * 货物转运处理控制器
 *
 * @author fengshuonan
 * @Date 2018-01-30 09:12:59
 */
@Controller
@RequestMapping("/makeCargo")
public class MakeCargoController extends BaseController {

    private String PREFIX = "/lawEnforcement/makeCargo/";

    @Autowired
    private IMakeCargoService makeCargoService;

    /**
     * 跳转到货物转运处理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "makeCargo.html";
    }

    /**
     * 跳转到添加货物转运处理
     */
    @RequestMapping("/makeCargo_add")
    public String makeCargoAdd(Model model) {
    	
    	PubUtil pub= new PubUtil();
    	Map<String,Object> sequenceMap = pub.getSequence("makecargono" , "" , 0 , "makecargono" , "b_make_cargo");
    	model.addAttribute("sequenceMap", sequenceMap);
    	
        return PREFIX + "makeCargo_add.html";
    }

    /**
     * 跳转到修改货物转运处理
     */
    @RequestMapping("/makeCargo_update/{makeCargoId}")
    public String makeCargoUpdate(@PathVariable Integer makeCargoId, Model model) {
        Map<String, Object> makeCargo = makeCargoService.selListById(makeCargoId);
        model.addAttribute("item",makeCargo);
        LogObjectHolder.me().set(makeCargo);
        return PREFIX + "makeCargo_edit.html";
    }
    

    /**
     * 获取货物转运处理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(value = "withholdno", required = false) String withholdno,
    		@RequestParam(value = "stationid", required = false) String stationid,
    		@RequestParam(value = "makevehicleid", required = false) String makevehicleid,
    		@RequestParam(value = "makecargodate", required = false) String makecargodate,
    		@RequestParam(value = "makecargono", required = false) String makecargono) {
        return makeCargoService.selList(withholdno, stationid, makevehicleid, makecargodate, makecargono);
    }

    /**
     * 新增货物转运处理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(MakeCargo makeCargo,
    		@RequestParam(value = "makecargodateString", required = false) String makecargodateString) {
    	if(!YUtil.isNullOrEmpty(makecargodateString, true)) {
			Date makecargodate = YUtil.StringToDate(makecargodateString);
			makeCargo.setMakecargodate(makecargodate);
		}
        makeCargoService.insert(makeCargo);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除货物转运处理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer makeCargoId) {
        makeCargoService.deleteById(makeCargoId);
        return SUCCESS_TIP;
    }

    /**
     * 修改货物转运处理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(MakeCargo makeCargo,
    		@RequestParam(value = "makecargodateString", required = false) String makecargodateString) {
    	if(!YUtil.isNullOrEmpty(makecargodateString, true)) {
			Date makecargodate = YUtil.StringToDate(makecargodateString);
			makeCargo.setMakecargodate(makecargodate);
		}
        makeCargoService.updateById(makeCargo);
        return super.SUCCESS_TIP;
    }

    /**
     * 货物转运处理详情
     */
    @RequestMapping(value = "/detail/{makeCargoId}")
    @ResponseBody
    public Object detail(@PathVariable("makeCargoId") Integer makeCargoId) {
    	
        return makeCargoService.selectById(makeCargoId);
    }
    
    /**
     * 卸货单信息弹框
     */
    @RequestMapping("/cargoHandling_popups")
    public 	Object cargoHandlingPopups() {
    	return PREFIX + "cargoHandling_popups.html";
    }
    
    /**
     * 跳转到打印预览
     */
    @RequestMapping("/preview/{makeCargoId}")
    public 	Object preview(@PathVariable Integer makeCargoId, Model model) {
        Map<String, Object> cargoHandling = makeCargoService.getPrintData(makeCargoId);
        model.addAttribute("item",cargoHandling);
        LogObjectHolder.me().set(cargoHandling);
    	return PREFIX + "print_preview.html";
    }
}
