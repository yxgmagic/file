package com.zhichao.admin.controller.compreAnalysis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhichao.dal.compreAnalysis.CarAreaCountDao;
import com.zhichao.service.compreAnalysis.ICarAreaCountService;
import com.zhichao.core.base.controller.BaseController;

/**
 *
 * @author yice
 * @Date 2018-01-02 08:48:59
 */
@Controller
@RequestMapping("/carAreaCount")
public class CarAreaCountController extends BaseController {

    private String PREFIX = "/compreAnalysis/carAreaCount/";
    
    @Autowired
    CarAreaCountDao carAreaCountDao;
    
    @Autowired
    ICarAreaCountService carAreaCountService;
    
    /**
     * 
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "carAreaCount.html";
    }
    
//    @RequestMapping(value = "/getCount")
//    @ResponseBody
//    public Object getStatistics() {
//    	return carAreaCountDao.calcStation();
//    }
    
    @RequestMapping(value = "/getOverRun")
    @ResponseBody
    public Object getOverRun(@RequestParam(value = "fctime", required = false) String fctime,
    		                    @RequestParam(value = "level", required = false) String level,
                             @RequestParam(value = "stationid", required = false) String stationid) {
    	return carAreaCountService.selOverRunArea(level,fctime, stationid);
    }
    
}
