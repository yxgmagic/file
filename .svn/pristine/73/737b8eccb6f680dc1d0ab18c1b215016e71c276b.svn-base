package com.zhichao.admin.controller.compreAnalysis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;
import com.zhichao.service.common.constant.factory.PageFactory;
import com.zhichao.beans.guns.Corpinfo;
import com.zhichao.beans.guns.Hlcminfo;
import com.zhichao.core.base.controller.BaseController;
import com.zhichao.dal.mapper.CorpinfoMapper;
import com.zhichao.dal.mapper.HlcminfoMapper;
import com.zhichao.dal.mapper.HspinfoMapper;
import com.zhichao.dal.mapper.LscinfoMapper;
import com.zhichao.dal.mapper.MeinfoMapper;

/**
 * 检测记录控制器
 *
 * @author zqf
 * @Date 2018-02-07 15:08:05
 */
@Controller
@RequestMapping("/hlcminfo")
public class HlcminfoController extends BaseController {

    private String PREFIX = "/compreAnalysis/hlcminfo/";

 
    @Autowired
    private HlcminfoMapper hm;
    @Autowired
    private LscinfoMapper lm;
    @Autowired
    private HspinfoMapper hspm;
    @Autowired
    private CorpinfoMapper cm;
    @Autowired
    private MeinfoMapper mm;

    /**
     * 跳转到检测记录首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "hlcminfo.html";
    }

    /**
     * 跳转到添加检测记录
     */
    @RequestMapping("/hlcminfo_add")
    public String hlcminfoAdd() {
        return PREFIX + "hlcminfo_add.html";
    }

 
    /**
     * 获取检测记录列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(  String sitename,  String areacode,  String  begindt
    		, String  enddt, String sitetype, String checkmode, String vehicleid) {

        Page<Hlcminfo> page = new PageFactory<Hlcminfo>().defaultPage();
        List<Hlcminfo> result = hm.listhlcminfo(page, sitename,  areacode,   begindt,   enddt, sitetype, checkmode, vehicleid);
        page.setRecords(result);
        return super.packForBT(page);
    }

 

 

    /**
     * 检测记录详情
     */
    @RequestMapping("/detail/{str}")
    public String detail(@PathVariable String str, Model model) {
    	Integer hlcminfoId=0;
    	String type="";
    	hlcminfoId=Integer.parseInt(str.split("_")[0]);
    	type=str.split("_")[1];
    	if("1".equals(type)){
    		Corpinfo hspinfo=hm.selHspInfo(hlcminfoId);
    		model.addAttribute("item",hspinfo);
    	}else  if("2".equals(type)){
    		Corpinfo corpinfo=hm.selCorpInfo(hlcminfoId);
    		model.addAttribute("item",corpinfo);
    	}else  if("3".equals(type)){
    		Corpinfo lscinfo = hm.selLscinfoById(hlcminfoId);
    		model.addAttribute("item",lscinfo);
    	}else  if("4".equals(type)){
    		Corpinfo meinfo =hm.selMeinfoById(hlcminfoId);
    		model.addAttribute("item",meinfo);
    	}
    	return PREFIX + "hlcminfo_edit.html";
    }
}
