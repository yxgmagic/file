package com.zhichao.admin.controller.dms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhichao.service.core.log.LogObjectHolder;
import com.zhichao.service.dms.IServerParaService;
import com.zhichao.beans.guns.ServerPara;
import com.zhichao.core.base.controller.BaseController;

/**
 * 数据服务器参数管理控制器
 *
 * @author fengshuonan
 * @Date 2018-02-26 15:46:04
 */
@Controller
@RequestMapping("/serverPara")
public class ServerParaController extends BaseController {

    private String PREFIX = "/dms/serverPara/";

    @Autowired
    private IServerParaService serverParaService;

    /**
     * 跳转到数据服务器参数管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "serverPara.html";
    }

    /**
     * 跳转到添加数据服务器参数管理
     */
    @RequestMapping("/serverPara_add")
    public String serverParaAdd() {
        return PREFIX + "serverPara_add.html";
    }

    /**
     * 跳转到修改数据服务器参数管理
     */
    @RequestMapping("/serverPara_update/{serverParaId}")
    public String serverParaUpdate(@PathVariable Integer serverParaId, Model model) {
        ServerPara serverPara = serverParaService.selectById(serverParaId);
        model.addAttribute("item",serverPara);
        LogObjectHolder.me().set(serverPara);
        return PREFIX + "serverPara_edit.html";
    }

    /**
     * 获取数据服务器参数管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return serverParaService.selectList(null);
    }

    /**
     * 新增数据服务器参数管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(ServerPara serverPara) {
        serverParaService.insert(serverPara);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除数据服务器参数管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer serverParaId) {
        serverParaService.deleteById(serverParaId);
        return SUCCESS_TIP;
    }

    /**
     * 修改数据服务器参数管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(ServerPara serverPara) {
        serverParaService.updateById(serverPara);
        return super.SUCCESS_TIP;
    }

    /**
     * 数据服务器参数管理详情
     */
    @RequestMapping(value = "/detail/{serverParaId}")
    @ResponseBody
    public Object detail(@PathVariable("serverParaId") Integer serverParaId) {
        return serverParaService.selectById(serverParaId);
    }
}
