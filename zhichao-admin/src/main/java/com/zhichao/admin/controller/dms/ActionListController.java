package com.zhichao.admin.controller.dms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhichao.service.core.log.LogObjectHolder;
import com.zhichao.service.dms.IActionListService;
import com.zhichao.beans.guns.ActionList;
import com.zhichao.core.base.controller.BaseController;


/**
 * 数据服务步骤管理控制器
 *
 * @author fengshuonan
 * @Date 2018-02-27 10:10:36
 */
@Controller
@RequestMapping("/actionList")
public class ActionListController extends BaseController {

    private String PREFIX = "/dms/actionList/";

    @Autowired
    private IActionListService actionListService;

    /**
     * 跳转到数据服务步骤管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "actionList.html";
    }

    /**
     * 跳转到添加数据服务步骤管理
     */
    @RequestMapping("/actionList_add")
    public String actionListAdd() {
        return PREFIX + "actionList_add.html";
    }

    /**
     * 跳转到修改数据服务步骤管理
     */
    @RequestMapping("/actionList_update/{actionListId}")
    public String actionListUpdate(@PathVariable Integer actionListId, Model model) {
        ActionList actionList = actionListService.selectById(actionListId);
        model.addAttribute("item",actionList);
        LogObjectHolder.me().set(actionList);
        return PREFIX + "actionList_edit.html";
    }

    /**
     * 获取数据服务步骤管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return actionListService.selectList(null);
    }

    /**
     * 新增数据服务步骤管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(ActionList actionList) {
        actionListService.insert(actionList);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除数据服务步骤管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer actionListId) {
        actionListService.deleteById(actionListId);
        return SUCCESS_TIP;
    }

    /**
     * 修改数据服务步骤管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(ActionList actionList) {
        actionListService.updateById(actionList);
        return super.SUCCESS_TIP;
    }

    /**
     * 数据服务步骤管理详情
     */
    @RequestMapping(value = "/detail/{actionListId}")
    @ResponseBody
    public Object detail(@PathVariable("actionListId") Integer actionListId) {
        return actionListService.selectById(actionListId);
    }
}
