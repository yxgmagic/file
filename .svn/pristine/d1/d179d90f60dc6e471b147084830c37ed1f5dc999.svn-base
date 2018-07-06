package com.zhichao.admin.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhichao.service.core.log.LogObjectHolder;
import com.zhichao.service.core.util.ParaUtil;
import com.zhichao.service.system.IParaService;
import com.zhichao.beans.guns.Para;
import com.zhichao.core.base.controller.BaseController;

/**
 * 系统参数表控制器
 *
 * @author fengshuonan
 * @Date 2018-01-15 17:12:08
 */
@Controller
@RequestMapping("/para")
public class ParaController extends BaseController {

    private String PREFIX = "/system/para/";

    @Autowired
    private IParaService paraService;

    /**
     * 跳转到系统参数表首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "para.html";
    }

    /**
     * 跳转到添加系统参数表
     */
    @RequestMapping("/para_add")
    public String paraAdd() {
        return PREFIX + "para_add.html";
    }

    /**
     * 跳转到修改系统参数表
     */
    @RequestMapping("/para_update/{paraId}")
    public String paraUpdate(@PathVariable Integer paraId, Model model) {
        Para para = paraService.selectById(paraId);
        model.addAttribute("item",para);
        LogObjectHolder.me().set(para);
        return PREFIX + "para_edit.html";
    }

    /**
     * 获取系统参数表列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
    	ParaUtil.regetInstance(null);
        return paraService.selectList(null);
    }

    /**
     * 新增系统参数表
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Para para) {
        paraService.insert(para);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除系统参数表
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer paraId) {
        paraService.deleteById(paraId);
        return SUCCESS_TIP;
    }

    /**
     * 修改系统参数表
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Para para) {
        paraService.updateById(para);
        return super.SUCCESS_TIP;
    }

    /**
     * 系统参数表详情
     */
    @RequestMapping(value = "/detail/{paraId}")
    @ResponseBody
    public Object detail(@PathVariable("paraId") Integer paraId) {
        return paraService.selectById(paraId);
    }
}
