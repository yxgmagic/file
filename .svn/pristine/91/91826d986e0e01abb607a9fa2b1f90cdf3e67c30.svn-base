package com.zhichao.admin.controller.dms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhichao.service.core.log.LogObjectHolder;
import com.zhichao.service.dms.IChainActionService;
import com.zhichao.beans.guns.ChainAction;
import com.zhichao.core.base.controller.BaseController;

/**
 * 数据服务链步骤关系维护控制器
 *
 * @author fengshuonan
 * @Date 2018-02-27 10:12:57
 */
@Controller
@RequestMapping("/chainAction")
public class ChainActionController extends BaseController {

    private String PREFIX = "/dms/chainAction/";

    @Autowired
    private IChainActionService chainActionService;

    /**
     * 跳转到数据服务链步骤关系维护首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "chainAction.html";
    }

    /**
     * 跳转到添加数据服务链步骤关系维护
     */
    @RequestMapping("/chainAction_add")
    public String chainActionAdd() {
        return PREFIX + "chainAction_add.html";
    }

    /**
     * 跳转到修改数据服务链步骤关系维护
     */
    @RequestMapping("/chainAction_update/{chainActionId}")
    public String chainActionUpdate(@PathVariable Integer chainActionId, Model model) {
        ChainAction chainAction = chainActionService.selectById(chainActionId);
        model.addAttribute("item",chainAction);
        LogObjectHolder.me().set(chainAction);
        return PREFIX + "chainAction_edit.html";
    }

    /**
     * 获取数据服务链步骤关系维护列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return chainActionService.selectList(null);
    }

    /**
     * 新增数据服务链步骤关系维护
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(ChainAction chainAction) {
        chainActionService.insert(chainAction);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除数据服务链步骤关系维护
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer chainActionId) {
        chainActionService.deleteById(chainActionId);
        return SUCCESS_TIP;
    }

    /**
     * 修改数据服务链步骤关系维护
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(ChainAction chainAction) {
        chainActionService.updateById(chainAction);
        return super.SUCCESS_TIP;
    }

    /**
     * 数据服务链步骤关系维护详情
     */
    @RequestMapping(value = "/detail/{chainActionId}")
    @ResponseBody
    public Object detail(@PathVariable("chainActionId") Integer chainActionId) {
        return chainActionService.selectById(chainActionId);
    }
}
