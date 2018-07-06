package com.zhichao.admin.controller.dms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhichao.service.core.log.LogObjectHolder;
import com.zhichao.service.dms.IParaListService;
import com.zhichao.beans.guns.ParaList;
import com.zhichao.core.base.controller.BaseController;

/**
 * 数据服务参数列表控制器
 *
 * @author fengshuonan
 * @Date 2018-02-27 10:13:27
 */
@Controller
@RequestMapping("/paraList")
public class ParaListController extends BaseController {

    private String PREFIX = "/dms/paraList/";

    @Autowired
    private IParaListService paraListService;

    /**
     * 跳转到数据服务参数列表首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "paraList.html";
    }

    /**
     * 跳转到添加数据服务参数列表
     */
    @RequestMapping("/paraList_add")
    public String paraListAdd() {
        return PREFIX + "paraList_add.html";
    }

    /**
     * 跳转到修改数据服务参数列表
     */
    @RequestMapping("/paraList_update/{paraListId}")
    public String paraListUpdate(@PathVariable Integer paraListId, Model model) {
        ParaList paraList = paraListService.selectById(paraListId);
        model.addAttribute("item",paraList);
        LogObjectHolder.me().set(paraList);
        return PREFIX + "paraList_edit.html";
    }

    /**
     * 获取数据服务参数列表列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return paraListService.selectList(null);
    }

    /**
     * 新增数据服务参数列表
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(ParaList paraList) {
        paraListService.insert(paraList);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除数据服务参数列表
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer paraListId) {
        paraListService.deleteById(paraListId);
        return SUCCESS_TIP;
    }

    /**
     * 修改数据服务参数列表
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(ParaList paraList) {
        paraListService.updateById(paraList);
        return super.SUCCESS_TIP;
    }

    /**
     * 数据服务参数列表详情
     */
    @RequestMapping(value = "/detail/{paraListId}")
    @ResponseBody
    public Object detail(@PathVariable("paraListId") Integer paraListId) {
        return paraListService.selectById(paraListId);
    }
}
