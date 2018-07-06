package com.zhichao.admin.controller.dms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhichao.service.core.log.LogObjectHolder;
import com.zhichao.service.dms.IServerLogService;
import com.zhichao.beans.guns.ServerLog;
import com.zhichao.core.base.controller.BaseController;

/**
 * 数据服务器日志上传管理控制器
 *
 * @author fengshuonan
 * @Date 2018-02-26 15:46:29
 */
@Controller
@RequestMapping("/serverLog")
public class ServerLogController extends BaseController {

    private String PREFIX = "/dms/serverLog/";

    @Autowired
    private IServerLogService serverLogService;

    /**
     * 跳转到数据服务器日志上传管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "serverLog.html";
    }

    /**
     * 跳转到添加数据服务器日志上传管理
     */
    @RequestMapping("/serverLog_add")
    public String serverLogAdd() {
        return PREFIX + "serverLog_add.html";
    }

    /**
     * 跳转到修改数据服务器日志上传管理
     */
    @RequestMapping("/serverLog_update/{serverLogId}")
    public String serverLogUpdate(@PathVariable Integer serverLogId, Model model) {
        ServerLog serverLog = serverLogService.selectById(serverLogId);
        model.addAttribute("item",serverLog);
        LogObjectHolder.me().set(serverLog);
        return PREFIX + "serverLog_edit.html";
    }

    /**
     * 获取数据服务器日志上传管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return serverLogService.selectList(null);
    }

    /**
     * 新增数据服务器日志上传管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(ServerLog serverLog) {
        serverLogService.insert(serverLog);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除数据服务器日志上传管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer serverLogId) {
        serverLogService.deleteById(serverLogId);
        return SUCCESS_TIP;
    }

    /**
     * 修改数据服务器日志上传管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(ServerLog serverLog) {
        serverLogService.updateById(serverLog);
        return super.SUCCESS_TIP;
    }

    /**
     * 数据服务器日志上传管理详情
     */
    @RequestMapping(value = "/detail/{serverLogId}")
    @ResponseBody
    public Object detail(@PathVariable("serverLogId") Integer serverLogId) {
        return serverLogService.selectById(serverLogId);
    }
}
