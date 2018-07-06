package com.zhichao.admin.controller.perforeValue;

import java.util.HashMap;
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
import com.zhichao.service.perforevalue.IPeAssessplanService;
import com.zhichao.beans.guns.QueryAssessInfoBO;
import com.zhichao.core.base.controller.BaseController;

/**
 * 考核计划控制器
 *
 * @author fengshuonan
 * @Date 2018-02-28 14:21:49
 */
@Controller
@RequestMapping("/assessplan")
public class PeAssessplanController extends BaseController {

    private String PREFIX = "/perforevalue/peAssessplan/";

    @Autowired
    private IPeAssessplanService peAssessplanService;

    /**
     * 跳转到考核计划首页
     */
    @RequestMapping("")
    public String index(@RequestParam(value = "page", required = false) String page) {
        //跳转到站点考核排名
        if ("siteAssess".equals(page)){
            return "/perforevalue/siteAssess/siteAssess.html";
        }
        return PREFIX + "peAssessplan.html";
    }

    /**
     * 跳转到添加考核计划
     */
    @RequestMapping("/peAssessplan_add")
    public String peAssessplanAdd() {
        return PREFIX + "peAssessplan_add.html";
    }

    /**
     * 跳转到修改考核计划
     */
    @RequestMapping("/peAssessplan_update/{peAssessplanId}")
    public String peAssessplanUpdate(@PathVariable Integer peAssessplanId, Model model) {
        Map<String, Object> peAssessplan = peAssessplanService.selById(peAssessplanId);
        model.addAttribute("item",peAssessplan);
        LogObjectHolder.me().set(peAssessplan);
        return PREFIX + "peAssessplan_edit.html";
    }

    /**
     * 获取考核计划列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(value = "assessName", required = false) String assessName,
                       @RequestParam(value = "assessTime", required = false) String assessTime) {
        return peAssessplanService.selList(assessName,assessTime);
    }

    /**
     * 新增考核计划
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(@RequestParam(value = "assessName") String assessName,
                      @RequestParam(value = "assessTime") String assessTime,
                      @RequestParam(value = "assessObjHidden") String assessObj,
                      @RequestParam(value = "assessIndicHidden") String assessIndic,
                      @RequestParam(value = "notes") String notes) {
        peAssessplanService.save(assessName,assessTime,assessObj,assessIndic,notes);
        return SUCCESS_TIP;
    }

    /**
     * 删除考核计划
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer peAssessplanId) {
        peAssessplanService.deleteById(peAssessplanId);
        return SUCCESS_TIP;
    }

    /**
     * 修改考核计划
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(@RequestParam(value = "id") Integer id,
                         @RequestParam(value = "assessName") String assessName,
                         @RequestParam(value = "assessTime") String assessTime,
                         @RequestParam(value = "assessObjHidden") String assessObj,
                         @RequestParam(value = "assessIndicHidden") String assessIndic,
                         @RequestParam(value = "notes") String notes) {
        peAssessplanService.edit(id,assessName,assessTime,assessObj,assessIndic,notes);
        return SUCCESS_TIP;
    }

    /**
     * 考核计划详情
     */
    @RequestMapping(value = "/detail/{peAssessplanId}")
    @ResponseBody
    public Object detail(@PathVariable("peAssessplanId") Integer peAssessplanId) {
        return peAssessplanService.selectById(peAssessplanId);
    }

    /**
     * 打开站点,指标选择插件
     * @param tp  标识站点 & 指标
     * @return
     */
    @RequestMapping(value = "openChoosePlugin/{tp}/{page}/{id}")
    public String openChoosePlugin(@PathVariable(value = "tp") String tp,
                                   @PathVariable(value = "page") String page,
                                   @PathVariable(value = "id", required = false) String id,
                                   Model model){

        Map<String, Object> resultMap = peAssessplanService.selSiteOrIndic(tp,page,id);

        model.addAttribute("item",resultMap);
        return PREFIX + "choosePlugin.html";
    }

    /**
     * 进入站点或指标后,查询出left和right的值
     * @param tp  site & indic
     * @param id assess_id
     * @return
     */
    @RequestMapping(value = "getSelData/{tp}/{id}")
    public @ResponseBody Map<String, Object> getSelData(@PathVariable(value = "tp") String tp,
                                           @PathVariable(value = "id") String id,
                                            Model model){

        Map<String, Object> resultMap = peAssessplanService.getSelData(tp,id);

        return resultMap;
    }




    /* ***************************************  站点考核排名  ***************************************           */
    /**
     * 打开考核详情
     */
    @RequestMapping("/siteAssess_info/{id}/{start}/{end}")
    public String siteAssess_info(@PathVariable("id") Integer id,
                                  @PathVariable("start") String start,
                                  @PathVariable("end") String end,
                                  Model model) {
        Map<String, Object> result = new HashMap<>(3);
        result.put("id",id);
        result.put("start",start);
        result.put("end",end);
        model.addAttribute("item",result);
        return "/perforevalue/siteAssess/siteAssess_info.html";
    }

    /**
     * 根据考核计划id查询对应的站点信息(考核详情)
     * @param assId
     * @return
     */
    @RequestMapping("/assessinfo/{assessid}/{start}/{end}")
    public @ResponseBody
    List<QueryAssessInfoBO> assessInfo(@PathVariable("assessid") String assId,
                                       @PathVariable("start") String start,
                                       @PathVariable("end") String end){

        return peAssessplanService.selAssessInfoByAssessId(assId, start, end);
    }

}
