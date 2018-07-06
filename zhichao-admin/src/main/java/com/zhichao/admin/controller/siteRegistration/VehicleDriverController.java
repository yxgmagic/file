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

import com.zhichao.admin.warpper.siteRegistration.VehicleDriverWarpper;
import com.zhichao.beans.guns.VehicleDriver;
import com.zhichao.core.base.controller.BaseController;
import com.zhichao.dal.siteRegistration.VehicleDriverDAO;
import com.zhichao.service.core.log.LogObjectHolder;
import com.zhichao.service.siteRegistration.IVehicleDriverService;

/**
 * 货运从业人员管理控制器
 *
 * @author zhichao
 * @Date 2018-01-02 14:28:38
 */
@Controller
@RequestMapping("/vehicleDriver")
public class VehicleDriverController extends BaseController {

    private String PREFIX = "/siteRegistration/vehicleDriver/";

    @Autowired
    VehicleDriverDAO VehicleDriverDAO;
    
    @Autowired
    private IVehicleDriverService vehicleDriverService;

    /**
     * 跳转到货运从业人员管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "vehicleDriver.html";
    }

    /**
     * 跳转到添加货运从业人员管理
     */
    @RequestMapping("/vehicleDriver_add")
    public String vehicleDriverAdd() {
        return PREFIX + "vehicleDriver_add.html";
    }

    /**
     * 跳转到修改货运从业人员管理
     */
    @RequestMapping("/vehicleDriver_update/{vehicleDriverId}")
    public String vehicleDriverUpdate(@PathVariable Integer vehicleDriverId, Model model) {
        VehicleDriver vehicleDriver = vehicleDriverService.selectById(vehicleDriverId);
        model.addAttribute("item",vehicleDriver);
        LogObjectHolder.me().set(vehicleDriver);
        return PREFIX + "vehicleDriver_edit.html";
    }

    /**
     * 获取货运从业人员管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(value = "drivername", required = false) String drivername,
    		@RequestParam(value = "corpcode", required = false) String corpcode,
    		@RequestParam(value = "idcard", required = false) String idcard,
    		@RequestParam(value = "sex", required = false) String sex,
    		@RequestParam(value = "driverid", required = false) String driverid,
    		@RequestParam(value = "qualificationid", required = false) String qualificationid) {
    	List<Map<String, Object>> list = VehicleDriverDAO.selectVerhicleDriverList(drivername, corpcode, idcard, sex, driverid, qualificationid);
    	return new VehicleDriverWarpper(list).warp();
    }

    /**
     * 新增货运从业人员管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(VehicleDriver vehicleDriver) {
        vehicleDriverService.insert(vehicleDriver);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除货运从业人员管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer vehicleDriverId) {
        vehicleDriverService.deleteById(vehicleDriverId);
        return SUCCESS_TIP;
    }

    /**
     * 修改货运从业人员管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(VehicleDriver vehicleDriver) {
        vehicleDriverService.updateById(vehicleDriver);
        return super.SUCCESS_TIP;
    }

    /**
     * 货运从业人员管理详情
     */
    @RequestMapping(value = "/detail/{vehicleDriverId}")
    @ResponseBody
    public Object detail(@PathVariable("vehicleDriverId") Integer vehicleDriverId) {
        return vehicleDriverService.selectById(vehicleDriverId);
    }
    
    
    /**
     * 身份证唯一性校验
     */
    @RequestMapping(value = "/dataIsExist")
    @ResponseBody
    public Object dataIsExist(@RequestParam(value = "id", required = false) Integer id,
    		@RequestParam(value = "idcard", required = false) String idcard,
    		@RequestParam(value = "driverid", required = false) String driverid,
    		@RequestParam(value = "qualificationid", required = false) String qualificationid) {
    	Integer num = vehicleDriverService.dataIsExist(id, idcard, driverid, qualificationid);
    	
    	return num;
    }

    /**
     * 跳转选择源头企业
     */
    @RequestMapping(value = "/company")
    public String company() {
        return PREFIX + "company.html";
    }
}
