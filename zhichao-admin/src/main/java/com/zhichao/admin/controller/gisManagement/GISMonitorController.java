package com.zhichao.admin.controller.gisManagement;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhichao.admin.warpper.gisManagement.GISMonitorWarpper;
import com.zhichao.core.base.controller.BaseController;
import com.zhichao.dal.gisManagement.GISMonitorDao;
import com.zhichao.service.gisManagement.IGISMonitorService;

/**
 * GIS地图监控
 *
 * @author yice
 * @Date 2018-01-02 08:48:59
 */
@Controller
@RequestMapping("/gismonitor")
public class GISMonitorController extends BaseController {

    private String PREFIX = "/gisManagement/gismonitor/";

    @Autowired
    GISMonitorDao gisMonitorDao;

    @Autowired
    IGISMonitorService iGISMonitorService;
    /**
     * 跳转到GIS地图监控首页
     */
    @RequestMapping("")
    public String index(@RequestParam(value = "longitude", required = false) String longitude,
                        @RequestParam(value = "latitude", required = false) String latitude,
                        @RequestParam(value = "siteType", required = false) String siteType,
                        Model model) {
        model.addAttribute("longitude",longitude);
        model.addAttribute("latitude",latitude);
        model.addAttribute("siteType",siteType);
        return PREFIX + "gismonitor.html";
    }

//    /**
//     * GIS地图选线路
//     */
//    @RequestMapping("/gisroad")
//    public String gisroad(){
//        return PREFIX + "gisroad.html";
//    }

    /**
     * v2 - 合并请求，初始化
     */
    @RequestMapping("/init")
    @ResponseBody
    public Object init() {
        Object list = gisMonitorDao.selImgFixed("HN0204");
        return list;
    }

    /**
     * 获取路线
     */
    @RequestMapping("/getLine")
    public String getLine (@RequestParam(value = "data", required = false) String data,
                           Model model) {
//        data = java.net.URLDecoder.decode(data, "UTF-8");
        model.addAttribute("data", data);
        return PREFIX + "gisroad.html";
    }

    /**
     * v2 - 获取坐标点 - 共用组件
     */
    @RequestMapping("/coordinate")
    public String getCoor(@RequestParam(value = "lng", required = false) String lng,
                          @RequestParam(value = "lat", required = false) String lat,
                          @RequestParam(value = "src", required = false) String src,
                          @RequestParam(value = "lngId", required = false) String lngId,
                          @RequestParam(value = "latId", required = false) String latId,
                          @RequestParam(value = "inputId", required = false) String inputId,
                          @RequestParam(value = "lnglat", required = false) String lnglat,
                          Model model) throws UnsupportedEncodingException {
        lng = java.net.URLDecoder.decode(lng, "UTF-8");
        lat = java.net.URLDecoder.decode(lat, "UTF-8");
        src = java.net.URLDecoder.decode(src, "UTF-8");
        if (lngId != null || latId != null) {
            lngId = java.net.URLDecoder.decode(lngId, "UTF-8");
            latId = java.net.URLDecoder.decode(latId, "UTF-8");
            model.addAttribute("lngId", lngId);
            model.addAttribute("latId", latId);

        } else {
            model.addAttribute("lngId", "");
            model.addAttribute("latId", "");
        }
        if (inputId != null) {
            inputId = java.net.URLDecoder.decode(inputId, "UTF-8");
            model.addAttribute("inputId", inputId);
        } else {
            model.addAttribute("inputId", "");
        }
        if (lnglat != null) {
            lnglat = java.net.URLDecoder.decode(lnglat, "UTF-8");
//            System.out.println(lnglat);
            model.addAttribute("lnglat", lnglat);
        } else {
            model.addAttribute("lnglat", "");
        }
        model.addAttribute("lng", lng);
        model.addAttribute("lat", lat);
        model.addAttribute("src", src);
        return PREFIX + "coordinate.html";
    }

    /**
     * v2 - 获取站点详细信息
     * @return
     */
    @RequestMapping("/selSiteInfo")
    @ResponseBody
    public Object selSiteInfo(@RequestParam(value = "longitude", required = false) String longitude,
                              @RequestParam(value = "latitude", required = false) String latitude,
                              @RequestParam(value = "sitetype", required = false) String sitetype,
                              @RequestParam(value = "time", required = false) String time) {

        Object list = iGISMonitorService.siteInfo(longitude, latitude, sitetype, time);
        return list;
    }

    // v2 - 查询固定治超站坐标
    @RequestMapping("/selfixedsite")
    @ResponseBody
    public Object selfixedsite(){
        List<Map<String, Object>> list = gisMonitorDao.selFixedSite();
        return list;
    }

    // v2 - 查询预检站坐标
    @RequestMapping("/selpresite")
    @ResponseBody
    public Object selPreSite(){
        List<Map<String, Object>> list = gisMonitorDao.selPreSite();
        return list;
    }

    // v2 - 查询源头企业检测站
    @RequestMapping("/selCrop")
    @ResponseBody
    public Object selCrop(){
        List<Map<String, Object>> list = gisMonitorDao.selCrop();
        return list;
    }

    // v2 - 查询高速公路出入口
    @RequestMapping("/selHsway")
    @ResponseBody
    public Object selHsway(){
        List<Map<String, Object>> list = gisMonitorDao.selHsway();
        return list;
    }

    // v2 - 查询移动单兵
    @RequestMapping("/sellawMan")
    @ResponseBody
    public Object sellawMan(){
        List<Map<String, Object>> list = gisMonitorDao.sellawMan();
        return list;
    }
    // v2 - 查询移动执法车
    @RequestMapping("/sellawCar")
    @ResponseBody
    public Object sellawCar(){
        List<Map<String, Object>> list = gisMonitorDao.sellawCar();
        return list;
    }

    // v2 - 全局查询超限信息
    @RequestMapping("/test")
    @ResponseBody
    public Object test(){

        List<Map<String, Object>> list = new ArrayList<>();

//        while(true){
//
//        }
        return null;


    }

    //v2 - 搜索
    @RequestMapping("/seach")
    @ResponseBody
    public List<Map<String, Object>> seach(@RequestParam(value = "sitename", required = false) String sitename){
        List<Map<String, Object>> list = iGISMonitorService.selGISSeach(sitename);
        return list;
    }








    /**
     * 获取固定检测站信息
     */
    @RequestMapping("/list")
    @ResponseBody
    public Object list(@RequestParam(value = "id", required = false) String id,
                       @RequestParam(value = "longitude", required = false) String longitude,
                       @RequestParam(value = "latitude", required = false) String latitude,
                       @RequestParam(value = "areacode", required = false) String areacode) {
        List<Map<String, Object>> list = iGISMonitorService.selectGISList(id, longitude, latitude, areacode);
        return new GISMonitorWarpper(list).warp();
    }

    /**
     * 获取不停车检测站信息
     */
    @RequestMapping("/lists")
    @ResponseBody
    public Object lists(@RequestParam(value = "id", required = false) String id,
                        @RequestParam(value = "longitude", required = false) String longitude,
                        @RequestParam(value = "latitude", required = false) String latitude,
                        @RequestParam(value = "areacode", required = false) String areacode) {
        List<Map<String, Object>> list = iGISMonitorService.selectPresiteList(id, longitude, latitude, areacode);
        return new GISMonitorWarpper(list).warp();
    }

    /**
     * 查找固定治超站
     */
    @RequestMapping("/selectSiteFix")
    @ResponseBody
    public Object selectSiteFix(@RequestParam(value = "sitename", required = true) String sitename) {
        List<Map<String, Object>> list = gisMonitorDao.selectSiteFix(sitename);
        return list;
    }

    /**
     * 查找不停车预检站
     */
    @RequestMapping("/selectSitePre")
    @ResponseBody
    public Object selectSitePre(@RequestParam(value = "sitename", required = true) String sitename) {
        List<Map<String, Object>> list = gisMonitorDao.selectSitePre(sitename);
        return list;
    }

    /**
     * 获取超限信息
     */
//    @RequestMapping("/selectOverRun")
//    @ResponseBody
//    public Object selectOverRun() {
//    	List<Map<String, Object>> list = iGISMonitorService.selectOverRun();
//    	return list;
//    }
    @RequestMapping("/selOverRun")
    @ResponseBody
    public Object selOverRun (@RequestParam(value = "checkno", required = false) String checkno){
        Object list = iGISMonitorService.selOverRun(checkno);
        return list;
    }
}
