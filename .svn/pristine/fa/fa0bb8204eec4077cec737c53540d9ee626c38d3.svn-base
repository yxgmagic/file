package com.zhichao.admin.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhichao.service.core.log.LogObjectHolder;
import com.zhichao.service.system.ISeqGenerateService;
import com.zhichao.beans.guns.SeqGenerate;
import com.zhichao.core.base.controller.BaseController;

/**
 * 控制器
 *
 * @author fengshuonan
 * @Date 2018-01-08 20:31:32
 */
@Controller
@RequestMapping("/seqGenerate")
public class SeqGenerateController extends BaseController {

    private String PREFIX = "/system/seqGenerate/";

    @Autowired
    private ISeqGenerateService seqGenerateService;

    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "seqGenerate.html";
    }

    /**
     * 跳转到添加
     */
    @RequestMapping("/seqGenerate_add")
    public String seqGenerateAdd() {
        return PREFIX + "seqGenerate_add.html";
    }

    /**
     * 跳转到修改
     */
    @RequestMapping("/seqGenerate_update/{seqGenerateId}")
    public String seqGenerateUpdate(@PathVariable Integer seqGenerateId, Model model) {
        SeqGenerate seqGenerate = seqGenerateService.selectById(seqGenerateId);
        model.addAttribute("item",seqGenerate);
        LogObjectHolder.me().set(seqGenerate);
        return PREFIX + "seqGenerate_edit.html";
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return seqGenerateService.selectList(null);
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(SeqGenerate seqGenerate) {
        seqGenerateService.insert(seqGenerate);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer seqGenerateId) {
        seqGenerateService.deleteById(seqGenerateId);
        return SUCCESS_TIP;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(SeqGenerate seqGenerate) {
        seqGenerateService.updateById(seqGenerate);
        return super.SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/detail/{seqGenerateId}")
    @ResponseBody
    public Object detail(@PathVariable("seqGenerateId") Integer seqGenerateId) {
        return seqGenerateService.selectById(seqGenerateId);
    }

    
}
