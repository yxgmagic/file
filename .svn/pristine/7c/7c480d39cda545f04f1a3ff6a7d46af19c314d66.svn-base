package com.zhichao.admin.controller.lawEnforcement;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhichao.admin.warpper.lawEnforcement.OverrunGbsLicWarpper;
import com.zhichao.beans.guns.OverrunGbsLic;
import com.zhichao.core.base.controller.BaseController;
import com.zhichao.dal.lawEnforcement.OverrunGbsLicDAO;
import com.zhichao.service.core.log.LogObjectHolder;
import com.zhichao.service.lawEnforcement.IOverrunGbsLicService;

/**
 * 超限货物许可证控制器
 *
 * @author fengshuonan
 * @Date 2018-01-22 09:47:14
 */
@Controller
@RequestMapping("/overrunGbsLic")
public class OverrunGbsLicController extends BaseController {

    private String PREFIX = "/lawEnforcement/overrunGbsLic/";
    
    @Autowired
    OverrunGbsLicDAO overrunGbsLicDAO;

    @Autowired
    private IOverrunGbsLicService overrunGbsLicService;

    /**
     * 跳转到超限货物许可证首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "overrunGbsLic.html";
    }

    /**
     * 跳转到添加超限货物许可证
     */
    @RequestMapping("/overrunGbsLic_add")
    public String overrunGbsLicAdd() {
        return PREFIX + "overrunGbsLic_add.html";
    }

    /**
     * 跳转到修改超限货物许可证
     */
    @RequestMapping("/overrunGbsLic_update/{overrunGbsLicId}")
    public String overrunGbsLicUpdate(@PathVariable Integer overrunGbsLicId, Model model) {
        OverrunGbsLic overrunGbsLic = overrunGbsLicService.selectById(overrunGbsLicId);
        model.addAttribute("item",overrunGbsLic);
        LogObjectHolder.me().set(overrunGbsLic);
        return PREFIX + "overrunGbsLic_edit.html";
    }

    /**
     * 获取超限货物许可证列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(value = "licid", required = false) String licid,
    		@RequestParam(value = "carid", required = false) String carid,
    		@RequestParam(value = "sourcename", required = false) String sourcename,
    		@RequestParam(value = "drivername", required = false) String drivername,
    		@RequestParam(value = "driverphone", required = false) String driverphone,
    		@RequestParam(value = "wayid", required = false) String wayid) {
    	List<Map<String, Object>> list = overrunGbsLicDAO.selectOverrunGbsLicList(licid, carid, sourcename, drivername, driverphone, wayid);
        return new OverrunGbsLicWarpper(list).warp();
    }

    /**
     * 新增超限货物许可证
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(OverrunGbsLic overrunGbsLic) {
        overrunGbsLicService.insert(overrunGbsLic);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除超限货物许可证
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer overrunGbsLicId) {
        overrunGbsLicService.deleteById(overrunGbsLicId);
        return SUCCESS_TIP;
    }

    /**
     * 修改超限货物许可证
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(OverrunGbsLic overrunGbsLic) {
        overrunGbsLicService.updateById(overrunGbsLic);
        return super.SUCCESS_TIP;
    }

    /**
     * 超限货物许可证详情
     */
    @RequestMapping(value = "/detail/{overrunGbsLicId}")
    @ResponseBody
    public Object detail(@PathVariable("overrunGbsLicId") Integer overrunGbsLicId) {
        return overrunGbsLicService.selectById(overrunGbsLicId);
    }
    
    /**
     * 企业车辆管理时选择企业时打开选择企业弹框
     */
    @RequestMapping(value = "/overrunGbsLicCorp")
    public String vehiclePageSelCorp() {
    	return "/lawEnforcement/overrunGbsLic/" + "bsCorp.html";
    }
}
