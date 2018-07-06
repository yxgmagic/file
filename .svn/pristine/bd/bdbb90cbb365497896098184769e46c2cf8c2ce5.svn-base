package com.zhichao.admin.controller.flow;

import com.zhichao.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.zhichao.service.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.zhichao.beans.flow.BasFlwWorkflow;
import com.zhichao.service.flow.IBasFlwWorkflowService;

/**
 * 流程管理控制器
 *
 * @author fengshuonan
 * @Date 2018-06-07 09:08:19
 */
@Controller
@RequestMapping("/flow")
public class FlowController extends BaseController {

    private String PREFIX = "/flow/";

    @Autowired
    private IBasFlwWorkflowService flowService;

    /**
     * 跳转到流程管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "flow.html";
    }

    /**
     * 跳转到添加流程管理
     */
    @RequestMapping("/flow_add")
    public String flowAdd() {
        return PREFIX + "flow_add.html";
    }

    /**
     * 跳转到修改流程管理
     */
    @RequestMapping("/flow_update/{flowId}")
    public String flowUpdate(@PathVariable Integer flowId, Model model) {
        BasFlwWorkflow flow = flowService.selectById(flowId);
        model.addAttribute("item",flow);
        LogObjectHolder.me().set(flow);
        return PREFIX + "flow_edit.html";
    }

    /**
     * 获取流程管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return flowService.selectList(null);
    }

    /**
     * 新增流程管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(BasFlwWorkflow flow) {
        flowService.insert(flow);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除流程管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer flowId) {
        flowService.deleteById(flowId);
        return SUCCESS_TIP;
    }

    /**
     * 修改流程管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(BasFlwWorkflow flow) {
        flowService.updateById(flow);
        return super.SUCCESS_TIP;
    }

    /**
     * 流程管理详情
     */
    @RequestMapping(value = "/detail/{flowId}")
    @ResponseBody
    public Object detail(@PathVariable("flowId") Integer flowId) {
        return flowService.selectById(flowId);
    }
}
