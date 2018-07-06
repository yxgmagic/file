package com.zhichao.admin.controller.compreAnalysis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhichao.service.compreAnalysis.ICarVolumeCountService;
import com.zhichao.core.base.controller.BaseController;

@Controller
@RequestMapping("/carvolumecount")
public class CarVolumeCountController extends BaseController {

	@Autowired
    ICarVolumeCountService carVolumeCountService;

	private String PREFIX = "/compreAnalysis/carvolumecount/";
	
	@RequestMapping("")
    public String index() {
        return PREFIX + "carvolumecount.html";
    }


    /**
     * 获取流量对比分析的数据
     * @param deptId            需要查询的部门的id
     * @param dateType          查询数据的类型(年,季度,月份,日)
     * @param dateString        查询的前一年的时间(年,年,月份,日)
     * @param thisDateString    查询的选中的时间(年,年,月份,日)
     * @return
     */
    @RequestMapping(value = "/getChartData")
    @ResponseBody
    public Object getChartData(@RequestParam(value = "deptId", required = false) String deptId,
                               @RequestParam(value = "dateType", required = false) String dateType,
                               @RequestParam(value = "dateString", required = false) String dateString,
                               @RequestParam(value = "thisDateString", required = false) String thisDateString) {
        return carVolumeCountService.getChartData(deptId, dateType, dateString, thisDateString);
    }
}
