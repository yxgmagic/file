package com.zhichao.admin.controller.dms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhichao.service.core.log.LogObjectHolder;
import com.zhichao.service.dms.IServerListService;
import com.zhichao.beans.guns.ServerList;
import com.zhichao.core.base.controller.BaseController;

/**
 * 节点路径列表控制器
 *
 * @author fengshuonan
 * @Date 2018-02-27 10:15:01
 */
@Controller
@RequestMapping("/serverList")
public class ServerListController extends BaseController {

    private String PREFIX = "/dms/serverList/";

    @Autowired
    private IServerListService serverListService;

    /**
     * 跳转到节点路径列表首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "serverList.html";
    }

    /**
     * 跳转到添加节点路径列表
     */
    @RequestMapping("/serverList_add")
    public String serverListAdd() {
        return PREFIX + "serverList_add.html";
    }

    /**
     * 跳转到修改节点路径列表
     */
    @RequestMapping("/serverList_update/{serverListId}")
    public String serverListUpdate(@PathVariable Integer serverListId, Model model) {
        ServerList serverList = serverListService.selectById(serverListId);
        model.addAttribute("item",serverList);
        LogObjectHolder.me().set(serverList);
        return PREFIX + "serverList_edit.html";
    }

    /**
     * 获取节点路径列表列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return serverListService.selectList(null);
    }

    /**
     * 新增节点路径列表
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(ServerList serverList) {
        serverListService.insert(serverList);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除节点路径列表
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer serverListId) {
        serverListService.deleteById(serverListId);
        return SUCCESS_TIP;
    }

    /**
     * 修改节点路径列表
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(ServerList serverList) {
        serverListService.updateById(serverList);
        return super.SUCCESS_TIP;
    }

    /**
     * 节点路径列表详情
     */
    @RequestMapping(value = "/detail/{serverListId}")
    @ResponseBody
    public Object detail(@PathVariable("serverListId") Integer serverListId) {
        return serverListService.selectById(serverListId);
    }
}
