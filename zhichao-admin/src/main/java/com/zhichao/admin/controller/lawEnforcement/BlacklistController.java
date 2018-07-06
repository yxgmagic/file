package com.zhichao.admin.controller.lawEnforcement;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zhichao.admin.warpper.lawEnforcement.BlacklistWarpper;
import com.zhichao.beans.guns.Blacklist;
import com.zhichao.core.base.controller.BaseController;
import com.zhichao.service.core.log.LogObjectHolder;
import com.zhichao.service.lawEnforcement.IBlacklistService;

/**
 * 黑白名单控制器
 *
 * @author fengshuonan
 * @Date 2018-01-23 09:04:32
 */
@Controller
@RequestMapping("/blacklist")
public class BlacklistController extends BaseController {

    private String PREFIX = "/lawEnforcement/blacklist/";

    @Autowired
    private IBlacklistService blacklistService;

    /**
     * 跳转到黑白名单首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "blacklist.html";
    }

    /**
     * 跳转到添加黑白名单
     */
    @RequestMapping("/blacklist_add")
    public String blacklistAdd() {
        return PREFIX + "blacklist_add.html";
    }

    /**
     * 跳转到修改黑白名单
     */
    @RequestMapping("/blacklist_update/{blacklistId}")
    public String blacklistUpdate(@PathVariable Integer blacklistId, Model model) {
        Blacklist blacklist = blacklistService.selectById(blacklistId);
        model.addAttribute("item",blacklist);
        LogObjectHolder.me().set(blacklist);
        return PREFIX + "blacklist_detail.html";
    }

    /**
     * 获取黑白名单列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(value = "vehicleid", required = false) String vehicleid, 
    		@RequestParam(value = "drivername", required = false) String drivername, 
    		@RequestParam(value = "corpname", required = false) String corpname) {
    	List<Map<String, Object>> list = blacklistService.selList(vehicleid, drivername, corpname);
    	return new BlacklistWarpper(list).warp();
    }

    /**
     * 新增黑白名单
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Blacklist blacklist,
                      @RequestParam(value = "image0", required = false) MultipartFile image0,
                      @RequestParam(value = "image1", required = false) MultipartFile image1,
                        @RequestParam(value = "image2", required = false) MultipartFile image2) throws IOException {
        return blacklistService.insert(blacklist,image0,image1,image2);
//        return super.SUCCESS_TIP;
    }

    /**
     * 删除黑白名单
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer blacklistId) {
        blacklistService.deleteById(blacklistId);
        return SUCCESS_TIP;
    }

    /**
     * 修改黑白名单
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Blacklist blacklist) {
        blacklistService.updateById(blacklist);
        return super.SUCCESS_TIP;
    }

    /**
     * 黑白名单详情
     */
    @RequestMapping(value = "/detail/{blacklistId}")
    @ResponseBody
    public Object detail(@PathVariable("blacklistId") Integer blacklistId) {
        return blacklistService.selectById(blacklistId);
    }
    
    /**
     * 获取所有的车头图片
     * @param vehicleid
     * @return
     */
    @RequestMapping(value = "/getVehicleImages")
    @ResponseBody
    public Object getVehicleImages (@RequestParam(value = "vehicleid") String vehicleid) {
    	return blacklistService.getVehicleImages(vehicleid);
    }
    
    /**
     * 更新黑名单状态
     * @param id 需要更改的黑名单记录的id
     * @param statusValue  需要改变的值
     * @return
     */
    @RequestMapping(value = "/update_black_status")
    @ResponseBody
    public Object updateBlackStatus(@RequestParam(value = "id") String id,
    		@RequestParam(value = "statusValue") String statusValue) {
    	blacklistService.updateBlackStatus(id, statusValue);
    	return super.SUCCESS_TIP;
    }
    
    @RequestMapping("/popup_select")
    public String popupSelect() {
        return PREFIX + "popup_select.html";
    }
    
    /**
     * 生成疑似黑名单 , 
     * 在案件办理和站点中取相对应的记录存入黑名单
     * @return
     */
    @RequestMapping(value = "/generate_blacklist")
    @ResponseBody
    public Object generateBlacklist(@RequestParam(value = "time", required = false) String time) {
    	return blacklistService.generateBlacklist(time);
    }
    
}
