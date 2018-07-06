package com.zhichao.admin.controller.dms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhichao.service.core.log.LogObjectHolder;
import com.zhichao.service.dms.IActionParaService;
import com.zhichao.beans.guns.ActionPara;
import com.zhichao.core.base.controller.BaseController;

/**
 * 数据服务步骤参数控制器
 *
 * @author fengshuonan
 * @Date 2018-02-27 10:12:30
 */
@Controller
@RequestMapping("/actionPara")
public class ActionParaController extends BaseController {

    private String PREFIX = "/dms/actionPara/";

    @Autowired
    private IActionParaService actionParaService;

    /**
     * 跳转到数据服务步骤参数首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "actionPara.html";
    }

    /**
     * 跳转到添加数据服务步骤参数
     */
    @RequestMapping("/actionPara_add")
    public String actionParaAdd() {
        return PREFIX + "actionPara_add.html";
    }

    /**
     * 跳转到修改数据服务步骤参数
     */
    @RequestMapping("/actionPara_update/{actionParaId}")
    public String actionParaUpdate(@PathVariable Integer actionParaId, Model model) {
        ActionPara actionPara = actionParaService.selectById(actionParaId);
        model.addAttribute("item",actionPara);
        LogObjectHolder.me().set(actionPara);
        return PREFIX + "actionPara_edit.html";
    }

    /**
     * 获取数据服务步骤参数列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return actionParaService.selectList(null);
    }

    /**
     * 新增数据服务步骤参数
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(ActionPara actionPara) {
        actionParaService.insert(actionPara);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除数据服务步骤参数
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer actionParaId) {
        actionParaService.deleteById(actionParaId);
        return SUCCESS_TIP;
    }

    /**
     * 修改数据服务步骤参数
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(ActionPara actionPara) {
        actionParaService.updateById(actionPara);
        return super.SUCCESS_TIP;
    }

    /**
     * 数据服务步骤参数详情
     */
    @RequestMapping(value = "/detail/{actionParaId}")
    @ResponseBody
    public Object detail(@PathVariable("actionParaId") Integer actionParaId) {
        return actionParaService.selectById(actionParaId);
    }
}
