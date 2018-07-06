package com.zhichao.admin.controller.lawEnforcement;

import java.util.List;
import java.util.Map;

import com.zhichao.common.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhichao.common.util.YUtil;
import com.zhichao.service.core.log.LogObjectHolder;
import com.zhichao.service.lawEnforcement.IOefullinfoService;
import com.zhichao.beans.guns.Dict;
import com.zhichao.beans.guns.Oefullinfo;
import com.zhichao.core.base.controller.BaseController;

/**
 * 案件处理控制器
 *
 * @author fengshuonan
 * @Date 2018-01-24 18:56:30
 */
@Controller
@RequestMapping("/casehand")
public class CaseHandController extends BaseController {

    private String PREFIX = "/lawEnforcement/casehand/";

    @Autowired
    private IOefullinfoService oefullinfoService;

    /**
     * 跳转到案件处理首页
     */
    @RequestMapping("/*")
    public String index() {
        return PREFIX + "casehand.html";
    }

    /**
     * 跳转到添加案件
     */
    @RequestMapping("/oefullinfo_add")
    public String oefullinfoAdd() {
        return PREFIX + "casehand_add.html";
    }

    /**
     * 跳转到修改案件
     */
    @RequestMapping("/oefullinfo_update/{oefullinfoId}")
    public String oefullinfoUpdate(@PathVariable Integer oefullinfoId, Model model) {
        Oefullinfo oefullinfo = oefullinfoService.findById(oefullinfoId);

        model.addAttribute("item",oefullinfo);
        
        LogObjectHolder.me().set(oefullinfo);
        return PREFIX + "casehand_edit.html";
    }

    /**
     * 获取执法文书类型列表
     * @param oefullinfoId
     * @return
     */
    @RequestMapping("/getIdTypeList")
    public @ResponseBody List<Dict> getIdTypeList(@RequestParam(value = "oefullinfoId") Integer oefullinfoId) {

        //TODO 待修改.可直接使用oefullinfoController里面的方法
        return oefullinfoService.selIdTypeList(null);
    }

    /**
     * 获取案件列表
     * @param casetime 案件办理时间区间
     * @param vehicleid 车牌号
     * @param caseno 案件号
     * @param prostatus 案件状态
     * @return
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(value = "casetime", required = false) String casetime,
    		@RequestParam(value = "vehicleid", required = false) String vehicleid,
    		@RequestParam(value = "caseno", required = false) String caseno,
    		@RequestParam(value = "prostatus", required = false) Integer prostatus) {
        return oefullinfoService.selList(casetime,vehicleid,caseno,prostatus);
    }

    /**
     * 获取文书下载列表
     * @param oeid
     * @return
     */
    @PostMapping("/getOefullLawdocList")
    public @ResponseBody List<Map<String, Object>> getOefullLawdocList(@RequestParam(value = "oefullLawdocId") Integer oeid){
    	return oefullinfoService.getOefullLawdocList(oeid);
    }
    
    /**
     * 通过按钮
     */
    @RequestMapping(value = "/via/{id}/{checkno}/{caseSource}")
    public @ResponseBody Object via(@PathVariable(value = "id", required = false) Integer id,
                                    @PathVariable(value = "checkno", required = false) String checkno,
                                    @PathVariable("caseSource") String caseSource) throws BusinessException {
    	return oefullinfoService.via(id,checkno, caseSource);
    }
    /**
     * 驳回按钮
     */
    @RequestMapping(value = "/turndown/{id}/{checkno}/{caseSource}")
    public @ResponseBody Object turndown(@PathVariable(value = "id", required = false) Integer id,
                                         @PathVariable(value = "checkno", required = false) String checkno,
                                         @PathVariable("caseSource") String caseSource) throws BusinessException {
    	return oefullinfoService.turndown(id,checkno, caseSource);
    }

    /**
     * 新增案件
     */
    @RequestMapping(value = "/add")
    public @ResponseBody Integer add(Oefullinfo oefullinfo) {
        oefullinfoService.insert(oefullinfo);
		return oefullinfo.getId();
    }

    /**
     * 删除案件
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer oefullinfoId) {
        oefullinfoService.deleteById(oefullinfoId);
        return SUCCESS_TIP;
    }

    /**
     * 案件处理详情
     */
    @RequestMapping(value = "/detail/{oefullinfoId}")
    @ResponseBody
    public Object detail(@PathVariable("oefullinfoId") Integer oefullinfoId) {
        return oefullinfoService.selectById(oefullinfoId);
    }
}
