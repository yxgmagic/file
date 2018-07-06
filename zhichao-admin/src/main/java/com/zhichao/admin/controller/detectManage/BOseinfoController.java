package com.zhichao.admin.controller.detectManage;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.zhichao.beans.guns.BsOsesite;
import com.zhichao.beans.guns.Oefullinfo;
import com.zhichao.common.util.YUtil;
import com.zhichao.dal.mapper.BOseinfoMapper;
import com.zhichao.dal.mapper.BsOsesiteMapper;
import com.zhichao.service.lawEnforcement.IOefullinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;
import com.zhichao.service.common.constant.factory.PageFactory;
import com.zhichao.service.detecManage.IBOseinfoService;
import com.zhichao.beans.guns.BOseinfo;
import com.zhichao.core.base.controller.BaseController;
import com.zhichao.common.constants.SystemConstants;
import com.zhichao.common.util.ToolUtil;

/**
 * 非现场执法数据管理控制器
 *
 * @author fengshuonan
 * @Date 2018-04-04 10:12:10
 */
@Controller
@RequestMapping("/bOseinfo")
public class BOseinfoController extends BaseController {

    private String PREFIX = "/detecManage/bOseinfo/";

    @Autowired
    private IBOseinfoService oseService;

    @Autowired
    private IOefullinfoService oefullinfoService;

    @Autowired
    private BOseinfoMapper oseinfoMapper;

    @Autowired
    private BsOsesiteMapper osesiteMapper;

    /**
     * 跳转到非现场执法数据管理首页
     */
    @RequestMapping("")
    public String index(@RequestParam(value = "stationid", required = false) String stationid, Model model) {
        model.addAttribute("stationid",stationid);
        return PREFIX + "bOseinfo.html";
    }

    /**
     * 获取非现场执法数据管理列表
     */
    @RequestMapping(value = "/list")
    public @ResponseBody Object list(
            @RequestParam(value = "vehicleid", required = false) String vehicleid,
            @RequestParam(value = "osetime", required = false) String osetime,
            @RequestParam(value = "depts", required = false) String depts,
            @RequestParam(value = "stationid", required = false) String stationid) {

        if (ToolUtil.isEmpty(depts) && ToolUtil.isEmpty(stationid)){
            return null;
        }

        Page<BOseinfo> page = new PageFactory<BOseinfo>().defaultPage();
        List<BOseinfo> result = oseService.selList(page,depts,stationid,vehicleid,osetime);
        page.setRecords(result);
        return super.packForBT(page);
    }

    /**
     * 非现场执法数据管理详情
     */
    @RequestMapping(value = "/detail/{bOseinfoId}")
    public @ResponseBody Object detail(@PathVariable("bOseinfoId") Integer bOseinfoId) {
        return oseService.selectById(bOseinfoId);
    }

    /**
     * 查询大屏数据的接口
     * @param depts 部门
     * @param stationid 站点
     * @return
     */
    @RequestMapping(value = "/getStatistics")
    public @ResponseBody Object getStatistics(@RequestParam(value = "depts", required = false) String depts,
                                @RequestParam(value = "stationid", required = false) String stationid) {
        return oseService.statistics(depts, stationid);
    }

    /**
     * 查询详情时查找车辆图片
     * @param id 详情id
     * @return
     */
    @RequestMapping("/getVehicleImages/{id}")
    public @ResponseBody Object getVehicleImages (@PathVariable(value = "id") Integer id) {
        return oseService.getVehicleImages(id);
    }

    /**
     * 查询非现场执法信息的详情
     * @param id id
     * @param model mvc model
     * @return
     */
    @RequestMapping("deteil/{id}")
    public String deteil(@PathVariable("id") Integer id, Model model){
        model.addAttribute("item", oseService.oseInfo(id));
        return PREFIX + "bOseinfo_edit.html";
    }

    /**
     * 非现场执法数据管理页面跳转到'立案'页面
     * @return
     */
    @RequestMapping("addOseCasePage/{id}")
    public String addOseCasePage(@PathVariable(value = "id") Integer id,
                                 Model model){

        Oefullinfo oefullinfo = oefullinfoService.findOefullInfoByOseId(id,SystemConstants.Case.TURNDOWN);
        //驳回的案件
        if (null != oefullinfo){
            model.addAttribute("oefullinfo", oefullinfo);
            return "/lawEnforcement/oefullinfo/oefullinfo_turndown.html";
        }

        // 非现场初次立案
        Map<String, Object> oseAndOseSiteInfo = oseService.findOseAndOseSiteInfo(id);
        model.addAttribute("oseAndOseSiteInfo", oseAndOseSiteInfo);
        return "/lawEnforcement/oefullinfo/oefullinfo_ose.html";
    }

}
