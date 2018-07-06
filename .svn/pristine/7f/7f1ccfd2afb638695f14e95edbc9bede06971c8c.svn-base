package com.zhichao.admin.controller.dms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhichao.service.core.log.LogObjectHolder;
import com.zhichao.service.dms.IServerAuthChainService;
import com.zhichao.beans.guns.ServerAuthChain;
import com.zhichao.core.base.controller.BaseController;

/**
 * 数据服务终端与链关系配置控制器
 *
 * @author fengshuonan
 * @Date 2018-02-27 10:13:57
 */
@Controller
@RequestMapping("/serverAuthChain")
public class ServerAuthChainController extends BaseController {

    private String PREFIX = "/dms/serverAuthChain/";

    @Autowired
    private IServerAuthChainService serverAuthChainService;

    /**
     * 跳转到数据服务终端与链关系配置首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "serverAuthChain.html";
    }

    /**
     * 跳转到添加数据服务终端与链关系配置
     */
    @RequestMapping("/serverAuthChain_add")
    public String serverAuthChainAdd() {
        return PREFIX + "serverAuthChain_add.html";
    }

    /**
     * 跳转到修改数据服务终端与链关系配置
     */
    @RequestMapping("/serverAuthChain_update/{serverAuthChainId}")
    public String serverAuthChainUpdate(@PathVariable Integer serverAuthChainId, Model model) {
        ServerAuthChain serverAuthChain = serverAuthChainService.selectById(serverAuthChainId);
        model.addAttribute("item",serverAuthChain);
        LogObjectHolder.me().set(serverAuthChain);
        return PREFIX + "serverAuthChain_edit.html";
    }

    /**
     * 获取数据服务终端与链关系配置列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return serverAuthChainService.selectList(null);
    }

    /**
     * 新增数据服务终端与链关系配置
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(ServerAuthChain serverAuthChain) {
        serverAuthChainService.insert(serverAuthChain);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除数据服务终端与链关系配置
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer serverAuthChainId) {
        serverAuthChainService.deleteById(serverAuthChainId);
        return SUCCESS_TIP;
    }

    /**
     * 修改数据服务终端与链关系配置
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(ServerAuthChain serverAuthChain) {
        serverAuthChainService.updateById(serverAuthChain);
        return super.SUCCESS_TIP;
    }

    /**
     * 数据服务终端与链关系配置详情
     */
    @RequestMapping(value = "/detail/{serverAuthChainId}")
    @ResponseBody
    public Object detail(@PathVariable("serverAuthChainId") Integer serverAuthChainId) {
        return serverAuthChainService.selectById(serverAuthChainId);
    }
}
