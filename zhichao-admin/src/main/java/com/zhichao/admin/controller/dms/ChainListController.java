package com.zhichao.admin.controller.dms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhichao.service.core.log.LogObjectHolder;
import com.zhichao.service.dms.IChainListService;
import com.zhichao.beans.guns.ChainList;
import com.zhichao.core.base.controller.BaseController;

/**
 * 数据服务链列表控制器
 *
 * @author fengshuonan
 * @Date 2018-02-27 10:13:14
 */
@Controller
@RequestMapping("/chainList")
public class ChainListController extends BaseController {

    private String PREFIX = "/dms/chainList/";

    @Autowired
    private IChainListService chainListService;

    /**
     * 跳转到数据服务链列表首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "chainList.html";
    }

    /**
     * 跳转到添加数据服务链列表
     */
    @RequestMapping("/chainList_add")
    public String chainListAdd() {
        return PREFIX + "chainList_add.html";
    }

    /**
     * 跳转到修改数据服务链列表
     */
    @RequestMapping("/chainList_update/{chainListId}")
    public String chainListUpdate(@PathVariable Integer chainListId, Model model) {
        ChainList chainList = chainListService.selectById(chainListId);
        model.addAttribute("item",chainList);
        LogObjectHolder.me().set(chainList);
        return PREFIX + "chainList_edit.html";
    }

    /**
     * 获取数据服务链列表列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return chainListService.selectList(null);
    }

    /**
     * 新增数据服务链列表
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(ChainList chainList) {
        chainListService.insert(chainList);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除数据服务链列表
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer chainListId) {
        chainListService.deleteById(chainListId);
        return SUCCESS_TIP;
    }

    /**
     * 修改数据服务链列表
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(ChainList chainList) {
        chainListService.updateById(chainList);
        return super.SUCCESS_TIP;
    }

    /**
     * 数据服务链列表详情
     */
    @RequestMapping(value = "/detail/{chainListId}")
    @ResponseBody
    public Object detail(@PathVariable("chainListId") Integer chainListId) {
        return chainListService.selectById(chainListId);
    }
}
