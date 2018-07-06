package com.zhichao.admin.controller.dms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhichao.service.core.log.LogObjectHolder;
import com.zhichao.service.dms.IServerChainService;
import com.zhichao.beans.guns.ServerChain;
import com.zhichao.core.base.controller.BaseController;

/**
 * 数据服务链与节点路径对应控制器
 *
 * @author fengshuonan
 * @Date 2018-02-28 09:13:48
 */
@Controller
@RequestMapping("/serverChain")
public class ServerChainController extends BaseController {

    private String PREFIX = "/dms/serverChain/";

    @Autowired
    private IServerChainService serverChainService;

    /**
     * 跳转到数据服务链与节点路径对应首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "serverChain.html";
    }

    /**
     * 跳转到添加数据服务链与节点路径对应
     */
    @RequestMapping("/serverChain_add")
    public String serverChainAdd() {
        return PREFIX + "serverChain_add.html";
    }

    /**
     * 跳转到修改数据服务链与节点路径对应
     */
    @RequestMapping("/serverChain_update/{serverChainId}")
    public String serverChainUpdate(@PathVariable Integer serverChainId, Model model) {
        ServerChain serverChain = serverChainService.selectById(serverChainId);
        model.addAttribute("item",serverChain);
        LogObjectHolder.me().set(serverChain);
        return PREFIX + "serverChain_edit.html";
    }

    /**
     * 获取数据服务链与节点路径对应列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return serverChainService.selectList(null);
    }

    /**
     * 新增数据服务链与节点路径对应
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(ServerChain serverChain) {
        serverChainService.insert(serverChain);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除数据服务链与节点路径对应
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer serverChainId) {
        serverChainService.deleteById(serverChainId);
        return SUCCESS_TIP;
    }

    /**
     * 修改数据服务链与节点路径对应
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(ServerChain serverChain) {
        serverChainService.updateById(serverChain);
        return super.SUCCESS_TIP;
    }

    /**
     * 数据服务链与节点路径对应详情
     */
    @RequestMapping(value = "/detail/{serverChainId}")
    @ResponseBody
    public Object detail(@PathVariable("serverChainId") Integer serverChainId) {
        return serverChainService.selectById(serverChainId);
    }
}
