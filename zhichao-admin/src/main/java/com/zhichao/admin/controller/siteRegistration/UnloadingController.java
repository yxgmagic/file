package com.zhichao.admin.controller.siteRegistration;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhichao.admin.warpper.siteRegistration.UnloadingWarpper;
import com.zhichao.beans.guns.Unloading;
import com.zhichao.core.base.controller.BaseController;
import com.zhichao.dal.siteRegistration.UnloadingDAO;
import com.zhichao.service.core.log.LogObjectHolder;
import com.zhichao.service.core.util.PubUtil;
import com.zhichao.service.siteRegistration.IUnloadingService;

/**
 * 卸货场管理控制器
 *
 * @author fengshuonan
 * @Date 2018-01-02 09:39:13
 */
@Controller
@RequestMapping("/unloading")
public class UnloadingController extends BaseController {

    private String PREFIX = "/siteRegistration/unloading/";

    @Autowired
    UnloadingDAO UnloadingDAO;
    
    @Autowired
    private IUnloadingService unloadingService;

    /**
     * 跳转到卸货场管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "unloading.html";
    }

    /**
     * 跳转到添加卸货场管理
     */
    @RequestMapping("/unloading_add")
    public String unloadingAdd(Model model) {

        PubUtil pub = new PubUtil();
        Map<String, Object> resMap = pub.getSequence(
                "ulcode",
                "",
                0,
                "ulcode",
                "bs_unloading"
        );
        model.addAttribute("resMap", resMap);

        return PREFIX + "unloading_add.html";
    }

    /**
     * 跳转到修改卸货场管理
     */
    @RequestMapping("/unloading_update/{unloadingId}")
    public String unloadingUpdate(@PathVariable Integer unloadingId, Model model) {
        Unloading unloading = unloadingService.selectById(unloadingId);
        model.addAttribute("item",unloading);
        LogObjectHolder.me().set(unloading);
        return PREFIX + "unloading_edit.html";
    }

    /**
     * 获取卸货场管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(value = "ulname", required = false) String ulname,
    		@RequestParam(value = "address", required = false) String address,
    		@RequestParam(value = "manager", required = false) String manager,
    		@RequestParam(value = "managertel", required = false) String managertel,
    		@RequestParam(value = "sitecode", required = false) String sitecode) {
    	List<Map<String, Object>> list = UnloadingDAO.selectUnloadingList(ulname, address, manager, managertel, sitecode);
    	return new UnloadingWarpper(list).warp();
    }

    /**
     * 新增卸货场管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Unloading unloading) {
        unloadingService.insert(unloading);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除卸货场管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer unloadingId) {
        unloadingService.deleteById(unloadingId);
        return SUCCESS_TIP;
    }

    /**
     * 修改卸货场管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Unloading unloading) {
        unloadingService.updateById(unloading);
        return super.SUCCESS_TIP;
    }

    /**
     * 卸货场管理详情
     */
    @RequestMapping(value = "/detail/{unloadingId}")
    @ResponseBody
    public Object detail(@PathVariable("unloadingId") Integer unloadingId) {
        return unloadingService.selectById(unloadingId);
    }
}
